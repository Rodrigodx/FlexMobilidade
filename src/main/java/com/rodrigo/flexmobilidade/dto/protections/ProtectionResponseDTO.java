package com.rodrigo.flexmobilidade.dto.protections;

import lombok.Data;

@Data
public class ProtectionResponseDTO {
    private Integer id;

    private String name;

    private String benefits;

    private Double value;
}
