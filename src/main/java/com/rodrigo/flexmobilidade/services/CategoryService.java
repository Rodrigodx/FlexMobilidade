package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.model.categories.Category;
import com.rodrigo.flexmobilidade.model.categories.dto.CategoryRequestDTO;
import com.rodrigo.flexmobilidade.model.categories.dto.CategoryResponseDTO;
import com.rodrigo.flexmobilidade.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CarsService carsService;
    private final ModelMapper modelMapper = new ModelMapper();
    @Transactional
    public CategoryResponseDTO save(CategoryRequestDTO categoryRequestDTO){

        List<Cars> carsList = categoryRequestDTO.getCarsList().stream().map(carsDTO -> carsService.findById(carsDTO.getId())).toList();

        Category category = modelMapper.map(categoryRequestDTO, Category.class);

        category.setCarsList(carsList);

        categoryRepository.save(category);

        return modelMapper.map(category, CategoryResponseDTO.class);
    }
    @ReadOnlyProperty
    public List<CategoryResponseDTO> findAll(){
        List<Category>  categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryResponseDTO.class)).collect(Collectors.toList());
    }
    @ReadOnlyProperty
    public Category findById(Integer id){
        return categoryRepository.findById(id).get();
    }


}
