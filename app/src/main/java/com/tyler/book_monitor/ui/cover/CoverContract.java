package com.tyler.book_monitor.ui.cover;

import com.tyler.book_monitor.data.models.Book;

public class CoverContract {
    public interface View {
        void onLoadContent(int themeMode, Book book, boolean isDownloaded);
        void toggleDownload(boolean isDownloaded);
    }

    public interface Presenter {
        void toChapterActivity(String bookId);
        void toggleDownload();
        void loadContent();
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(Book book, boolean isDownloaded);
            void onFailure(String message);
        }
        void loadContent(String bookId, OnLoadContentListener listener);
        void download(Book book);
        void deleteDownload(String bookId);
    }
}
