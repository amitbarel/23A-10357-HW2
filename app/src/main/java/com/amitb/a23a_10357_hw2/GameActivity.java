package com.amitb.a23a_10357_hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    public static final String KEY_SPEED = "KEY_SPEED";
    public static final String KEY_MODE = "KEY_MODE";
    private final int MULT = 3;

    private Vibrator v;
    private GameManager GM;
    private Toast toast;
    private ExtendedFloatingActionButton goLeft;
    private ExtendedFloatingActionButton goRight;
    private ShapeableImageView[][] Cones;
    private ShapeableImageView[][] Wrenches;
    private ShapeableImageView[] Hearts;
    private ShapeableImageView[] Cars;
    private AppCompatImageView background;
    private Timer timer;
    private long startTime;
    private int DELAY = 1000;
    private int currentSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        currentSpot = 2;
        findViews();
        initViews();
        GM = new GameManager(Hearts.length);
        goLeft.setOnClickListener(view -> slideLeft());
        goRight.setOnClickListener(view -> slideRight());
        Intent previousIntent = getIntent();
        String speed = previousIntent.getStringExtra(KEY_SPEED);
        String mode = previousIntent.getStringExtra(KEY_MODE);
        if(mode == "arrows" && speed == "fast"){
            DELAY = 500;
        }else if (mode == "sensors"){
            goLeft.setVisibility(View.INVISIBLE);
            goRight.setVisibility(View.INVISIBLE);
        }
        timer = new Timer();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        toast.cancel();
//    }

    @Override
    protected void onStop() {
        super.onStop();
        if(toast != null)
            toast.cancel();
     }

    private void findViews() {
        background = findViewById(R.id.game_IMG_background);
        goLeft = findViewById(R.id.game_FAB_goLeft);
        goRight = findViewById(R.id.game_FAB_goRight);
        Cars = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_car0),
                findViewById(R.id.game_IMG_car1),
                findViewById(R.id.game_IMG_car2),
                findViewById(R.id.game_IMG_car3),
                findViewById(R.id.game_IMG_car4)
        };
        Hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_heart_1),
                findViewById(R.id.game_IMG_heart_2),
                findViewById(R.id.game_IMG_heart_3)
        };
        Cones = new ShapeableImageView[][]{
                {findViewById(R.id.game_cone_0_1), findViewById(R.id.game_cone_1_1), findViewById(R.id.game_cone_2_1), findViewById(R.id.game_cone_3_1), findViewById(R.id.game_cone_4_1)},
                {findViewById(R.id.game_cone_0_2), findViewById(R.id.game_cone_1_2), findViewById(R.id.game_cone_2_2), findViewById(R.id.game_cone_3_2), findViewById(R.id.game_cone_4_2)},
                {findViewById(R.id.game_cone_0_3), findViewById(R.id.game_cone_1_3), findViewById(R.id.game_cone_2_3), findViewById(R.id.game_cone_3_3), findViewById(R.id.game_cone_4_3)},
                {findViewById(R.id.game_cone_0_4), findViewById(R.id.game_cone_1_4), findViewById(R.id.game_cone_2_4), findViewById(R.id.game_cone_3_4), findViewById(R.id.game_cone_4_4)},
                {findViewById(R.id.game_cone_0_5), findViewById(R.id.game_cone_1_5), findViewById(R.id.game_cone_2_5), findViewById(R.id.game_cone_3_5), findViewById(R.id.game_cone_4_5)},
                {findViewById(R.id.game_cone_0_6), findViewById(R.id.game_cone_1_6), findViewById(R.id.game_cone_2_6), findViewById(R.id.game_cone_3_6), findViewById(R.id.game_cone_4_6)},
                {findViewById(R.id.game_cone_0_7), findViewById(R.id.game_cone_1_6), findViewById(R.id.game_cone_2_6), findViewById(R.id.game_cone_3_7), findViewById(R.id.game_cone_4_7)}
        };
        Wrenches = new ShapeableImageView[][]{
                {findViewById(R.id.game_wrench_0_1), findViewById(R.id.game_wrench_1_1), findViewById(R.id.game_wrench_2_1), findViewById(R.id.game_wrench_3_1), findViewById(R.id.game_wrench_4_1)},
                {findViewById(R.id.game_wrench_0_2), findViewById(R.id.game_wrench_1_2), findViewById(R.id.game_wrench_2_2), findViewById(R.id.game_wrench_3_2), findViewById(R.id.game_wrench_4_2)},
                {findViewById(R.id.game_wrench_0_3), findViewById(R.id.game_wrench_1_3), findViewById(R.id.game_wrench_2_3), findViewById(R.id.game_wrench_3_3), findViewById(R.id.game_wrench_4_3)},
                {findViewById(R.id.game_wrench_0_4), findViewById(R.id.game_wrench_1_4), findViewById(R.id.game_wrench_2_4), findViewById(R.id.game_wrench_3_4), findViewById(R.id.game_wrench_4_4)},
                {findViewById(R.id.game_wrench_0_5), findViewById(R.id.game_wrench_1_5), findViewById(R.id.game_wrench_2_5), findViewById(R.id.game_wrench_3_5), findViewById(R.id.game_wrench_4_5)},
                {findViewById(R.id.game_wrench_0_6), findViewById(R.id.game_wrench_1_6), findViewById(R.id.game_wrench_2_6), findViewById(R.id.game_wrench_3_6), findViewById(R.id.game_wrench_4_6)},
                {findViewById(R.id.game_wrench_0_7), findViewById(R.id.game_wrench_1_6), findViewById(R.id.game_wrench_2_6), findViewById(R.id.game_wrench_3_7), findViewById(R.id.game_wrench_4_7)}
        };
    }

    private void initViews() {
        Glide
                .with(this)
                .load("https://i.imgur.com/8whojL1.png")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(background);

        startGame();
    }


    private void slideLeft() {
        if (currentSpot > 0) {
            Cars[currentSpot].setVisibility(View.INVISIBLE);
            Cars[currentSpot - 1].setVisibility(View.VISIBLE);
            currentSpot--;
        }
    }

    private void slideRight() {
        if (currentSpot < Cars.length - 1) {
            Cars[currentSpot].setVisibility(View.INVISIBLE);
            Cars[currentSpot + 1].setVisibility(View.VISIBLE);
            currentSpot++;
        }
    }

    private boolean isFinish = false;
    private void startGame() {
        startTime = System.currentTimeMillis();
        timer = new Timer();
            timer.scheduleAtFixedRate(
                    new TimerTask() {
                        @Override
                        public void run() {
                            if(!isFinish)
                                runOnUiThread(() -> GameActivity.this.updateConeLocation());
                        }
                    }
                    , DELAY, DELAY);
    }

    private void updateConeLocation() {
        checkHit();
        lowerLocations();
        initRandomCone();
    }

    private void lowerLocations() {
        for (int i = Cones.length - 1; i > 0; i--) {
            for (int j = 0; j < Cones[i].length; j++) {
                Cones[i][j].setVisibility(Cones[i - 1][j].getVisibility());
                Wrenches[i][j].setVisibility(Wrenches[i - 1][j].getVisibility());
            }
        }
    }

    private void initRandomCone() {
        Random rand = new Random();
        int rnd = rand.nextInt(9);
        for (int i = 0; i < Cones[0].length; i++) {
            Cones[0][i].setVisibility(View.INVISIBLE);
            Wrenches[0][i].setVisibility(View.INVISIBLE);
        }
        if (rnd >= 0 && rnd < 5) {
            Cones[0][rnd].setVisibility(View.VISIBLE);
        } else if (rnd > 7) {
            int rnd2 = rand.nextInt(5);
            Wrenches[0][rnd2].setVisibility(View.VISIBLE);
        }
    }

    private void checkHit() {
        if (Cones[Cones.length - 1][currentSpot].getVisibility() == View.VISIBLE) {
            GM.updateWrong();
            if (GM.getWrong() != GM.getLife()) {
                Hearts[Hearts.length - GM.getWrong()].setVisibility(View.INVISIBLE);
                Log.d("wrongs: ", "" + GM.getWrong());
            }
            else if (GM.getWrong() == GM.getLife()){
               openFinishScreen();
               return;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.d("Vibrations", "Vibrate!");
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(500);
            }
            toast.makeText(this, "You just got hit!", Toast.LENGTH_SHORT).show();
        }
        if (Wrenches[Wrenches.length - 1][currentSpot].getVisibility() == View.VISIBLE) {
            if (GM.getWrong() < GM.getLife() && GM.getWrong() > 0) {
                Hearts[Hearts.length - GM.getWrong()].setVisibility(View.VISIBLE);
            }
            GM.obtainLife();
            toast.makeText(this, "You just got more lives!", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFinishScreen() {
        //flag to stop the timer action
        isFinish = true;
        int now = (int) ((System.currentTimeMillis()-startTime)/1000);
        timer.cancel();
        String score = ""+ (now * MULT);
        Intent finishIntent = new Intent(this,FinishingActivity.class);
        finishIntent.putExtra(FinishingActivity.KEY_SCORE,"" + score);
        startActivity(finishIntent);
        GameActivity.this.finish();
        }
    }
