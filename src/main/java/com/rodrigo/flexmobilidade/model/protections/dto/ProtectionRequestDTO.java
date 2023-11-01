package com.rodrigo.flexmobilidade.model.protections.dto;

import lombok.Data;

@Data
public class ProtectionRequestDTO {
    private Integer id;

    private String name;

    private String benefits;

    private Double value;
}
