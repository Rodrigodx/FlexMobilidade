package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.accessories.AccessoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.accessories.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.dto.cars.CarsRequestDTO;
import com.rodrigo.flexmobilidade.dto.cars.CarsResponseDTO;
import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.repositories.CarsRepository;
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
    public Cars findById(Integer id){
        return carsRepository.findById(id).get();
    }

    @Transactional
    public void delete(Integer id){
        Optional<Cars> cars = carsRepository.findById(id);
        if (cars.isPresent()){
            carsRepository.deleteById(id);
        }else {
            throw new NoSuchElementException("Cars with ID:" + id + " not found");
        }
    }
    @Transactional
    public CarsResponseDTO update(CarsRequestDTO carsRequestDTO, Integer id) throws IllegalArgumentException {
        Optional<Cars> optionalCars = carsRepository.findById(id);
        if (optionalCars.isPresent()) {
            Cars cars = optionalCars.get();

            cars.setModel(carsRequestDTO.getModel());

            Cars updatedCars = carsRepository.save(cars);

            return modelMapper.map(updatedCars, CarsResponseDTO.class);
        }

        throw new NoSuchElementException("Cars with ID:" + id + " not found");
}

}
