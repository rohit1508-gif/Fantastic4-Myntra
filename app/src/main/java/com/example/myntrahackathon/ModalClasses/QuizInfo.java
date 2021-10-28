package com.example.myntrahackathon.ModalClasses;

public class QuizInfo {
    String name;
    String id;
    String description;

    public QuizInfo(String name, String id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
