package com.tyler.book_monitor.ui.content;

import android.app.Activity;

import com.tyler.book_monitor.data.models.Chapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ContentPresenter implements ContentContract.Presenter {

    private ContentContract.View view;

    public ContentPresenter(ContentContract.View view) {
        this.view = view;
    }

    @Override
    public void loadContent(Activity activity) {
        int color = activity.getIntent().getExtras().getInt("color");
        String chapter = "The first avenger";
        String content = "Hello world";

        view.onLoadContent(color, chapter, content);
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
        chapters.add(new Chapter("Chapter 10", "This is chapter 10"));

        ArrayList<String> chapterChoices = new ArrayList<>();

        for (Chapter chapter : chapters) {
            chapterChoices.add(chapter.getTitle());
        }

        view.onShowChapterChoices(chapterChoices);
    }
}
