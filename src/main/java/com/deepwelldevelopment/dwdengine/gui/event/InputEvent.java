package com.deepwelldevelopment.dwdengine.gui.event;

public class InputEvent {

    private boolean consumed;

    public boolean isConsumed() {
        return consumed;
    }

    public void consume() {
        consumed = true;
    }
}
