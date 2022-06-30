package com.tyler.book_monitor.ui.cover;

import android.content.Context;
import android.content.Intent;

import com.tyler.book_monitor.data.prefs.SettingsManager;
import com.tyler.book_monitor.ui.chapter.ChapterActivity;

public class CoverPresenter implements CoverContract.Presenter {

    private Context context;
    private CoverContract.View view;

    public CoverPresenter(Context context, CoverContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void toChapterActivity() {
        Intent intent = new Intent(context, ChapterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initialize() {
        int themeMode = SettingsManager.getThemeMode(context);

        view.onInitialize(themeMode);
    }
}
