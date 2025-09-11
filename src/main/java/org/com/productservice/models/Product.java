package org.com.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Product extends BaseModel{
    private String title;
    private Double price;
    private String description;
    private String imageUrl;
    @ManyToOne(cascade = {jakarta.persistence.CascadeType.PERSIST})
    @JoinColumn
    private Category category;
}
