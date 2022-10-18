package com.example.cookbook.controller;

import com.example.cookbook.model.AppUser;
import com.example.cookbook.repository.AppUserRepository;
import com.example.cookbook.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> create(@RequestBody AppUser newAppUser) {
        return appUserService.addUser(newAppUser);
    }

    @GetMapping("/user/all")
    public ResponseEntity<Iterable<AppUser>>getAll() {
        return appUserService.getAll();
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

        Optional<AppUser> userInDb = appUserRepository.findById(updatedUser.getUserId());

        if(userInDb.isPresent()) {
            AppUser savedUser = appUserRepository.save(updatedUser);
            return new ResponseEntity<AppUser>(savedUser, HttpStatus.OK);
        }
        return new ResponseEntity("User with id of " + updatedUser.getUserId() + " not found", HttpStatus.NOT_FOUND);
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

    @GetMapping("/validate")
    private ResponseEntity<String> validate(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {

        var validUser = appUserRepository.findByEmailAndPassword(email, password);

        if(validUser.isPresent()) {
            return new ResponseEntity<String>("API Secret: " + validUser.get().getSecret(), HttpStatus.OK);
        }
        return new ResponseEntity("Wrong credentials/No account found", HttpStatus.NOT_FOUND);
    }

}
