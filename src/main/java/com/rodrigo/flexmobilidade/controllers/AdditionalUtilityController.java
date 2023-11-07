package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.model.additionalutility.dto.AdditionalUtilityRequestDTO;
import com.rodrigo.flexmobilidade.model.additionalutility.dto.AdditionalUtilityResponseDTO;
import com.rodrigo.flexmobilidade.services.AdditionalUtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/addUtility")
@RequiredArgsConstructor
public class AdditionalUtilityController {

    private final AdditionalUtilityService utilityService;
    @PostMapping
    public ResponseEntity<AdditionalUtilityResponseDTO> save(AdditionalUtilityRequestDTO additionalUtility){
        return new ResponseEntity<>(utilityService.save(additionalUtility), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AdditionalUtilityResponseDTO>> findAll(){
        return ResponseEntity.ok(utilityService.findAll());
    }
    @GetMapping(value = "/id")
    public ResponseEntity<AdditionalUtility> findById(Integer id){
        return ResponseEntity.ok(utilityService.findById(id));
    }

}
