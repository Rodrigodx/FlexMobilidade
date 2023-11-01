package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.acessories.Accessory;
import com.rodrigo.flexmobilidade.repositories.AccessoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;

    @Transactional
    public Accessory save(Accessory accessory){
        return accessoryRepository.save(accessory);
    }

    @ReadOnlyProperty
    public List<Accessory> findAll(){
        return accessoryRepository.findAll();
    }


}
