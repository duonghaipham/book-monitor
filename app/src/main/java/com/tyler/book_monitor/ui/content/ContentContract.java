package com.tyler.book_monitor.ui.content;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.SettingContent;

import java.util.ArrayList;

public class ContentContract {
    public interface View {
        void onInitialize(int themeMode, int color, Book book);
        void onLoadContent(SettingContent setting, String chapter, String content);
        void onShowChapterChoices(ArrayList<String> chapterChoices);
        void onShowSettings(SettingContent setting);
        void onSaveSettings(SettingContent setting);
    }

    public interface Presenter {
        void initialize();
        void loadContent();
        void showChapterChoices();
        void showSettings();
        void jumpToChapter(int chapterIndex);
        void jumpPreviousChapter();
        void jumpNextChapter();
        void saveSettings(SettingContent setting);
    }
}
