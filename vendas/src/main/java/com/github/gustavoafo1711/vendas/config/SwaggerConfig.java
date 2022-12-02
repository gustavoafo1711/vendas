package com.github.gustavoafo1711.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	  @Bean 
	  public Docket docket() { 
		  return new Docket(DocumentationType.SWAGGER_2)
				  .useDefaultResponseMessages(false) 
				  .select() 
				  .apis(RequestHandlerSelectors
				 	.basePackage("com.github.gustavoafo1711.vendas.rest.controller"))
				  .paths(PathSelectors.any()) 	
				  .build() 
				  .apiInfo(apiInfo()); }
	  
	  private Contact contact() { 
		  return new Contact("Gustavo Fontenele",
				  			 "https://github.com/gustavoafo1711", 
				  			 "gustavoafo@hotmail.com");
	  }
	  
	  private ApiInfo apiInfo() { 
		  return new ApiInfoBuilder() 
				  .title("Vendas API")
				  .description("API do Projeto de Vendas") 
				  .version("1.0") 
				  .contact(contact())
				  .build(); 
	  }
	 
	
}
