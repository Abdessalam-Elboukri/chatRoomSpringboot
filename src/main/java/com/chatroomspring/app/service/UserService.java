package com.chatroomspring.app.service;

import com.chatroomspring.app.entity.Message;
import com.chatroomspring.app.entity.UserApp;

public interface UserService {
    UserApp register(UserApp userApp) throws IllegalAccessException;

    UserApp findByEmail(String email);
}

