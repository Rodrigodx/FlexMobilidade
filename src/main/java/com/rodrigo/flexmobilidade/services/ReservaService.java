package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.model.reserva.Reserva;
import com.rodrigo.flexmobilidade.model.reserva.dto.ReservaRequestDTO;
import com.rodrigo.flexmobilidade.model.reserva.dto.ReservaResponseDTO;
import com.rodrigo.flexmobilidade.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public ReservaResponseDTO save(ReservaRequestDTO reservaRequestDTO){
        Reserva reserva = reservaRepository.save(modelMapper.map(reservaRequestDTO, Reserva.class));
        return modelMapper.map(reserva, ReservaResponseDTO.class);
    }

    @ReadOnlyProperty
    public List<ReservaResponseDTO> findAll (){
        List<Reserva> reservaList = reservaRepository.findAll();
        return reservaList.stream().map(reserva -> modelMapper.map(reserva, ReservaResponseDTO.class)).collect(Collectors.toList());
    }
}
