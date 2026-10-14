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
| GET | `/api/araclar` | List all cars |
| GET | `/api/araclar/{id}` | Get a car by id |
| POST | `/api/araclar` | Add a new car |
| PUT | `/api/araclar/{id}` | Update a car |
| DELETE | `/api/araclar/{id}` | Delete a car |
| GET | `/api/musteriler` | List all customers |
| GET | `/api/musteriler/{id}` | Get a customer by id |
| POST | `/api/musteriler` | Add a new customer |
| PUT | `/api/musteriler/{id}` | Update a customer |
| DELETE | `/api/musteriler/{id}` | Delete a customer |
| GET | `/api/subeler` | List all branches |
| GET | `/api/subeler/{id}` | Get a branch by id |
| POST | `/api/subeler` | Add a new branch |
| PUT | `/api/subeler/{id}` | Update a branch |
| DELETE | `/api/subeler/{id}` | Delete a branch |

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

- [x] CRUD operations for all entities
- [ ] Rental feature linking customers, cars and branches
- [ ] Docker support