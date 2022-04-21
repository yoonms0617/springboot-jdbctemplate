package com.example.springjdbc.service;

import com.example.springjdbc.dto.UserList;
import com.example.springjdbc.dto.UserRequest;
import com.example.springjdbc.dto.UserResponse;
import com.example.springjdbc.model.User;
import com.example.springjdbc.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long save(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id);
        return new UserResponse(user.getName(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public UserResponse findByName(String name) {
        User user = userRepository.findByName(name);
        return new UserResponse(user.getName(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return new UserResponse(user.getName(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public UserList findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(user -> new UserResponse(
                        user.getName(),
                        user.getEmail()
                )).collect(Collectors.toList());
        return new UserList(userResponses);
    }

}
