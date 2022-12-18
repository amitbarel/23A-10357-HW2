package com.amitb.a23a_10357_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.amitb.a23a_10357_hw2.interfaces.CallBack_userProtocol;
import com.amitb.a23a_10357_hw2.utils.DataManager;
import com.amitb.a23a_10357_hw2.views.ListFragment;
import com.amitb.a23a_10357_hw2.views.MapFragment;

import java.util.ArrayList;

public class FinishingActivity extends AppCompatActivity {

    public static final String KEY_SCORE = "KEY_SCORE";
    private TextView score_LBL;
    private ListFragment listFragment;
    private MapFragment mapFragment;

    CallBack_userProtocol callBack = new CallBack_userProtocol() {
        @Override
        public void sendLoc(double latitude, double longitude) {
            mapFragment.zoom(latitude,longitude);
        }

        @Override
        public void showTop5(ArrayList<Player> players) {
            mapFragment.setOnMap(players);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishing);
        initFragments();
        findViews();
        Intent previousIntent = getIntent();
        String score = previousIntent.getStringExtra(KEY_SCORE);
        score_LBL.setText("Your score is: " + score);
    }

    private void initFragments() {
        listFragment = new ListFragment();
        listFragment.setCallback(callBack);
        mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.upper_frame,listFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.lower_frame,mapFragment).commit();
    }

    public void findViews(){
        score_LBL = findViewById(R.id.text_score);
    }
}