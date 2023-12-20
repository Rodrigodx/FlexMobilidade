package com.rodrigo.flexmobilidade.infra.security;


import com.rodrigo.flexmobilidade.model.user.User;
import com.rodrigo.flexmobilidade.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User orElseThrow = this.userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username +" Not Found "));
		return orElseThrow;
	}
	
	


}
