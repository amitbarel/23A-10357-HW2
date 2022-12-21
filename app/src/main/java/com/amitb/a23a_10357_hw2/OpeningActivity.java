package com.amitb.a23a_10357_hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class OpeningActivity extends AppCompatActivity {

    private ToggleButton speedTGL;
    private MaterialButton arrows_BTN;
    private MaterialButton sensors_BTN;
    private AppCompatImageView car_gif;
    private MaterialButton scoreList_BTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        findViews();
        initViews();
        arrows_BTN.setOnClickListener(view -> startArrows());
        sensors_BTN.setOnClickListener(view -> startSensors());
        scoreList_BTN.setOnClickListener(view -> goToScoreList());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initViews() {
        int car_res = R.drawable.car;
        Glide.with(this).load(car_res).into(car_gif);
    }

    private void startArrows() {
        Intent arrowsIntent = new Intent(this,GameActivity.class);
        arrowsIntent.putExtra(GameActivity.KEY_MODE,arrows_BTN.getText().toString());
        arrowsIntent.putExtra(GameActivity.KEY_SPEED,speedTGL.getText());
        startActivity(arrowsIntent);
        finish();
    }

    private void startSensors() {
        Intent sensorsIntent = new Intent(this,GameActivity.class);
        sensorsIntent.putExtra(GameActivity.KEY_MODE,sensors_BTN.getText().toString());
        sensorsIntent.putExtra(GameActivity.KEY_SPEED,speedTGL.getText());
        startActivity(sensorsIntent);
        finish();
    }

    private void goToScoreList(){
        Intent sensorsIntent = new Intent(this,FinishingActivity.class);
        startActivity(sensorsIntent);
        finish();
    }

    private void findViews() {
        car_gif = findViewById(R.id.opening_gif);
        speedTGL = findViewById(R.id.opening_toggle_speed);
        arrows_BTN = findViewById(R.id.opening_BTN_arrows);
        sensors_BTN = findViewById(R.id.opening_BTN_sensors);
        scoreList_BTN = findViewById(R.id.opening_BTN_records);
    }
}