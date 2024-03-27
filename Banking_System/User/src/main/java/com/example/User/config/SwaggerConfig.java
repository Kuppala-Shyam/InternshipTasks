package com.example.User.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuration class for Swagger documentation.
 */

@Configuration
@OpenAPIDefinition

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class SwaggerConfig {
	/**
	 * Customizes the OpenAPI documentation.
	 *
	 * @return OpenAPI object with customized settings.
	 */
	@Bean
	public OpenAPI CustomizedOpenAPI() {
		return new OpenAPI().components(new Components())
				.info(new Info().title("Hello, Welcome to our Banking System").version("1.0")
						.description("APIs for Banking "))
				.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth"));
	}
}
