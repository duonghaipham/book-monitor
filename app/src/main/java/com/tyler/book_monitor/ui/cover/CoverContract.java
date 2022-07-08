package com.tyler.book_monitor.ui.cover;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;

import java.util.List;

public class CoverContract {
    public interface View {
        void onLoadContent(int themeMode, Book book, boolean isDownloaded, boolean isArchived);
        void toggleDownload(boolean isDownloaded);
        void toggleArchive(boolean isArchived);
    }

    public interface Presenter {
        void toChapterActivity(String bookId);
        void toggleArchive();
        void toggleDownload();
        void loadContent();
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(Book book, List<Chapter> chapters, boolean isDownloaded, boolean isArchived);
            void onFailure(String message);
        }
        void loadContent(String bookId, OnLoadContentListener listener);
        void download(Book book, List<Chapter> chapters);
        void deleteDownload(Book book);
        void archive(Book book);
        void deleteArchive(String bookId);
    }
}
