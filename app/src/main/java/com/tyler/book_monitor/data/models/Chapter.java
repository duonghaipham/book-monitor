package com.tyler.book_monitor.data.models;

public class Chapter {
    private String title;
    private String additionalInfo;

    public Chapter(String title, String additionalInfo) {
        this.title = title;
        this.additionalInfo = additionalInfo;
    }

    public String getTitle() {
        return title;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
