package com.rodrigo.flexmobilidade.controllers;


import com.rodrigo.flexmobilidade.exceptions.UserPresentException;
import com.rodrigo.flexmobilidade.infra.security.JwtAuthRequest;
import com.rodrigo.flexmobilidade.infra.security.JwtAuthResponse;
import com.rodrigo.flexmobilidade.infra.security.JwtService;
import com.rodrigo.flexmobilidade.infra.security.UserInfoUserDetailsService;
import com.rodrigo.flexmobilidade.model.ReturnData;
import com.rodrigo.flexmobilidade.model.user.dto.UsersDto;
import com.rodrigo.flexmobilidade.services.UserService;
import com.rodrigo.flexmobilidade.utility.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author swapnil.mane
 *
 */
@RestController
public class UsersController {

	/**
	 * New USER_REGISTRATION.
	 */
	private static final String USER_REGISTRATION = "/user/registration";
	
	/**
	 *  USER_LOGIN.
	 */
	private static final String USER_LOGIN = "/user/login";
	
	/**
	 *  GET_ALL_USERS from db.
	 */

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserInfoUserDetailsService userInfoUserDetailsService;

	
	@PostMapping(path = USER_REGISTRATION)
	public ResponseEntity<?> newUserRegistration(@Valid @RequestBody UsersDto usersDto) throws UserPresentException, UserPresentException {
		ReturnData data = new ReturnData();
		UsersDto returnData = this.userService.newUserRegistration(usersDto);
		data.setData(returnData);
		data.setMessage(Constants.NEW_USER);
		data.setStatusCode(Constants.SUCCESS_CODE);
		return new ResponseEntity<ReturnData>(data, HttpStatus.CREATED);
	}

	@PostMapping(path = USER_LOGIN)
	public ResponseEntity<?> userLogin(@RequestBody JwtAuthRequest jwtAuthRequest) {

		this.userService.authenticate(jwtAuthRequest.getEmail(), jwtAuthRequest.getPassword());
		
		UserDetails userDetails = this.userInfoUserDetailsService.loadUserByUsername(jwtAuthRequest.getEmail());

		String username = jwtAuthRequest.getEmail();
		
		String token = this.jwtService.generateToken(userDetails);

		return ResponseEntity.ok(new JwtAuthResponse(username, token));

	}
	
}
