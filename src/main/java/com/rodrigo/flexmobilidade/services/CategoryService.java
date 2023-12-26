package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.model.categories.Category;
import com.rodrigo.flexmobilidade.dto.categories.CategoryCarsRequestDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryResponseDTO;
import com.rodrigo.flexmobilidade.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CarsService carsService;
    private final ModelMapper modelMapper = new ModelMapper();
    @Transactional
    public CategoryResponseDTO save(CategoryRequestDTO categoryRequestDTO){
        Category category = categoryRepository.save(modelMapper.map(categoryRequestDTO, Category.class));
        return modelMapper.map(category, CategoryResponseDTO.class);
    }
    @Transactional
    public CategoryResponseDTO addCars(CategoryCarsRequestDTO categoryCarsRequestDTO, Integer id){
        List<Cars> carsList = categoryCarsRequestDTO.getCarsList().stream().map(carsDTO -> carsService.findById(carsDTO.getId())).toList();

        System.out.println(carsList);


        Optional<Category> existingCategoryOptional = categoryRepository.findById(id);

        Category category = modelMapper.map(categoryCarsRequestDTO, Category.class);

        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            String categoryName = existingCategory.getName();
            category.setId(id);
            category.setName(categoryName);
            category.setCarsList(carsList);
            categoryRepository.save(category);
        }

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
