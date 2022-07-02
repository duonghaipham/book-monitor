package com.tyler.book_monitor.ui.chapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.data.prefs.SettingsManager;
import com.tyler.book_monitor.ui.content.ContentActivity;
import com.tyler.book_monitor.utils.DataHolder;

import java.util.List;

public class ChapterPresenter implements ChapterContract.Presenter {

    private final Activity activity;
    private final ChapterContract.View view;
    private final ChapterContract.Model model;

    public ChapterPresenter(Activity activity, ChapterContract.View view) {
        this.activity = activity;
        this.view = view;
        this.model = new ChapterModel();
    }

    @Override
    public void loadContent() {
        int themeMode = SettingsManager.getThemeMode(activity);

        Intent intent = activity.getIntent();
        String bookId = intent.getStringExtra("bookId");

        model.loadContent(bookId, new ChapterModel.OnLoadContentListener() {
            @Override
            public void onSuccess(Book book, List<Chapter> chapters) {
                DataHolder.getInstance().setBook(book);
                DataHolder.getInstance().setChapters(chapters);

                view.onLoadContent(themeMode, book, chapters);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void toContentActivity(int color, int chapterIndex) {
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        bundle.putInt("chapterIndexCurrent", chapterIndex);

        Intent intent = new Intent(activity, ContentActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}
