# 🔐 Spring Boot Security Practice

A comprehensive Spring Boot application demonstrating secure user authentication, authorization, and password management using Spring Security and JWT principles.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Security Configuration](#security-configuration)
- [Database Schema](#database-schema)
- [Usage Examples](#usage-examples)
- [Project Structure](#project-structure)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [Contact](#contact)

## 🌟 Overview

This project is a robust Spring Boot application that implements industry-standard security practices for user management. It showcases authentication, role-based authorization, password encryption, and secure endpoint protection using Spring Security framework.

The application provides a RESTful API for user registration, authentication, password management, and administrative controls, making it an excellent demonstration of secure backend development practices.

## ✨ Features

- ✅ **User Registration & Authentication**: Secure user registration with BCrypt password encryption
- ✅ **Role-Based Access Control (RBAC)**: Admin and User roles with different permission levels
- ✅ **Password Management**: 
  - Change password (for authenticated users)
  - Forgot password recovery
  - Admin password reset functionality
- ✅ **Secure Endpoints**: HTTP Basic Authentication with Spring Security
- ✅ **User Management**: 
  - View all users (Admin only)
  - Delete user account with password verification
- ✅ **Database Integration**: JPA/Hibernate with MySQL
- ✅ **RESTful API Design**: Clean and intuitive API structure
- ✅ **Method-Level Security**: Using `@PreAuthorize` annotations

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| **Spring Boot** | 4.0.1 | Main application framework |
| **Spring Security** | Latest | Authentication & Authorization |
| **Spring Data JPA** | Latest | Database operations |
| **Hibernate** | Latest | ORM framework |
| **MySQL** | 8.0+ | Relational database |
| **Lombok** | Latest | Reduce boilerplate code |
| **BCrypt** | Latest | Password encryption |
| **Maven** | Latest | Dependency management |
| **Java** | 25 | Programming language |

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                     Client Layer                             │
│              (HTTP Requests with Basic Auth)                 │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                  Controller Layer                            │
│         (UserController - REST Endpoints)                    │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│               Spring Security Filter                         │
│    (Authentication & Authorization Checks)                   │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                   Service Layer                              │
│      (UserService - Business Logic)                          │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                 Repository Layer                             │
│         (UserRepo - Database Operations)                     │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                    MySQL Database                            │
│                   (users table)                              │
└─────────────────────────────────────────────────────────────┘
```

## 🚀 Getting Started

### Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 25** or higher
- **Maven 3.8+**
- **MySQL Server 8.0+**
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)
- **Postman or cURL** (for testing API endpoints)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/spring-security-practice.git
   cd spring-security-practice
   ```

2. **Create MySQL Database**
   ```sql
   CREATE DATABASE library_db;
   ```

3. **Update Database Configuration**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/library_db
   spring.datasource.username=YOUR_MYSQL_USERNAME
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`

### Configuration

#### Application Properties

```properties
# Application Name
spring.application.name=SecurityPractice

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## 📡 API Endpoints

### Public Endpoints (No Authentication Required)

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/api` | Test endpoint | None |
| `POST` | `/register` | Register new user | `{"username": "user1", "password": "pass123", "roles": "USER"}` |
| `PUT` | `/forgetPassword` | Reset forgotten password | Query Params: `username`, `password` |

### Protected Endpoints (Authentication Required)

| Method | Endpoint | Description | Auth Required | Request Body |
|--------|----------|-------------|---------------|--------------|
| `PUT` | `/changePassword` | Change password for logged-in user | Yes (USER) | Query Param: `password` |
| `DELETE` | `/delete` | Delete user account | Yes (USER) | Query Params: `username`, `pass` |

### Admin Endpoints (Admin Role Required)

| Method | Endpoint | Description | Auth Required | Request Body |
|--------|----------|-------------|---------------|--------------|
| `GET` | `/users` | Get all users | Yes (ADMIN) | None |
| `PUT` | `/admin/reset/{username}` | Admin reset user password | Yes (ADMIN) | Query Param: `password` |

## 🔒 Security Configuration

### Authentication
- **Type**: HTTP Basic Authentication
- **Password Encoding**: BCrypt (Strength: 10 rounds)

### Authorization Rules

```java
/api, /register, /forgetPassword → Public access
/users → ADMIN role only
/admin/** → ADMIN role only
/delete → Authenticated users
All other endpoints → Authenticated users
```

### Security Features

1. **CSRF Protection**: Disabled for API usage (enable for production web apps)
2. **Password Encryption**: All passwords are encrypted using BCrypt
3. **Method-Level Security**: `@PreAuthorize` annotations for fine-grained control
4. **Custom UserDetailsService**: Loads user data from database

## 💾 Database Schema

### Users Table

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255)
);
```

### Sample Data

```sql
-- Admin User (password: admin123)
INSERT INTO users (username, password, roles) 
VALUES ('admin', '$2a$10$encoded_password_here', 'ADMIN');

-- Regular User (password: user123)
INSERT INTO users (username, password, roles) 
VALUES ('user1', '$2a$10$encoded_password_here', 'USER');
```

## 📝 Usage Examples

### 1. Register a New User

```bash
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "newuser",
    "password": "password123",
    "roles": "USER"
  }'
```

**Response:**
```json
{
  "id": 1,
  "username": "newuser",
  "password": "$2a$10$encrypted_password",
  "roles": "USER"
}
```

### 2. Get All Users (Admin Only)

```bash
curl -X GET http://localhost:8080/users \
  -u admin:admin123
```

**Response:**
```json
[
  {
    "id": 1,
    "username": "admin",
    "password": "$2a$10$encrypted_password",
    "roles": "ADMIN"
  },
  {
    "id": 2,
    "username": "user1",
    "password": "$2a$10$encrypted_password",
    "roles": "USER"
  }
]
```

### 3. Change Password

```bash
curl -X PUT "http://localhost:8080/changePassword?password=newpass456" \
  -u user1:oldpassword
```

### 4. Forgot Password

```bash
curl -X PUT "http://localhost:8080/forgetPassword?username=user1&password=resetpass789"
```

### 5. Admin Reset User Password

```bash
curl -X PUT "http://localhost:8080/admin/reset/user1?password=adminresetpass" \
  -u admin:admin123
```

### 6. Delete User Account

```bash
curl -X DELETE "http://localhost:8080/delete?username=user1&pass=currentpass" \
  -u user1:currentpass
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/saptarshi/SecurityPractice/
│   │       ├── config/
│   │       │   └── MyConfiguration.java          # Security configuration
│   │       ├── controller/
│   │       │   └── UserController.java           # REST endpoints
│   │       ├── model/
│   │       │   └── User.java                     # Entity class
│   │       ├── repository/
│   │       │   └── UserRepo.java                 # JPA repository
│   │       ├── service/
│   │       │   ├── UserService.java              # Business logic
│   │       │   └── MyUserDetails.java            # Custom UserDetailsService
│   │       └── SecurityPracticeApplication.java  # Main application
│   └── resources/
│       └── application.properties                # Configuration file
└── test/
    └── java/
        └── com/saptarshi/SecurityPractice/
            └── SecurityPracticeApplicationTests.java
```

## 🔄 Future Enhancements

- [ ] **JWT Authentication**: Replace HTTP Basic with JWT tokens
- [ ] **Email Verification**: Add email verification for registration
- [ ] **Password Reset via Email**: Send password reset links
- [ ] **Rate Limiting**: Implement API rate limiting
- [ ] **Audit Logging**: Track user actions and security events
- [ ] **Two-Factor Authentication (2FA)**: Add extra security layer
- [ ] **Role Management API**: Dynamic role assignment
- [ ] **Swagger Documentation**: Interactive API documentation
- [ ] **Docker Support**: Containerize the application
- [ ] **Unit & Integration Tests**: Comprehensive test coverage

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📧 Contact

**Saptarshi** - [Your Email] - [Your LinkedIn]

Project Link: [https://github.com/yourusername/spring-security-practice](https://github.com/yourusername/spring-security-practice)

---

<div align="center">

### ⭐ Star this repository if you find it helpful!

Made with ❤️ by Saptarshi

</div>
