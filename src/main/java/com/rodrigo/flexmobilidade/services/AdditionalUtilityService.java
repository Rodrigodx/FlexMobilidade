package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.repositories.AdditionalUtilityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalUtilityService {

    private final AdditionalUtilityRepository utilityRepository;

    @Transactional
    public AdditionalUtility save (AdditionalUtility additionalUtility){
        return utilityRepository.save(additionalUtility);
    }

    @ReadOnlyProperty
    public List<AdditionalUtility> findAll(){
        return utilityRepository.findAll();
    }

}
