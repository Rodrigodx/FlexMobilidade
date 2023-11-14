package com.rodrigo.flexmobilidade.model.categories.dto;

import com.rodrigo.flexmobilidade.model.cars.dto.CarsDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryRequestDTO {

    private String name;

    private List<CarsDTO> carsList = new ArrayList<>();

}
