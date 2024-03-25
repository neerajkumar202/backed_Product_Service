package com.productservice.backendproductservice.services;


import com.productservice.backendproductservice.dtos.FakeStoreProductDto;
import com.productservice.backendproductservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {

    public GenericProductDto getProductById(Long id);
    public List<GenericProductDto> getAllProducts();


    public GenericProductDto deleteProductById(Long id);


    public GenericProductDto createProduct(GenericProductDto genericProductDto);


    public GenericProductDto updateProductById(Long id , GenericProductDto genericProductDto );

}
