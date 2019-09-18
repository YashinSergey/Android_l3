package com.example.mvp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvp.MainView;
import com.example.mvp.R;
import com.example.mvp.presenter.Presenter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private Presenter presenter;
    private Map<Integer, Integer> buttonMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtonMap();
        setOnClickListeners();
        presenter = new Presenter(this);
    }

    @SuppressLint("UseSparseArrays")
    private void initButtonMap() {
        buttonMap = new HashMap<>(3);
        buttonMap.put(R.id.btnCounter0, 0);
        buttonMap.put(R.id.btnCounter1, 1);
        buttonMap.put(R.id.btnCounter2, 2);
    }

    private void setOnClickListeners() {
        findViewById(R.id.btnCounter0).setOnClickListener(this);
        findViewById(R.id.btnCounter1).setOnClickListener(this);
        findViewById(R.id.btnCounter2).setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("numbers", presenter.getNumbers());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int[] arr = savedInstanceState.getIntArray("numbers");
        setButtonText(R.id.btnCounter0, arr[0]);
        setButtonText(R.id.btnCounter1, arr[1]);
        setButtonText(R.id.btnCounter2, arr[2]);
        presenter.restoreValues(arr);
    }

    @Override
    public void onClick(View view) {
         presenter.onClick(view.getId(), buttonMap.get(view.getId()));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setButtonText(int id, int value) {
        Button button = findViewById(id);
        if (button != null) button.setText("Количество = " + value);
    }
}
