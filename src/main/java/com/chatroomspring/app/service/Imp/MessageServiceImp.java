package com.chatroomspring.app.service.Imp;

import com.chatroomspring.app.cryptography.CryptoUtils;
import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.Message;
import com.chatroomspring.app.entity.UserApp;
import com.chatroomspring.app.repository.ConversationThreadRepository;
import com.chatroomspring.app.repository.MessageRepository;
import com.chatroomspring.app.service.MessageService;
import com.chatroomspring.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class MessageServiceImp implements MessageService {

    @Autowired
    CryptoUtils cryptoUtils;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ConversationThreadRepository threadRepository;

    @Autowired
    UserService userService;




    @Override
    public Message saveMessage(Message message) throws Exception {
        if(message==null){
            throw new IllegalAccessException("Error while sending the message");
        }
        if(message.getRecipients()==null||
        message.getSender()==null){
            throw
                    new IllegalAccessException("Error while sending the message");
        }if(message.getContent()==null){
            throw
                    new IllegalAccessException("Please provide  a valid message");
        }

        ConversationThread thread = new ConversationThread();
        Set<UserApp> collection=new HashSet<>() ;
        collection.add(message.getSender());
        collection.addAll(message.getRecipients());

        List<ConversationThread> checkThreadIsAlreadyStarted=threadRepository.findByUsers(collection,(long) collection.size());

                if(checkThreadIsAlreadyStarted.isEmpty()){
                    if(thread.getUsers().size()<2){
                        Optional<UserApp> receiver= Optional.ofNullable(userService.findByEmail(message.getRecipients().stream().findFirst().orElseThrow().getEmail()));
                        if (receiver.isPresent()) {
                            thread.setName(receiver.orElseThrow().getUserName());
                        }
                    }
                thread.getUsers().addAll(collection);
                thread.setStartedAt(LocalDateTime.now());
                threadRepository.save(thread);
                }

        message.setTime(LocalDateTime.now());
        String key=cryptoUtils.generateKey();
        message.setCryptKey(key);
        message.setContent(cryptoUtils.encrypt(message.getContent(),key));
        return messageRepository.save(message) ;
    }

    @Override
    public List<Message> getMessagesBySender(UserApp sender) {
        List<Message> data= messageRepository.findAllBySender(sender);
        data.forEach(message -> {
            try {
                message.setContent(cryptoUtils.decrypt(message.getContent(),message.getCryptKey()));
                message.setCryptKey(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return data;
    }
}
