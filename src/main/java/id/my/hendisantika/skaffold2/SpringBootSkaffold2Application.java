package id.my.hendisantika.skaffold2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@SpringBootApplication
public class SpringBootSkaffold2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSkaffold2Application.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "Spring Boot JIB - Gradle " + LocalDateTime.now();
    }
}
