package com.tyler.book_monitor.ui.cover;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.prefs.SettingsManager;
import com.tyler.book_monitor.ui.chapter.ChapterActivity;

public class CoverPresenter implements CoverContract.Presenter {

    private final Activity activity;
    private final CoverContract.View view;
    private final CoverContract.Model model;

    public CoverPresenter(Activity activity, CoverContract.View view) {
        this.activity = activity;
        this.view = view;
        this.model = new CoverModel();
    }

    @Override
    public void toChapterActivity(String bookId) {
        Bundle bundle = new Bundle();
        bundle.putString("bookId", bookId);

        Intent intent = new Intent(activity, ChapterActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public void loadContent() {
        int themeMode = SettingsManager.getThemeMode(activity);

        String bookId = activity.getIntent().getStringExtra("bookId");

        model.loadContent(bookId, new CoverModel.OnLoadContentListener() {
            @Override
            public void onSuccess(Book book) {
                view.onLoadContent(themeMode, book);
            }

            @Override
            public void onFailure(String message) {
                view.onLoadContent(0, null);
            }
        });
    }
}
