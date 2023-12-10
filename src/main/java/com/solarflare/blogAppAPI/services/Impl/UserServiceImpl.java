package com.solarflare.blogAppAPI.services.Impl;

import com.solarflare.blogAppAPI.entities.User;
import com.solarflare.blogAppAPI.exceptions.ResourceNotFoundException;
import com.solarflare.blogAppAPI.payloads.UserDTO;
import com.solarflare.blogAppAPI.repositories.UserRepo;
import com.solarflare.blogAppAPI.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = this.DtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.UserToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepo.save(user);
        return this.UserToDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.UserToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> usersDto = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return usersDto;
    }

    @Override
    public void deleteUser(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }

    public User DtoToUser(UserDTO userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
//      user.setId(userDto.getId());

    }

    public UserDTO UserToDto(User user) {
        UserDTO userDto = this.modelMapper.map(user, UserDTO.class);
        return userDto;
    }
}
