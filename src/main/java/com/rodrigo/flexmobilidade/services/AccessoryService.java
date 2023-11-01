package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.accessories.dto.AccessoryRequestDTO;
import com.rodrigo.flexmobilidade.model.accessories.dto.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.repositories.AccessoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public AccessoryResponseDTO save(AccessoryRequestDTO accessory){
        Accessory accessory1 = accessoryRepository.save(modelMapper.map(accessory, Accessory.class));
        return modelMapper.map(accessory1, AccessoryResponseDTO.class);
    }

    @ReadOnlyProperty
    public List<AccessoryResponseDTO> findAll(){
        List<Accessory> accessories = accessoryRepository.findAll();
        return accessories.stream().map(accessory -> modelMapper.map(accessory, AccessoryResponseDTO.class)).collect(Collectors.toList());
    }


}
