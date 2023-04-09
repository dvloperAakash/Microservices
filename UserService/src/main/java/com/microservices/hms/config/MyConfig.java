package com.microservices.hms.config;

import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Configuration
public class MyConfig {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
