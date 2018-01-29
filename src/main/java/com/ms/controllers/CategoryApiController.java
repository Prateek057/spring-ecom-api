package com.ms.controllers;

import com.ms.models.Category;
import com.ms.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryApiController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value= "/categories", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getCategories(){
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/category/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return new ResponseEntity<Category>(categoryService.createOrUpdateCategory(category), HttpStatus.CREATED);
    }

    @RequestMapping(value="/category/delete", method=RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable String id ){
        return new ResponseEntity<>(categoryService.deleteCategoryById(id), HttpStatus.OK);
    }

    @RequestMapping(value="/category/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Category updateCategory(@RequestBody Category category){
        return categoryService.createOrUpdateCategory(category);
    }
}
