package com.github.gustavoafo1711.vendas.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
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
				  .securityContexts(Arrays.asList(securityContext()))
				  .securitySchemes(Arrays.asList(apiKey()))
				  .apiInfo(apiInfo()); 
		  }
	  
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
	  
	  public ApiKey apiKey() {
		  return new ApiKey("JWT", "Authorization", "header");
	  }
	  
	  private SecurityContext securityContext() {
		  return SecurityContext.builder()
				  					.securityReferences(defaultAuth())
				  					.forPaths(PathSelectors.any())
				  					.build();
	  }
	  
	  private List<SecurityReference> defaultAuth(){
		  AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		  AuthorizationScope[] scopes = new AuthorizationScope[1];
		  scopes[0] = authorizationScope;
		  SecurityReference securityReference = new SecurityReference("JWT", scopes);
		  List<SecurityReference> auths = new ArrayList<>();
		  auths.add(securityReference);
		  return auths;
	  }
	 
	
}
