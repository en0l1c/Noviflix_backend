# Noviflix Backend

Noviflix Backend is a movie management API that allows users to manage a movie database. The API supports operations such as retrieving movies, adding movies, deleting movies, and fetching random movies.

## Features

- **Retrieve Movies**:
    - Retrieve all movies stored in the database.
    - Retrieve a movie by its unique ID (UUID).
- **Add Movies**:
    - Add a new movie to the database (the ID is auto-generated).
- **Delete Movies**:
    - Delete a movie by its unique ID.
- **Random Movies**:
    - Retrieve a random movie from the database.

## API Documentation

The API is documented using Swagger. You can access the API documentation at the following URL:

[Noviflix API Documentation](https://konstantinosgialantzis.github.io/portfolio/noviflix/swagger-ui)

## Environment Configuration

To ensure proper functionality, the following environment variables must be set:

### System Environment Variables

#### Variables for `application-prod.properties`:

| Variable Name       | Description                                |
|---------------------|--------------------------------------------|
| `DATABASE_HOST`     | Hostname for the PostgreSQL database.      |
| `DATABASE_SCHEMA`   | Schema name for the PostgreSQL database.   |
| `DATABASE_USERNAME` | Username for the PostgreSQL database.      |
| `DATABASE_PASSWORD` | Password for the PostgreSQL database.      |

### Example Environment Variable Setup

Below is an example of the environment variables configured on Render.com:

```plaintext
DATABASE_HOST=your-database-host
DATABASE_SCHEMA=noviflixdb
DATABASE_USERNAME=your-username
DATABASE_PASSWORD=your-password
```

### Production-Specific Configuration

- **Server Address and Port**:
    - `server.address=0.0.0.0`
    - `server.port=8080`

These settings are required for deployment on platforms like Render.com.

- **Server URL**:
    - `server.url` is used to distinguish between local and production environments:
        - In `application.properties`: `http://localhost:8080/api`
        - In `application-prod.properties`: `https://noviflix-backend.onrender.com/api`

The value of `server.url` is loaded into the application using the `@Value` annotation.

## Database Configuration

- **Local Development**:
    - The default configuration in `application.properties` uses an H2 database.
    - Example:
      ```
      spring.datasource.url=jdbc:h2:file:./testdb
      spring.datasource.driver-class-name=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=sa
      spring.jpa.hibernate.ddl-auto=update
      ```

- **Production**:
    - The configuration in `application-prod.properties` uses PostgreSQL.
    - Example:
      ```
      spring.datasource.url=jdbc:postgresql://${DATABASE_HOST}:5432/${DATABASE_SCHEMA}
      spring.datasource.username=${DATABASE_USERNAME}
      spring.datasource.password=${DATABASE_PASSWORD}
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
      ```

## Docker Support

- The Dockerfile is configured to use the `application-prod.properties` file by default when building the Docker image.
- You can build and run the Docker container using the following commands:

```bash
docker build -t noviflix-backend .
docker run -p 8080:8080 noviflix-backend
```

## Error Handling

The application uses a centralized error handling mechanism:

- **ApiError Class**:
    - Provides detailed information about errors in the following format:
      ```json
      {
        "timestamp": "2025-01-27T12:47:43.028Z",
        "status": 0,
        "error": "string",
        "message": "string",
        "path": "string",
        "methodName": "string"
      }
      ```

- **GlobalExceptionHandler**:
    - Handles exceptions like validation errors, resource not found, conflicts, no content, and more.
    - Custom exceptions:
        - `ResourceNotFoundException`: Thrown when a resource is not found.
        - `NoContentException`: Used to return HTTP 204 responses.
        - `ConflictException`: Used to return HTTP 409 responses.

## CORS Configuration

- CORS is configured to allow requests from the following origins:
    - `serverUrl`
    - `http://localhost:4200/` (for Angular development)
    - `https://konstantinosgialantzis.github.io/`

## Decoupled Architecture

- The application uses a `MovieDTO` class to separate the API layer from the database layer, avoiding direct coupling with the `Movie` entity.

## Deployment Notes

- The production backend is hosted at `https://noviflix-backend.onrender.com/api`.
- Internal database connections in production use the internal Render.com URL for the PostgreSQL database.

## Swagger Annotations

Custom Swagger annotations are used to improve the appearance of status codes and responses in the API documentation.

## Releases

The latest service can be downloaded from the Releases section of the repository.

## Screenshots

Below are example screenshots of the configuration and API responses:

### Environment Variables Configuration on Render.com

![Render Environment Variables](<img src="screenshots/render-environment-variables.png" alt="Screenshot 1" width="200"/>)

### Swagger Documentation Example

![Swagger UI Example](<img src="screenshots/swagger.png" alt="Screenshot 1" width="200"/>)
![Swagger UI Example](<img src="screenshots/localhost-swagger.png" alt="Screenshot 1" width="200"/>)

## Conclusion

Noviflix Backend is a robust and scalable API for managing movies. With support for Docker, PostgreSQL, and detailed error handling, it is designed for both development and production environments.

