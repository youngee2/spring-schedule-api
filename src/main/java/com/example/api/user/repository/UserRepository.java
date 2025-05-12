package com.example.api.user.repository;

import com.example.api.user.entity.User;

public interface UserRepository {
    Long save(User user);
}
