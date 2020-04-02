# MoviesDB

MoviesDB is an resutful Spring boot application to perform CRUD operations to retrieve informations about movies like title,description,image path,video path and so on. It has pagination support by using Pageable interface.It also supports fetching latest movie details from https://www.themoviedb.org/.

## Getting Started
Clone the project to your local repository.

### Prerequisites
Make sure you configure your postgreSQL by using the below configuration. 

```
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=password
spring.datasource.url=jdbc:postgresql://localhost:5432/moviesdb
```

Create a database named moviesdb using the following SQL query.

```
CREATE DATABASE moviesdb;
```
## Deployment

After cloning the project, inside the project folder run the below command to run the executable JAR file.

```
java -jar movies-db-0.0.1-SNAPSHOT.jar
```

Or you can run the `mvn install` command and run using the MoviesDbApplication or using the `mvn spring-boot:run` command.

## Screen URL

```
http://localhost:8090/swagger-ui.html
```

## Screen UI

![MoviesDB-UI](https://user-images.githubusercontent.com/16051087/78248252-e7109300-7509-11ea-98fc-14d02b4b14d2.png)

## Built with

```
* Java 8
* Maven
* Spring Boot
* PostgreSQL
* Swagger UI
* Flyway
* Lombok
* Mapstruct
* Jackson
```
