package com.heimdallauth.utils.heimdallorchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HeimdallOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeimdallOrchestratorApplication.class, args);
	}

}
