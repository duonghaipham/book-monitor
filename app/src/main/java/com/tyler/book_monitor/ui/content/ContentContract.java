package com.tyler.book_monitor.ui.content;

import android.app.Activity;
import android.content.Context;

import com.tyler.book_monitor.data.models.Font;
import com.tyler.book_monitor.data.models.Setting;

import java.util.ArrayList;

public class ContentContract {
    public interface View {
        void onInitialize(int color);
        void onLoadContent(Setting setting, String chapter, String content);
        void onShowChapterChoices(ArrayList<String> chapterChoices);
        void onShowSettings(Setting setting);
        void onSaveSettings(Setting setting);
    }

    public interface Presenter {
        void initialize(Activity activity);
        void loadContent(Context context);
        void showChapterChoices();
        void showSettings(Context context);
        void jumpToChapter(Context context, String chapterName, int chapterIndex);
        void jumpPreviousChapter(Context context);
        void jumpNextChapter(Context context);
        void saveSettings(Context context, Setting setting);
    }
}
