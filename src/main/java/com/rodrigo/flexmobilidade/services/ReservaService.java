package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.model.categories.Category;
import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.model.reserva.Reserva;
import com.rodrigo.flexmobilidade.dto.reserva.ReservaRequestDTO;
import com.rodrigo.flexmobilidade.dto.reserva.ReservaResponseDTO;
import com.rodrigo.flexmobilidade.repositories.ReservaRepository;
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
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ProtectionService protectionService;
    private final AccessoryService accessoryService;
    private final AdditionalUtilityService additionalUtilityService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public ReservaResponseDTO save(ReservaRequestDTO reservaRequestDTO){
        Category category = categoryService.findById(reservaRequestDTO.getCategory());

        Protection protection = protectionService.findById(reservaRequestDTO.getProtection());

        List<Accessory> accessories = reservaRequestDTO.getAccessories().stream().map(accessory -> accessoryService.findById(accessory.getId())).toList();
        List<AdditionalUtility> additionalUtilities = reservaRequestDTO.getAdditionalUtilities().stream().map(additionalUtility -> additionalUtilityService.findById(additionalUtility.getId())).toList();

        Reserva reserva = modelMapper.map(reservaRequestDTO, Reserva.class);

        reserva.setCategory(category);
        reserva.setProtection(protection);
        reserva.setAccessories(accessories);
        reserva.setAdditionalUtilities(additionalUtilities);

        reservaRepository.save(reserva);

        return modelMapper.map(reserva, ReservaResponseDTO.class);
    }

    @ReadOnlyProperty
    public List<ReservaResponseDTO> findAll (){
        List<Reserva> reservaList = reservaRepository.findAll();
        return reservaList.stream().map(reserva -> modelMapper.map(reserva, ReservaResponseDTO.class)).collect(Collectors.toList());
    }

    @ReadOnlyProperty
    public Optional<ReservaResponseDTO> findById(Integer id){
        Optional<Reserva> reserva = reservaRepository.findById(id);
        return Optional.ofNullable(modelMapper.map(reserva, ReservaResponseDTO.class));
    }
    @Transactional
    public void delete(Integer id){
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()){
            reservaRepository.deleteById(id);
        }else {
            throw new NoSuchElementException("Reserva with ID:" + id + " not found");
        }
    }
    @Transactional
    public ReservaResponseDTO update(ReservaRequestDTO reservaRequestDTO, Integer id) throws IllegalArgumentException {
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            Category category = categoryService.findById(reservaRequestDTO.getCategory());
            Protection protection = protectionService.findById(reservaRequestDTO.getProtection());
            List<Accessory> accessories = reservaRequestDTO.getAccessories().stream().map(accessory -> accessoryService.findById(accessory.getId())).toList();
            List<AdditionalUtility> additionalUtilities = reservaRequestDTO.getAdditionalUtilities().stream().map(additionalUtility -> additionalUtilityService.findById(additionalUtility.getId())).toList();

            reserva.setLocation(reservaRequestDTO.getLocation());
            reserva.setPersonalData(reservaRequestDTO.getPersonalData());
            reserva.setInicial(reservaRequestDTO.getInicial());
            reserva.setFinish(reservaRequestDTO.getFinish());
            reserva.setCategory(category);
            reserva.setProtection(protection);
            reserva.setAccessories(accessories);
            reserva.setAdditionalUtilities(additionalUtilities);

            Reserva updatedReserva = reservaRepository.save(reserva);

            return modelMapper.map(updatedReserva, ReservaResponseDTO.class);
        }
        throw new NoSuchElementException("Reserva with ID:" + id + " not found");
    }
}
