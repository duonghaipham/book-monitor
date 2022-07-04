package com.tyler.book_monitor.ui.download;

import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class DownloadContract {
    public interface View {
        void onLoadContent(List<Book> downloadedBooks);
    }

    public interface Presenter {
        void loadContent();
        void toCoverActivity(String bookId);
        void removeBookFromDownload(String bookId);
    }
}
