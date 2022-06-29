package com.tyler.book_monitor.ui.content;

import android.app.Activity;
import android.content.Context;

import com.tyler.book_monitor.data.models.SettingContent;

import java.util.ArrayList;

public class ContentContract {
    public interface View {
        void onInitialize(int themeMode, int color);
        void onLoadContent(SettingContent setting, String chapter, String content);
        void onShowChapterChoices(ArrayList<String> chapterChoices);
        void onShowSettings(SettingContent setting);
        void onSaveSettings(SettingContent setting);
    }

    public interface Presenter {
        void initialize(Activity activity);
        void loadContent(Context context);
        void showChapterChoices();
        void showSettings(Context context);
        void jumpToChapter(Context context, String chapterName, int chapterIndex);
        void jumpPreviousChapter(Context context);
        void jumpNextChapter(Context context);
        void saveSettings(Context context, SettingContent setting);
    }
}
