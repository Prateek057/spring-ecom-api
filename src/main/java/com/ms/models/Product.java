package com.ms.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Product {


    @Id
    @NotNull
    private String sku;
    private String name;
    private String department;
    private Pricing pricing;
    private String category;
    private String brand;
    private List<Attribute> attributes;

    public Pricing getPricing() {
        return pricing;
    }


    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public Product() {
    }


}