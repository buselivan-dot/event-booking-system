# 🎟️ EventBooking

A backend REST API for booking tickets to events, built with Java and Spring Boot. I built this to deepen my understanding of backend architecture, JWT authentication, and real-world business logic - things like seat availability, ownership checks, and role-based access control.

## What it does

I wanted to build something with actual state management and rules, not just a database wrapper.

- **Auth system:** Users register and receive a JWT token immediately. Every protected request requires a valid token in the Authorization header.
- **Role-based access:** Regular users can browse events and book tickets. Only admins can create or cancel events.
- **Booking logic:** When a ticket is booked, available seats decrement atomically. The system prevents double-booking, booking cancelled events, and cancelling someone else's booking.
- **Persistent storage:** All data is stored in PostgreSQL and persists between restarts.

## Tech Stack

- **Backend:** Java 25, Spring Boot 4, Spring Security
- **Auth:** JWT (jjwt 0.12.6) with BCrypt password hashing
- **Database:** PostgreSQL with Spring Data JPA / Hibernate
- **Mapping:** MapStruct for Entity ↔ DTO conversion
- **Validation:** Jakarta Bean Validation (@Valid, @NotBlank, @Email)
- **Build tool:** Maven

---

## How to run it locally

### Prerequisites
- Java 21+
- PostgreSQL running locally
- Maven

### 1. Set up the database
```sql
CREATE DATABASE eventbooking;
CREATE USER ivan WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE eventbooking TO ivan;
```

### 2. Configure `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/eventbooking
spring.datasource.username=ivan
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your-base64-encoded-secret
jwt.expiration=86400000
```

### 3. Run the app
Open the project in IntelliJ and run the main application class, or:
```bash
mvn spring-boot:run
```
The API starts on `http://localhost:8080`.

---

## API Routes

### Auth (public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register and receive a JWT token |
| POST | `/auth/login` | Login and receive a JWT token |

### Events
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| GET | `/api/events` | Public | Get all events |
| GET | `/api/events/{id}` | Public | Get event by ID |
| POST | `/api/events` | Admin only | Create a new event |
| PUT | `/api/events/{id}/cancel` | Admin only | Cancel an event |

### Bookings
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/bookings` | User | Book a ticket |
| DELETE | `/api/bookings/{id}` | User | Cancel a booking |
| GET | `/api/bookings/user/{id}` | User | Get all bookings for a user |

### Users
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| GET | `/api/users/{id}` | User | Get user by ID |
| DELETE | `/api/users/{id}` | User | Delete account |
| PUT | `/api/users/{id}/role` | Admin | Set user role |

---

## Business Rules

- You can't book an event with 0 available seats
- You can't book the same event twice
- You can't cancel someone else's booking
- Only admins can create or cancel events
- Cancelled events can't be booked

---

## Project Structure

```
src/main/java/com/system/eventBooking/
├── config/          # Security configuration
├── controllers/     # REST controllers
├── dto/             # Request and response DTOs
├── entities/        # JPA entities
├── enums/           # Role, BookingStatus, EventStatus
├── exceptions/      # Global exception handler
├── mappers/         # MapStruct mappers
├── repositories/    # Spring Data JPA repositories
├── security/        # JWT filter
└── services/        # Business logic
```
