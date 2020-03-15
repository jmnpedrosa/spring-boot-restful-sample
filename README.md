# spring-boot-restful-sample

Example of a Spring Boot application that serves RESTful operations.

## Build Instructions:
### Perform a Maven installation:

 1. If you have Maven accessible from the command line, just run the following command from the root directory of tye project:
```
mvn clean install
```

 2. From STS or similar, select "_Run As..._" -> "Maven install".  
The **.jar** archive will be installed in the `target` directory of the project.

## How to run:
Navigate to the location of the directory a run the following command:

```
java -jar spring-boot-restful-sample-0.0.2-SNAPSHOT.jar
```
The application will be acessible at http://localhost:8080.

## Checking the API:
This application uses the [Springfox](https://springfox.github.io/springfox/) library to provide a [Swagger](https://swagger.io/) documentation that can be accessed at http://localhost:8080/swagger-ui.html.

## Testing with Postman
You can also download and use [Postman](https://www.getpostman.com/) to test the API. Just import the [Postman Collection](spring-boot-restful-sample.postman_collection.json) at the root of the project.
