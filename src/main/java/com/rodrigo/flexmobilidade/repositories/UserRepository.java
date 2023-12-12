package com.rodrigo.flexmobilidade.repositories;

import com.rodrigo.flexmobilidade.model.user.User;
import com.rodrigo.flexmobilidade.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String username);

    User findByRole(UserRole role);
}
