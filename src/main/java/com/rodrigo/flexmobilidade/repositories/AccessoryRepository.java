package com.rodrigo.flexmobilidade.repositories;

import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Integer>{

}
