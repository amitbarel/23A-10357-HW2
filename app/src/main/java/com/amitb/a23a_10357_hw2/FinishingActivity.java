package com.amitb.a23a_10357_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amitb.a23a_10357_hw2.interfaces.CallBack_userProtocol;
import com.amitb.a23a_10357_hw2.model.Player;
import com.amitb.a23a_10357_hw2.views.ListFragment;
import com.amitb.a23a_10357_hw2.views.MapFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class FinishingActivity extends AppCompatActivity {

    private ListFragment listFragment;
    private MapFragment mapFragment;
    private ShapeableImageView returnIMG;

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
        returnIMG.setOnClickListener(v->{
            Intent openingIntent = new Intent(this,OpeningActivity.class);
            startActivity(openingIntent);
            finish();
        });
    }

    private void initFragments() {
        listFragment = new ListFragment();
        listFragment.setCallback(callBack);
        mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.upper_frame,listFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.lower_frame,mapFragment).commit();
    }

    public void findViews(){
        returnIMG = findViewById(R.id.IMG_return);
    }
}