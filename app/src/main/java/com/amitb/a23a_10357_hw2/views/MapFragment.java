package com.amitb.a23a_10357_hw2.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amitb.a23a_10357_hw2.R;
import com.google.android.material.textview.MaterialTextView;


public class MapFragment extends Fragment {
    private MaterialTextView title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        title = view.findViewById(R.id.map_LBL_title);
    }


    public void zoom(Double latitude, Double longitude) {
        title.setText(latitude + "\n" + longitude);
    }
}
