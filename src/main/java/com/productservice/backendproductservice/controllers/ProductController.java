package com.productservice.backendproductservice.controllers;

import com.productservice.backendproductservice.dtos.ExceptionDto;
import com.productservice.backendproductservice.dtos.GenericProductDto;
import com.productservice.backendproductservice.exceptions.ProductNotFoundException;
import com.productservice.backendproductservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService) { // Constructor Injection
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        //return "product with id: " + id;
        return productService.getProductById(id);

    }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();

    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);

    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);


    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) {
        return productService.updateProductById(id, genericProductDto);

    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
//
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessege(productNotFoundException.getMessage());
//        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
//        ResponseEntity responseEntity = new ResponseEntity(exceptionDto, HttpStatus.NOT_FOUND);
//
//        return responseEntity;
    //}
}
