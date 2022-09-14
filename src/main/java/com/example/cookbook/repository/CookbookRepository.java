package com.example.cookbook.repository;

import com.example.cookbook.model.Cookbook;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CookbookRepository extends CrudRepository<Cookbook, Integer> {

    Set<Cookbook> findAllByUserId(int userId);

}
