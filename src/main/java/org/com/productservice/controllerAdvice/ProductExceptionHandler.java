package org.com.productservice.controllerAdvice;

import org.com.productservice.dto.ExceptionDto;
import org.com.productservice.dto.ProductNotFoundExceptionDto;
import org.com.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ExceptionDto> RunTimeException(){
//
//        ExceptionDto exceptionDto=new ExceptionDto();
//        exceptionDto.setMessage("Something Went Wrong");
//        exceptionDto.setResolutionDetails("You need to Learn more process");
//
//        return new ResponseEntity<>(exceptionDto,HttpStatus.UNAUTHORIZED);
//    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
        ProductNotFoundExceptionDto exceptionDto=new ProductNotFoundExceptionDto();

        exceptionDto.setProductId(e.getProductId());
        exceptionDto.setMessage("Product Not Found");
        exceptionDto.setResolutionDetails("Please check the product ID and try again");


        return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
    }
}
