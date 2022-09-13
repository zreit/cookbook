package com.example.cookbook.repository;

import com.example.cookbook.model.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByEmailAndPassword(String email, String password);


}
