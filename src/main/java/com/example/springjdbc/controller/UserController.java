package com.example.springjdbc.controller;

import com.example.springjdbc.dto.UserList;
import com.example.springjdbc.dto.UserRequest;
import com.example.springjdbc.dto.UserResponse;
import com.example.springjdbc.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody UserRequest userRequest) {
        Long save = userService.save(userRequest);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Long id) {
        UserResponse userResponse = userService.findById(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/find-name/{name}")
    public ResponseEntity<UserResponse> findByName(@PathVariable("name") String name) {
        UserResponse userResponse = userService.findByName(name);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable("email") String email) {
        UserResponse userResponse = userService.findByEmail(email);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/find/all")
    public ResponseEntity<UserList> findAll() {
        UserList userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

}
