package com.dock.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author luiz henrique
 *
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig  {
    /**
     * Bean de configuracao do SWAGGER
     * @return
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dock.desafio.ws"))
                .build()                
                .apiInfo(metaData())
                ;
    }
    /**
     * 
     * @return
     */
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("DOCK - Desafio")
                .description("\"Spring Boot REST API para desafio DOCK\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Luiz Henrique Ribeiro", "https://dock.tech/", "henrickzero@gmail.com"))
                .build();
    }
    
 

}