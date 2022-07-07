package com.tyler.book_monitor.ui.cover;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.data.prefs.SettingsManager;
import com.tyler.book_monitor.ui.chapter.ChapterActivity;

import java.util.List;

public class CoverPresenter implements CoverContract.Presenter {

    private final Activity activity;
    private final CoverContract.View view;
    private final CoverContract.Model model;

    private Book mBook;
    private List<Chapter> mChapters;
    private boolean mIsDownloaded;

    public CoverPresenter(Activity activity, CoverContract.View view) {
        this.activity = activity;
        this.view = view;
        this.model = new CoverModel(activity);
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
    public void toggleDownload() {
        if (mIsDownloaded) {
            model.deleteDownload(mBook);
            mIsDownloaded = false;
        } else {
            model.download(mBook, mChapters);
            mIsDownloaded = true;
        }

        view.toggleDownload(mIsDownloaded);
    }

    @Override
    public void loadContent() {
        int themeMode = SettingsManager.getThemeMode(activity);

        String bookId = activity.getIntent().getStringExtra("bookId");

        model.loadContent(bookId, new CoverModel.OnLoadContentListener() {
            @Override
            public void onSuccess(Book book, List<Chapter> chapters, boolean isDownloaded) {
                mBook = book;
                mChapters = chapters;
                mIsDownloaded = isDownloaded;

                view.onLoadContent(themeMode, book, isDownloaded);
            }

            @Override
            public void onFailure(String message) {
                view.onLoadContent(0, null, false);
            }
        });
    }
}
