package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.dto.reserva.ReservaRequestDTO;
import com.rodrigo.flexmobilidade.dto.reserva.ReservaResponseDTO;
import com.rodrigo.flexmobilidade.services.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    @Operation(summary = " um Categoria de carros", method = "POST")
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> save(@RequestBody @Valid ReservaRequestDTO reservaRequestDTO){
        return new ResponseEntity<>(reservaService.save(reservaRequestDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> findAll(){
        return ResponseEntity.ok(reservaService.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<ReservaResponseDTO>> findById(@PathVariable Integer id){
        return ResponseEntity.ok(reservaService.findById(id));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservaResponseDTO> update(@RequestBody ReservaRequestDTO reservaRequestDTO, @PathVariable Integer id){
        return ResponseEntity.ok(reservaService.update(reservaRequestDTO, id));
    }
}
