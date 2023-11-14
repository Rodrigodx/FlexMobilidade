package com.rodrigo.flexmobilidade.model.additionalutility.dto;

import lombok.Data;

@Data
public class AdditionalUtilityResponseDTO {
    private Integer id;

    private String name;

    private Double value;

    private Integer quantity = 0;
}
