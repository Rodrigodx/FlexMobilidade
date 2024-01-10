package com.rodrigo.flexmobilidade.dto.additonalutility;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdditionalUtilityResponseDTO {
    private Integer id;

    private String name;

    private Double value;

    private Integer quantity;

    public AdditionalUtilityResponseDTO(Integer id, String name, Double value, Integer quantity){
        this.id = id;
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    public void setQuantity(Integer quantity) {
        quantity = 0;
        this.quantity = quantity;
    }
}
