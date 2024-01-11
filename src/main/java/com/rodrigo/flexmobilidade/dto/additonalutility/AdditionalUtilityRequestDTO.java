package com.rodrigo.flexmobilidade.dto.additonalutility;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdditionalUtilityRequestDTO {

    private String name;

    private Double value;

    public AdditionalUtilityRequestDTO(String name, Double value, Integer quantity) {
        this.name = name;
        this.value = value;
    }

}
