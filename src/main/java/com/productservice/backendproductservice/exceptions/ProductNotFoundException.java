package com.productservice.backendproductservice.exceptions;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String messege){
        super(messege);
    }
}
