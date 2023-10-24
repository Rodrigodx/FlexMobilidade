package com.rodrigo.flexmobilidade.dto.grupo;

import com.rodrigo.flexmobilidade.model.Cars;
import lombok.Data;

import java.util.List;

@Data
public class GrupoResponseDTO {

    private Integer id;

    private String name;

    private List<Cars> cars_id;

}
