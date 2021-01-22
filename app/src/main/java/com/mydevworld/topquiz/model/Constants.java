package com.mydevworld.topquiz.model;

import com.mydevworld.topquiz.view.GameActivity;

public class Constants {
    public static final int GAME_ACTIVITY_REQUEST_CODE = 101;
    public static final String BUNDLE_EXTRA_SCORE = GameActivity.class.getCanonicalName().concat("BUNDLE_EXTRA_SCORE");
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_NUMBER_OF_QUESTIONS = "numberOfQuestion";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";


    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRST_NAME = "PREF_KEY_FIRST_NAME";
}
