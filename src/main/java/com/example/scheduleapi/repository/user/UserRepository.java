package com.example.scheduleapi.repository.user;

import com.example.scheduleapi.domain.User;

public interface UserRepository {
    Long save(User user);
}
