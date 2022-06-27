package com.tyler.book_monitor.ui.content;

import android.app.Activity;

import java.util.ArrayList;

public class ContentContract {
    public interface View {
        void onInitialize(int color);
        void onLoadContent(String chapter, String content);
        void onShowChapterChoices(ArrayList<String> chapterChoices);
        void onShowSettings();
    }

    public interface Presenter {
        void initialize(Activity activity);
        void loadContent();
        void showChapterChoices();
        void showSettings();
        void jumpToChapter(String chapterName, int chapterIndex);
        void jumpPreviousChapter();
        void jumpNextChapter();
    }
}
