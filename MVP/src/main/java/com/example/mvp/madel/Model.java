package com.example.mvp.madel;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Map;

public class Model {

    private Map<Integer, Integer> countMap;

    @SuppressLint("UseSparseArrays")
    public Model() {
        this.countMap = new HashMap<>(3);
        countMap.put(0, 0);
        countMap.put(1, 0);
        countMap.put(2, 0);
    }

    public int getElementValueByKey(int key) {
        return countMap.get(key);
    }

    public void setElementValueByKey(int key, int value) {
        countMap.put(key, value);
    }
}
