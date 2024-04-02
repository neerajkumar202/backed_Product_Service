package com.productservice.backendproductservice.services;


import com.productservice.backendproductservice.dtos.GenericProductDto;
import com.productservice.backendproductservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    public GenericProductDto getProductById(Long id) throws ProductNotFoundException;

    public List<GenericProductDto> getAllProducts();


    public GenericProductDto deleteProductById(Long id);


    public GenericProductDto createProduct(GenericProductDto genericProductDto);


    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto);

}
