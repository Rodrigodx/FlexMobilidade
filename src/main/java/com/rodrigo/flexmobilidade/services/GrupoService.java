package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.Grupo;
import com.rodrigo.flexmobilidade.repositories.GrupoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository grupoRepository;

    @Transactional
    public Grupo save (Grupo grupo){
        return grupoRepository.save(grupo);
    }

    @ReadOnlyProperty
    public List<Grupo> findAll(){
        return grupoRepository.findAll();
    }

}
