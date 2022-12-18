package com.amitb.a23a_10357_hw2.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amitb.a23a_10357_hw2.Player;
import com.amitb.a23a_10357_hw2.PlayerAdapter;
import com.amitb.a23a_10357_hw2.R;
import com.amitb.a23a_10357_hw2.interfaces.CallBack_userProtocol;
import com.amitb.a23a_10357_hw2.utils.DataManager;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private RecyclerView scoreRecycler;

    CallBack_userProtocol callback = new CallBack_userProtocol() {
        @Override
        public void sendLoc(double latitude, double longitude) {
            if (callback != null)
                callback.sendLoc(latitude, longitude);
        }

        @Override
        public void showTop5(ArrayList<Player> players) {
            callback.showTop5(players);
        }
    };

    public void setCallback(CallBack_userProtocol callback){
        this.callback = callback;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        scoreRecycler = view.findViewById(R.id.main_LST_players);
        ArrayList<Player> players = DataManager.getPlayers();
        Context context = getContext();
        PlayerAdapter playerAdapter = new PlayerAdapter(context,players);
        scoreRecycler.setLayoutManager(new LinearLayoutManager(context));
        scoreRecycler.setAdapter(playerAdapter);
        return view;
    }

}
