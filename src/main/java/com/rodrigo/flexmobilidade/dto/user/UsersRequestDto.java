package com.rodrigo.flexmobilidade.dto.user;



import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UsersRequestDto {
	@NotBlank(message = "Please enter a first name")
	private String name;
	@NotBlank(message = "Please enter a Email Address")
	private String email;
	@NotBlank(message = "Please enter a password")
	private String password;

}
