package com.tyler.book_monitor.ui.content;

import android.app.Activity;

import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.data.models.SettingContent;
import com.tyler.book_monitor.data.prefs.SettingsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ContentPresenter implements ContentContract.Presenter {

    private Activity activity;
    private ContentContract.View view;

    private int mChapterIndexCurrent = 0;
    private int mChapterIndexTotal = 0;

    public ContentPresenter(Activity activity, ContentContract.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void initialize() {
        int themeMode = SettingsManager.getThemeMode(activity);

        int color = activity.getIntent().getExtras().getInt("color");
        mChapterIndexCurrent = activity.getIntent().getExtras().getInt("chapterIndex");
        mChapterIndexTotal = activity.getIntent().getExtras().getInt("chapterIndexTotal");

        view.onInitialize(themeMode, color);
    }

    @Override
    public void loadContent() {
        String chapter = "The first avenger";
        String content = "Hello world";

        view.onLoadContent(loadSettings(), chapter, content);
    }

    @Override
    public void showChapterChoices() {
        List<Chapter> chapters = new Vector<>();
        chapters.add(new Chapter("Chapter 1", "This is chapter 1"));
        chapters.add(new Chapter("Chapter 2", "This is chapter 2"));
        chapters.add(new Chapter("Chapter 3", "This is chapter 3"));
        chapters.add(new Chapter("Chapter 4", "This is chapter 4"));
        chapters.add(new Chapter("Chapter 5", "This is chapter 5"));
        chapters.add(new Chapter("Chapter 6", "This is chapter 6"));
        chapters.add(new Chapter("Chapter 7", "This is chapter 7"));
        chapters.add(new Chapter("Chapter 8", "This is chapter 8"));
        chapters.add(new Chapter("Chapter 9", "This is chapter 9"));

        ArrayList<String> chapterChoices = new ArrayList<>();

        for (Chapter chapter : chapters) {
            chapterChoices.add(chapter.getTitle());
        }

        view.onShowChapterChoices(chapterChoices);
    }

    @Override
    public void showSettings() {
        SettingContent setting = loadSettings();

        view.onShowSettings(setting);
    }

    @Override
    public void jumpToChapter(String chapterName, int chapterIndex) {
        SettingContent setting = loadSettings();

        mChapterIndexCurrent = chapterIndex;
        view.onLoadContent(setting, chapterName, "This is chapter " + chapterIndex);
    }

    @Override
    public void jumpPreviousChapter() {
        if (mChapterIndexCurrent > 0) {
            SettingContent setting = loadSettings();

            mChapterIndexCurrent--;
            view.onLoadContent(setting,"Chapter " + mChapterIndexCurrent, "This is chapter " + mChapterIndexCurrent);
        }
    }

    @Override
    public void jumpNextChapter() {
        if (mChapterIndexCurrent < mChapterIndexTotal) {
            SettingContent setting = loadSettings();

            mChapterIndexCurrent++;
            view.onLoadContent(setting,"Chapter " + mChapterIndexCurrent, "This is chapter " + mChapterIndexCurrent);
        }
    }

    @Override
    public void saveSettings(SettingContent setting) {
        SettingsManager.setFont(activity, setting.getFont());
        SettingsManager.setFontSize(activity, setting.getFontSize());
        SettingsManager.setNavigation(activity, setting.getNavigation());

        view.onSaveSettings(setting);
    }

    private SettingContent loadSettings() {
        int font = SettingsManager.getFont(activity);
        int fontSize = SettingsManager.getFontSize(activity);
        boolean navigation = SettingsManager.getNavigation(activity);

        return new SettingContent(font, fontSize, navigation);
    }
}
