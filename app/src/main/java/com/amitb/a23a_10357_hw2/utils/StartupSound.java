package com.amitb.a23a_10357_hw2.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import com.amitb.a23a_10357_hw2.R;

public class StartupSound extends AsyncTask<Void,Void,Void> {

    private Context context;

    public StartupSound(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this.context, R.raw.startup);
        mediaPlayer.setLooping(false);
        mediaPlayer.setVolume(1.0f,1.0f);
        mediaPlayer.start();
        return null;
    }
}
