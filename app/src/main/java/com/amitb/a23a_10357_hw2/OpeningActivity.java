package com.amitb.a23a_10357_hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class OpeningActivity extends AppCompatActivity {

    private MaterialButton arrows_slow;
    private MaterialButton arrows_fast;
    private MaterialButton sensors;
    private AppCompatImageView car_gif;

    private String mode;
    private String speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        findViews();
        initViews();
        arrows_slow.setOnClickListener(view -> start());
        arrows_fast.setOnClickListener(view -> start());
        sensors.setOnClickListener(view -> start());
    }

    private void initViews() {
        int car_res = R.drawable.car;
        Glide.with(this).load(car_res).into(car_gif);
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
        car_gif = findViewById(R.id.opening_gif);
        arrows_slow = findViewById(R.id.opening_button_arrows_mode_1);
        arrows_fast = findViewById(R.id.opening_button_arrows_mode_2);
        sensors = findViewById(R.id.opening_button_sensor_mode);
    }
}