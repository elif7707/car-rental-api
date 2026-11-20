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

## Error Handling

When a requested resource does not exist, the API returns `404 Not Found` with a JSON body:

```json
{
  "status": 404,
  "message": "Car not found with id: 999"
}
```

This applies to `GET`, `PUT` and `DELETE` requests on `/api/araclar/{id}`, `/api/musteriler/{id}` and `/api/subeler/{id}`.


When a request body is invalid, the API returns `400 Bad Request` with the invalid fields:

```json
{
  "status": 400,
  "errors": {
    "subeAdi": "must not be blank",
    "sehir": "must not be blank"
  }
}
```

Currently this applies to `POST /api/subeler`: `subeAdi`, `sehir` and `adres` are required.


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

### Rentals

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/kiralamalar` | List all rentals |
| POST | `/api/kiralamalar` | Create a rental |

Example request:

```json
{
    "aracId": 3, "musteriId": 3, "alisSubesiId": 3,
      "baslangicTarihi": "2026-12-20", "bitisTarihi": "2026-12-22" 
}
```
Example response:

```json
{
     "aracMarka": "Toyota",
     "aracModel": "Corolla",
     "baslangicTarihi": "2026-12-20",
     "bitisTarihi": "2026-12-22",
     "kiralamaId": 3,
     "musteriAd": "Zeynep",
     "musteriSoyad": "Kaya",
     "subeAdi": "Çankaya Şubesi",
     "toplamUcret": 3600.0
}
```
Rental endpoints return a response DTO (KiralamaCevabi) instead of the entity, so only the needed fields are exposed.

Rules:
- The total price is calculated automatically: number of days (start and end included) × daily price.
- The end date cannot be before the start date → `400`
- The car must be available (`musaitMi = true`) → otherwise `409`
- The car cannot be rented for overlapping dates → `409`
- The car, customer and branch must exist → otherwise `404`

## Running with Docker

Requirements: only Docker (Java, Maven and MySQL run inside containers).

1. Create your `.env` file from the example and set a password:
   ```
   cp .env.example .env
   ```
2. Start the app and the database:
   ```
   docker compose up --build
   ```
3. The API is available at `http://localhost:8080`.

The MySQL data is stored in a Docker volume, so it is kept when containers are removed.

### Sample data

When running with Docker, the database is filled with sample data on startup (`src/main/resources/data.sql`):
3 branches, 5 cars (one of them not available), 3 customers and 2 rentals.

You can try the API right away, for example `GET http://localhost:8080/api/araclar`.

Sample data is only loaded in Docker (`SPRING_SQL_INIT_MODE: always` in `docker-compose.yml`).
When running the app locally (e.g. from the IDE), your own database is not changed.
Restarting the app does not create duplicates. To reset the database to the sample data, use `docker compose down -v`.

To stop: `Ctrl+C` or `docker compose down`. To also delete the data: `docker compose down -v`.

## Roadmap

- [x] CRUD operations for all entities
- [x] Error handling (404 for missing resources)
- [x] Rental feature linking customers, cars and branches
- [x] Running with Docker
- [x] Request validation (branches)
