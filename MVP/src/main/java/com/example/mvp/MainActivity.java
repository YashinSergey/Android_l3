package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnClickListeners();
        presenter = new Presenter(this);
    }

    private void setOnClickListeners() {
        findViewById(R.id.btnCounter1).setOnClickListener(this);
        findViewById(R.id.btnCounter2).setOnClickListener(this);
        findViewById(R.id.btnCounter3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        presenter.onClick(view.getId());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setButtonText(int id, int value) {
        Button button = findViewById(id);
        if (button != null) {
            button.setText("Количество = " + value);
        }
    }
}
