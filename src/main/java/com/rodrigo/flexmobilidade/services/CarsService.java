package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.Cars;
import com.rodrigo.flexmobilidade.model.Grupo;
import com.rodrigo.flexmobilidade.repositories.CarsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsService {

    private final CarsRepository carsRepository;
    @Transactional
    public Cars save (Cars cars){
        return carsRepository.save(cars);
    }
    @ReadOnlyProperty
    public List<Cars> findAll(){
        return carsRepository.findAll();
    }

    @ReadOnlyProperty
    public List<Cars> findByGrupoName(String name){
        return carsRepository.findByGrupoName(name);
    }

}
