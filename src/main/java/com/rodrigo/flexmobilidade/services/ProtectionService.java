package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.repositories.ProtectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtectionService {

    private final ProtectionRepository protectionRepository;
    @Transactional
    public Protection save(Protection protection){
        return protectionRepository.save(protection);
    }

    @ReadOnlyProperty
    public List<Protection> findAll(){
        return protectionRepository.findAll();
    }


}
