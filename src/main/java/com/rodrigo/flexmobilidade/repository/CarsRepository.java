package com.rodrigo.flexmobilidade.repository;

import com.rodrigo.flexmobilidade.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Cars, Integer>{
}
