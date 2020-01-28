package com.ar.pescore.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()
				.apiInfo(
						new ApiInfo("API_NOTIFICACIONES", "API NOTIFICACIONES REST", "1.0", null,
								new Contact("MA", "https://www.mercantilandina.com.ar",
										"nicolas.menna@lamercantil.com.ar"),
								null, null, Collections.emptyList()))
				.securitySchemes(Arrays.asList(authorizationBearer()))
				.securityContexts(Arrays.asList(securityContext()));
		
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(Arrays.asList(new SecurityReference("apiKey",
				new AuthorizationScope[] { new AuthorizationScope("global", "Global access") }))).build();
	}

	private ApiKey authorizationBearer() {
		return new ApiKey("apiKey", "Authorization", "header");
	}

}
