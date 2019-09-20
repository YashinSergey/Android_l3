package com.example.eventbusexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstEditText;
    private Button firstSendBtn;

    private EditText secondEditText;
    private Button secondSendBtn;
    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment1, new Fragment1())
                .add(R.id.fragment2, new Fragment2())
                .commit();
        message = new Message();

        initViews();
        setOnClickListeners();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.firstSendBtn:
                message.sendMessage(firstEditText, this);
                break;
            case R.id.secondSendBtn:
                message.sendMessage(secondEditText, this);
                break;
        }
    }

    private void setOnClickListeners() {
        firstSendBtn.setOnClickListener(this);
        secondSendBtn.setOnClickListener(this);
    }

    private void initViews() {
        firstEditText = findViewById(R.id.firstEditText);
        firstSendBtn = findViewById(R.id.firstSendBtn);

        secondEditText = findViewById(R.id.secondEditText);
        secondSendBtn = findViewById(R.id.secondSendBtn);
    }

}
