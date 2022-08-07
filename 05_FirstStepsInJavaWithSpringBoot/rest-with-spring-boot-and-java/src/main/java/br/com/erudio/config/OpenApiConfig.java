package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig  {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
				.title("API REST with Spring Boot and Java 18")
				.version("v1")
				.description("REST API using Spring Boot 2.7.2 and Java 18")
				.termsOfService("https://pub.erudio.com.br/meus-cursos")
				.license(
						new License()
							.name("Apache 2.0")
							.url("https://pub.erudio.com.br/meus-cursos")
						)
				);
	}


}
