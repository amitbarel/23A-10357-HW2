package com.amitb.a23a_10357_hw2.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amitb.a23a_10357_hw2.R;
import com.amitb.a23a_10357_hw2.interfaces.CallBack_userProtocol;
import com.google.android.material.textview.MaterialTextView;

public class ListFragment extends Fragment {
    private MaterialTextView title;
    private Button user1;
    private CallBack_userProtocol callback;

    public void setCallback(CallBack_userProtocol callback){
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        user1.setOnClickListener(v -> user1Clicked());
        return view;
    }

    private void user1Clicked() {
        if (callback != null)
            callback.user("Amit");
    }

}
