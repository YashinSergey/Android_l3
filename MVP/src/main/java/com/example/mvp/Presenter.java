package com.example.mvp;

class Presenter {

    private Model model;
    private MainView view;

    Presenter(MainView view) {
        model = new Model();
        this.view = view;
    }

    private int calcNewModelValue(int index) {
        int currentValue = model.getElementValueByKey(index);
        return currentValue + 1;
    }

    void onClick(int btnNum) {
        int newModelValue;
        switch (btnNum) {
            case 0:
                newModelValue = calcNewModelValue(0);
                model.setElementValueByKey(0, newModelValue);
                view.setButtonText(0, newModelValue);
                break;
            case 1:
                newModelValue = calcNewModelValue(1);
                model.setElementValueByKey(1, newModelValue);
                view.setButtonText(1, newModelValue);
                break;
            case 2:
                newModelValue = calcNewModelValue(2);
                model.setElementValueByKey(2, newModelValue);
                view.setButtonText(2, newModelValue);
                break;
        }
    }
}
