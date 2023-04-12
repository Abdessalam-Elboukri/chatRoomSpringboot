package com.chatroomspring.app.service.Imp;

import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.repository.ConversationThreadRepository;
import com.chatroomspring.app.service.ConversationThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationThreadServiceImp implements ConversationThreadService {
    @Autowired
    ConversationThreadRepository conversationThreadRepository;
    @Override
    public ConversationThread getById(Long id) {
        return conversationThreadRepository.findById(id).get();
    }
}
