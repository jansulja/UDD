package com.ftn.jan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class SuccessHandlerConfig {

	@Primary
	@Bean
	public MappingJackson2HttpMessageConverter primaryMessageConverter(){
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		return converter;
	}
	
}
