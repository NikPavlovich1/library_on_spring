package org.example.library_on_spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.library_on_spring.dto.UserCreateEditDto;
import org.example.library_on_spring.dto.UserReadDto;
import org.example.library_on_spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserReadDto>> findAll() {
        List<UserReadDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> findById(@PathVariable Long id) {
        UserReadDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<UserReadDto> create(@RequestBody UserCreateEditDto userDto) {
        UserReadDto createdUser = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserReadDto> update(@PathVariable Long id, @RequestBody UserCreateEditDto userDto) {
        UserReadDto updatedUser = userService.update(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}