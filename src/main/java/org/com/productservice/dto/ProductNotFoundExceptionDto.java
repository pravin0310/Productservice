package org.com.productservice.dto;
import org.com.productservice.exception.ProductNotFoundException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductNotFoundExceptionDto extends Exception {
    private Long productId;
    private String message;
    private String resolutionDetails;
}
