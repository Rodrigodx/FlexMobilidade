package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.dto.accessories.AccessoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.accessories.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.services.AccessoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/accessory")
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;
    @PostMapping
    public ResponseEntity<AccessoryResponseDTO> save(@RequestBody @Valid AccessoryRequestDTO accessory){
        return new ResponseEntity<>(accessoryService.save(accessory), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AccessoryResponseDTO>> findAll(){
        return ResponseEntity.ok(accessoryService.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Accessory> findById(@PathVariable Integer id){
        return ResponseEntity.ok(accessoryService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        accessoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccessoryResponseDTO> update(@RequestBody AccessoryRequestDTO accessoryRequestDTO, @PathVariable Integer id){
        return ResponseEntity.ok(accessoryService.update(accessoryRequestDTO, id));
    }

}
