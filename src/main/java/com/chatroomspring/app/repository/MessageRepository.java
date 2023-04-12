package com.chatroomspring.app.repository;

import com.chatroomspring.app.entity.Message;
import com.chatroomspring.app.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllBySender(UserApp sender);
}
