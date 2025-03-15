# spring-boot-skaffold2

Dockerizing Java/Spring Boot Applications Using Jib :A Guide for Maven and Gradle

To dockerize a Java or Spring Boot application using Jib, you can follow these steps. Jib is a tool from Google that
allows you to build Docker and OCI images for your Java applications without needing a Dockerfile. It integrates
seamlessly with Maven and Gradle.

### Benefits of Using Jib

1. No Dockerfile Required: Jib automatically creates a Docker image without needing a Dockerfile.
2. Efficient Layering: Jib optimizes the layers in your Docker image for faster builds and smaller images.
3. Integration: Seamless integration with Maven and Gradle.

Let us start and build docker image and run the container using both MAVEN and GRADLE

### For Gradle Projects

Run the following command:

```shell
./gradlew clean jib
```

This will build the Docker image and push it to the specified Docker registry.

### Running Your Dockerized Application

After the image is pushed to your Docker registry, you can run it using Docker:

```shell
docker run -p 8080:8080 hendisantika/spring-skaffold:v1.0.1
```

This will start your Spring Boot application inside a Docker container, exposing it on port 8080.

