package com.solarflare.blogAppAPI.services;

import com.solarflare.blogAppAPI.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDto);
    UserDTO updateUser(UserDTO userDto, int userId);
    UserDTO getUserById(int userId);
    List<UserDTO>getAllUsers();
    void deleteUser(int userId);
}
