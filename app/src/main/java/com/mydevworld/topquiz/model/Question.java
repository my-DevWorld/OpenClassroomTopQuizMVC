package com.mydevworld.topquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Question implements Parcelable {
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex) {
        mQuestion = question;
        mChoiceList = choiceList;
        mAnswerIndex = answerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        if(choiceList == null || choiceList.isEmpty()){
            throw new IllegalArgumentException("Choice List cannot be null or empty");
        }
        mChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        if(answerIndex < 0 || answerIndex >= mChoiceList.size()){
            throw new IllegalArgumentException("Answer index out of bound");
        }
        mAnswerIndex = answerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiceList=" + mChoiceList +
                ", mAnswerIndex=" + mAnswerIndex +
                '}';
    }

    protected Question(Parcel in) {
        mQuestion = in.readString();
        mChoiceList = in.createStringArrayList();
        mAnswerIndex = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuestion);
        dest.writeStringList(mChoiceList);
        dest.writeInt(mAnswerIndex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
