package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.Reserva;
import com.rodrigo.flexmobilidade.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> save(@RequestBody Reserva reserva){
        return new ResponseEntity<>(reservaService.save(reserva), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> findAll(){
        return ResponseEntity.ok(reservaService.findAll());
    }



}
