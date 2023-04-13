package com.chatroomspring.app.service;

import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.Message;
import com.chatroomspring.app.entity.UserApp;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MessageService {
    Message saveMessage(Message message) throws Exception;

    List<Message> getMessagesBySender(UserApp sender);

}
