package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.services.AdditionalUtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/addUtility")
@RequiredArgsConstructor
public class AdditionalUtilityController {

    private final AdditionalUtilityService utilityService;

    @PostMapping
    public ResponseEntity<AdditionalUtility> save(AdditionalUtility additionalUtility){
        return new ResponseEntity<>(utilityService.save(additionalUtility), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdditionalUtility>> findAll(){
        return ResponseEntity.ok(utilityService.findAll());
    }

}
