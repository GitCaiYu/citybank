package com.tansun.citybank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.tansun" })
@MapperScan("com.tansun.citybank.dao")
public class CitybankApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitybankApplication.class, args);
	}
}
