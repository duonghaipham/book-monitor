package com.tyler.book_monitor.data.models;

public class Book implements IObject {
    private String title;
    private String author;
    private int cover;

    public Book(String title, String author, int cover) {
        this.title = title;
        this.author = author;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCover() {
        return cover;
    }
}
