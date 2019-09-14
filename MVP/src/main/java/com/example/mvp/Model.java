package com.example.mvp;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Map;

class Model {

    private Map<Integer, Integer> countMap;

    @SuppressLint("UseSparseArrays")
    Model() {
        this.countMap = new HashMap<>(3);
        countMap.put(0, 0);
        countMap.put(1, 0);
        countMap.put(2, 0);
    }

    int getElementValueByKey(int key) {
        return countMap.get(key);
    }

    void setElementValueByKey(int key, int value) {
        countMap.put(key, value);
    }
}
