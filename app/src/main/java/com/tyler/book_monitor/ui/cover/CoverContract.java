package com.tyler.book_monitor.ui.cover;

import com.tyler.book_monitor.data.models.Book;

public class CoverContract {
    public interface View {
        void onLoadContent(int themeMode, Book book);
    }

    public interface Presenter {
        void toChapterActivity(String bookId);
        void loadContent();
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(Book book);
            void onFailure(String message);
        }
        void loadContent(String bookId, OnLoadContentListener listener);
    }
}
