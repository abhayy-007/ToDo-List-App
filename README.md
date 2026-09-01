# ToDo List Application

ToDo List App is a secure and responsive Spring Boot web application designed to manage daily tasks. It provides complete CRUD operations (Create, Read, Update, Delete), user authentication, method-level authorization, and task state management (Priority, Status, and Due Dates).

## Tech Stack
- **Backend:** Java (25+), Spring Boot (4.1.1), Spring Security, Spring Data JPA, Hibernate
- **Security:** Spring Security (Form-based Authentication, Method-based Authorization via `@EnableMethodSecurity`, BCrypt Password Hashing, CSRF Protection)
- **Database:** MySQL
- **Frontend:** HTML5, Thymeleaf, Custom Responsive CSS (with Light & Dark Mode support)

## Features
- **User Authentication:** Secure registration and login system with encrypted password storage using `BCryptPasswordEncoder`.
- **Method-Based Authorization & User Isolation:** Role-based access control with `@EnableMethodSecurity` and user-scoped data access ensuring users only manage their own tasks.
- **Task Management (CRUD):** Create, view, update, and delete personal todo tasks.
- **Task Categorization & Tracking:**
  - **Status:** `PENDING`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`
  - **Priority:** `LOW`, `MEDIUM`, `HIGH`
  - **Due Dates & Overdue Indicator:** Automated tracking and highlighting of overdue tasks.
- **Consistent UI Design:** Clean, centered card layout with solid button-style status and priority badges.
- **CSRF & Input Validation:** Built-in form validation and CSRF-protected operations.

## Local Setup & Installation

### 1. Database Setup
Create a MySQL database named `todo_db`:
```sql
CREATE DATABASE todo_db;
```

### 2. Configuration
Copy the template configuration file to `application.properties` and add your database credentials:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

### 3. Run the Application
Run the project using Maven Wrapper:
```bash
# On Linux/macOS
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```
Once started, the application will be accessible at: [http://localhost:8081](http://localhost:8081)

