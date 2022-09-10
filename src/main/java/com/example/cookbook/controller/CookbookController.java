package com.example.cookbook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookbookController {

    @RequestMapping("/")
    public String home() {
        return "hello";
    }

}
