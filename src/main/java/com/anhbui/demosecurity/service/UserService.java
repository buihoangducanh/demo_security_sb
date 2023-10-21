package com.anhbui.demosecurity.service;

import com.anhbui.demosecurity.entity.User;

public interface UserService {
    void saveUser(User user);
    boolean isUserExists(String username);

    User findByUsername(String username);
}
