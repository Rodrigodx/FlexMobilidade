package com.rodrigo.flexmobilidade.repositories;

import com.rodrigo.flexmobilidade.config.TestConfig;
import com.rodrigo.flexmobilidade.dto.user.UsersDTO;
import com.rodrigo.flexmobilidade.model.user.UserRole;
import com.rodrigo.flexmobilidade.model.user.Users;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findByEmailCase1() {
        String email = "teste@email.com";
        UsersDTO data = new UsersDTO("teste", email, "12345", UserRole.USER);
        this.createUser(data);

        Optional<Users> result = userRepository.findByEmail(email);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from DB when not exists")
    void findByEmailCase2() {
        String email = "teste@email.com";

        Optional<Users> result = userRepository.findByEmail(email);

        assertThat(result.isEmpty()).isTrue();
    }

    private Users createUser(UsersDTO data){
        Users newUser = new Users(data);
        entityManager.persist(newUser);
        return newUser;
    }
}