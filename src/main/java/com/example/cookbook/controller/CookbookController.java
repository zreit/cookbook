package com.example.cookbook.controller;

import com.example.cookbook.model.Cookbook;
//import com.example.cookbook.repository.CookbookRepository;
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
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> home(@RequestParam(value = "name") String name) {
        return new ResponseEntity<String>("hello " + name, HttpStatus.OK);
    }

    @PostMapping("/cookbook")
    public ResponseEntity<Cookbook> create(@RequestBody Cookbook newCookbook) {
        cookbookRepository.save(newCookbook);
        return new ResponseEntity<Cookbook>(newCookbook, HttpStatus.OK);
    }

    @GetMapping("/cookbook/all")
    public ResponseEntity<Iterable<Cookbook>> getAll() {
        Iterable<Cookbook> allCookbooksInDb = cookbookRepository.findAll();
        return new ResponseEntity<Iterable<Cookbook>>(allCookbooksInDb, HttpStatus.OK);
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

}