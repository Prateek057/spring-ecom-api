package com.ms.repositories;

import com.ms.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    Category save(Category category);

    Long deleteCategoryById(String Id);

    List<Category> findAll();

    List<Category> findCategoriesBySlug(String slug);
}
