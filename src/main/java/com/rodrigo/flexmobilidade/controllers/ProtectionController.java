package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.protections.dto.ProtectionRequestDTO;
import com.rodrigo.flexmobilidade.model.protections.dto.ProtectionResponseDTO;
import com.rodrigo.flexmobilidade.services.ProtectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/protection")
@RequiredArgsConstructor
public class ProtectionController {

    private final ProtectionService protectionService;
    @PostMapping
    public ResponseEntity<ProtectionResponseDTO> save(@RequestBody ProtectionRequestDTO protection){
        return new ResponseEntity<>(protectionService.save(protection), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProtectionResponseDTO>> findAll(){
        return ResponseEntity.ok(protectionService.findAll());
    }
    @GetMapping(value = "/id")
    public ResponseEntity<Optional<ProtectionResponseDTO>> findById(Integer id){
        return ResponseEntity.ok(protectionService.findById(id));
    }
}
