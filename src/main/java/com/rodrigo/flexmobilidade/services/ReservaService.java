package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.accessories.dto.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.model.additionalutility.dto.AdditionalUtilityResponseDTO;
import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.model.protections.dto.ProtectionResponseDTO;
import com.rodrigo.flexmobilidade.model.reserva.Reserva;
import com.rodrigo.flexmobilidade.model.reserva.dto.ReservaRequestDTO;
import com.rodrigo.flexmobilidade.model.reserva.dto.ReservaResponseDTO;
import com.rodrigo.flexmobilidade.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ProtectionService protectionService;
    private final AccessoryService accessoryService;
    private final AdditionalUtilityService additionalUtilityService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public ReservaResponseDTO save(ReservaRequestDTO reservaRequestDTO){
        ProtectionResponseDTO protection = protectionService.findById(reservaRequestDTO.getIdProtection());
        Protection protection1 = modelMapper.map(protection, Protection.class);

        List<Accessory> accessories = reservaRequestDTO.getAccessories().stream().map(accessory -> accessoryService.findById(accessory.getId())).toList();
        List<AdditionalUtility> additionalUtilities = reservaRequestDTO.getAdditionalUtilities().stream().map(additionalUtility -> additionalUtilityService.findById(additionalUtility.getId())).toList();

        Reserva reserva = modelMapper.map(reservaRequestDTO, Reserva.class);

        reserva.setProtection(protection1);
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
}
