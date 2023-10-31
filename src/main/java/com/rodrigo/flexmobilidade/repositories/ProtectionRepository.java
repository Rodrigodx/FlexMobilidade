package com.rodrigo.flexmobilidade.repositories;

import com.rodrigo.flexmobilidade.model.protections.Protection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtectionRepository extends JpaRepository<Protection, Integer> {
}
