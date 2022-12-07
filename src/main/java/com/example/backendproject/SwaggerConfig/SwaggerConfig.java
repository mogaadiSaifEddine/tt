package com.example.backendproject.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)

                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.regex("/add"))
                .build()
                .apiInfo(apiInfo());

    }
    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()

                .title("Tech Interface - Spring Boot Swagger Configuration")
                .description("\"Swagger configuration for application \"")
                .version("1.1.0")
                .license("Apache 9.0.64")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();

    }
}
