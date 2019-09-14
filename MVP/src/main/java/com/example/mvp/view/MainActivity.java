package com.example.mvp.view;

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
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Map<Integer, Integer> buttonMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
        initButtonMap();
        setOnClickListeners();
        presenter = new Presenter(this);
    }

    private void initButtons() {
        btn0 = findViewById(R.id.btnCounter0);
        btn1 = findViewById(R.id.btnCounter1);
        btn2 = findViewById(R.id.btnCounter2);
    }

    @SuppressLint("UseSparseArrays")
    private void initButtonMap() {
        buttonMap = new HashMap<>(3);
        buttonMap.put(R.id.btnCounter0, 0);
        buttonMap.put(R.id.btnCounter1, 1);
        buttonMap.put(R.id.btnCounter2, 2);
    }

    private void setOnClickListeners() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
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
