package com.rodrigo.flexmobilidade.repositories;

import com.rodrigo.flexmobilidade.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
