package com.example.myntrahackathon.ModalClasses;

public class Question {
    String image, question, option1, option2, option3, option4, correctOption, recom1, recom2;

    public Question(String question, String image, String option1, String option2, String option3, String option4, String correctOption, String recom1, String recom2) {
        this.image = image;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.recom1 = recom1;
        this.recom2 = recom2;
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

    public String getRecom1() {
        return recom1;
    }

    public String getRecom2() {
        return recom2;
    }
}
