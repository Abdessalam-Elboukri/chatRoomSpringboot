package com.chatroomspring.app.service.Imp;

import com.chatroomspring.app.cryptography.CryptoUtils;
import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.UserApp;
import com.chatroomspring.app.repository.ConversationThreadRepository;
import com.chatroomspring.app.service.ConversationThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ConversationThreadServiceImp implements ConversationThreadService {
    @Autowired
    ConversationThreadRepository conversationThreadRepository;

    @Autowired
    CryptoUtils cryptoUtils;


    @Override
    public ConversationThread getById(Long id) {
        ConversationThread thread= conversationThreadRepository.findById(id).orElseThrow();
        thread.getUsers().forEach(userApp -> userApp.getMessages().forEach(message -> {
            try {
                message.setContent(cryptoUtils.decrypt(message.getContent(), message.getCryptKey()));
                message.setCryptKey(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));
        return thread;
    }


    @Override
    public List<ConversationThread> getByUser(UserApp userApp) {
        Set<UserApp> user= new HashSet<>();
        user.add(userApp);
        List<ConversationThread>  threads= conversationThreadRepository.findByUsers(user,1L);
        threads.forEach(th ->th.getUsers().forEach(userApp1 -> userApp1.setMessages(null)) );
        return threads;
    }
}
