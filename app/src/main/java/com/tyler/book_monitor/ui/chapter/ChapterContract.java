package com.tyler.book_monitor.ui.chapter;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;

import java.util.List;

public class ChapterContract {
    public interface View {
        void onLoadContent(int themeMode, Book book, List<Chapter> chapters);
    }

    public interface Presenter {
        void loadContent();
        void toContentActivity(int color, int chapterIndex);
    }

    public interface Model {
        interface OnLoadContentListener {
            void onSuccess(Book book, List<Chapter> chapters);
            void onFailure(String message);
        }

        void loadContent(String bookId, OnLoadContentListener listener);
    }
}
