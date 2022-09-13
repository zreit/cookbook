package com.example.cookbook.controller;

import com.example.cookbook.model.AppUser;
import com.example.cookbook.model.Cookbook;
import com.example.cookbook.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @PostMapping("/register")
    public ResponseEntity<AppUser> create(@RequestBody AppUser newAppUser) {
        appUserRepository.save(newAppUser);
        return new ResponseEntity<AppUser>(newAppUser, HttpStatus.OK);
    }

    @GetMapping("/user/all")
    public ResponseEntity<Iterable<AppUser>> getAll() {
        Iterable<AppUser> allUserInDb = appUserRepository.findAll();
        return new ResponseEntity<Iterable<AppUser>>(allUserInDb, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<AppUser> getById(@RequestParam(value = "userId") int userId) {
        Optional<AppUser> userInDb = appUserRepository.findById(userId);
        if(userInDb.isPresent()) {
            return new ResponseEntity<AppUser>(userInDb.get(), HttpStatus.OK);
        }
        return new ResponseEntity("No user found with the id of " + userId, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteById(@RequestParam(value = "userId") int userId) {

        Optional<AppUser> userInDb = appUserRepository.findById(userId);

        if(userInDb.isPresent()) {
            appUserRepository.deleteById(userId);
            return new ResponseEntity("User with id of " + userId + " deleted", HttpStatus.OK);
        }
        return new ResponseEntity("User with id of " + userId + " not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/user")
    public ResponseEntity<AppUser> updateById(@RequestBody AppUser updatedUser) {

        Optional<AppUser> userInDb = appUserRepository.findById(updatedUser.getUserid());

        if(userInDb.isPresent()) {
            AppUser savedUser = appUserRepository.save(updatedUser);
            return new ResponseEntity<AppUser>(savedUser, HttpStatus.OK);
        }
        return new ResponseEntity("User with id of " + updatedUser.getUserid() + " not found", HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/user/updateName")
    public ResponseEntity<AppUser> updateUsernameById(@RequestParam(value = "userId") int userId, @RequestParam(value = "username") String username) {

        Optional<AppUser> userInDb = appUserRepository.findById(userId);

        if(userInDb.isPresent()) {
            userInDb.get().setUsername(username);
            AppUser savedUser = appUserRepository.save(userInDb.get());
            return new ResponseEntity<AppUser>(savedUser, HttpStatus.OK);

        }
        return new ResponseEntity("User with id of " + userId + " not found", HttpStatus.NOT_FOUND);
    }

}