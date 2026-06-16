# Enviro365 Junior Software Developer Assessment

## Author

**Sthembiso Ngwenyama**

## Project Overview

This project was developed as part of the Enviro365 Junior Software Developer Assessment.

The system allows investors to:

* View investor portfolios
* View products linked to portfolios
* Submit withdrawal notices
* Validate withdrawal requests against business rules
* View withdrawal history
* Export withdrawal statements to CSV

The solution consists of:

* Spring Boot REST API Backend
* React Frontend
* H2 In-Memory Database

---

## Technologies Used

### Backend

* Java 21
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Hibernate
* H2 Database
* Maven
* Swagger/OpenAPI
* JUnit 5
* Mockito

### Frontend

* React
* React Router
* Axios
* CSS Modules

---

## Features Implemented

### Investor Portfolio Management

* Retrieve all investors
* View investor portfolio details
* View products linked to portfolios

### Withdrawal Management

* Submit withdrawal notices
* Automatic approval of valid withdrawals
* Withdrawal history table
* CSV export with filtering

### Validation Rules

* Investor must exist
* Portfolio must exist
* Product must exist
* Retirement withdrawals only allowed if investor age is greater than 65 years
* Withdrawal amount may not exceed available balance
* Withdrawal amount may not exceed 90% of product balance

### Advanced Features

* DTO Layer
* Global Exception Handling
* Input Validation
* Unit Tests
* UI Validation
* CSV Export

---

## Project Structure

```txt
src/main/java/com/enviro/assessment/sthembiso
│
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── repository
├── service
└── Application.java
```

---

## API Endpoints

### Investors

#### Get All Investors

```http
GET /api/investors
```

#### Get Investor Portfolio

```http
GET /api/investors/{id}/portfolio
```

---

### Withdrawals

#### Create Withdrawal

```http
POST /api/withdrawals
```

Example Request:

```json
{
  "investorId": 1,
  "portfolioId": 1,
  "productId": 1,
  "amount": 10000,
  "reason": "Emergency withdrawal"
}
```

---

#### Get Withdrawal History

```http
GET /api/withdrawals
```

---

#### Export CSV

```http
GET /api/withdrawals/export
```

Filter by status:

```http
GET /api/withdrawals/export?status=APPROVED
```

---

## Business Rules

### Retirement Product Rule

Investors may only withdraw from Retirement products if their age is greater than 65 years.

### Balance Rule

The withdrawal amount may not exceed the available product balance.

### 90% Rule

The withdrawal amount may not exceed 90% of the product balance.

### Auto Approval

Withdrawals that pass all business rules are automatically approved.

### Balance Deduction

When a withdrawal is approved:

* Product balance is reduced
* Portfolio balance is reduced

---

## Running the Backend

### Prerequisites

* Java 21
* Maven 3.9+
* IntelliJ IDEA (Recommended)

### Clone Repository

```bash
git clone <repository-url>
```

### Navigate to Backend

```bash
cd backend
```

### Run Application

Using Maven:

```bash
mvn spring-boot:run
```

Or using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

### Swagger UI

```txt
http://localhost:8081/swagger-ui.html
```

### H2 Database Console

```txt
http://localhost:8081/h2-console
```

Database URL:

```txt
jdbc:h2:mem:enviro365db
```

Username:

```txt
sa
```

Password:

```txt
(password empty)
```

---

## Running the Frontend

### Navigate to Frontend

```bash
cd frontend
```

### Install Dependencies

```bash
npm install
```

### Start Development Server

```bash
npm run dev
```

Application URL:

```txt
http://localhost:5173
```

---

## Unit Tests

Run all tests:

```bash
mvn test
```

Run specific test:

```bash
mvn test -Dtest=InvestorServiceTest
```

Windows:

```bash
mvnw.cmd test
```

---

## Screenshots

### Dashboard

Add screenshot here.

### Investor Portfolio

Add screenshot here.

### Withdrawal Form

Add screenshot here.

### Withdrawal History

Add screenshot here.

### CSV Export

Add screenshot here.

### Swagger Documentation

Add screenshot here.

---

## AI Usage Disclosure

AI tools were used to assist with:

* Architecture planning
* Code explanations
* Documentation generation
* UI improvements

All generated code was reviewed, understood, modified, tested, and validated before submission.

---

## Assessment Requirements Covered

* Portfolio Dashboard
* Withdrawal Form
* Withdrawal History Table
* CSV Export
* Business Rule Validation
* DTO Layer
* Exception Handling
* Input Validation
* Unit Tests
* REST API Integration
* Documentation

---

## Conclusion

This solution demonstrates a complete full-stack implementation using Spring Boot and React while following clean architecture principles, RESTful API design, validation best practices, and business rule enforcement.
