package com.tyler.book_monitor.ui.author;

import android.content.Context;

import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class AuthorContract {
    public interface View {
        void onLoadContent(List<Book> books);
    }

    public interface Presenter {
        void loadContent();
        void toCoverActivity();
    }
}
