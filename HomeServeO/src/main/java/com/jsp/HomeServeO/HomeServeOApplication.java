package com.jsp.HomeServeO;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeServeOApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeServeOApplication.class, args);
	}
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

// inted this use @crossorigin() 
//	public class WebConfig implements WebMvcConfigurer{
//		public void addCorsMappings(CorsRegistry registry) {
//			registry.addMapping("/**")
//			.allowedOrigins("*")
//			.allowedMethods("GET","POST","PUT","DELETE");
//		}
//	}
}
