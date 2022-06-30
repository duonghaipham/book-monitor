package com.tyler.book_monitor.ui.chapter;

import com.tyler.book_monitor.data.models.Chapter;

import java.util.List;

public class ChapterContract {
    public interface View {
        void onInitialize(int themeMode);
        void onLoadContent(List<Chapter> chapters);
    }

    public interface Presenter {
        void initialize();
        void loadContent();
        void toContentActivity(int color, int chapterIndex);
    }
}
