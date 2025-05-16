# Dynamic Percentage API

RESTful APIs desarrollados con Spring Boot 3 y Java 21 además guarda un historial de logs en PostgreSQL, se ejecuta con Docker Compose.

## 🚀 Características

- Endpoint de cálculo con porcentaje externo (simulado y cacheado por 30 min)
- Historial de llamadas (guardado en PostgreSQL)
- Caché con fallback
- Swagger UI para documentación
- Tests unitarios
- Docker + Docker Compose para despliegue local

## 📦 Requisitos

- Java 21
- Maven
- Docker + Docker Compose

## 🐳 Ejecución con Docker

```bash
# Empaquetar la app
mvn clean package

# Levantar todo con docker-compose
docker-compose up --build
```
### Este proyecto tambien esta disponible como imagen en Docker Hub:

👉 https://hub.docker.com/r/andrefernandoec/percentage-api

### 📥 Descargar y ejecutar localmente

Asegúrate de tener [Docker instalado](https://docs.docker.com/get-docker/), luego ejecuta:

```bash
docker pull andrefernandoec/percentage-api:latest
docker run -p 8080:8080 andrefernandoec/percentage-api
```

## 📘 API Documentation

La documentación Swagger UI está disponible en:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


## 👨‍💻 Autor
[![LinkedIn](https://img.shields.io/badge/LinkedIn-André%20Llumiquinga-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/andre-llc/)
[![GitHub](https://img.shields.io/badge/GitHub-André%20Llumiquinga-black?style=flat&logo=github)](https://github.com/andrefernandoec2608)