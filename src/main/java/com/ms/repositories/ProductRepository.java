package com.ms.repositories;

import com.ms.models.Attribute;
import com.ms.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Product save(Product product);

    List<Product> findAll();

    Long deleteBySku(String id);

    Product findProductBySku(String sku);

    List<Product> findAllByCategoryLike(String category);

    @Query("{ 'category': { '$in': ?0} }")
    List<Product> findAllBy(List<String> categories);

    List<Product> findProductsByAttributesIn(List<Attribute> attributes);
}
