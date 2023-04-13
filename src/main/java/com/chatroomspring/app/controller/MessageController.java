package com.chatroomspring.app.controller;

import com.chatroomspring.app.dto.ResponseDto;
import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.Message;
import com.chatroomspring.app.entity.UserApp;
import com.chatroomspring.app.service.ConversationThreadService;
import com.chatroomspring.app.service.MessageService;
import com.chatroomspring.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;


    @Autowired
     SimpMessagingTemplate template;

    @PostMapping("/save-message/{from}/{to}")
    public ResponseDto saveMessage(@PathVariable String from,
                                   @PathVariable String to,
                                   @RequestBody Message message) throws Exception {
        UserApp sender = userService.findByEmail(from);
        UserApp receiver=userService.findByEmail(to);
        if(sender==null || receiver==null){
            throw new IllegalAccessException("Error while sending the message , try agin");
        }
        message.setSender(sender);
        message.getRecipients().add(receiver);
        return new ResponseDto("200", "message sent", messageService.saveMessage(message) );

    }

    @GetMapping("/get-messages/{sender}/{receivers}")
    public ResponseDto getMessages(@PathVariable String sender,
                                   @PathVariable String receivers){
        UserApp sender1 = userService.findByEmail(sender);
        List<Message> messages = messageService.getMessagesBySender(sender1);
        //template.convertAndSend("/topic/message",new ResponseDto("200", "all messages", messages));
        return new ResponseDto("200", "all messages", messages);

    }




}
