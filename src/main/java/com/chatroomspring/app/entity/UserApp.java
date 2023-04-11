package com.chatroomspring.app.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String userName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String phone;
    private String avatar;
    private Boolean isBanned;
    private Boolean isSubscribed;
    private LocalDateTime createdAt;
    @ManyToMany(mappedBy = "recipients")
    private List<Message> messages = new ArrayList<>();

    public UserApp(Long id, String userName, String email, String password, String phone, String avatar, Boolean isBanned, Boolean isSubscribed, LocalDateTime createdAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.avatar = avatar;
        this.isBanned = isBanned;
        this.isSubscribed = isSubscribed;
        this.createdAt = createdAt;
    }

    public UserApp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public Boolean getSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        isSubscribed = subscribed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
