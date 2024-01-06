package com.rodrigo.flexmobilidade.dto.accessories;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessoryResponseDTO {

    private Integer id;

    private String name;

    private Double values;

    public AccessoryResponseDTO(Integer id, String name, Double values) {
        this.id = id;
        this.name = name;
        this.values = values;
    }
}
