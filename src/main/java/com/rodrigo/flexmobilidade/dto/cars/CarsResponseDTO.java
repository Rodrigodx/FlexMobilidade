package com.rodrigo.flexmobilidade.dto.cars;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarsResponseDTO {

    private Integer id;

    private String model;

    public CarsResponseDTO(Integer id, String model) {
        this.id = id;
        this.model = model;
    }
}
