package com.chatroomspring.app.controller;

import com.chatroomspring.app.dto.ResponseDto;
import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.Message;
import com.chatroomspring.app.entity.UserApp;
import com.chatroomspring.app.service.ConversationThreadService;
import com.chatroomspring.app.service.MessageService;
import com.chatroomspring.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    ConversationThreadService conversationThreadService;


    @Autowired
     SimpMessagingTemplate template;

    @MessageMapping("/save-message/{from}/{to}")
    public ResponseDto saveMessage(@PathVariable String from,
                                   @DestinationVariable Long to,
                                   @RequestBody Message message) throws Exception {
        UserApp sender = userService.findByEmail(from);
        ConversationThread receiver=conversationThreadService.getById(to);
        if(sender==null || receiver==null){
            throw new IllegalAccessException("Error while sending the message , try again");
        }
        message.setSender(sender);
        return new ResponseDto("200", "message sent", messageService.saveMessage(message) );

    }

    @MessageMapping("/get-messages/{sender}/{receivers}")
    public void getMessages(WebSocketSession session, @DestinationVariable String sender, @DestinationVariable String receivers) {
        UserApp sender1 = userService.findByEmail(sender);
        List<Message> messages = messageService.getMessagesBySender(sender1);
        ResponseDto response = new ResponseDto("200", "all messages", messages);

        try {
            session.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(response)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
