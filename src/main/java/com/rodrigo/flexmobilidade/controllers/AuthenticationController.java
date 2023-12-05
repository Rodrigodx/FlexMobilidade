package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.user.AuthenticationDTO;
import com.rodrigo.flexmobilidade.model.user.RegisterDTO;
import com.rodrigo.flexmobilidade.model.user.User;
import com.rodrigo.flexmobilidade.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO registerDTO){
        if (this.userRepository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.login(), encryptedPassword, registerDTO.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }



}
