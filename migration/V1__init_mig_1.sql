CREATE TABLE categories
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    create_at        datetime NULL,
    last_modified_at datetime NULL,
    title            VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE instructor
(
    id      BIGINT NOT NULL,
    name    VARCHAR(255) NULL,
    email   VARCHAR(255) NULL,
    subject VARCHAR(255) NULL,
    rating DOUBLE NULL,
    CONSTRAINT pk_instructor PRIMARY KEY (id)
);

CREATE TABLE jt_instructor
(
    user_id BIGINT NOT NULL,
    subject VARCHAR(255) NULL,
    rating DOUBLE NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentor
(
    user_id     BIGINT NOT NULL,
    companyname VARCHAR(255) NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (user_id)
);

CREATE TABLE jt_ta
(
    user_id     BIGINT NOT NULL,
    number_ofhr INT    NOT NULL,
    CONSTRAINT pk_jt_ta PRIMARY KEY (user_id)
);

CREATE TABLE jt_user
(
    id    BIGINT NOT NULL,
    name  VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (id)
);

CREATE TABLE mentor
(
    id          BIGINT NOT NULL,
    name        VARCHAR(255) NULL,
    email       VARCHAR(255) NULL,
    companyname VARCHAR(255) NULL,
    CONSTRAINT pk_mentor PRIMARY KEY (id)
);

CREATE TABLE product
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    create_at        datetime NULL,
    last_modified_at datetime NULL,
    title            VARCHAR(255) NULL,
    price DOUBLE NULL,
    `description`    VARCHAR(255) NULL,
    image_url        VARCHAR(255) NULL,
    category_id      BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE st_user
(
    id          BIGINT NOT NULL,
    user_type   INT NULL,
    name        VARCHAR(255) NULL,
    email       VARCHAR(255) NULL,
    number_ofhr INT    NOT NULL,
    subject     VARCHAR(255) NULL,
    rating DOUBLE NULL,
    companyname VARCHAR(255) NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

CREATE TABLE ta
(
    id          BIGINT NOT NULL,
    name        VARCHAR(255) NULL,
    email       VARCHAR(255) NULL,
    number_ofhr INT    NOT NULL,
    CONSTRAINT pk_ta PRIMARY KEY (id)
);

CREATE TABLE tpc_instructor
(
    id      BIGINT NOT NULL,
    name    VARCHAR(255) NULL,
    email   VARCHAR(255) NULL,
    subject VARCHAR(255) NULL,
    rating DOUBLE NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id          BIGINT NOT NULL,
    name        VARCHAR(255) NULL,
    email       VARCHAR(255) NULL,
    companyname VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_ta
(
    id          BIGINT NOT NULL,
    name        VARCHAR(255) NULL,
    email       VARCHAR(255) NULL,
    number_ofhr INT    NOT NULL,
    CONSTRAINT pk_tpc_ta PRIMARY KEY (id)
);

CREATE TABLE tpc_user
(
    id    BIGINT NOT NULL,
    name  VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_user PRIMARY KEY (id)
);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE jt_ta
    ADD CONSTRAINT FK_JT_TA_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);