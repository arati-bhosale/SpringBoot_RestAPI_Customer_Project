package com.sunbase.customer.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.sunbase.customer.model" })
@EnableJpaRepositories("com.sunbase.customer.repository")
@ComponentScan(basePackages = { "com.sunbase.customer.controller", "com.sunbase.customer.repository",
		"com.sunbase.customer.service" })
public class SpringBootRestApiCustomerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiCustomerProjectApplication.class, args);
		System.out.println("Hiiiiiiii Arati");

	}

}
