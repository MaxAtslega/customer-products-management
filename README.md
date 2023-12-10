# Customer Products Management
A JavaFX-based application for managing customer and product information. 
This project is a solution for retail operations, designed to streamline the management of products, orders, and customer information. It offers a user-friendly interface that allows retail staff to efficiently add and manage product inventories, track orders, and maintain detailed customer profiles. This project was created during my academic school years as part of my coursework.

## Technical Architecture
### System Architecture
- **Frontend**: JavaFX-based user interface.
- **Backend**: Spring Framework with RESTful API services.
- **Database**: PostgreSQL for persistent data storage.
- **Security**: Bearer Token Authentication for secure API access.

### Directory Structure
- `/frontend`: Contains the JavaFX project with all frontend-related files.
- `/backend`: Houses the Spring Framework project for backend services.
- `/scripts`: Scripts for environment setup, deployment, and other utilities.

## Setup and Installation
### Prerequisites
1. Java Development Kit (JDK): JDK version 19 or later is required. You can download it from Oracle's website or through a package manager like apt on Ubuntu or Homebrew on macOS.
2. The project uses [Gradle](https://gradle.org) as a build tool. It already contains `./gradlew` wrapper script, so there's no need to install gradle.
3. Docker: Docker is used to run the application in a container. You can download it from [here](https://www.docker.com/products/docker-desktop).

### Project Setup
Open a terminal and run the following command to clone the project repository:

```bash
  git clone https://github.com/MaxAtslega/customer-products-management.git
```

Navigate into the project directory:

```bash
  cd customer-products-management
```

### Backend Setup
Instructions for installing and running the Spring Boot backend.

#### Prerequisites
Navigate into the backend directory:

```bash
  cd backend
```

#### Building the application
To build the backend execute the following command:

```shell
  ./gradlew build
```
#### Running the application

Create the image of the application by executing the following command:

```shell
  ./gradlew assemble
```

Create docker image:

```shell
  docker-compose build
```


### Frontend Installation
Instructions for installing and running the JavaFX frontend, detailing system prerequisites and configuration steps.

#### Prerequisites
Navigate into the backend directory:

```bash
  cd frontend
```

#### Building the application
To build the frontend execute the following command:

```shell
  ./gradlew build
```

#### Run the JavaFX Application
After a successful build, start the JavaFX application using Gradle:
    
```shell
   ./gradlew run
```

### Troubleshooting
- JDK Compatibility Issues: Check your JDK version with java -version. The project requires JDK 19 or later.
- Gradle Build Failures: If the build fails, inspect the console for error messages. Common issues include network connectivity problems or missing dependencies.
- JavaFX Runtime Components Missing: Ensure that your PATH and JAVA_HOME environment variables are correctly set, pointing to your JDK installation.

### Additional Resources
- [JavaFX Official Site](https://openjfx.io/)
- [JavaFX Tutorial](https://openjfx.io/openjfx-docs/)
- [Spring Framework Official Site](https://spring.io/)
- [Gradle Official Site](https://gradle.org/)
- [Docker Official Site](https://www.docker.com/)
- [PostgreSQL Official Site](https://www.postgresql.org/)

## API Documentation
### Overview
The Customer Products Management API provides tools for managing customers, orders, and products.

#### Version: 1.0
#### Base URL: `http://localhost:8080/api/v1`

### Swagger UI
Explore the API in more detail with Swagger UI:
[Swagger Documentation](http://localhost:8080/api/v1/swagger-ui/index.html)

### Authentication
- **User Registration (`POST /auth/register`):** Registers a new admin user and their company. Registration may be disabled.
- **User Login (`POST /auth/login`):** Authenticates a user and returns a token upon successful login.

### User Management
- **Retrieve User by ID (`GET /users/{id}`):** Find and return a user using their unique ID. Accessible by admin users.
- **Update User (`PUT /users/{id}`):** Update the details of an existing user. Admin access required.
- **Delete User by ID (`DELETE /users/{id}`):** Remove a user using their unique ID. Accessible by admin users.
- **Retrieve All Users (`GET /users/`):** Fetch a list of all users, with pagination options. Admin access required.
- **Create New User (`POST /users/`):** Add a new user to the system. Admin access required.

### Product Management
- **Retrieve Product by ID (`GET /products/{id}`):** Find and return a product using its unique ID.
- **Update Product (`PUT /products/{id}`):** Update the details of an existing product.
- **Delete Product by ID (`DELETE /products/{id}`):** Remove a product using its unique ID.
- **Retrieve All Products (`GET /products/`):** Fetch a list of all products, with pagination options.
- **Create New Product (`POST /products/`):** Add a new product to the system.

### Order Management
- **Retrieve Order by ID (`GET /orders/{id}`):** Find and return an order using its unique ID.
- **Update Order (`PUT /orders/{id}`):** Update the details of an existing order.
- **Delete Order by ID (`DELETE /orders/{id}`):** Remove an order using its unique ID.
- **Retrieve All Orders (`GET /orders/`):** Fetch a list of all orders, with pagination options.
- **Create New Order (`POST /orders/`):** Add a new order to the system.

### Customer Management
- **Find Customer by ID (`GET /customers/{id}`):** Locate a customer using their unique ID.
- **Update Customer Info (`PUT /customers/{id}`):** Update information for an existing customer.
- **Delete Customer by ID (`DELETE /customers/{id}`):** Remove a customer using their unique ID.
- **Find All Customers (`GET /customers/`):** Retrieve a list of all customers.
- **Create New Customer (`POST /customers/`):** Add a new customer to the database.

### Health Check
- **Health Check (`GET /healthy`):** Check if the service is up and running.

### Error Handling
Responses for erroneous requests include a detailed `ErrorResponse` schema.

### Data Models
The API uses specific request and response schemas for different operations.

## Authors
- Max Atslega: Responsible for both Backend & Frontend development. [GitHub Profile](https://github.com/MaxAtslega)
- Lasse Hüls: Specializing in Frontend development. [GitHub Profile](https://github.com/lay002)
- Leon Enns: A Frontend developer.
- Dennis Rybin: Focused on Backend development.