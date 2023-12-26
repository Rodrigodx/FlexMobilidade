package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.dto.protections.ProtectionRequestDTO;
import com.rodrigo.flexmobilidade.dto.protections.ProtectionResponseDTO;
import com.rodrigo.flexmobilidade.services.ProtectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Protection> findById(Integer id){
        return ResponseEntity.ok(protectionService.findById(id));
    }
}
