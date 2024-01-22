package com.ucoltis.jv2f2.hgarcia.bookshop.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
   
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	} 
}
