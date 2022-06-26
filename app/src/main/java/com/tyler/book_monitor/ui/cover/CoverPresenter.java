package com.tyler.book_monitor.ui.cover;

import android.content.Context;
import android.content.Intent;

import com.tyler.book_monitor.ui.chapter.ChapterActivity;

public class CoverPresenter implements CoverContract.Presenter {

    private CoverContract.View view;

    public CoverPresenter(CoverContract.View view) {
        this.view = view;
    }

    @Override
    public void toChapterActivity(Context context) {
        Intent intent = new Intent(context, ChapterActivity.class);
        context.startActivity(intent);
    }
}
