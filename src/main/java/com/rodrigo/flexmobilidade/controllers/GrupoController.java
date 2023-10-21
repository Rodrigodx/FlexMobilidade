package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.Grupo;
import com.rodrigo.flexmobilidade.services.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/grupo")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoService grupoService;

    @PostMapping
    public ResponseEntity<Grupo> save (@RequestBody Grupo grupo){
        return new ResponseEntity<>(grupoService.save(grupo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Grupo>> findAll(){
        return ResponseEntity.ok(grupoService.findAll());
    }


}
