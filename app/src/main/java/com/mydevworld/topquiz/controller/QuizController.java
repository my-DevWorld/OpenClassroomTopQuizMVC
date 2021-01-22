package com.mydevworld.topquiz.controller;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mydevworld.topquiz.model.Question;

public class QuizController {
    public static int checkAnswer(Context context, View view, int getAnswerIndex, int score){
        int responseIndex = (int) view.getTag();
        if(responseIndex == getAnswerIndex){
            Toast.makeText(context, "Correct", Toast.LENGTH_SHORT).show();
            score++;
        }
        else {
            Toast.makeText(context, "Incorrect", Toast.LENGTH_SHORT).show();
        }
        return score;
    }

//    public static void displayQuestion(Question question, TextView textView, Button ...buttons){
//        textView.setText(question.getQuestion());
//        for (int i = 0; i < buttons.length; i++){
//        }
//    }
}





















