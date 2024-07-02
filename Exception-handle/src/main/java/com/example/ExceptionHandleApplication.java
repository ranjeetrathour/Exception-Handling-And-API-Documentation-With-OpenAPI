package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class ExceptionHandleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionHandleApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Order Service API")
						.description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
						.version("v1.0").license(new License()
						.name("Apache 2.0")
						.url("http://springdoc.org")))
				        .externalDocs(new ExternalDocumentation()
						.description("Order Service Documentation")
						.url("https://example.com/docs"));
	}

}
