package com.example.mvp;

import java.util.ArrayList;
import java.util.List;

class Model {

    private List<Integer> mList;

    Model() {
        this.mList = new ArrayList<>(3);
        mList.add(0);
        mList.add(0);
        mList.add(0);
    }

    int getElementValueByIndex(int index) {
        return mList.get(index);
    }

    void setElementValueByIndex(int index, int value) {
        mList.set(index, value);
    }
}
