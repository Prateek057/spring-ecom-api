package com.ms.services;

import com.ms.models.Product;
import com.ms.models.Attribute;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllProducts(List<String> category);

    Product getAllProductBySku(String sku);

    Product createOrUpdateProduct(Product product);

    Long deleteProduct(String id);

    List<Product> getProductsByAttributes(List<Attribute> attributes);
}
