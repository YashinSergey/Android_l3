package com.example.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvp.MainView;
import com.example.mvp.R;
import com.example.mvp.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private Presenter presenter;
    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
        setOnClickListeners();
        presenter = new Presenter(this);
    }

    private void initButtons() {
        btn1 = findViewById(R.id.btnCounter1);
        btn2 = findViewById(R.id.btnCounter2);
        btn3 = findViewById(R.id.btnCounter3);
    }

    private void setOnClickListeners() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int btn = -1;
        switch (view.getId()) {
            case R.id.btnCounter1:
                btn = 0;
                break;
            case R.id.btnCounter2:
                btn = 1;
                break;
            case R.id.btnCounter3:
                btn = 2;
                break;
        }
        if (btn != -1) presenter.onClick(btn);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setButtonText(int btn, int value) {
        switch (btn) {
            case 0:
                btn1.setText("Количество = " + value);
                break;
            case 1:
                btn2.setText("Количество = " + value);
                break;
            case 2:
                btn3.setText("Количество = " + value);
                break;
        }
    }
}
