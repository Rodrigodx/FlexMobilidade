package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.accessories.AccessoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.accessories.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityRequestDTO;
import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityResponseDTO;
import com.rodrigo.flexmobilidade.repositories.AdditionalUtilityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    @Transactional
    public void delete(Integer id){
        Optional<AdditionalUtility> utility = utilityRepository.findById(id);
        if (utility.isPresent()){
            utilityRepository.deleteById(id);
        }else {
            throw new NoSuchElementException("AdditionalUtility with ID:" + id + " not found");
        }
    }
    @Transactional
    public AdditionalUtilityResponseDTO update(AdditionalUtilityRequestDTO additionalUtilityRequestDTO, Integer id) throws IllegalArgumentException {
        Optional<AdditionalUtility> utilityOptional = utilityRepository.findById(id);
        if (utilityOptional.isPresent()) {
            AdditionalUtility additionalUtility = utilityOptional.get();

            additionalUtility.setName(additionalUtilityRequestDTO.getName());
            additionalUtility.setValue(additionalUtilityRequestDTO.getValue());

            AdditionalUtility updatedUtility = utilityRepository.save(additionalUtility);

            return modelMapper.map(updatedUtility, AdditionalUtilityResponseDTO.class);
        }

        throw new NoSuchElementException("AdditionalUtility with ID:" + id + " not found");    }

}


