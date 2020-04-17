package com.nemytow.recycleCo.RecycleCo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.*"})
public class RecycleCoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecycleCoApplication.class, args);
	}

}
