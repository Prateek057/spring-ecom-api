package com.ms.models;

import javax.validation.constraints.Size;

public class Pricing {

    @Size(min = 3, max = 3, message = "Please provide only 3 digit code of the currency")
    private String currency;
    private Float price;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency.toUpperCase();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Pricing() {}
}
