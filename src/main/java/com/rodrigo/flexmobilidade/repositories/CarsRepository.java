package com.rodrigo.flexmobilidade.repositories;

import com.rodrigo.flexmobilidade.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Cars, Integer>{
}
