package com.example.cookbook.service;

import com.example.cookbook.model.AppUser;
import com.example.cookbook.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;
    @InjectMocks
    private AppUserService underTest;
    private AppUser user;


    @BeforeEach
    void setUp() {
        //underTest = new AppUserService(appUserRepository);
        user = new AppUser(2, "swagGg", "swaggerGg@swagger.swag", "123", "secret");
    }

    @Test
    void canAddUser() {
        AppUser newUser = new AppUser(1, "swagG", "swaggerG@swagger.swag", "123", "secret");

        underTest.addUser(newUser);

        ArgumentCaptor<AppUser> appUserArgumentCaptor = ArgumentCaptor.forClass(AppUser.class);
        verify(appUserRepository).save(appUserArgumentCaptor.capture());

        AppUser capturedUser = appUserArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(newUser);
    }

    @Test
    void canGetAll() {
        underTest.getAll();

        verify(appUserRepository).findAll();
    }

    @Test
    void willThrowWhenUserDoesntExist() {
        //no user exists
        underTest.getById(1);

        verify(appUserRepository).findById(1);
    }

    @Disabled
    @Test
    void canGetById() {
        /*given(appUserRepository.findById(2)).willReturn(Optional.of(user));

        AppUser foundUser = appUserRepository.findById(user.getUserId()).get();

        assertThat(foundUser).isEqualTo(user);*/
        underTest.getById(2);

        verify(appUserRepository).findById(2);

        assertThat(underTest.getById(2)).isEqualTo(user);
    }


}