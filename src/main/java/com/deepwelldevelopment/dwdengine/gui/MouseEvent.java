package com.deepwelldevelopment.dwdengine.gui;

public class MouseEvent extends InputEvent {

    public static final int LEFT_MOUSE_BUTTON = 0;
    public static final int RIGHT_MOUSE_BUTTON = 1;
    public static final int MIDDLE_MOUSE_BUTTON = 2;

    public static final int BUTTON_PRESS = 3;
    public static final int BUTTON_RELEASE = 4;
    public static final int MOVED = 5;
    public static final int SCROLL = 6;
    public static final int DRAG = 7;

    private int eventCode;
    private int button;

    private int x;
    private int y;

    public MouseEvent(int eventCode, int button, int x, int y) {
        this.eventCode = eventCode;
        this.button = button;
        this.x = x;
        this.y = y;
    }

    public MouseEvent(int eventCode, int x, int y) {
        System.out.println(x + ", " + y);
        this.eventCode = eventCode;
        this.x = x;
        this.y = y;
    }

    public int getEventCode() {
        return eventCode;
    }

    public int getButton() {
        return button;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
