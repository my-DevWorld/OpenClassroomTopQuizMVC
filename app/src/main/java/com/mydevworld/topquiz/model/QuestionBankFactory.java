package com.mydevworld.topquiz.model;

import java.util.ArrayList;

public class QuestionBankFactory {
    public static QuestionBank questionBank(ArrayList<Question> questions) {
        return new QuestionBank(questions);

    }
}
