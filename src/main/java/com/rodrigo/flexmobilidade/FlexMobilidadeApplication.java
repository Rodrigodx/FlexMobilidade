package com.rodrigo.flexmobilidade;

import com.rodrigo.flexmobilidade.model.user.User;
import com.rodrigo.flexmobilidade.model.user.UserRole;
import com.rodrigo.flexmobilidade.repositories.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flex Mobilidade", version = "1", description = "API desenvolvida para locação de veículos"))
public class FlexMobilidadeApplication implements CommandLineRunner {


	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(FlexMobilidadeApplication.class, args);
	}

	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if (null == adminAccount) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setName("rodrigo");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
