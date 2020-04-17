package com.nemytow.recycleCo.RecycleCo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.*"})
public class RecycleCoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecycleCoApplication.class, args);
	}

}
