package com.example.cookbook.controller;

import com.example.cookbook.model.Cookbook;
//import com.example.cookbook.repository.CookbookRepository;
import com.example.cookbook.repository.AppUserRepository;
import com.example.cookbook.repository.CookbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CookbookController {

    @Autowired
    private CookbookRepository cookbookRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @PostMapping("/cookbook")
    public ResponseEntity<Cookbook> create(@RequestBody Cookbook newCookbook) {
        cookbookRepository.save(newCookbook);
        return new ResponseEntity<Cookbook>(newCookbook, HttpStatus.OK);
    }

    @GetMapping("/cookbook/all")
    public ResponseEntity<Iterable<Cookbook>> getAll(@RequestHeader("api-secret") String secret) {

        var userBySecret = appUserRepository.findBySecret(secret);

        if(userBySecret.isPresent()) {
            Iterable<Cookbook> allCookbooksInDb = cookbookRepository.findAllByUserId(userBySecret.get().getUserId());
            return new ResponseEntity<Iterable<Cookbook>>(allCookbooksInDb, HttpStatus.OK);
        }
        return new ResponseEntity("Invalid API secret", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/cookbook")
    public ResponseEntity<Cookbook> getById(@RequestParam(value = "cookbookId") int cookbookId) {
        Optional<Cookbook> cookbookInDb = cookbookRepository.findById(cookbookId);
        if(cookbookInDb.isPresent()) {
            return new ResponseEntity<Cookbook>(cookbookInDb.get(), HttpStatus.OK);
        }
        return new ResponseEntity("No cookbook found with the id of " + cookbookId, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/cookbook")
    public ResponseEntity deleteById(@RequestParam(value = "cookbookId") int cookbookId) {

        Optional<Cookbook> cookbookInDb = cookbookRepository.findById(cookbookId);

        if(cookbookInDb.isPresent()) {
            cookbookRepository.deleteById(cookbookId);
            return new ResponseEntity("Cookbook with id of " + cookbookId + " deleted", HttpStatus.OK);
        }
        return new ResponseEntity("Cookbook with id of " + cookbookId + " not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/cookbook")
    public ResponseEntity<Cookbook> updateById(@RequestBody Cookbook updatedCookbook) {

        Optional<Cookbook> cookbookInDb = cookbookRepository.findById(updatedCookbook.getCookbookId());

        if(cookbookInDb.isPresent()) {
            Cookbook savedCookbook = cookbookRepository.save(updatedCookbook);
            return new ResponseEntity<Cookbook>(savedCookbook, HttpStatus.OK);
        }
        return new ResponseEntity("Cookbook with id of " + updatedCookbook.getCookbookId() + " not found", HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/cookbook/updateName")
    public ResponseEntity<Cookbook> updateNameById(@RequestParam(value = "cookbookId") int cookbookId, @RequestParam(value = "name") String name) {

        Optional<Cookbook> cookbookInDb = cookbookRepository.findById(cookbookId);

        if(cookbookInDb.isPresent()) {
            cookbookInDb.get().setName(name);
            Cookbook savedCookbook = cookbookRepository.save(cookbookInDb.get());
            return new ResponseEntity<Cookbook>(savedCookbook, HttpStatus.OK);

        }
        return new ResponseEntity("Cookbook with id of " + cookbookId + " not found", HttpStatus.NOT_FOUND);
    }

}
