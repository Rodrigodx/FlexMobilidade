package com.rodrigo.flexmobilidade.model.protections.dto;

import lombok.Data;

@Data
public class ProtectionResponseDTO {
    private Integer id;

    private String name;

    private String benefits;

    private Double value;
}
