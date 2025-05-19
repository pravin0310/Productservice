package org.com.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.com.productservice.models.Category;

@Getter
@Setter

public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
