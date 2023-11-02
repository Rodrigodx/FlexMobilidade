package com.rodrigo.flexmobilidade.model.reserva.dto;

import com.rodrigo.flexmobilidade.model.reserva.Grupo;
import lombok.Data;

@Data
public class CarsResponseDTO {

    private Integer id;

    private String model;

    private Grupo grupo;

}
