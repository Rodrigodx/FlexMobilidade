package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityRequestDTO;
import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityResponseDTO;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.services.AdditionalUtilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/utility")
@RequiredArgsConstructor
public class AdditionalUtilityController {

    private final AdditionalUtilityService utilityService;
    @PostMapping
    public ResponseEntity<AdditionalUtilityResponseDTO> save(@RequestBody @Valid AdditionalUtilityRequestDTO utility){
        return new ResponseEntity<>(utilityService.save(utility), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AdditionalUtilityResponseDTO>> findAll(){
        return ResponseEntity.ok(utilityService.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AdditionalUtility> findById(@PathVariable Integer id){
        return ResponseEntity.ok(utilityService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        utilityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AdditionalUtilityResponseDTO> update(@RequestBody AdditionalUtilityRequestDTO utilityRequestDTO, @PathVariable Integer id){
        return ResponseEntity.ok(utilityService.update(utilityRequestDTO, id));
    }

}
