package com.ms.controllers;

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


    /**
     * GET Method to get all products.
     * Additionally pass category list to find products based on categories
     * pass currency code to retrieve product prices in different currency
     * @param category
     * @param currency
     * @return a List of products List<Product>
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts(@RequestParam(value = "category", required = false) List<String> category,
                                     @RequestParam(value="currency", required = false) String currency
                                     ){
        System.out.print(category);
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

    /**
     * Method to get product by its id
     * @param product_id
     * @return Product
     */
    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable String product_id){
        return productService.getAllProductBySku(product_id);
    }

    /**
     * Method to find products by attributes
     * @param attributes
     * @return List of products
     */
    @RequestMapping(value="/productByAttributes", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductByAttributes(@RequestBody List<Attribute> attributes){
        return productService.getProductsByAttributes(attributes);
    }

    /**
     * Method to create product
     * @param product
     * @return Product created
     */
    @RequestMapping(value="/product/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.createOrUpdateProduct(product), HttpStatus.CREATED);
    }

    /**
     * Method to delete product by its id/ SKU  (Stock Keeping Unit)
     * @param id
     * @return no of products deleted
     */
    @RequestMapping(value = "/product/delete/{product_id}", method = RequestMethod.DELETE )
    public ResponseEntity deleteProduct(@PathVariable String id ){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    /**
     * MEethod to update a given product by its id
     * @param product
     * @return Product updated
     */
    @RequestMapping(value="/product/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product product){
        return productService.createOrUpdateProduct(product);
    }
}