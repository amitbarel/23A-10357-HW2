package com.amitb.a23a_10357_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class OpeningActivity extends AppCompatActivity {

    private MaterialButton arrows_slow;
    private MaterialButton arrows_fast;
    private MaterialButton sensors;

    private String mode;
    private String speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        findViews();
        arrows_slow.setOnClickListener(view -> start());
        arrows_fast.setOnClickListener(view -> start());
        sensors.setOnClickListener(view -> start());
    }

    private void start() {
        if (arrows_slow.isChecked() || arrows_fast.isChecked()){
            mode = "arrows";
            if(arrows_slow.isChecked()){
                speed = "slow";
            }else{
                speed = "fast";
            }
        }else if (sensors.isChecked()){
            mode = "sensors";
        }
        Intent gameIntent = new Intent(this,GameActivity.class);
        gameIntent.putExtra(GameActivity.KEY_MODE,mode);
        gameIntent.putExtra(GameActivity.KEY_SPEED,speed);
        startActivity(gameIntent);
        finish();
    }

    private void findViews() {
        arrows_slow = findViewById(R.id.opening_button_arrows_mode_1);
        arrows_fast = findViewById(R.id.opening_button_arrows_mode_2);
        sensors = findViewById(R.id.opening_button_sensor_mode);
    }
}