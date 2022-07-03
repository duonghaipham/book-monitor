package com.tyler.book_monitor.ui.all_books;

import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class AllBooksContract {
    public interface View {
        void onLoadContent(List<Book> books);
    }

    public interface Presenter {
        void loadContent();
        void toCoverActivity(int position);
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(List<Book> books);
            void onFailure(String message);
        }
        void loadContent(OnLoadContentListener listener);
    }
}
