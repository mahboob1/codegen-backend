package com.mhc.iamservice.service;

import com.mhc.iamservice.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}

