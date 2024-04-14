package com.jsp.HomeServeO.util;

import java.util.ArrayList;
import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class HomeServoConfig {
	
	@Bean
	public Docket getDocket() {
		
		//contact is from springfox.documentation.service.Contact
		Contact contact= new Contact("jspiders", "www.jspiders.com", "jsp@gmail.com");
		List<VendorExtension> extensions = new ArrayList<>();
		ApiInfo apiInfo =  new ApiInfo("HomeServeO", "a Web Application which connects homeworkers with homes in need"
				, "1.0", "terms", contact, "license", "url", extensions);
		
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.jsp.HomeServeO"))
				.build().apiInfo(apiInfo).useDefaultResponseMessages(true)	;	
	}
	

}
