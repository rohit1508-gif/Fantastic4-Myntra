package com.example.myntrahackathon.ModalClasses;

public class Board {
    String name;
    int score;

    public Board() {
    }

    public Board(String name, int score) {
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
}
