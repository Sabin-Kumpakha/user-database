package com.sk.userdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sk.userdatabase.entity.UserEntity;
import com.sk.userdatabase.model.User;
import com.sk.userdatabase.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUsers() {
        ResponseEntity<List<User>> reponse = userService.getAllUsers();
        return ResponseEntity.status(reponse.getStatusCode()).body(reponse.getBody());
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody UserEntity user) {
        ResponseEntity<String> response = userService.addUser(user);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping("/updateuser")
    public ResponseEntity<String> updateUser(@RequestBody UserEntity user) {
        ResponseEntity<String> response = userService.updateUser(user);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<String> removeUser(@RequestBody UserEntity user) {
        ResponseEntity<String> response = userService.removeUser(user);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
