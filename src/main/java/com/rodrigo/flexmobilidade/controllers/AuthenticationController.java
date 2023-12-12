package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.user.User;
import com.rodrigo.flexmobilidade.model.user.dto.SingUpRequest;
import com.rodrigo.flexmobilidade.services.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/singup")
    public ResponseEntity<User> singup(@RequestBody SingUpRequest singUpRequest){
        return new ResponseEntity<>(authenticationService.singup(singUpRequest), HttpStatus.CREATED);
    }



}
