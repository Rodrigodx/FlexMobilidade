package com.rodrigo.flexmobilidade.dto.categories;

import com.rodrigo.flexmobilidade.dto.cars.CarsDTO;
import com.rodrigo.flexmobilidade.model.cars.Cars;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCarsRequestDTO {

    private List<CarsDTO> carsList = new ArrayList<>();

}
