package com.rodrigo.flexmobilidade.controllers;


import com.rodrigo.flexmobilidade.model.categories.Category;
import com.rodrigo.flexmobilidade.dto.categories.CategoryCarsRequestDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryResponseDTO;
import com.rodrigo.flexmobilidade.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO){
        return new ResponseEntity<>(categoryService.save(categoryRequestDTO), HttpStatus.CREATED);
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> addCars(@RequestBody @Valid CategoryCarsRequestDTO categoryCarsRequestDTO, @PathVariable Integer id){
        return ResponseEntity.ok(categoryService.addCars(categoryCarsRequestDTO, id));
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

}
