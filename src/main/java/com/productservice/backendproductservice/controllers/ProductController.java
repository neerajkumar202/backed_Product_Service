package com.productservice.backendproductservice.controllers;

import com.productservice.backendproductservice.dtos.FakeStoreProductDto;
import com.productservice.backendproductservice.dtos.GenericProductDto;
import com.productservice.backendproductservice.services.FakeStoreProductService;
import com.productservice.backendproductservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService){ // Constructor Injection
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        //return "product with id: " + id;
        return productService.getProductById(id);

    }
@GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();

  }
    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProductById(id);

    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody  GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);


    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@PathVariable ("id") Long id,@RequestBody GenericProductDto genericProductDto ){
        return productService.updateProductById(id,genericProductDto);

    }
}
