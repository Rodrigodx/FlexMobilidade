package com.rodrigo.flexmobilidade.dto.accessories;

import lombok.Data;

@Data
public class AccessoryRequestDTO {

    private String name;

    private Double value;

    public AccessoryRequestDTO(String name, double value) {
        this.name = name;
        this.value = value;
    }
}
