package com.rodrigo.flexmobilidade.repositories;

import com.rodrigo.flexmobilidade.model.Cars;
import com.rodrigo.flexmobilidade.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface CarsRepository extends JpaRepository<Cars, Integer>{

    List<Cars> findByGrupoName(String name);

}
