package com.chatroomspring.app.controller;

import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.Message;
import com.chatroomspring.app.entity.UserApp;
import com.chatroomspring.app.service.ConversationThreadService;
import com.chatroomspring.app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class chatController {

    @Autowired
     SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    MessageService messageService;

    @Autowired
    ConversationThreadService conversationThreadService;

    @MessageMapping("/chat/{to}") //to = nome canale
    public void sendMessage(@DestinationVariable String to , Message message) throws Exception {
        System.out.println("handling send message: " + message + " to: " + to);
        message.setTime(LocalDateTime.now());
        message = messageService.saveMessage(message);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }

    @PostMapping("/getChats")
    public List<ConversationThread> getChats(@RequestBody UserApp user){
        return conversationThreadService.getByUser(user);
    }

    //returns an empty list if the chat doesn't exist
    @PostMapping("/getMessages")
        public List<Message> getMessages(@RequestBody Long chat) {
        ConversationThread ce = conversationThreadService.getById(chat);

        if(ce != null) {
            return messageService.getByThread(ce.getId());
        }
        else{
            return new ArrayList<Message>();
        }
    }

    //finds the chat whose name is the parameter, if it doesn't exist it gets created, the ID gets returned either way


}
