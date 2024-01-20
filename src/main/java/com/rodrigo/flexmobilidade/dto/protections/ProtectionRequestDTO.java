package com.rodrigo.flexmobilidade.dto.protections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtectionRequestDTO {

    private String name;

    private String benefits;

    private Double value;
}
