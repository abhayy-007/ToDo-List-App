# TaskFlow - ToDo List Application

TaskFlow is a simple and clean Spring Boot web application designed to manage daily tasks. It provides complete CRUD operations (Create, Read, Update, Delete) and manages task states like Priority, Status, and Due Dates.

## Tech Stack
- **Backend:** Java (25+), Spring Boot (4.1.0), Spring Data JPA, Hibernate
- **Database:** MySQL
- **Frontend:** HTML5, Thymeleaf, Custom Vanilla CSS

## Features
- **Task Management:** Create, view, edit, and delete todo tasks.
- **Detailed Attributes:** Add descriptions, specify due dates, categorize status (`PENDING`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`), and prioritize (`LOW`, `MEDIUM`, `HIGH`).
- **Overdue Indicator:** Highlights tasks whose due dates have already passed.
- **Clean Table Layout:** A clean, standard user interface with badges for clear visibility.
- **AI Help Notice:** Frontend CSS design and Thymeleaf integration refined with the assistance of AI.

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
Once started, the application will be accessible at: [http://localhost:8080](http://localhost:8080)
