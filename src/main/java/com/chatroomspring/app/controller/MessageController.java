package com.chatroomspring.app.controller;

import com.chatroomspring.app.dto.ResponseDto;
import com.chatroomspring.app.entity.Message;
import com.chatroomspring.app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/save-message/{from}/{to}")
    public ResponseDto saveMessage(@PathVariable String from,
                                   @PathVariable String to,
                                   @RequestBody Message message){
        return null;
    }
}
