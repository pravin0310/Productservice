package org.com.productservice.inheritanceDemo.tableperclass;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name ="tpc_instructor")
public class Instructor extends User {
    private String subject;
    private Double rating;
}
