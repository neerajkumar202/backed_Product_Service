package com.productservice.backendproductservice.services;

import com.productservice.backendproductservice.dtos.FakeStoreProductDto;
import com.productservice.backendproductservice.dtos.GenericProductDto;
import com.productservice.backendproductservice.exceptions.ProductNotFoundException;
import com.productservice.backendproductservice.thirdPartyClient.fakeStoreClient.FakeStoreClientAdapter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private FakeStoreClientAdapter fakeStoreClientAdapter;

    FakeStoreProductService(FakeStoreClientAdapter fakeStoreClientAdapter){
        this.fakeStoreClientAdapter = fakeStoreClientAdapter;
    }

    private static GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());

        return genericProductDto;

    }


    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {

        return convertToGenericProductDto(fakeStoreClientAdapter.getProductById(id));

    }

    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClientAdapter.getAllProducts();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
       for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            genericProductDtos.add(convertToGenericProductDto(fakeStoreProductDto));
        }
       return genericProductDtos;

    }


    public GenericProductDto deleteProductById(Long id) {
        return convertToGenericProductDto(fakeStoreClientAdapter.deleteProductById(id));

    }


    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return convertToGenericProductDto(fakeStoreClientAdapter.createProduct(genericProductDto));

    }


    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
       return convertToGenericProductDto(fakeStoreClientAdapter.updateProductById(id,genericProductDto));

    }
}
