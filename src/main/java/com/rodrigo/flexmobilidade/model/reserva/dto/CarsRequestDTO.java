package com.rodrigo.flexmobilidade.dto.cars;

import com.rodrigo.flexmobilidade.model.reserva.Grupo;
import lombok.Data;

@Data
public class CarsRequestDTO {

    private Integer id;

    private String model;

    private Grupo grupo;
}
