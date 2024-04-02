package com.productservice.backendproductservice.controllers;

import com.productservice.backendproductservice.dtos.ExceptionDto;
import com.productservice.backendproductservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class ProductControllerAdvices {

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleProductNotFoundEception(
            ProductNotFoundException productNotFoundException

    ){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessege(productNotFoundException.getMessage());
        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);


    }

}
