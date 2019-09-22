package com.example.eventbusexample;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

class Message {

    private String msg;

    Message() {
    }

    private Message(String msg) {
        this.msg = msg;
    }

    String getMsg() {
        return msg;
    }

    void sendMessage(EditText editText, Activity activity) {
        if (!editText.getText().toString().equals("")) {
            EventBus.getBus().post(new Message(editText.getText().toString()));
            editText.setText("");
        }
        hideKeyboard(activity);
    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
