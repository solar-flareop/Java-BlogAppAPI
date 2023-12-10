package com.solarflare.blogAppAPI.controllers;

import com.solarflare.blogAppAPI.payloads.APIResponse;
import com.solarflare.blogAppAPI.payloads.UserDTO;
import com.solarflare.blogAppAPI.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUserDto = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int userId) {
        UserDTO user = this.userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO>allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("id") int userId) {
        UserDTO updatedUser = this.userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable("id") int userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new APIResponse("User deleted successfully", true), HttpStatus.OK);
    }
}
