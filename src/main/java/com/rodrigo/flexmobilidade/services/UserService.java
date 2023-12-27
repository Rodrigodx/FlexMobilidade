package com.rodrigo.flexmobilidade.services;


import com.rodrigo.flexmobilidade.dto.user.UsersRequestDto;
import com.rodrigo.flexmobilidade.exceptions.UserPresentException;

public interface UserService {
	UsersRequestDto newUserRegistration(UsersRequestDto usersDto) throws UserPresentException;

	public void authenticate(String email, String password);
}
