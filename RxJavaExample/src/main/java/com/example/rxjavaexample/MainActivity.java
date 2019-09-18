package com.example.rxjavaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView{

    private TextView textView;
    private EditText editText;
    private Watcher watcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        watcher = new Watcher(this);
        editText.addTextChangedListener(watcher);
    }

    private void initViews() {
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
    }

    @Override
    public void addSymbol(String s) {
        textView.setText(s);
    }
}
