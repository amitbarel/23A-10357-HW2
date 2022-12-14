package com.amitb.a23a_10357_hw2.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amitb.a23a_10357_hw2.R;
import com.amitb.a23a_10357_hw2.interfaces.RecordCallBack;
import com.amitb.a23a_10357_hw2.model.Player;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private Context context;
    private ArrayList<Player> players;
    private RecordCallBack recordCallBack;

    public PlayerAdapter(Context context, ArrayList<Player> players){
        this.context = context;
        this.players = players;
    }

    public PlayerAdapter setRecordCallBack(RecordCallBack rcb){
        this.recordCallBack = rcb;
        return this;
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder{
        private MaterialTextView name;
        private MaterialTextView score;
        private MaterialTextView latitude;
        private MaterialTextView longitude;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.player_LBL_name);
            score = itemView.findViewById(R.id.player_LBL_score);
            latitude = itemView.findViewById(R.id.player_LBL_latitude);
            longitude = itemView.findViewById(R.id.player_LBL_longitude);
            itemView.setOnClickListener(v -> recordCallBack.itemClicked(getItem(getAdapterPosition()),getAdapterPosition()));
        }
    }

    private Player getItem(int position) {
        return players.get(position);
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item,parent,false);
        PlayerViewHolder mvh = new PlayerViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = getItem(position);
        holder.name.setText(player.getName() + "");
        holder.score.setText(player.getScore() + " points");
        holder.latitude.setText(player.getLatitude() + "");
        holder.longitude.setText(player.getLongitude() + "");
    }

    @Override
    public int getItemCount() {
        return players == null? 0: players.size();
    }
}
