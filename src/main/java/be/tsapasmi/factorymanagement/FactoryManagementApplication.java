package be.tsapasmi.factorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FactoryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FactoryManagementApplication.class, args);
    }

}
