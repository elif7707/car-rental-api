# Car Rental API

A REST API for managing a car rental business: cars, customers, branches and rentals.
Built with Java 21, Spring Boot and MySQL.

> **Status:** Work in progress. This is a learning project that is being developed step by step.

## Tech Stack

- **Java 21**
- **Spring Boot** (Web MVC, Data JPA)
- **MySQL**
- **Lombok**
- **Maven**

## Domain Model

| Entity | Table | Description |
|---|---|---|
| `Arac` | `araclar` | Cars: brand, model, daily price, year, segment, availability |
| `Musteri` | `musteriler` | Customers: name, surname, age, driving license year |
| `Sube` | `sube` | Branches: name, city, address |
| `Kiralama` | `kiralamalar` | Rentals (in progress) |

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/araclar` | List all cars |

More endpoints will be added as the project grows.

## Getting Started

### Prerequisites

- JDK 21
- MySQL 8 or later

### Setup

1. Clone the repository:

```bash
   git clone https://github.com/elif7707/car-rental-api.git
   cd car-rental-api
```

2. Create `src/main/resources/application-local.properties` with your MySQL password:

```properties
   spring.datasource.password=your_password
```

This file is listed in `.gitignore`, so your password is never committed.

3. Run the application:

```bash
   ./mvnw spring-boot:run
```

The `arackiralama` database is created automatically on first run.

## Roadmap

- [ ] CRUD operations for all entities
- [ ] Rental feature linking customers, cars and branches
- [ ] Docker support