package com.ms.services;

import com.ms.models.Attribute;
import com.ms.models.Product;
import com.ms.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(List<String> category) {
        if(category!= null){
            //return productRepository.findAllByCategory(category);
            return productRepository.findAllBy(category);
        }
        return productRepository.findAll();
    }

    @Override
    public Product getAllProductBySku(String sku) {
        return productRepository.findProductBySku(sku);
    }

    @Override
    public Product createOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Long deleteProduct(String id) {
        return productRepository.deleteBySku(id);
    }

    @Override
    public List<Product> getProductsByAttributes(List<Attribute> attributes) {
        return productRepository.findProductsByAttributesIn(attributes);
    }


}