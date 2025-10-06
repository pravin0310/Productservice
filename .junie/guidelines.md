Project: Productservice (Spring Boot 3, Java 21, Maven)

This document captures project-specific knowledge to streamline future development, builds, and testing.

1) Build and Configuration
- Java/Maven baseline
  - Java: 21 (see pom.xml <java.version>21</java.version>)
  - Build: Maven Wrapper (mvnw/mvnw.cmd). Prefer using the wrapper to keep plugin versions consistent.
  - Spring Boot parent: 3.4.5 (spring-boot-starter-parent in pom.xml)

- Dependency management notes
  - Starters are normally version-managed by the Spring Boot parent. In this POM, spring-boot-starter-data-jpa explicitly sets version 3.5.0 while the parent is 3.4.5. Mixing versions across the BOM may work but can cause subtle conflicts. Recommended: remove the explicit version on starters and rely on the parent-managed versions unless you have a deliberate reason to override.
  - MySQL driver: com.mysql:mysql-connector-j:9.3.0
  - Flyway: 11.10.4 (core + flyway-mysql)

- Application configuration (src/main/resources/application.properties)
  - Defaults point to a local MySQL instance:
    - spring.datasource.url=jdbc:mysql://localhost:3306/productservice
    - spring.datasource.username=root
    - spring.datasource.password=
    - spring.jpa.hibernate.ddl-auto=update
    - spring.jpa.show-sql=true  (consider using = rather than :)
  - Flyway migration scripts are in migration/V1__init_mig_1.sql. Ensure Flyway can connect to the DB at app startup (it runs before Hibernate).

- Running the app locally
  - Ensure MySQL is running and a database named productservice exists (or permissions allow creation if using ddl-auto=update).
  - Run: ./mvnw spring-boot:run (Linux/Mac) or .\\mvnw.cmd spring-boot:run (Windows)
  - If you don’t want to hit a real DB during development, create a separate profile (see Testing section for H2 profile idea) or supply a docker-compose for MySQL.

2) Testing
- Why default context tests can fail here
  - The default application.properties is wired to a real MySQL. Spring context-loading tests (e.g., @SpringBootTest) will try to connect and fail if MySQL isn’t available.

- Ways to test safely without a DB
  1) Pure unit tests (no Spring context): Use plain JUnit 5 assertions, no @SpringBootTest. These run fast and avoid the datasource entirely.
  2) Use a test profile with an in-memory database (recommended for integration tests):
     - Create src/test/resources/application-test.properties with an H2 datasource, and activate it with -Dspring.profiles.active=test or @ActiveProfiles("test"). Example properties:
       spring.datasource.url=jdbc:h2:mem:productservice;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
       spring.datasource.username=sa
       spring.datasource.password=
       spring.datasource.driver-class-name=org.h2.Driver
       spring.jpa.hibernate.ddl-auto=update
       spring.jpa.show-sql=true
       # Optionally disable Flyway for unit-style integration tests
       spring.flyway.enabled=false
     - Add H2 test dependency if you run integration tests with H2:
       <dependency>
         <groupId>com.h2database</groupId>
         <artifactId>h2</artifactId>
         <scope>test</scope>
       </dependency>

- Running tests with Maven
  - All tests: ./mvnw test (Linux/Mac) or .\\mvnw.cmd test (Windows)
  - Single class: ./mvnw -Dtest=org.com.productservice.controllers.ProductControllerTest test
  - Single method: ./mvnw -Dtest=ProductControllerTest#getAllProducts test
  - With test profile: ./mvnw -Dspring.profiles.active=test test

- How to add and execute new tests
  - Create a class under src/test/java mirroring package structure.
  - Prefer pure unit tests where possible (no Spring context) for speed and isolation.
  - For slice tests, consider @WebMvcTest (for controllers), @DataJpaTest (for repositories) with embedded DB and explicit configuration to avoid touching real MySQL.

- Verified simple test example (already in repo)
  - The class src/test/java/org/com/productservice/controllers/ProductControllerTest.java contains four pure JUnit tests that do not start Spring. They pass without a DB.
  - To run just this example test: .\\mvnw.cmd -Dtest=org.com.productservice.controllers.ProductControllerTest test
    Expectation: 4 passed, 0 failed.

3) Additional Development Notes
- Architecture overview (selected pieces)
  - Controllers: org.com.productservice.controllers.ProductController exposes product-related endpoints.
  - Services: org.com.productservice.services.* includes ProductService (interface), SelfProductService (likely uses JPA), FakeStoreProductService (external DTO-based integration).
  - Data: JPA entities in org.com.productservice.models (Product, Category, BaseModel); repositories in org.com.productservice.repository.
  - Error handling: org.com.productservice.controllerAdvice.ProductExceptionHandler and DTOs for exception payloads.
  - Inheritance demo packages exist under org.com.productservice.inheritanceDemo for different JPA inheritance strategies; these are educational and not directly tied to product endpoints.

- Flyway + Hibernate DDL
  - When Flyway is enabled, prefer managing schema changes via migration scripts instead of relying on hibernate.ddl-auto=update in production. For local dev it’s fine to keep update, but ensure migrations stay the single source of truth.

- Logging/observability tips
  - SQL: spring.jpa.show-sql=true (use =). For more detail add logging.level.org.hibernate.SQL=DEBUG and logging.level.org.hibernate.orm.jdbc.bind=TRACE.
  - Controller/request logging: leverage Spring Boot logging configuration in application.properties or a logback-spring.xml if you add one.

- Code style and Lombok
  - Lombok is configured as optional; ensure annotation processing is enabled in your IDE if you add Lombok annotations.
  - Follow standard Spring naming conventions; keep DTOs free of JPA annotations, and keep entities in models package.

- Version alignment caution
  - Keep Spring Boot starter versions aligned with the parent BOM. If you intentionally override (e.g., data-jpa 3.5.x), verify compatibility across spring-boot-starter, spring-boot-starter-web, and testing libs.

- Controller testing strategy
  - For controller logic, prefer @WebMvcTest with MockMvc and mocked service beans to avoid DB and full context. Example outline:
    @WebMvcTest(ProductController.class)
    class ProductControllerWebTest { /* mock ProductService and assert MVC responses */ }
  - If SelfProductService uses repositories, consider @DataJpaTest for repository-only slices with H2.

- Common local pitfalls
  - ContextLoad failures during tests typically mean the real MySQL isn’t running. Either start MySQL, switch to an H2 test profile, or avoid context in unit tests.
  - application.properties currently uses a colon for spring.jpa.show-sql; prefer using equals.

4) Quickstart checklist
- Build: .\\mvnw.cmd -q -DskipTests package
- Run app (requires MySQL): .\\mvnw.cmd spring-boot:run
- Run fast sanity tests: .\\mvnw.cmd -Dtest=org.com.productservice.controllers.ProductControllerTest test
- For integration tests without MySQL: add H2 test dependency and a test profile as shown above, then run: .\\mvnw.cmd -Dspring.profiles.active=test test
