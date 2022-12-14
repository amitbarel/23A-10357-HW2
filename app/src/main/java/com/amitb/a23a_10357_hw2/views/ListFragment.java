package com.amitb.a23a_10357_hw2.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amitb.a23a_10357_hw2.model.Player;
import com.amitb.a23a_10357_hw2.model.Records;
import com.amitb.a23a_10357_hw2.utils.MySPv;
import com.amitb.a23a_10357_hw2.utils.PlayerAdapter;
import com.amitb.a23a_10357_hw2.R;
import com.amitb.a23a_10357_hw2.interfaces.UserProtocolCallBack;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private RecyclerView scoreRecycler;

    private UserProtocolCallBack callback;

    public void setCallback(UserProtocolCallBack callback){
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        scoreRecycler = view.findViewById(R.id.main_LST_players);
        String impGson = MySPv.getInstance().getString(MySPv.getInstance().getMyKey(), "");
        Records recs = new Gson().fromJson(impGson,Records.class);
        if (recs == null){
            recs = new Records();
        }
        Context context = getContext();
        PlayerAdapter playerAdapter = new PlayerAdapter(context,recs.getRecords());
        playerAdapter.setRecordCallBack((player, position) -> callback.sendLocation(player.getLatitude(),player.getLongitude()));
        scoreRecycler.setLayoutManager(new LinearLayoutManager(context));
        scoreRecycler.setAdapter(playerAdapter);
        return view;
    }

}
