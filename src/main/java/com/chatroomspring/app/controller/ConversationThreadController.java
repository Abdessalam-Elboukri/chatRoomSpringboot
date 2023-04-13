package com.chatroomspring.app.controller;


import com.chatroomspring.app.dto.ResponseDto;
import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.UserApp;
import com.chatroomspring.app.service.ConversationThreadService;
import com.chatroomspring.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/thread")
public class ConversationThreadController {

    @Autowired
    ConversationThreadService conversationThreadService;

    @Autowired
    UserService userService;


    @GetMapping("/get-thread/{id}")
    public ResponseDto getOneConversationThread(@PathVariable Long id){
        ConversationThread thread = conversationThreadService.getById(id);
        return new ResponseDto("200", "all message for this thread", thread);
    }

    @GetMapping("/get-my-threads/{email}")
    public ResponseDto getMyConversations(@PathVariable String email){
        UserApp me = userService.findByEmail(email);
        if(me==null){
            return new ResponseDto("400", "User not found");
        }
        return new ResponseDto("200", "Retrieve all your Conversations" , conversationThreadService.getByUser(me) );
    }



}
