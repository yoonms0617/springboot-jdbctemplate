package com.example.springjdbc.repository;

import com.example.springjdbc.model.User;

import java.util.List;

public interface UserRepository {

    Long save(User user);

    User findById(Long id);

    User findByName(String name);

    User findByEmail(String email);

    List<User> findAll();

}
