package com.rodrigo.flexmobilidade.model.cars.dto;

import com.rodrigo.flexmobilidade.model.categories.Grupo;
import lombok.Data;

@Data
public class CarsResponseDTO {

    private Integer id;

    private String model;

    private Grupo grupo;

}
