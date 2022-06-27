package com.tyler.book_monitor.data.models;

public class Font {
    private final String performedName;
    private final String actualName;

    public Font(String performedName, String actualName) {
        this.performedName = performedName;
        this.actualName = actualName;
    }

    public String getPerformedName() {
        return performedName;
    }

    public String getActualName() {
        return actualName;
    }
}
