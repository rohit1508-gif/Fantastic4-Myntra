package com.example.myntrahackathon.ModalClasses;

import java.util.List;

public class Question {
    String image;
    String question;
    String option1, option2, option3, option4;
    String correctOption;
    List<String> recommendations;

    public Question() {
    }

    public Question(String question, String image, String option1, String option2, String option3, String option4, String correctOption, List<String> recommendations) {
        this.image = image;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.recommendations = recommendations;
    }

    public String getImage() {
        return image;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }
}
