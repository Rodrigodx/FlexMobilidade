package com.rodrigo.flexmobilidade.model.categories.dto;

import com.rodrigo.flexmobilidade.model.cars.Cars;
import lombok.Data;

import java.util.List;
@Data
public class CategoryResponseDTO {

    private Integer id;

    private String name;

    private List<Cars> carsList;

}
