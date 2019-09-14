package com.example.mvp;

class Presenter {

    private Model model;
    private MainView view;

    Presenter(MainView view) {
        model = new Model();
        this.view = view;
    }

    private int calcNewModelValue(int index) {
        int currentValue = model.getElementValueByIndex(index);
        return currentValue + 1;
    }

    void onClick(int btnNum) {
        int newModelValue;
        switch (btnNum) {
            case 1:
                newModelValue = calcNewModelValue(0);
                model.setElementValueByIndex(0, newModelValue);
                view.setButtonText(1, newModelValue);
                break;
            case 2:
                newModelValue = calcNewModelValue(1);
                model.setElementValueByIndex(1, newModelValue);
                view.setButtonText(2, newModelValue);
                break;
            case 3:
                newModelValue = calcNewModelValue(2);
                model.setElementValueByIndex(2, newModelValue);
                view.setButtonText(3, newModelValue);
                break;
        }
    }
}
