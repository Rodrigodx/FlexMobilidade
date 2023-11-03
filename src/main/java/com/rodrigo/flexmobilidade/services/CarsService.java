package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.cars.dto.CarsRequestDTO;
import com.rodrigo.flexmobilidade.model.cars.dto.CarsResponseDTO;
import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.repositories.CarsRepository;
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
public class CarsService {

    private final CarsRepository carsRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Transactional
    public CarsResponseDTO save (CarsRequestDTO carsRequestDTO){
        Cars cars = carsRepository.save(modelMapper.map(carsRequestDTO, Cars.class));
        return modelMapper.map(cars, CarsResponseDTO.class);
    }
    @ReadOnlyProperty
    public List<CarsResponseDTO> findAll(){
        List<Cars> carsList = carsRepository.findAll();
        return carsList.stream().map(cars -> modelMapper.map(cars, CarsResponseDTO.class)).collect(Collectors.toList());
    }
    @ReadOnlyProperty
    public List<CarsResponseDTO> findByGrupoName(String name){
        List<Cars> carsList = carsRepository.findByGrupoName(name);
        return carsList.stream().map(cars -> modelMapper.map(cars, CarsResponseDTO.class)).collect(Collectors.toList());
    }
    @ReadOnlyProperty
    public Optional<CarsResponseDTO> findById(Integer id){
        Optional<Cars> cars = carsRepository.findById(id);
        return Optional.ofNullable(modelMapper.map(cars, CarsResponseDTO.class));
    }

}
