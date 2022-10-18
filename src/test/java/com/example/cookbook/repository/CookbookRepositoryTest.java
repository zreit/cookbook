package com.example.cookbook.repository;

import com.example.cookbook.model.AppUser;
import com.example.cookbook.model.Cookbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CookbookRepositoryTest {

    @Autowired
    private CookbookRepository underTestCookbook;

    @Autowired
    private AppUserRepository underTestAppUser;

    @BeforeEach
    void buildTestObjects() {
        AppUser user = new AppUser(1, "swagToni", "swagToni@swagger.swag", "123", "secret");
        underTestAppUser.save(user);
        Cookbook cookbook = new Cookbook( 1,"Test", 1);
        underTestCookbook.save(cookbook);
    }

    @AfterEach
    void tearDownTestObjects() {
        underTestCookbook.deleteAll();
        underTestAppUser.deleteAll();
    }

    @Test
    void findAllByUserId() {
        Set<Cookbook> expected = underTestCookbook.findAllByUserId(1);
        assertThat(expected).isNotNull();
    }
}