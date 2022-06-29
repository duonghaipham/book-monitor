package com.tyler.book_monitor.ui.content;

import android.app.Activity;
import android.content.Context;

import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.data.models.SettingContent;
import com.tyler.book_monitor.data.prefs.SettingsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ContentPresenter implements ContentContract.Presenter {

    private ContentContract.View view;

    private int mChapterIndexCurrent = 0;
    private int mChapterIndexTotal = 0;

    public ContentPresenter(ContentContract.View view) {
        this.view = view;
    }

    @Override
    public void initialize(Activity activity) {
        int themeMode = SettingsManager.getThemeMode(activity);

        int color = activity.getIntent().getExtras().getInt("color");
        mChapterIndexCurrent = activity.getIntent().getExtras().getInt("chapterIndex");
        mChapterIndexTotal = activity.getIntent().getExtras().getInt("chapterIndexTotal");

        view.onInitialize(themeMode, color);
    }

    @Override
    public void loadContent(Context context) {
        String chapter = "The first avenger";
        String content = "Hello world";

        view.onLoadContent(loadSettings(context), chapter, content);
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
    public void showSettings(Context context) {
        SettingContent setting = loadSettings(context);

        view.onShowSettings(setting);
    }

    @Override
    public void jumpToChapter(Context context, String chapterName, int chapterIndex) {
        SettingContent setting = loadSettings(context);

        mChapterIndexCurrent = chapterIndex;
        view.onLoadContent(setting, chapterName, "This is chapter " + chapterIndex);
    }

    @Override
    public void jumpPreviousChapter(Context context) {
        if (mChapterIndexCurrent > 0) {
            SettingContent setting = loadSettings(context);

            mChapterIndexCurrent--;
            view.onLoadContent(setting,"Chapter " + mChapterIndexCurrent, "This is chapter " + mChapterIndexCurrent);
        }
    }

    @Override
    public void jumpNextChapter(Context context) {
        if (mChapterIndexCurrent < mChapterIndexTotal) {
            SettingContent setting = loadSettings(context);

            mChapterIndexCurrent++;
            view.onLoadContent(setting,"Chapter " + mChapterIndexCurrent, "This is chapter " + mChapterIndexCurrent);
        }
    }

    @Override
    public void saveSettings(Context context, SettingContent setting) {
        SettingsManager.setFont(context, setting.getFont());
        SettingsManager.setFontSize(context, setting.getFontSize());
        SettingsManager.setNavigation(context, setting.getNavigation());

        view.onSaveSettings(setting);
    }

    private SettingContent loadSettings(Context context) {
        int font = SettingsManager.getFont(context);
        int fontSize = SettingsManager.getFontSize(context);
        boolean navigation = SettingsManager.getNavigation(context);

        return new SettingContent(font, fontSize, navigation);
    }
}
