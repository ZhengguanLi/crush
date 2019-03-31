# Crush E-Commerce

This is a Spring MVC e-commerce web application which supports user authentication/authorization, complete purchasing experience, and CRUD operations via Restful service and admin pages.

[Live demo](http://crush.us-east-2.elasticbeanstalk.com/)

## Preview

![Home page](preview.JPG)

User admin page:

![update user](update-user.JPG)

Menu admin page:

![update menu](update-menu.JPG)

Update a meal:

<img src="update-meal.JPG" alt="update meal" width="300px">

Restful API page:

![restful page](restful-page.JPG)

Restful example:

![restful example](restful-example.JPG)

Login page:

<img src="login.JPG" alt="login" width="300px">

Register page:

<img src="register.JPG" alt="sign up" width="300px">

Payment page:

<img src="pay-info.JPG" alt="update meal" width="300px">

Payment success:

![payment success](payment-success.JPG)

## Getting started

### Prerequisites

- Spring Boot
- Intellij/Eclipse
- MySQL
- Stripe Account
  
### Usage

- Populate database with `crush.sql` in MySQL
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

### Deploy the appplication to AWS

Please refer to the article: [The Ultimate Guide to Hosting a Java Web App with Amazon Web Services (AWS)
](https://howtoprogramwithjava.com/ultimate-guide-hosting-java-web-app-amazon-web-services-aws/#commentform)
