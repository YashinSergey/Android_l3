package com.example.mvp.presenter;

import com.example.mvp.MainView;
import com.example.mvp.madel.Model;

public class Presenter {

    private Model model;
    private MainView view;

    public Presenter(MainView view) {
        model = new Model();
        this.view = view;
    }

    private void calcNewModelValue(int key) {
        int newValue = model.getElementValueByKey(key) + 1;
        model.setElementValueByKey(key, newValue);
        view.setButtonText(key, newValue);
    }

    public void onClick(int btnNum) {
        calcNewModelValue(btnNum);
    }
}
