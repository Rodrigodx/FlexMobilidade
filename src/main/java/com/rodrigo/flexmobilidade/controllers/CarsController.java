package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.dto.cars.CarsRequestDTO;
import com.rodrigo.flexmobilidade.dto.cars.CarsResponseDTO;
import com.rodrigo.flexmobilidade.services.CarsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
    public ResponseEntity<CarsResponseDTO> save(@RequestBody @Valid CarsRequestDTO carsRequestDTO){
        return new ResponseEntity<>(carsService.save(carsRequestDTO), HttpStatus.CREATED);
    }
    @Operation(summary = "Mostra todos os carros cadastrados", method = "GET")
    @GetMapping
    public ResponseEntity<List<CarsResponseDTO>> findAll(){
        return ResponseEntity.ok(carsService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cars> findById(@PathVariable Integer id){
        return ResponseEntity.ok(carsService.findById(id));
    }


}
