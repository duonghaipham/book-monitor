package com.tyler.book_monitor.data.models;

public class Chapter {
    private String title;
    private String content;

    public Chapter(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
