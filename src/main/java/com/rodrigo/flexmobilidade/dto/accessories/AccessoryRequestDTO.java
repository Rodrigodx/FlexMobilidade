package com.rodrigo.flexmobilidade.dto.accessories;

import lombok.Data;

@Data
public class AccessoryRequestDTO {

    private String name;

    private Double values;

    public AccessoryRequestDTO(String name, double values) {
        this.name = name;
        this.values = values;
    }
}
