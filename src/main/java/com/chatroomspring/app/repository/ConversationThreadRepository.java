package com.chatroomspring.app.repository;

import com.chatroomspring.app.entity.ConversationThread;
import com.chatroomspring.app.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ConversationThreadRepository extends JpaRepository<ConversationThread,Long> {

    @Query("SELECT c FROM ConversationThread c JOIN c.users p WHERE p IN :users GROUP BY c HAVING COUNT(DISTINCT p) = :numParticipants")
    List<ConversationThread> findByUsers(@Param("users") Set<UserApp> users, @Param("numParticipants") Long numParticipants);

}
