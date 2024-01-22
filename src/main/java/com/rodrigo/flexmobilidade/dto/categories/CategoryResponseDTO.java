package com.rodrigo.flexmobilidade.dto.categories;

import com.rodrigo.flexmobilidade.model.cars.Cars;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {

    private Integer id;

    private String name;

    private List<Cars> carsList;

}
