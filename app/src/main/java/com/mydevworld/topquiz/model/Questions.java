package com.mydevworld.topquiz.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Questions {
    private ArrayList<Question> mQuestion;

    public ArrayList<Question> generateQuestions(){
        mQuestion = new ArrayList<>();
        mQuestion.add(new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1));
        mQuestion.add(new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2));

        mQuestion.add(new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0));

        mQuestion.add(new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3));

        mQuestion.add(new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0));

        mQuestion.add(new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1));

        mQuestion.add(new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2));

        mQuestion.add(new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3));

        mQuestion.add(new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3));

        return mQuestion;
    }
}
