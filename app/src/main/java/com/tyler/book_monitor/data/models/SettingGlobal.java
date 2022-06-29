package com.tyler.book_monitor.data.models;

public class SettingGlobal implements ISetting {
    private final int language;
    private final int themeMode;

    public SettingGlobal(int language, int themeMode) {
        this.language = language;
        this.themeMode = themeMode;
    }

    public int getLanguage() {
        return language;
    }

    public int getThemeMode() {
        return themeMode;
    }
}
