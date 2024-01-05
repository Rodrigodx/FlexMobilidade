package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.dto.accessories.AccessoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.accessories.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.repositories.AccessoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    @ReadOnlyProperty
    public Accessory findById(Integer id){
        return accessoryRepository.findById(id).get();
    }

    @Transactional
    public void delete(Integer id){
        Optional<Accessory> accessory = Optional.ofNullable(findById(id));
        if (accessory.isPresent()){
            accessoryRepository.deleteById(id);
        }else {
            throw new NoSuchElementException("Accessory with ID:" + id + " not found");
        }
    }
    @Transactional
    public AccessoryResponseDTO update(AccessoryRequestDTO accessoryRequestDTO, Integer id) throws IllegalArgumentException {
        Optional<Accessory> optionalAccessory = accessoryRepository.findById(id);
        if (optionalAccessory.isPresent()) {
            Accessory accessory = optionalAccessory.get();

            accessory.setName(accessoryRequestDTO.getName());
            accessory.setValues(accessoryRequestDTO.getValues());

            Accessory updatedAccessory = accessoryRepository.save(accessory);

            return modelMapper.map(updatedAccessory, AccessoryResponseDTO.class);
        }

        throw new NoSuchElementException("Accessory with ID:" + id + " not found");    }
}


