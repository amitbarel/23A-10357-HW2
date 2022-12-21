package com.amitb.a23a_10357_hw2.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amitb.a23a_10357_hw2.model.Player;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amitb.a23a_10357_hw2.R;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap gMap;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap = googleMap;
        gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(),R.raw.map_style));
    }

    public void zoom(double latitude, double longitude) {
        LatLng rnd = new LatLng(latitude,longitude);
        gMap.addMarker(new MarkerOptions().position(rnd));
        CameraPosition cPos = new CameraPosition.Builder().target(rnd).zoom(12).build();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cPos));
    }

    public void setOnMap(ArrayList<Player> players){
        for (Player p :players) {
            LatLng rnd = new LatLng(p.getLatitude(),p.getLongitude());
            gMap.addMarker(new MarkerOptions().position(rnd));
        }
    }
}
