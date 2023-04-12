package com.chatroomspring.app.repository;

import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ConversationThreadRepository extends JpaRepository<ConversationThread,Long> {
    //ConversationThread findConversationThreadByUsers(Set<UserApp> users);
}
