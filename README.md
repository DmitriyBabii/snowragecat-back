# CRM Microservices with Kafka and Frontend

This project is a modular microservice architecture for a CRM system. It includes backend services written in Java with Spring Boot, Apache Kafka for asynchronous communication, PostgreSQL as a database, and a Next.js frontend. All services are containerized and orchestrated with Docker Compose.

---

## 🧱 Project Structure

```
.
├── app-main       # Main backend service (API)
├── app-mail       # Email notification service
├── app-openai     # Service integrating OpenAI API
├── frontend       # Next.js frontend application
├── common-config  # Shared configuration
├── common-dto     # Shared DTOs
├── docker-compose.yml
├── .env           # Environment variables
```

---

## 🐘 PostgreSQL

- Used as the relational database for `app-main`
- Configured via environment variables in `.env`

## 🔄 Kafka

- Uses Confluent's Kafka (`cp-kafka:7.8.0`) and Zookeeper (`cp-zookeeper:7.8.0`)
- Listeners configured for different access levels:
    - `INTERNAL://kafka:19092` — used by services inside the Docker network
    - `EXTERNAL://localhost:9092` — used from the host machine (e.g. for testing)
    - `DOCKER://host.docker.internal:29092` — optional bridge for specific setups

## 🌐 Networks

All services are connected to the shared custom bridge network:

```yaml
networks:
  crm-network:
    name: crm-network
    driver: bridge
```

Frontend is in a separate `docker-compose.frontend.yml`, but connected via `external` network:

```yaml
networks:
  crm-network:
    external: true
```

---

## 🚀 Running the Project

### 1. Create `.env` file:

```
POSTGRES_DB=crm
POSTGRES_USER=crm_user
POSTGRES_PASSWORD=crm_pass
POSTGRES_PORT=5432

KAFKA_PORT=9092
OPENAI_KEY=your_openai_key
MAIL_USER=your_email@example.com
MAIL_PASSWORD=your_email_password
```

### 2. Run Docker Compose

```bash
# Backend & Kafka
docker compose -f docker-compose.yml up --build

# Frontend (separate terminal)
docker compose -f docker-compose.frontend.yml up --build
```

Frontend will be accessible at [http://localhost:3000](http://localhost:3000)

---

## 🧹 Cleanup

To fully reset volumes and networks:

```bash
docker compose down -v --remove-orphans
docker network rm crm-network
docker volume prune
```

---

## ✅ Services Summary

| Service    | Description                 | Port |
| ---------- | --------------------------- | ---- |
| app-main   | Main Spring Boot API        | 8080 |
| app-openai | Integration with OpenAI API | 8081 |
| app-mail   | Sends notification emails   | 8082 |
| postgres   | PostgreSQL database         | 5432 |
| kafka      | Apache Kafka broker         | 9092 |
| zoo        | Zookeeper for Kafka         | 2181 |
| next-app   | Next.js frontend            | 3000 |

---

## 📌 Notes

- Services connect to Kafka via `kafka:19092`
- `host.docker.internal` is used only for special cases
- All backend containers live on the same Docker network and discover each other via service names

---

Feel free to adjust this README to your future needs or team onboarding! 🙌

