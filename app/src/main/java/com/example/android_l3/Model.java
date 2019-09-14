package com.example.android_l3;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

class Model extends Observable {

    private List<Integer> mList;

    Model() {
        this.mList = new ArrayList<>(3);
        mList.add(0);
        mList.add(0);
        mList.add(0);
    }

    int getElementValueByIndex(int _index) {
        return mList.get(_index);
    }

    void setElementValueByIndex(int _index) {
        int currentValue = mList.get(_index);
        mList.set(_index, currentValue + 1);
        setChanged();
        notifyObservers();
    }
}
