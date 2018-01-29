package com.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ms.configs", "com.ms.controllers", "com.ms.services","com.ms.models", "com.ms.utils"})
public class EcomApplication{

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}
}
