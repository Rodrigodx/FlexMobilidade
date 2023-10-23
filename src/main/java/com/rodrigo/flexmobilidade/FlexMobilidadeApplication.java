package com.rodrigo.flexmobilidade;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flex Mobilidade", version = "1", description = "API desenvolvida para locação de veículos"))
public class FlexMobilidadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexMobilidadeApplication.class, args);
	}

}
