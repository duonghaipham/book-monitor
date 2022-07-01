package com.tyler.book_monitor.data.models;

public class Book implements IObject {
    private String title;
    private String author;
    private String cover;

    public Book(String title, String author, String cover) {
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

    public String getCover() {
        return cover;
    }
}
