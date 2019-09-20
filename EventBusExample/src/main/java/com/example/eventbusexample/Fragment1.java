package com.example.eventbusexample;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.otto.Subscribe;

public class Fragment1 extends Fragment {

    private TextView textView1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        textView1 = view.findViewById(R.id.textView1);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getBus().unregister(this);
    }

    @Subscribe
    public void receivedMessage(Message m) {
        textView1.setText(m.getMsg());
    }
}
