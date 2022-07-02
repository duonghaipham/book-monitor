package com.tyler.book_monitor.data.models;

public class Book implements IObject {
    private final String id;
    private final String title;
    private final String author;
    private final String cover;

    public Book(String id, String title, String author, String cover) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCover() {
        return cover;
    }
}
