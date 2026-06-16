# System Architecture

## Overview

The Enviro365 Junior Software Developer Assessment solution follows a layered architecture that separates responsibilities into distinct components.

The application consists of:

* React Frontend
* Spring Boot REST API Backend
* H2 In-Memory Database

## Architecture Diagram

```txt
+----------------------+
|    React Frontend    |
|                      |
| Dashboard            |
| Portfolio Page       |
| Withdrawals Page     |
+----------+-----------+
           |
           | HTTP / REST API
           |
+----------v-----------+
|  Spring Boot Backend |
|                      |
| Controllers          |
| Services             |
| DTOs                 |
| Validation           |
| Exception Handling   |
+----------+-----------+
           |
           | JPA / Hibernate
           |
+----------v-----------+
|      H2 Database     |
|                      |
| Investors            |
| Portfolios           |
| Products             |
| Withdrawals          |
+----------------------+
```

---

## Backend Layers

### Controller Layer

Responsible for handling HTTP requests and responses.

Controllers:

* InvestorController
* WithdrawalController

Endpoints:

```http
GET /api/investors
GET /api/investors/{id}/portfolio
POST /api/withdrawals
GET /api/withdrawals
GET /api/withdrawals/export
```

---

### Service Layer

Contains business logic.

Services:

* InvestorService
* WithdrawalService

Responsibilities:

* Portfolio retrieval
* Withdrawal processing
* Validation
* CSV generation
* Business rule enforcement

---

### Repository Layer

Provides access to database operations through Spring Data JPA.

Repositories:

* InvestorRepository
* PortfolioRepository
* ProductRepository
* WithdrawalRepository

---

### DTO Layer

Used to transfer data between backend and frontend.

DTOs:

* InvestorDto
* InvestorPortfolioDto
* PortfolioDto
* ProductDto
* WithdrawalRequestDto
* WithdrawalResponseDto

---

### Database Layer

Uses an H2 in-memory database.

Advantages:

* Lightweight
* Fast startup
* No installation required
* Ideal for assessment projects

---

## Frontend Architecture

### Dashboard

Displays all investors.

### Investor Portfolio Page

Displays:

* Investor details
* Portfolio balances
* Product information

### Withdrawals Page

Displays:

* Withdrawal form
* Validation messages
* Withdrawal history
* CSV export functionality

---

## Security Considerations

For this assessment:

* No authentication required
* Validation performed at service level
* Exception handling implemented globally

Future improvements:

* JWT Authentication
* Role-based access control
* Audit logging

---

## Design Principles

The solution follows:

* Separation of Concerns
* Single Responsibility Principle
* Layered Architecture
* DTO Pattern
* RESTful API Design
* Clean Code Principles

---

## Conclusion

The architecture provides a scalable, maintainable, and testable foundation while satisfying all requirements of the Enviro365 Junior Software Developer Assessment.
