package com.example.cookbook.repository;

import com.example.cookbook.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

}
