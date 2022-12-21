package com.amitb.a23a_10357_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.amitb.a23a_10357_hw2.interfaces.UserProtocolCallBack;
import com.amitb.a23a_10357_hw2.model.Player;
import com.amitb.a23a_10357_hw2.views.ListFragment;
import com.amitb.a23a_10357_hw2.views.MapFragment;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class FinishingActivity extends AppCompatActivity {

    private ListFragment listFragment;
    private MapFragment mapFragment;
    private ShapeableImageView returnIMG;

    UserProtocolCallBack callBack = new UserProtocolCallBack() {
        @Override
        public void sendLocation(double latitude, double longitude) {
            mapFragment.zoom(latitude,longitude);
        }

        @Override
        public void showAllLocations(ArrayList<Player> players) {
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