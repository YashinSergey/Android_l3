package com.example.eventbusexample;

import com.squareup.otto.Bus;

class EventBus {

    private static Bus bus = new Bus();

    static Bus getBus() {
        return bus;
    }
}
