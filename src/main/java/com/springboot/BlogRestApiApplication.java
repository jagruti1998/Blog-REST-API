package com.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App REST APIs",
				description = "Spring Boot Blog App REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "jaggu",
						email = "jagrutig@gmail.com",
						url = "https://www.learningspringboot.net"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.learningspringboot.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog App Documentation",
				url = "https://github.com/jagruti1998/Blog-REST-API"
		)
)
public class BlogRestApiApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}
}
