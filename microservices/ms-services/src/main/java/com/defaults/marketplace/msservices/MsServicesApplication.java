package com.defaults.marketplace.msservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsServicesApplication.class, args);
	}

}
