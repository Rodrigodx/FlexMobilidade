package com.rodrigo.flexmobilidade.repositories;

import com.rodrigo.flexmobilidade.model.user.Users;
import com.rodrigo.flexmobilidade.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByEmail(String email);

    Users findByRole(UserRole userRole);
}
