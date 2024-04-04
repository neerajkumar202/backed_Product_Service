package com.productservice.backendproductservice.thirdPartyClient.fakeStoreClient;

import com.productservice.backendproductservice.dtos.FakeStoreProductDto;
import com.productservice.backendproductservice.dtos.GenericProductDto;
import com.productservice.backendproductservice.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreClient {


    private RestTemplateBuilder restTemplateBuilder;// use to call the external api

    FakeStoreClient(RestTemplateBuilder restTemplateBuilder,
                  //  @Value("${fakestore.api.url}") String fakeStoreUrl,
                   // @Value("${fakestore.api.paths.products}") String pathForProducts

    ) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.genericProductUrl = fakeStoreUrl + pathForProducts;
        this.specificProductUrl =fakeStoreUrl + pathForProducts +"/{id}";
    }

    //@Value("${fakestore.api.url}") we gonna initialize it in constructor
    private String fakeStoreUrl;
    //@Value("${fakestore.api.paths.products}") we gonna initialize it in constructor
    private String pathForProducts;
    private String specificProductUrl ;
    private String genericProductUrl ;





    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);

        // handling the exception
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        if (fakeStoreProductDto == null) {
            //Throw an exception
            throw new ProductNotFoundException("Product with id: " + id + " not exist ");
        }

        //before returning convert the return type to generic productType for extra layer.


        return fakeStoreProductDto;

    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);

//        List<GenericProductDto> result = new ArrayList<>();
//        List<FakeStoreProductDto> fakeStoreProductDtos = List.of(responseEntity.getBody());
//        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
//            result.add(convertToGenericProductDto(fakeStoreProductDto));
//        }
//        return result;
        return List.of(responseEntity.getBody());


    }


    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return responseEntity.getBody();

    }


    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(genericProductUrl, genericProductDto, FakeStoreProductDto.class);


        return responseEntity.getBody();

    }


    public FakeStoreProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(specificProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        return responseEntity.getBody();

    }
}
