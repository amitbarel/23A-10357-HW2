package com.amitb.a23a_10357_hw2.interfaces;

import com.amitb.a23a_10357_hw2.model.Player;

import java.util.ArrayList;

public interface CallBack_userProtocol {
    void sendLoc(double latitude, double longitude);
    void showTop5(ArrayList<Player> players);
}
