package com.example.myntrahackathon.ModalClasses;

public class LeaderboardUser {
    String userId;
    String name;
    int score;

    public LeaderboardUser() {
    }

    public LeaderboardUser(String userId, String name, int score) {
        this.userId = userId;
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
