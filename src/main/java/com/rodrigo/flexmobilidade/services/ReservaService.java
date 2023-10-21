package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.Reserva;
import com.rodrigo.flexmobilidade.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Transactional
    public Reserva save(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    @ReadOnlyProperty
    public List<Reserva> findAll (){
        return reservaRepository.findAll();
    }


}
