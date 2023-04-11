package com.chatroomspring.app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ConversationThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "thread_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "thread_id"))

    private Set<UserApp> users  = new HashSet<>();

    public ConversationThread() {
    }

    public ConversationThread(Long id, Set<UserApp> users) {
        this.id = id;
        this.users = users;
    }
}
