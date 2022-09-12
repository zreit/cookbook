package com.example.cookbook.repository;

import com.example.cookbook.model.Cookbook;
import org.springframework.data.repository.CrudRepository;

public interface CookbookRepository extends CrudRepository<Cookbook, Integer> {

}
