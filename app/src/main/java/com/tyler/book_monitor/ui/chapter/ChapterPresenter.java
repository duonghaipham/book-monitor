package com.tyler.book_monitor.ui.chapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.ui.content.ContentActivity;

import java.util.List;
import java.util.Vector;

public class ChapterPresenter implements ChapterContract.Presenter {

    private ChapterContract.View view;

    public ChapterPresenter(ChapterContract.View view) {
        this.view = view;
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
        chapters.add(new Chapter("Chapter 10", "This is chapter 10"));

        view.onLoadContent(chapters);
    }

    @Override
    public void toContentActivity(Context context, int color) {
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);

        Intent intent = new Intent(context, ContentActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
