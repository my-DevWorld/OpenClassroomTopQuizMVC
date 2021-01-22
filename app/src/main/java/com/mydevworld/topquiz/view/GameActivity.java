package com.mydevworld.topquiz.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mydevworld.topquiz.R;
import com.mydevworld.topquiz.controller.QuizController;
import com.mydevworld.topquiz.model.Question;
import com.mydevworld.topquiz.model.QuestionBank;
import com.mydevworld.topquiz.model.QuestionBankFactory;
import com.mydevworld.topquiz.model.Questions;

import static com.mydevworld.topquiz.model.Constants.BUNDLE_EXTRA_SCORE;
import static com.mydevworld.topquiz.model.Constants.BUNDLE_STATE_NUMBER_OF_QUESTIONS;
import static com.mydevworld.topquiz.model.Constants.BUNDLE_STATE_QUESTION;
import static com.mydevworld.topquiz.model.Constants.BUNDLE_STATE_SCORE;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionTextView;
    private Button mAnswerBtn1;
    private Button mAnswerBtn2;
    private Button mAnswerBtn3;
    private Button mAnswerBtn4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mScore;
    private int mNumberOfQuestions;

    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setUp();

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            System.out.println("---------------- onCreate ------------->> mScore " + mScore);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_NUMBER_OF_QUESTIONS);
            System.out.println("---------------- onCreate ------------->> " + mNumberOfQuestions);
            mCurrentQuestion = savedInstanceState.getParcelable(BUNDLE_STATE_QUESTION);
//            System.out.println("---------------- onCreate ------------->> " + mCurrentQuestion.toString());

        } else {
            mScore = 0;
            mNumberOfQuestions = 4;
            generateQuestion();
        }
        displayQuestion(mCurrentQuestion);
    }

    private void setUp(){
        mEnableTouchEvents = true;

        mQuestionTextView = findViewById(R.id.activity_game_question_text);
        mAnswerBtn1 = findViewById(R.id.activity_game_answer1_btn);
        mAnswerBtn2 = findViewById(R.id.activity_game_answer2_btn);
        mAnswerBtn3 = findViewById(R.id.activity_game_answer3_btn);
        mAnswerBtn4 = findViewById(R.id.activity_game_answer4_btn);

        mAnswerBtn1.setTag(0);
        mAnswerBtn2.setTag(1);
        mAnswerBtn3.setTag(2);
        mAnswerBtn4.setTag(3);

        mAnswerBtn1.setOnClickListener(this);
        mAnswerBtn2.setOnClickListener(this);
        mAnswerBtn3.setOnClickListener(this);
        mAnswerBtn4.setOnClickListener(this);

    }

    private void generateQuestion(){
        mQuestionBank = QuestionBankFactory.questionBank(new Questions().generateQuestions());
        mCurrentQuestion = mQuestionBank.getQuestion();
    }

    //region Display question and possible answers on screen
    private void displayQuestion(Question question){
        mQuestionTextView.setText(question.getQuestion());
        mAnswerBtn1.setText(question.getChoiceList().get(0));
        mAnswerBtn2.setText(question.getChoiceList().get(1));
        mAnswerBtn3.setText(question.getChoiceList().get(2));
        mAnswerBtn4.setText(question.getChoiceList().get(3));
    }
    //endregion

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Well done!!!")
                .setMessage("Your score is: " + mScore)
                .setPositiveButton("OK", (dialog, which) -> {
                    Intent intent = new Intent();
                    intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                    setResult(RESULT_OK, intent);
                    finish();
                }).setCancelable(false)
                .create()
                .show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_NUMBER_OF_QUESTIONS, mNumberOfQuestions);
        outState.putParcelable(BUNDLE_STATE_QUESTION, mCurrentQuestion);
        System.out.println("---------------- onSaveInstanceState ------------->>  " + mScore + " ------- " + mNumberOfQuestions);
//        System.out.println("---------------- onSaveInstanceState ------------->>  " + mCurrentQuestion.toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
//        int responseIndex = (int) v.getTag();
//        if(responseIndex == mQuestionBank.getQuestion().getAnswerIndex()){
//            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
//            mScore++;
//        }
//        else {
//            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
//        }

        mScore = QuizController.checkAnswer(this, v, mCurrentQuestion.getAnswerIndex(),mScore);

        mEnableTouchEvents = false;

        new Handler().postDelayed(() -> {
            if(--mNumberOfQuestions == 0){
                endGame();
            }
            else {
                generateQuestion();
                displayQuestion(mCurrentQuestion);
            }
            mEnableTouchEvents =true;
        }, 2000);
    }
}







