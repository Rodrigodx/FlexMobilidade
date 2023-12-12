package com.rodrigo.flexmobilidade.services.impl;

import com.rodrigo.flexmobilidade.model.user.User;
import com.rodrigo.flexmobilidade.model.user.UserRole;
import com.rodrigo.flexmobilidade.model.user.dto.SingUpRequest;
import com.rodrigo.flexmobilidade.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User singup(SingUpRequest singUpRequest){
        User user = new User();

        user.setEmail(singUpRequest.getEmail());
        user.setName(singUpRequest.getName());
        user.setPassword(passwordEncoder.encode(singUpRequest.getPassword()));
        user.setRole(UserRole.USER);

        return userRepository.save(user);
    }
}
