package com.productservice.backendproductservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class ExceptionDto {
    private HttpStatus httpStatus;
    private String messege;
}
