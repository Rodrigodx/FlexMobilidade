package com.rodrigo.flexmobilidade.dto.additonalutility;

import lombok.Data;

@Data
public class AdditionalUtilityRequestDTO {

    private String name;

    private Double value;

    private Integer quantity = 0;
}
