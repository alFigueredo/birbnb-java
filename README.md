# 🏡 Birbnb

## Birbnb

**Birbnb** es una aplicación web que permite a usuarios buscar alojamientos, realizar y gestionar reservas, y recibir notificaciones.

## Tecnologías Utilizadas

### Backend

- **Java 21**
- **Spring Boot**
- **Spring Security**
- **OAuth2 Client**
- **JPA/Hibernate**
- **Maven**
- **Swagger**

### Frontend

- **React**
- **Typescript**
- **Axios**
- **Vite**

### Base de Datos

- **PostgreSQL**

### General

- **Github**
- **Docker (opcional)**

### Despliegue final

## Estructura del Proyecto

```
birbnb-java/
│
├── birbnb-backend/
│   ├── src/main/java/com/panki/birbnb_backend/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── exception/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── security/
│   │   ├── service/
│   │   ├── specs/
│   │   └── BirbnbBackendApplication.java
│   │   └── DataLoader.java
│   └── .gitignore
│   └── Dockerfile
│   └── mvnw
│   └── pom.xml
│   └── rest.http
│   └── .env.example
│
├── birbnb-frontend/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── context/
│   │   ├── pages/
│   │   ├── router/
│   │   ├── services/
│   │   ├── styles/
│   │   ├── types/
│   │   ├── App.tsx
│   │   ├── main.tsx
│   └── public/
│   └── .gitignore
│   └── Dockerfile
│   └── eslint.config.js
│   └── index.html
│   └── nginx.conf
│   └── tsconfig.app.json
│   └── tsconfig.json
│   └── tsconfig.node.json
│   └── vite.config.js
│   └── package.json
│   └── .env.example
│
├── docker-compose.yml
├── docker-compose-db.yml
└── README.md
```

## 🛠️ Requisitos

- Java >= 21
- Maven >= 3.9
- Node.js >= 22
- PostgreSQL >= 16
- Docker y Docker Compose (una alternativa si no se cuenta con PostgreSQL)

## Scripts

Todos estos scripts pueden ejecutarse de forma local con solo tener instaladas las dependencias necesarias, lo que sí se requiere en la mayoría de los casos es una instancia de MongoDB con la cual conectarse. De no contar con una instancia, aún así existe otra alternativa (véase Docker).

### Backend

- **export $(grep -v '^#' .env | xargs)**: exporta las variables de entorno en .env.
- **./mvnw spring-boot:run**

#### Variables de entorno del backend

Las variables de entorno se encuentran listadas en el .env.example correspondiente. Se deben escribir en un archivo .env local:

### Frontend

- **npm run dev**
- **npm run build**
- **npm run start**
- **npm run lint**

#### Variables de entorno del frontend

Las variables de entorno se encuentran listadas en el .env.example correspondiente. Se deben escribir en un archivo .env local:

## Docker

Si bien no es condición necesaria contar con Docker, sí se puede aprovechar esta herramienta para ejecutar la aplicacion entera sin la necesidad de una instancia externa de MongoDB. Se cuentan con 2 archivos docker-compose\*.yml para desplegar la aplicación en distintos contenedores:

- **docker-compose.yml**: Despliega una instancia de Java para el backend, una instancia de Node sobre Nginx para el frontend, y una instancia de Postgres con un volumen de datos.
- **docker-compose-db.yml**: Despliega una instancia de postgres que se puede usar como base de datos relacional. Se requiere un .env con las variables correspondientes.
