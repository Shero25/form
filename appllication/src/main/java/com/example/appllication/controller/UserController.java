package com.example.appllication.controller;

import com.example.appllication.entity.Users;
import com.example.appllication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users users) {
        Users registeredUser = userService.registerUser(users);
        return ResponseEntity.ok(registeredUser);
    }
}
