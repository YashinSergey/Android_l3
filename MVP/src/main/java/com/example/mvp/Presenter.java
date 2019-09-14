package com.example.mvp;

class Presenter {

    private Model model;
    private MainView view;

    Presenter(MainView view) {
        model = new Model();
        this.view = view;
    }

    private void updateValue(int index, int id) {
        int newValue = model.getElementValueByIndex(index) + 1;
        model.setElementValueByIndex(index, newValue);
        view.setButtonText(id, newValue);
    }

    void onClick(int id) {
        switch (id) {
            case R.id.btnCounter1:
                updateValue(0,id);
                break;
            case R.id.btnCounter2:
                updateValue(1,id);
                break;
            case R.id.btnCounter3:
                updateValue(2,id);
                break;
        }
    }
}
