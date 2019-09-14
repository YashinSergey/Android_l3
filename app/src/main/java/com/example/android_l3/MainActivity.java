package com.example.android_l3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer, View.OnClickListener {

    private Model mModel;
    private Button btnCounter1;
    private Button btnCounter2;
    private Button btnCounter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
        setOnClickListeners();

        mModel = new Model();
        mModel.addObserver(this);
    }

    private void setOnClickListeners() {
        btnCounter1.setOnClickListener(this);
        btnCounter2.setOnClickListener(this);
        btnCounter3.setOnClickListener(this);
    }

    private void initButtons() {
        btnCounter1 = findViewById(R.id.btnCounter1);
        btnCounter2 = findViewById(R.id.btnCounter2);
        btnCounter3 = findViewById(R.id.btnCounter3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCounter1:
                mModel.setElementValueByIndex(0);
                break;
            case R.id.btnCounter2:
                mModel.setElementValueByIndex(1);
                break;
            case R.id.btnCounter3:
                mModel.setElementValueByIndex(2);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void update(Observable observable, Object o) {
        btnCounter1.setText("Количество = " + mModel.getElementValueByIndex(0));
        btnCounter2.setText("Количество = " + mModel.getElementValueByIndex(1));
        btnCounter3.setText("Количество = " + mModel.getElementValueByIndex(2));
    }
}
