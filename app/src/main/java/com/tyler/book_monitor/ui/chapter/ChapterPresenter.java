package com.tyler.book_monitor.ui.chapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.data.prefs.SettingsManager;
import com.tyler.book_monitor.ui.content.ContentActivity;

import java.util.List;
import java.util.Vector;

public class ChapterPresenter implements ChapterContract.Presenter {

    private Context context;
    private ChapterContract.View view;

    private int mChapterIndexTotal = 0;

    public ChapterPresenter(Context context, ChapterContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void initialize() {
        int themeMode = SettingsManager.getThemeMode(context);

        view.onInitialize(themeMode);
    }

    @Override
    public void loadContent() {
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

        mChapterIndexTotal = chapters.size();

        view.onLoadContent(chapters);
    }

    @Override
    public void toContentActivity(int color, int chapterIndex) {
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        bundle.putInt("chapterIndexCurrent", chapterIndex);
        bundle.putInt("chapterIndexTotal", mChapterIndexTotal);

        Intent intent = new Intent(context, ContentActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
