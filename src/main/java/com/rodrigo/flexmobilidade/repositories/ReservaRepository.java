package com.rodrigo.flexmobilidade.repositories;

import com.rodrigo.flexmobilidade.model.reserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
