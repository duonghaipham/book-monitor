package com.tyler.book_monitor.ui.all_books;

import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class AllBooksContract {
    public interface View {
        void onLoadContent(List<Book> books, List<String> pages);
        void onLoadContentByPage(List<Book> books);
    }

    public interface Presenter {
        void loadContent();
        void loadContentByPage(int page);
        void toCoverActivity(String bookId);
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(List<Book> books, List<String> pages);
            void onFailure(String message);
        }
        interface OnLoadContentByPageListener {
            void onSuccess(List<Book> books);
            void onFailure(String message);
        }
        void loadContent(OnLoadContentListener listener);
        void loadContentByPage(int page, OnLoadContentByPageListener listener);
    }
}
