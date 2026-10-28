# spring-boot-skaffold2

[![Java CI with Gradle](https://github.com/hendisantika/spring-boot-skaffold2/actions/workflows/gradle.yml/badge.svg)](https://github.com/hendisantika/spring-boot-skaffold2/actions/workflows/gradle.yml)
![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-brightgreen)
![Gradle](https://img.shields.io/badge/Gradle-9.8.2-02303A)

### Tech Stack

| Component       | Version                                    |
|-----------------|--------------------------------------------|
| Java (toolchain)| 25                                         |
| Spring Boot     | 4.1.2 (WebFlux + Actuator)                 |
| Gradle Wrapper  | 9.8.2                                      |
| Jib             | 3.5.4 (base image `amazoncorretto:25-alpine`) |
| Skaffold        | `skaffold/v4beta12`                        |

### Prerequisites

- JDK 25 (Gradle toolchains will use it automatically if installed)
- Docker (to run the image) and a registry account to push to
- Optional: Skaffold and a Kubernetes cluster (e.g. minikube / kind)

### Build & Test

```shell
./gradlew clean build
```

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

### Deploying to Kubernetes with Skaffold

The manifests in `k8s/` expect a ConfigMap named `spring-skaffold-envs` and an image pull secret named `regcred`:

```shell
kubectl create configmap spring-skaffold-envs
kubectl create secret docker-registry regcred \
  --docker-username=<user> --docker-password=<password>
```

Then build and deploy in dev mode (rebuilds on code changes):

```shell
skaffold dev
```

The service is exposed as a `NodePort` on port `8080`.

### CI/CD & Dependency Updates

- **GitHub Actions** (`.github/workflows/gradle.yml`) builds and tests the project with Temurin JDK 25 on every push and
  pull request to `main`, and submits the Gradle dependency graph for Dependabot alerts.
- **Dependabot** (`.github/dependabot.yml`) checks Gradle dependencies and GitHub Actions daily at 05:00 (Asia/Jakarta).
  Updates are grouped (Spring Boot, Gradle plugins, minor/patch, and GitHub Actions), labeled, and use
  conventional commit prefixes (`build(deps)` / `ci(deps)`).
