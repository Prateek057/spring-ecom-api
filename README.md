# spring-ecom-api
Sample Spring-MongoDB ECommerce API Server. 
Server should be run using gradle using IDE of your choice

## Requirements
1. Database Schema: Create schema to store product and categories. (Getting full category path for the products might be a future requirement)
2. CRUD Operation: Client should perform CRUD operations on Products and Categories.
3. Currency Exchange: Add an ability to create products with price in different from Euro. Possible integration with Third Party Currency Exchange API

## Prerequisites
1. Spring Framework
2. Swagger2 Documentation


## Dependencies
1. Java 1.8 
2. Spring Boot 1.5.9.RELEASE
3. SpringFox Swagger 2.7.0 
4. Spring for MongoDB 

## Running
1. Application available at : http://localhost:8080/api/v1
2. API Documents available at : http://localhost:8080/api/v1/swagger-ui.html