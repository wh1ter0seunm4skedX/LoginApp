# Spring Boot Login Application

## Description

This Spring Boot application demonstrates a secure login and registration system using Spring Security. It features two types of users: `Admin` and `User`, each with their respective login, registration, and dashboard views. The application utilizes Thymeleaf for server-side rendering, Spring Data JPA for database interactions, and PostgreSQL as the database. It also includes a "Remember Me" functionality that keeps users logged in for 30 minutes, enhancing user experience without compromising security.

## Features

- Secure user authentication and authorization with Spring Security.
- Separate login and registration for Admin and User roles.
- "Remember Me" functionality for convenience and enhanced user experience.
- Persistent login sessions managed through a PostgreSQL database.
- Stylish and responsive design using CSS.

## Prerequisites

- JDK 11 or newer
- Maven 3.2+
- PostgreSQL

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/wh1ter0seunm4skedX/Secure-Login-and-Registration-with-Spring-Boot.git
    ```
2. Navigate to the project directory:
    ```bash
    cd Secure-Login-and-Registration-with-Spring-Boot
    ```
3. Create a PostgreSQL database named `LoginApp`.
4. Update `src/main/resources/application.properties` with your PostgreSQL username and password:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/LoginApp
    spring.datasource.username=<YOUR_USERNAME>
    spring.datasource.password=<YOUR_PASSWORD>
    ```
5. Build and run the application:
    ```bash
    mvn spring-boot:run
    ```

## Usage

After starting the application, visit `http://localhost:8080` in your web browser to access the landing page. From there, you can navigate to the login or registration pages for either Admin or User roles.

## Contributing

Contributions are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT](https://choosealicense.com/licenses/mit/)
