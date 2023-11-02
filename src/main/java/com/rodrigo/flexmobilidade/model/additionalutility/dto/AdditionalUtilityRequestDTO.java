package com.rodrigo.flexmobilidade.model.additionalutility.dto;

import lombok.Data;

@Data
public class AdditionalUtilityRequestDTO {
    private Integer id;

    private String name;

    private Double value;

    private Integer quant = 0;
}
