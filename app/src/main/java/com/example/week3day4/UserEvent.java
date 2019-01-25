package com.example.week3day4;

import com.example.week3day4.GitHubUser.User;

public class UserEvent {
    User user = new User();

    public UserEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
