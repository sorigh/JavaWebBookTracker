# Book Management System

A Spring Boot-based web application for managing books and their various properties, including categories and tags. The system allows users to perform CRUD operations on books and offers flexibility in linking tags to books. The project includes a simple interface for managing the system's entities and is built with various best practices in mind, including validation, proper entity relationships, and various design patterns for maintainability and scalability. Additionally, the application includes a login system using Spring Security to differentiate the operations available to users based on their roles.

## Features

- **Book Management**: Create, view, update, and delete books.
- **Category Management**: Organize books into categories and manage categories.
- **Tag Management**: Add and remove tags to/from books.
- **User Interface**: A simple, user-friendly web interface using Spring MVC.
- **Validation**: Input validation using Jakarta Validation annotations.
- **User Roles**: Authentication and authorization using Spring Security to differentiate user permissions (e.g., Admin, User).

## Technologies Used

- **Spring Boot**: A framework for building Java-based web applications.
- **Spring MVC**: A model-view-controller framework for building web applications in Java.
- **Spring Data JPA**: Provides integration with the Java Persistence API for data access, allowing interaction with the database using repository patterns.
- **Thymeleaf**: A server-side Java template engine for rendering HTML views.
- **Jakarta Validation**: Used for validating user input on forms.
- **Spring Security**: Provides authentication and authorization features, ensuring that only admin users can perform CRUD operations on users.
- **Lombok**: A library to reduce boilerplate code in model classes.
- **SQL Database**: A relational database for entities like books, categories, and tags.
- **Gradle**: For managing project dependencies.

## Best Practices

- **Separation of Concerns**: Clear distinction between business logic, UI components, and data access layers.
- **Entity Relationships**: Using JPA annotations to define relationships between entities.
- **Validation**: Ensuring data integrity through Jakarta validation annotations in model classes.
- **Security**: Implementing role-based access control with Spring Security for different user roles 
