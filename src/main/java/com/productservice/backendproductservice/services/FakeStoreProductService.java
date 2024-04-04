package com.productservice.backendproductservice.services;

import com.productservice.backendproductservice.dtos.FakeStoreProductDto;
import com.productservice.backendproductservice.dtos.GenericProductDto;
import com.productservice.backendproductservice.exceptions.ProductNotFoundException;
import com.productservice.backendproductservice.thirdPartyClient.fakeStoreClient.FakeStoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Primary

@Service
public class FakeStoreProductService implements ProductService {

    private FakeStoreClient fakeStoreClient;

    FakeStoreProductService(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient = fakeStoreClient;
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

        return convertToGenericProductDto(fakeStoreClient.getProductById(id));

    }

    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
       for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            genericProductDtos.add(convertToGenericProductDto(fakeStoreProductDto));
        }
       return genericProductDtos;

    }


    public GenericProductDto deleteProductById(Long id) {
        return convertToGenericProductDto(fakeStoreClient.deleteProductById(id));

    }


    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return convertToGenericProductDto(fakeStoreClient.createProduct(genericProductDto));

    }


    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
       return convertToGenericProductDto(fakeStoreClient.updateProductById(id,genericProductDto));

    }
}
