package com.eazybytes.accounts;

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
                title = "Accounts microservice rest api documentation",
                description = "EazyBank accounts microservice rest api documentation",
                version = "v1",
                contact = @Contact(
                        name = "Vikas Kumar",
                        email = "vikaskumar95951@gmail.com",
                        url = "https://mygithub.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://mygithub.com"

                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EazyBank accounts microservice Rest Api documentation",
                url = "https://mygithub.com/swagger-ui.html"
        )
)
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class);
    }
}
