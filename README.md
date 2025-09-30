# Matches API

A Spring Boot REST API for managing **matches** and their **odds**.

---

## 🚀 Getting Started

### 1. Database Setup (PostgreSQL with Docker)

Start a PostgreSQL container:

```bash
docker run -p 5432:5432 \
  -e POSTGRES_PASSWORD=accepted \
  -e POSTGRES_USER=accepted \
  -e POSTGRES_DB=accepted \
  postgres:latest
```

### 2. Schema Initialization
Connect to the DB and run:

```sql
-- Sequences
CREATE SEQUENCE match_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE match_odds_id_seq START WITH 1 INCREMENT BY 1;

-- Match table
CREATE TABLE match (
    id BIGINT PRIMARY KEY DEFAULT nextval('match_id_seq'),
    description VARCHAR(255) NOT NULL,
    match_date DATE NOT NULL,
    match_time TIME NOT NULL,
    team_a VARCHAR(100) NOT NULL,
    team_b VARCHAR(100) NOT NULL,
    sport SMALLINT NOT NULL
);

-- MatchOdds table
CREATE TABLE match_odds (
    id BIGINT PRIMARY KEY DEFAULT nextval('match_odds_id_seq'),
    match_id BIGINT NOT NULL REFERENCES match(id) ON DELETE CASCADE,
    specifier VARCHAR(10) NOT NULL,
    odd NUMERIC(5,2) NOT NULL
);
```

### 3. Build & Run
Build the project with Maven:

```bash
./mvnw clean package
```

Run the application:

```bash
./mvnw spring-boot:run
```

The app will start at http://localhost:8080.

### 4. API Documentation (Swagger UI)
Swagger UI is enabled via springdoc-openapi.

Swagger UI: http://localhost:8080/swagger-ui/index.html
