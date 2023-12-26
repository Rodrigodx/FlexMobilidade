package com.rodrigo.flexmobilidade.dto.categories;

import com.rodrigo.flexmobilidade.dto.cars.CarsDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryCarsRequestDTO {

    private List<CarsDTO> carsList = new ArrayList<>();

}
