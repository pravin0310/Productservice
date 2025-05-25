package org.com.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ExceptionDto {
    private String message;
    private String resolutionDetails;
}
