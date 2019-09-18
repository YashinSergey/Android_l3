package com.example.rxjavaexample;

import android.text.Editable;
import android.text.TextWatcher;

public class Watcher implements TextWatcher {

    private MainView mainView;

    Watcher(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        mainView.addSymbol(editable.toString());
    }
}
