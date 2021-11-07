package com.example.myntrahackathon.ModalClasses;

public class LoginUser {
    String password, username;
    int user_id, score;

    public LoginUser(int user_id, String password, int score, String username) {
        this.user_id = user_id;
        this.password = password;
        this.score = score;
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }
}
