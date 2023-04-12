package com.chatroomspring.app.repository;

import com.chatroomspring.app.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserApp,Long> {
    UserApp findByEmail(String email);
}
