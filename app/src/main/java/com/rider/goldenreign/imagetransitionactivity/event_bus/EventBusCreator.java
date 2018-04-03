package com.rider.goldenreign.imagetransitionactivity.event_bus;

import com.squareup.otto.Bus;

/**
 * Created by goldenreign on 4/3/2018.
 */

public class EventBusCreator {

    private static Bus bus;

    public static Bus defaultEventBus() {

        if (bus == null) {
            synchronized (EventBusCreator.class) {
                if (bus == null) {
                    bus = new Bus();
                }
            }
        }
        return bus;
    }
}
