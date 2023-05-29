package com.defaults.marketplace.productsearch;

import jakarta.persistence.PersistenceContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@PersistenceContext
@EnableDiscoveryClient
public class MsServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsServicesApplication.class, args);
	}
}
