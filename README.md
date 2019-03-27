# Crush E-Commerce

This is a Spring MVC e-commerce web application which supports user authentication/authorization, complete purchasing experience, and CRUD operations via Restful service and admin pages.

[Live demo](http://crush.us-east-2.elasticbeanstalk.com/)

## Preview

![Home page](preview.jpg)

User admin page:

![update user](update-user.jpg)

Menu admin page:

![update menu](update-menu.jpg)

Update a meal:

<img src="update-meal.jpg" alt="update meal" width="300px">

Restful API page:

![restful page](restful-page.jpg)

Restful example:

![restful example](restful-example.jpg)

Login page:

<img src="login.jpg" alt="login" width="300px">

Register page:

<img src="register.jpg" alt="sign up" width="300px">

Payment page:

<img src="pay-info.jpg" alt="update meal" width="300px">

Payment success:

![payment success](payment-success.jpg)

## Getting started

### Prerequisites

- Spring Boot
- Intellij/Eclipse
- MySQL
- Stripe account
  
### Usage

- Populate database with `crush.sql`
- Set up datasource of your database in `application.properties` file
    ```text
    # Datasource properties
    spring.datasource.url=jdbc:mysql://localhost:3306/<dbname>
    spring.datasource.username=<connection username>
    spring.datasource.password=<connection password>
    ```
- Set up your stripe public/secret key in `application.properties` file
    ```text
    # stripe key
    STRIPE_PUBLIC_KEY=<stripe public key>
    STRIPE_SECRET_KEY=<stripe secret key>
    ```
- Set up your port number
  
  ```
  server.port=<your port number>
  ```

- Run application
- Open `http://localhost:<port number>` on browser
  
Note: by default in `application.properties` file, the port number is set to 5000