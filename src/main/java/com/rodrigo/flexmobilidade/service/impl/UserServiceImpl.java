package com.rodrigo.flexmobilidade.service.impl;

import com.rodrigo.flexmobilidade.exceptions.UserPresentException;
import com.rodrigo.flexmobilidade.model.user.Users;
import com.rodrigo.flexmobilidade.model.user.UserRole;
import com.rodrigo.flexmobilidade.model.user.dto.UsersDto;
import com.rodrigo.flexmobilidade.repositories.UserRepository;
import com.rodrigo.flexmobilidade.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	

	@Override
	public UsersDto newUserRegistration(UsersDto usersDto) throws UserPresentException {
		
		Optional<Users> findByEmailAddress = userRepository.findByEmail(usersDto.getEmail());
		if(!findByEmailAddress.isPresent()) {
		Users user = new Users();
		user.setName(usersDto.getName());
		user.setEmail(usersDto.getEmail());
		user.setPassword(this.passwordEncoder.encode(usersDto.getPassword()));
		user.setRole(UserRole.USER);
		// saving to db
		this.userRepository.save(user);
		user.setRole(UserRole.USER);
		usersDto.setPassword(this.passwordEncoder.encode(user.getPassword()));
		}else {
			throw new UserPresentException(usersDto.getEmail() + " is already present please try with login");
		}
		return usersDto;
		
	}

	@Override
	public void authenticate(String email, String password) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("invalid username or password pls try again");
		}

	}



}
