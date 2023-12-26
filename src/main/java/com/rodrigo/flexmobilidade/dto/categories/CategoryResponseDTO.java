package com.rodrigo.flexmobilidade.dto.categories;

import com.rodrigo.flexmobilidade.model.cars.Cars;
import lombok.Data;

import java.util.List;
@Data
public class CategoryResponseDTO {

    private Integer id;

    private String name;

    private List<Cars> carsList;

}
