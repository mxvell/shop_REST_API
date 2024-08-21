# Shop REST API

This is a RESTful API for a simple online shop, developed using Java, Spring Boot, and PostgreSQL.

## Project Overview
The main goal of this project is to create a backend API for an online shop. The API provides endpoints for managing products, orders, and users. 

Some key features of the project include:
- CRUD operations for products, orders, and users
- Ability to upload product images
- Functionality for managing a user's shopping basket
- Random product retrieval

### Controllers
- `OrderController`: Handles order-related endpoints, such as retrieving an order by ID and updating the order status.
- `ProductController`: Manages product-related functionality, including retrieving products, creating/updating/deleting products, and uploading product images.
- `UserController`: Provides endpoints for user management, including creating/updating/deleting users, managing the user's shopping basket, and retrieving the total price of the basket.

### Models
- `Order`: Represents an order placed by a user, with fields for order status, user, and order items.
- `Product`: Represents a product sold in the shop, with fields for name, description, price, and category.
- `User`: Represents a user of the shop, with fields for name, email, and shopping basket.
- `Category`: An enum representing the different categories of products, such as LAPTOP, MONITOR, PHONE, and CHARGER.
- `OrderStatus`: An enum representing the different statuses an order can have, such as IN_PROGRESS, ORDER_ACCEPTED, DELIVERING, and DELIVERED.

### Repositories
- `OrderRepository`: Handles CRUD operations for orders.
- `ProductRepository`: Handles CRUD operations for products.
- `UserRepository`: Handles CRUD operations for users.
- `ImageRepository`: Handles CRUD operations for orders.

### Services
- `ImageService`: Handles the upload and storage of product images.
- `OrderService`: Provides business logic for order management, including updating order status.
- `ProductService`: Provides business logic for product management, including creating, updating, and deleting products.
- `UserService`: Provides business logic for user management, including managing the user's shopping basket.

## Endpoint Documentation

The API provides the following endpoints:

### ProductController
- `GET /products`: Retrieves a list of all products.
- `GET /products/{id}`: Retrieves a product by its ID.
- `GET /products/by-name/{name}`: Retrieves a list of products where the name starts with the specified string.
- `POST /products/{productId}/images`: Uploads an image for the specified product.
- `POST /products`: Creates a new product.
- `PUT /products/{id}`: Updates an existing product by its ID.
- `DELETE /products/{id}`: Deletes a product by its ID.
- `GET /products/random`: Retrieves a random product.
- `GET /products/random/{count}`: Retrieves a list of `count` random products.

### OrderController
- `GET /orders/{id}`: Retrieves an order by its ID.
- `PUT /orders/{id}/status`: Updates the status of an order by its ID.

### UserController
- `GET /users`: Retrieves a list of all users.
- `GET /users/{id}`: Retrieves a user by its ID.
- `GET /users/by-name/{name}`: Retrieves a list of users with the specified name.
- `GET /users/by-email/{email}`: Retrieves a user by their email.
- `POST /users`: Creates a new user.
- `PUT /users/{id}`: Updates an existing user by its ID.
- `DELETE /users/{id}`: Deletes a user by its ID.
- `POST /users/basket/add`: Adds a product to the user's basket.
- `DELETE /users/basket/delete/{userId}/{productId}`: Deletes a product from the user's basket.
- `DELETE /users/basket/deleteAll`: Clears the user's basket.
- `GET /users/basket/total{id}`: Retrieves the total price of the products in the user's basket.
- `GET /users/basket/{id}`: Retrieves the list of products in the user's basket.

Feel free to explore the code and the available functionality. If you have any questions or issues, please don't hesitate to reach out.
