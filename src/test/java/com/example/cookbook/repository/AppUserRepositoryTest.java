package com.example.cookbook.repository;

import com.example.cookbook.model.AppUser;
import com.example.cookbook.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository underTest;

    @BeforeEach
    void setUp() {
        AppUser user = new AppUser(1, "swagG", "swaggerG@swagger.swag", "123", "secret");
        underTest.save(user);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByEmailAndPassword() {
        Optional<AppUser> expected = underTest.findByEmailAndPassword("swaggerG@swagger.swag", "123");
        assertThat(expected).isNotNull();
    }

    @Test
    void findBySecret() {
        Optional<AppUser> expected = underTest.findBySecret("secret");
        assertThat(expected).isNotNull();
    }
}