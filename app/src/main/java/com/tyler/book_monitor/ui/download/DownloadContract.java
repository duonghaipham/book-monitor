package com.tyler.book_monitor.ui.download;

import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class DownloadContract {
    public interface View {
        void onLoadContent(List<Book> downloadedBooks);
    }

    public interface Presenter {
        void loadContent();
        void toChapterActivity(String bookId);
        void removeBookFromDownload(Book book);
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(List<Book> downloadedBooks);
            void onFailure(String message);
        }
        void loadContent(OnLoadContentListener listener);
        void removeBookFromDownload(Book book);
    }
}
