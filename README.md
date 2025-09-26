# 🪙 Crypto-Price-Alert-Service

A microservices-based Spring Boot application that lets users **register/login**, create **crypto price alerts**, and receive **email notifications** when a target price is reached.

---

## ✨ Key Highlights
- **Java 25 + Spring Boot 3.5.6** microservices architecture.
- Secure **JWT authentication** with Spring Security & BCrypt.
- **RESTful communication** between services via `WebClient`.
- **Scheduled tasks** to check prices and trigger alerts.
- Integration with the **CoinGecko API** for live crypto prices.
- **Email notifications** using JavaMailSender.
- Persistent storage with **MySQL + Spring Data JPA**.

---

## 🏗️ Microservices Overview
| Service            | Responsibility                                    | Default Port |
|--------------------|----------------------------------------------------|-------------|
| **user-service**   | User registration, login, and JWT management       | 8081 |
| **price-service**  | Fetch real-time crypto prices from CoinGecko API   | 8082 |
| **alert-service**  | Create & monitor alerts, schedule checks, trigger emails | 8083 |
| **notification-service** | Send email notifications to users             | 8084 |

---

## 🧩 Architecture

User → user-service → alert-service → {price-service, notification-service}

- **alert-service** runs a `@Scheduled` job every few seconds to:
  1. Retrieve current prices from `price-service`.
  2. Validate user info from `user-service`.
  3. Send email via `notification-service` when a threshold is met.

---

🧠 Skills Demonstrated

    Distributed architecture with independent Spring Boot microservices.

    Security best practices: JWT tokens, Spring Security, BCrypt password hashing.

    API integration & reactive clients using WebClient.

    Event-driven scheduling for background tasks.

    Clean code with DTOs, layered services, and Lombok.