package com.mydevworld.topquiz.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mydevworld.topquiz.R;
import com.mydevworld.topquiz.model.User;
import com.mydevworld.topquiz.utils.Essentials;

import static com.mydevworld.topquiz.model.Constants.BUNDLE_EXTRA_SCORE;
import static com.mydevworld.topquiz.model.Constants.GAME_ACTIVITY_REQUEST_CODE;
import static com.mydevworld.topquiz.model.Constants.PREF_KEY_FIRST_NAME;
import static com.mydevworld.topquiz.model.Constants.PREF_KEY_SCORE;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mUserNameInput;
    private Button mPlayBtn;
    private User mUser;

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();
    }

    private void setUp(){
        mUser = new User();
        mPreferences = getPreferences(MODE_PRIVATE);

        mGreetingText = findViewById(R.id.activity_main_greeting_txt);
        mUserNameInput = findViewById(R.id.activity_main_user_name_edtTxt);
        mPlayBtn = findViewById(R.id.activity_main_play_btn);
        mPlayBtn.setEnabled(false);

        greetUser();

        mUserNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayBtn.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayBtn.setOnClickListener(v -> {
            Essentials.hideSoftKeyboard(this, mPlayBtn);
            mUser.setUserName(mUserNameInput.getText().toString().trim());
            mPreferences.edit().putString(PREF_KEY_FIRST_NAME, mUser.getUserName()).apply();

            Intent goToGameActivity = new Intent(this, GameActivity.class);
            goToGameActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(goToGameActivity, GAME_ACTIVITY_REQUEST_CODE);
        });
    }

    private void greetUser(){
        String userName = mPreferences.getString(PREF_KEY_FIRST_NAME, null);
        if(userName != null){
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

            String fulltext = "Welcome back, " + userName
                    + "!\nYour last score was " + score
                    + ", will you do better this time?";

            mGreetingText.setText(fulltext);
            mUserNameInput.setText(userName);
            mUserNameInput.setSelection(userName.length());
            mUserNameInput.setEnabled(true);

            mPlayBtn.setEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            int score = 0;
            if (data != null) {
                score = data.getIntExtra(BUNDLE_EXTRA_SCORE, 0);
            }
            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
            greetUser();
        }
    }
}






























