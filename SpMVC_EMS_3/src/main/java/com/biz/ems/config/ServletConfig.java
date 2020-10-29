package com.biz.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.biz.ems.controller","com.biz.ems.service"} )
public class ServletConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("/files/**")
			.addResourceLocations(
						new String[]{"/static/files/", 
						 "file:///Users/callor/Documents/workspace/upload/", 
						 "file:///C:/bizwork/workspace/upload/" 
						 });
		WebMvcConfigurer.super.addResourceHandlers(registry);
	
	}

	@Bean
	public InternalResourceViewResolver resolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
		
	}
	
	
	
}
