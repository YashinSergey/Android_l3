package com.example.mvp.presenter;

import com.example.mvp.MainView;
import com.example.mvp.madel.Model;

public class Presenter {

    private Model model;
    private MainView view;
    private int[] numbers;

    public Presenter(MainView view) {
        model = new Model();
        numbers = new int[model.getCountMapSize()];
        this.view = view;
    }

    private void calcNewModelValue(int id, int key) {
        int newValue = model.getElementValueByKey(key) + 1;
        model.setElementValueByKey(key, newValue);
        numbers[key] = model.getElementValueByKey(key);
        view.setButtonText(id, newValue);
    }

    public void onClick(int id, int btn) {
        calcNewModelValue(id, btn);
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void restoreValues(int[] array) {
        for (int i = 0; i < array.length; i++) {
            model.setElementValueByKey(i, array[i]);
        }
    }
}
