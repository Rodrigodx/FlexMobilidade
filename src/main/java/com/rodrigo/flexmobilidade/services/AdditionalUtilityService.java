package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.model.additionalutility.dto.AdditionalUtilityRequestDTO;
import com.rodrigo.flexmobilidade.model.additionalutility.dto.AdditionalUtilityResponseDTO;
import com.rodrigo.flexmobilidade.repositories.AdditionalUtilityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdditionalUtilityService {

    private final AdditionalUtilityRepository utilityRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Transactional
    public AdditionalUtilityResponseDTO save(AdditionalUtilityRequestDTO additionalUtilityRequestDTO) {
        AdditionalUtility additionalUtility = utilityRepository.save(modelMapper.map(additionalUtilityRequestDTO, AdditionalUtility.class));
        return modelMapper.map(additionalUtility, AdditionalUtilityResponseDTO.class);
    }
    @ReadOnlyProperty
    public List<AdditionalUtilityResponseDTO> findAll() {
        List<AdditionalUtility> additionalUtilities = utilityRepository.findAll();
        return additionalUtilities.stream().map(additionalUtility -> modelMapper.map(additionalUtility, AdditionalUtilityResponseDTO.class)).collect(Collectors.toList());
    }
    @ReadOnlyProperty
    public AdditionalUtility findById(Integer id){
        return utilityRepository.findById(id).get();
    }

}


