package com.rodrigo.flexmobilidade.services;


import com.rodrigo.flexmobilidade.exceptions.UserPresentException;
import com.rodrigo.flexmobilidade.model.user.dto.UsersDto;


public interface UserService {
	UsersDto newUserRegistration(UsersDto usersDto) throws UserPresentException;

	public void authenticate(String email, String password);
}
