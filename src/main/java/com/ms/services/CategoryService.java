package com.ms.services;

import com.ms.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category createOrUpdateCategory(Category category);
    Long deleteCategoryById(String id);
    List<Category> getCategoriesBySlug(String slug);
}
