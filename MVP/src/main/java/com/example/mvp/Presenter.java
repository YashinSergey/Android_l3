package com.example.mvp;

class Presenter {

    private Model model;
    private MainView view;

    Presenter(MainView view) {
        model = new Model();
        this.view = view;
    }

    private void calcNewModelValue(int key) {
        int newValue = model.getElementValueByKey(key) + 1;
        model.setElementValueByKey(key, newValue);
        view.setButtonText(key, newValue);
    }

    void onClick(int btnNum) {
        calcNewModelValue(btnNum);
    }
}
