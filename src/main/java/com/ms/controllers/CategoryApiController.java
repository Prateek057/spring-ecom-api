/**
 *
 * @file CategoryApiController.java
 * @author Prateek Bagrecha
 * @description Spring ECommerce Endpoint APIs for Category Catalog
 *      - GET /categories - gets all categories
 * 		- POST /category/create - creates a category
 * 	    - DELETE /category/delete/{id} - delete a category by its id
 * 	    - POST /category/update - updates an existing category
 */
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

    /**
     * GET Method mapping to retrieve all categories
     *
     * @return List<Category>
     */
    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * GET Method to retrieve category by its id
     * @param id
     * @return Category
     */
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCategoryById(@PathVariable String id){
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    /**
     * GET Method to retrieve category by its slug
     * @param slug
     * @return Category
     */
    @RequestMapping(value="/category/bySlug/{slug}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getCategoryBySlug(@PathVariable String slug){
        return categoryService.getCategoriesBySlug(slug);
    }


    /**
     * POST method to create new category
     *
     * @param category
     * @return Category wrapped in ResponseEntity
     */
    @RequestMapping(value = "/category/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.createOrUpdateCategory(category), HttpStatus.CREATED);
    }

    /**
     * DELETE method to delete an existing category
     *
     * @param id
     * @return ResponseEntity with no of documents deleted
     */
    @RequestMapping(value = "/category/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable String id) {
        return new ResponseEntity<>(categoryService.deleteCategoryById(id), HttpStatus.OK);
    }

    /**
     * Method to update an existing category
     *
     * @param category
     * @return Category : Updated document
     */
    @RequestMapping(value = "/category/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.createOrUpdateCategory(category);
    }
}
