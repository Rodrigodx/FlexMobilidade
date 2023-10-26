package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.Cars;
import com.rodrigo.flexmobilidade.services.CarsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cars", produces = {"application/json"})
@RequiredArgsConstructor
public class CarsController {

    private final CarsService carsService;

    @Operation(summary = "Realiza cadastro de carros no sistema", method = "POST")
    @PostMapping
    public ResponseEntity<Cars> save(@RequestBody Cars cars){
        return new ResponseEntity<>(carsService.save(cars), HttpStatus.CREATED);
    }

    @Operation(summary = "Mostra todos os carros cadastrados", method = "GET")
    @GetMapping
    public ResponseEntity<List<Cars>> findAll(){
        return ResponseEntity.ok(carsService.findAll());
    }
}
