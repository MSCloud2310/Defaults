package com.defaults.marketplace.marketplaceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MarketplaceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceServerApplication.class, args);
	}

}
