package com.eazybytes.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Cards microservice Rest Api documentation",
                description = "Eazy Bank cards microservice rest api documentation",
                version = "v1",
                contact = @Contact(
                        name= "Vikas Kumar",
                        email="vikaskumar95951@gmail.com",
                        url="https://www.mygithub.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.mygithub.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Eazybank cards microservice Rest Api Documentation",
                url = "https://www.mygithub.com/swagger-ui.html"
        )
)
public class CardsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }
}