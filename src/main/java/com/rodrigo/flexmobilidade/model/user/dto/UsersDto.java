package com.rodrigo.flexmobilidade.model.user.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsersDto {
	@NotBlank(message = "Please enter a first name")
	private String name;
	@NotBlank(message = "Please enter a Email Address")
	private String email;
	@NotBlank(message = "Please enter a password")
	private String password;

}
