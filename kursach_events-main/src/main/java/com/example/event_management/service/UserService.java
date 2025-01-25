package com.example.event_management.service;

import com.example.event_management.dto.UserDto;
import com.example.event_management.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}