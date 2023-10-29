package com.rodrigo.flexmobilidade.dto.cars;

import com.rodrigo.flexmobilidade.model.Grupo;
import lombok.Data;

@Data
public class CarsResponseDTO {

    private Integer id;

    private String model;

    private Grupo grupo;

}
