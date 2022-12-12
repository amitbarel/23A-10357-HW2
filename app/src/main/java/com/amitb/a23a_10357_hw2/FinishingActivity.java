package com.amitb.a23a_10357_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FinishingActivity extends AppCompatActivity {

    public static final String KEY_SCORE = "KEY_SCORE";

    private TextView score_LBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishing);

        findViews();

        Intent previousIntent = getIntent();
        String score = previousIntent.getStringExtra(KEY_SCORE);
        score_LBL.setText("Your score is: " + score);
    }

    public void findViews(){
        score_LBL = findViewById(R.id.text_score);
    }
}