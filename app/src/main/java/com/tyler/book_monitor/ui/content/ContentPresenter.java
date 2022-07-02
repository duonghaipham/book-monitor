package com.tyler.book_monitor.ui.content;

import android.app.Activity;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.data.models.SettingContent;
import com.tyler.book_monitor.data.prefs.SettingsManager;
import com.tyler.book_monitor.utils.DataHolder;

import java.util.ArrayList;
import java.util.List;

public class ContentPresenter implements ContentContract.Presenter {

    private final Activity activity;
    private final ContentContract.View view;

    private int mChapterIndexCurrent = 0;
    private int mChapterIndexTotal = 0;
    private Book mBook;
    private List<Chapter> mChapters;

    public ContentPresenter(Activity activity, ContentContract.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void initialize() {
        int themeMode = SettingsManager.getThemeMode(activity);

        int color = activity.getIntent().getExtras().getInt("color");
        mChapterIndexCurrent = activity.getIntent().getExtras().getInt("chapterIndexCurrent");

        mBook = DataHolder.getInstance().getBook();
        mChapters = DataHolder.getInstance().getChapters();
        mChapterIndexTotal = mChapters.size();

        view.onInitialize(themeMode, color, mBook);
    }

    @Override
    public void loadContent() {
        String chapter = mChapters.get(mChapterIndexCurrent).getTitle();
        String content = standardize(mChapters.get(mChapterIndexCurrent).getContent());

        view.onLoadContent(loadSettings(), chapter, content);
    }

    @Override
    public void showChapterChoices() {
        ArrayList<String> chapterChoices = new ArrayList<>();

        for (Chapter chapter : mChapters) {
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
    public void jumpToChapter(int chapterIndex) {
        SettingContent setting = loadSettings();

        mChapterIndexCurrent = chapterIndex;

        String title = mChapters.get(chapterIndex).getTitle();
        String content = standardize(mChapters.get(chapterIndex).getContent());

        view.onLoadContent(setting, title, content);
    }

    @Override
    public void jumpPreviousChapter() {
        if (mChapterIndexCurrent > 0) {
            SettingContent setting = loadSettings();

            mChapterIndexCurrent--;

            String title = mChapters.get(mChapterIndexCurrent).getTitle();
            String content = standardize(mChapters.get(mChapterIndexCurrent).getContent());

            view.onLoadContent(setting, title, content);
        }
    }

    @Override
    public void jumpNextChapter() {
        if (mChapterIndexCurrent < mChapterIndexTotal - 1) {
            SettingContent setting = loadSettings();

            mChapterIndexCurrent++;

            String title = mChapters.get(mChapterIndexCurrent).getTitle();
            String content = standardize(mChapters.get(mChapterIndexCurrent).getContent());

            view.onLoadContent(setting, title, content);
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

    private String standardize(String text) {
        String returnText = text;
        returnText += "\\n\\n\\n\\n\\n\\n\\n";

        return returnText.replace("\\n", "\n");
    }
}
