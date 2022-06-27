package com.tyler.book_monitor.data.models;

public class Setting {
    private final int font;
    private final int fontSize;
    private final boolean navigation;

    public Setting(int font, int fontSize, boolean navigation) {
        this.font = font;
        this.fontSize = fontSize;
        this.navigation = navigation;
    }

    public int getFont() {
        return font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public boolean getNavigation() {
        return navigation;
    }
}
