package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.model.role.Role;
import com.rodrigo.flexmobilidade.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> save(@RequestBody Role role){
        return new ResponseEntity<>(roleService.save(role), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Role>> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

}
