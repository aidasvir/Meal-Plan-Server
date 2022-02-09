meal-plan-server
================

Add your favorite meals to database, create your meal plans and track your macros

## Database structure

* Products table
    * id
    * type
    * meal
    * calories
    * carbs
    * protein
    * fats
    * fiber


* Users table
  * id
  * username
  * password
  * plan

## Server-side

### Hibernate
* insert product
* get product list
* get products by type
* get product by name
* update plan
* create user
* get user
* get users list
* get users plan

### Meal plan formation
* add product to meal
* add meal to plan

## Client-side

### Spark
* login page
* registration page
* product list page
* products by type list
* single product page
* insert new product
* add to meal
* add to plan
* plan page
* macros page

## Technologies

* Java Persistence API
* Hibernate
* MySQL DB
* Spark
