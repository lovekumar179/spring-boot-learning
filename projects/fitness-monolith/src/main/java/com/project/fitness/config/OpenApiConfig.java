package com.project.fitness.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI customAPI() {
    return new OpenAPI().info(
            new Info()
                    .title("Fitness Tracking API")
                    .version("v1.0")
                    .description("Production Grade Fitness Application API's")
                    .contact(new Contact()
                            .name("Love Kumar")
                            .email("lovekumar4782@gmail.com")
                            .url("https://github.com/lovekumar179/spring-boot-learning/tree/main/projects/fitness-monolith")
                    )
                    .license(new License()
                            .name("Apache 2.0")
                            .url("http://apache.com")
                    )
    );
  }
}
