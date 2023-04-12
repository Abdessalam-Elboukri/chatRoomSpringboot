package com.chatroomspring.app.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Message {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDate time;
    private String cryptKey;
    @ManyToOne
    private UserApp sender;

    @ManyToOne
    private ConversationThread conversationThread;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "message_recipient",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id"))

    private Set<UserApp> recipients  = new HashSet<>();

    public Message() {
    }

    public Message(Long id, String content, LocalDate time, UserApp sender, Set<UserApp> recipients) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.sender = sender;
        this.recipients = recipients;
    }

    public String getCryptKey() {
        return cryptKey;
    }

    public void setCryptKey(String cryptKey) {
        this.cryptKey = cryptKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public UserApp getSender() {
        return sender;
    }

    public void setSender(UserApp sender) {
        this.sender = sender;
    }

    public Set<UserApp> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<UserApp> recipients) {
        this.recipients = recipients;
    }
}
