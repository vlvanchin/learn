package com.my.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.my.common.Greeting;

@Configuration
public class AppConfig {

	@Bean(name="greeting")
	public Greeting greeting() {
		return new Greeting();
	}
	
}
