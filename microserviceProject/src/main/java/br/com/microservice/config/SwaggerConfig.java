package br.com.microservice.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfig {
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("br.com.microservice.controller"))
          .paths(PathSelectors.any())
//          .paths(PathSelectors.ant("/microservice/*"))
          .build()
          .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "CAR API", 
          "API RESTful para Sistema de Usuários de carro.", 
          "API TOS", 
          "Terms of service", 
          new Contact("Saulo de Tarso", "https://www.linkedin.com/in/saulo-de-tarso-b5217932/", "osaulo_tarso@hotmail.com"), 
          "License of API", "API license URL", Collections.emptyList());
    }
}