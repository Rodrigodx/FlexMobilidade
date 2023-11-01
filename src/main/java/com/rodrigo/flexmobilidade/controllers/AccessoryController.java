package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.acessories.Accessory;
import com.rodrigo.flexmobilidade.services.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/accessory")
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;

    @PostMapping
    public ResponseEntity<Accessory> save(Accessory accessory){
        return new ResponseEntity<>(accessoryService.save(accessory), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Accessory>> findAll(){
        return ResponseEntity.ok(accessoryService.findAll());
    }

}
