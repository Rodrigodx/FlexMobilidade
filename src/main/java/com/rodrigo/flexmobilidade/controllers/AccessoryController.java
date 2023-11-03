package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.accessories.dto.AccessoryRequestDTO;
import com.rodrigo.flexmobilidade.model.accessories.dto.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.services.AccessoryService;
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
@RequestMapping(value = "/api/accessory")
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;
    @PostMapping
    public ResponseEntity<AccessoryResponseDTO> save(AccessoryRequestDTO accessory){
        return new ResponseEntity<>(accessoryService.save(accessory), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AccessoryResponseDTO>> findAll(){
        return ResponseEntity.ok(accessoryService.findAll());
    }
    @GetMapping(value = "/id")
    public ResponseEntity<Optional<AccessoryResponseDTO>> findById(Integer id){
        return ResponseEntity.ok(accessoryService.findById(id));
    }

}
