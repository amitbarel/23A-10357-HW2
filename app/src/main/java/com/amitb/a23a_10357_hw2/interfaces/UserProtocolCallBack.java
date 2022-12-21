package com.amitb.a23a_10357_hw2.interfaces;

import com.amitb.a23a_10357_hw2.model.Player;

import java.util.ArrayList;

public interface UserProtocolCallBack {
    void sendLocation(double latitude, double longitude);
    void showAllLocations(ArrayList<Player> players);
}
