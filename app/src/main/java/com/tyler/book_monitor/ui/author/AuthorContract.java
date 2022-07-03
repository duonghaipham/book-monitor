package com.tyler.book_monitor.ui.author;

import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class AuthorContract {
    public interface View {
        void onLoadContent(Author author, List<Book> books);
    }

    public interface Presenter {
        void loadContent();
        void toCoverActivity(int position);
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(Author author, List<Book> books);
            void onFailure(String message);
        }
        void loadContent(String authorId, String authorName, OnLoadContentListener listener);
    }
}
