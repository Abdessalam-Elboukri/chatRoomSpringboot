package com.chatroomspring.app.service.Imp;

import com.chatroomspring.app.entity.UserApp;
import com.chatroomspring.app.repository.UserRepository;
import com.chatroomspring.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserApp register(UserApp userApp) throws IllegalAccessException {
        if(userApp==null){
            throw
                    new IllegalAccessException("Please fill Your information");
        }
        else if(userApp.getEmail()==null ||
                userApp.getPassword()==null ||
                userApp.getPhone()==null ||
                userApp.getUserName()==null ){
            throw
                    new IllegalAccessException("Please fill all information");
        }
        userApp.setCreatedAt(LocalDateTime.now());
        //userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        userApp.setBanned(false);
        userApp.setSubscribed(false);
        return userRepository.save(userApp);
    }
}
