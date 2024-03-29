package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.cars.CarsResponseDTO;
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
import java.util.NoSuchElementException;
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

    @Transactional
    public void delete(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){
            categoryRepository.deleteById(id);
        }else {
            throw new NoSuchElementException("Category with ID:" + id + " not found");
        }
    }
    @Transactional
    public CategoryResponseDTO update(CategoryRequestDTO categoryRequestDTO, Integer id) throws IllegalArgumentException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            category.setName(categoryRequestDTO.getName());

            Category updatedCategory = categoryRepository.save(category);

            return modelMapper.map(updatedCategory, CategoryResponseDTO.class);
        }

        throw new NoSuchElementException("Category with ID:" + id + " not found");
    }


}
