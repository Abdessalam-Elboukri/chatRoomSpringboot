package com.chatroomspring.app.controller;


import com.chatroomspring.app.dto.ResponseDto;
import com.chatroomspring.app.entity.UserApp;
import com.chatroomspring.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class userController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseDto register(@RequestBody UserApp userApp) throws IllegalAccessException {
        if(userApp==null){
            return new ResponseDto("400","please fill all your information");
        }
        return new ResponseDto("200" , "success", userService.register(userApp));
    }
}
