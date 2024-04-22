package com.productservice.backendproductservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    //private Long id;
    private String title;
    private  String description;
    private int price;
    private String image;
//    need to find the cardianality
    @ManyToOne
    private Category category;



}
