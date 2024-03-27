package com.productservice.backendproductservice.services;

import com.productservice.backendproductservice.dtos.FakeStoreProductDto;
import com.productservice.backendproductservice.dtos.GenericProductDto;
import com.productservice.backendproductservice.exceptions.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;// use to call the external api

    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    private  String specificProductUrl = "https://fakestoreapi.com/products/{id}";
    private String genericProductUrl = "https://fakestoreapi.com/products";



    private static GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());

        return genericProductDto;

    }



    public GenericProductDto getProductById( Long id) throws ProductNotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class,id);

        // handling the exception
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        if(fakeStoreProductDto== null){
            //Throw an exception
            throw new ProductNotFoundException("Product with id: " + id + " not exist ");
        }

         //before returning convert the return type to generic productType for extra layer.


        return convertToGenericProductDto(responseEntity.getBody());

    }
    public List<GenericProductDto> getAllProducts(){
      RestTemplate restTemplate = restTemplateBuilder.build();
      ResponseEntity<FakeStoreProductDto[]> responseEntity =
          restTemplate.getForEntity(genericProductUrl,FakeStoreProductDto[].class);

      List<GenericProductDto> result = new ArrayList<>();
      List<FakeStoreProductDto> fakeStoreProductDtos = List.of(responseEntity.getBody());
      for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
          result.add(convertToGenericProductDto(fakeStoreProductDto));
      }
        return  result  ;


    }


    public GenericProductDto deleteProductById(Long id){
         RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
        restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return  convertToGenericProductDto(responseEntity.getBody());

    }


    public GenericProductDto createProduct(GenericProductDto genericProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> responseEntity =
        restTemplate.postForEntity(genericProductUrl,genericProductDto,FakeStoreProductDto.class);


        return  convertToGenericProductDto(responseEntity.getBody());

    }


    public GenericProductDto updateProductById(Long id,GenericProductDto genericProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(specificProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        return  convertToGenericProductDto(responseEntity.getBody());

    }
}
