package com.example.plannerREST.utils;


import com.google.common.base.Predicates;
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
public class Swagger2UiConfiguration {

    @Bean
    public Docket activitiesApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.or(
                        PathSelectors.regex("/companies/.*"),
                        PathSelectors.regex("/activities/.*"),
                        PathSelectors.regex("/permissions/.*"),
                        PathSelectors.regex("/contacts/.*"),
                        PathSelectors.regex("/users/.*")
                        )
                )
                .build().apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Planner Rest Api Documentation")
                .description("Planner Rest Api Documentation")
                .build();
    }
}
