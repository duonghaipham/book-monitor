package com.tyler.book_monitor.utils;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;

import java.util.List;

public class DataHolder {

    private List<Chapter> chapters;
    private Book book;

    private static DataHolder instance;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        if (instance == null) {
            instance = new DataHolder();
        }
        return instance;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
