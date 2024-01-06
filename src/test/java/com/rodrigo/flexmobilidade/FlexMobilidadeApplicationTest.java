package com.rodrigo.flexmobilidade;

import com.rodrigo.flexmobilidade.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static com.rodrigo.flexmobilidade.FlexMobilidadeApplication.main;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlexMobilidadeApplicationTest {

    @Test
    void testMain() {
        main(new String[]{});
    }
}