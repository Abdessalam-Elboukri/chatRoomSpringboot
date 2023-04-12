package com.chatroomspring.app.service;

import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.UserApp;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface ConversationThreadService {

    ConversationThread getById(Long id);
}