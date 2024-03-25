package com.productservice.backendproductservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    //private Long id;
    private String title;
    private  String desc;
    private int price;
    private String image;
    private Category category;



}
