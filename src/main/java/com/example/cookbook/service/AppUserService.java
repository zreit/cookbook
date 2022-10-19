package com.example.cookbook.service;

import com.example.cookbook.model.AppUser;
import com.example.cookbook.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public ResponseEntity<AppUser> addUser(@RequestBody AppUser newAppUser) {
        newAppUser.setSecret(UUID.randomUUID().toString());
        appUserRepository.save(newAppUser);
        return new ResponseEntity<AppUser>(newAppUser, HttpStatus.OK);
    }

    public ResponseEntity<Iterable<AppUser>> getAll() {
        Iterable<AppUser> allUserInDb = appUserRepository.findAll();
        return new ResponseEntity<Iterable<AppUser>>(allUserInDb, HttpStatus.OK);
    }

    public ResponseEntity<AppUser> getById(@RequestParam(value = "userId") int userId) {
        Optional<AppUser> userInDb = appUserRepository.findById(userId);
        if(userInDb.isPresent()) {
            return new ResponseEntity<AppUser>(userInDb.get(), HttpStatus.OK);
        }
        return new ResponseEntity("No user found with the id of " + userId, HttpStatus.NOT_FOUND);
    }
}
