package com.tyler.book_monitor.data.models;

import java.util.List;

public class Book implements IObject {
    private final String id;
    private final String title;
    private final String author;
    private final String cover;
    private final String introduction;
    private final List<String> categories;

    public Book(String id, String title, String author, String cover, String introduction, List<String> categories) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.cover = cover;
        this.introduction = introduction;
        this.categories = categories;
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

    public String getIntroduction() {
        return introduction;
    }

    public List<String> getCategories() {
        return categories;
    }
}
