package com.tyler.book_monitor.ui.chapter;

import android.content.Context;

import com.tyler.book_monitor.data.models.Chapter;

import java.util.List;

public class ChapterContract {
    public interface View {
        void onInitialize(int themeMode);
        void onLoadContent(List<Chapter> chapters);
    }

    public interface Presenter {
        void initialize(Context context);
        void loadContent();
        void toContentActivity(Context context, int color, int chapterIndex);
    }
}
