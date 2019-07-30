package com.example.patserfelices.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String AUTH_SERVER = "localhost:8080";

    public SwaggerConfig() {
    }

    @Bean
    public Docket api() {
        return (new Docket(DocumentationType.SWAGGER_2)).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().securitySchemes(Arrays.asList(this.securityScheme()));
    }

    private SecurityScheme securityScheme() {
        return new ApiKey("Bearer", "Authorization", "header");
    }
}
