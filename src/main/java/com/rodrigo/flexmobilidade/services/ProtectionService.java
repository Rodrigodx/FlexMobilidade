package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.dto.protections.ProtectionRequestDTO;
import com.rodrigo.flexmobilidade.dto.protections.ProtectionResponseDTO;
import com.rodrigo.flexmobilidade.repositories.ProtectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProtectionService {

    private final ProtectionRepository protectionRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Transactional
    public ProtectionResponseDTO save(ProtectionRequestDTO protection){
        Protection protection1 = protectionRepository.save(modelMapper.map(protection, Protection.class));
        return modelMapper.map(protection1, ProtectionResponseDTO.class);
    }
    @ReadOnlyProperty
    public List<ProtectionResponseDTO> findAll(){
        List<Protection> protections = protectionRepository.findAll();
        return protections.stream().map(protection -> modelMapper.map(protection, ProtectionResponseDTO.class)).collect(Collectors.toList());
    }
    @ReadOnlyProperty
    public Protection findById(Integer id){
        return protectionRepository.findById(id).get();
    }

}
