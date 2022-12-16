package com.amitb.a23a_10357_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.amitb.a23a_10357_hw2.interfaces.CallBack_userProtocol;
import com.amitb.a23a_10357_hw2.views.ListFragment;
import com.amitb.a23a_10357_hw2.views.MapFragment;

public class FinishingActivity extends AppCompatActivity {

    public static final String KEY_SCORE = "KEY_SCORE";
    private TextView score_LBL;
    private FrameLayout lower;
    private ListFragment listFragment;
    private MapFragment mapFragment;

    CallBack_userProtocol callBack = name -> showUserName(name);

    private void showUserName(String name) {
        Double latitude = 32.085973341670496,longitude = 34.87092853235433;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishing);
        initFragments();
        findViews();
        Intent previousIntent = getIntent();
        String score = previousIntent.getStringExtra(KEY_SCORE);
        score_LBL.setText("Your score is: " + score);
//        getSupportFragmentManager().beginTransaction().add(R.id.upper_frame,listFragment).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.lower_frame,mapFragment).commit();
    }

    private void initFragments() {
//        listFragment = new ListFragment();
//        listFragment.setCallback(callBack);
        mapFragment = new MapFragment();
    }

    public void findViews(){
        score_LBL = findViewById(R.id.text_score);
    }
}