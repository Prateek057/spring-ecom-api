package com.ms.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.ms.models.Pricing;
import com.ms.models.Product;
import com.ms.models.Attribute;
import com.ms.services.ProductService;
import com.ms.utils.fixer.io.FixerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductApiController {
    @Autowired private ProductService productService;
    @Autowired private FixerService fixerService;

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts(@RequestParam(value = "category", required = false) String category,
                                     @RequestParam(value="currency", required = false) String currency
                                     ){
        List<Product> products = productService.getAllProducts(category);
        if(currency!= null){
            for(Product product: products){
                String base = product.getPricing().getCurrency();
                Float rate = fixerService.convertCurrency(base, currency);
                Float newPrice = product.getPricing().getPrice() * rate;
                Pricing newPricing = new Pricing();
                newPricing.setCurrency(currency);
                newPricing.setPrice(newPrice);
                product.setPricing(newPricing);
            }
        }
        return products;
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable String product_id){
        return productService.getAllProductBySku(product_id);
    }

    @RequestMapping(value="/productByAttributes", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductByAttributes(@RequestBody List<Attribute> attributes){
        return productService.getProductsByAttributes(attributes);
    }

    @RequestMapping(value="/product/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<Product>(productService.createOrUpdateProduct(product), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/delete/{product_id}", method = RequestMethod.DELETE )
    public ResponseEntity deleteProduct(@PathVariable String id ){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @RequestMapping(value="/product/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product product){
        return productService.createOrUpdateProduct(product);
    }
}