package com.amitb.a23a_10357_hw2.model;

import java.util.ArrayList;

public class Records {
    private ArrayList<Player> players = new ArrayList<>();

    public Records() {
    }


    public ArrayList<Player> getSongs() {
        return players;
    }

    public Records setSongs(ArrayList<Player> songs) {
        this.players = songs;
        return this;
    }

}
