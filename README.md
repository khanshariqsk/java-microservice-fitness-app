# 🏋️ Fitness App Microservices

A production-style microservices architecture for a fitness tracking
application built using:

-   Spring Boot 3 and 4
-   Spring Cloud
-   Spring Security (OAuth2 Resource Server)
-   Keycloak (Authentication & Authorization)
-   Kafka (Event-driven communication)
-   MongoDB (AI Service)
-   PostgreSQL (User & Activity Services)
-   Eureka (Service Discovery)
-   Spring Cloud Gateway (API Gateway)
-   Docker

------------------------------------------------------------------------

# 🏗️ Architecture Overview

Frontend (Vite / React) ↓ Spring Cloud Gateway (7777) ↓
------------------------------------- \| Microservices \|
------------------------------------- User Service (PostgreSQL) Activity
Service (PostgreSQL) AI Service (MongoDB + Gemini AI)
------------------------------------- ↓ Keycloak (Auth Server) ↓ Eureka
(Service Discovery)

------------------------------------------------------------------------

# 📁 Project Structure

FitnessAppMicroservices │ ├── activityservice ├── aiservice ├──
configserver ├── eureka ├── gateway ├── userservice ├── frontend └──
.gitignore

------------------------------------------------------------------------

# 🔐 Authentication & Security

-   Authentication handled by Keycloak
-   JWT tokens validated in every microservice
-   Token propagation between services using Bearer forwarding
-   Each service acts as an independent security boundary

------------------------------------------------------------------------

# 🚀 How To Run The System

## 1️⃣ Start Keycloak

docker run -p 8080:8080\
-e KC_BOOTSTRAP_ADMIN_USERNAME=admin\
-e KC_BOOTSTRAP_ADMIN_PASSWORD=admin\
quay.io/keycloak/keycloak:26.5.3 start-dev

Create realm: fitness-app

------------------------------------------------------------------------

## 2️⃣ Start Eureka

cd eureka mvn spring-boot:run

------------------------------------------------------------------------

## 3️⃣ Start Config Server

cd configserver mvn spring-boot:run

------------------------------------------------------------------------

## 4️⃣ Start Microservices

cd userservice mvn spring-boot:run

cd activityservice mvn spring-boot:run

cd aiservice mvn spring-boot:run

------------------------------------------------------------------------

## 5️⃣ Start Gateway

cd gateway mvn spring-boot:run

Gateway runs on: http://localhost:7777

------------------------------------------------------------------------

## 6️⃣ Start Frontend

cd frontend npm install npm run dev

Frontend runs on: http://localhost:5173

------------------------------------------------------------------------

# 🔄 Service Communication

Synchronous: - Gateway → UserService - Gateway → ActivityService -
Gateway → AIService - AIService → ActivityService (JWT forwarded)

Asynchronous: - ActivityService publishes ActivityCreatedEvent -
AIService consumes event via Kafka - AIService generates recommendation
using Gemini AI

------------------------------------------------------------------------

# 🧠 AI Recommendation Flow

1.  User creates activity
2.  Activity stored in PostgreSQL
3.  Kafka event published
4.  AIService consumes event
5.  Gemini AI generates recommendation
6.  Recommendation stored in MongoDB
7.  Gateway aggregates Activity + Recommendation

------------------------------------------------------------------------

# 📦 Technologies Used

Gateway: Spring Cloud Gateway Auth: Keycloak Discovery: Eureka Config:
Spring Cloud Config Messaging: Kafka Database: PostgreSQL, MongoDB AI:
Gemini API Frontend: React + Vite

------------------------------------------------------------------------

# 👨‍💻 Author

Shariq Khan\
Full Stack Developer\
Java • Spring Boot • Microservices • Security • Kafka

------------------------------------------------------------------------

⭐ If you like this project, give it a star!
