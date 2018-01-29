package com.ms.services;

import com.ms.models.Category;
import com.ms.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createOrUpdateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Long deleteCategoryById(String id) {
        return categoryRepository.deleteCategoryById(id);
    }

    @Override
    public List<Category> getCategoriesBySlug(String slug) {
        return categoryRepository.findCategoriesBySlug(slug);
    }
}
