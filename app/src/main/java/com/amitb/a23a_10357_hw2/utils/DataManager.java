package com.amitb.a23a_10357_hw2.utils;

import com.amitb.a23a_10357_hw2.Player;

import java.util.ArrayList;

public class DataManager {

    public static ArrayList<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList<>();

        players.add(new Player()
                .setName("Amit").setScore(91).setLocation(-2.77873,10.11945)
        );

        players.add(new Player()
                .setName("Yossi").setScore(99).setLocation(11.35667,106.98512)
        );
        players.sort((p1,p2) -> p2.getScore()-p1.getScore());
        return players;
    }
}
