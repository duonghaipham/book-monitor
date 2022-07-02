package com.tyler.book_monitor.ui.chapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.ui.content.ContentActivity;

import java.util.List;

public class ChapterPresenter implements ChapterContract.Presenter {

    private Activity activity;
    private ChapterContract.View view;
    private ChapterContract.Model model;

    private String mBookId;
    private String jsonBook;
    private String jsonChapters;

    public ChapterPresenter(Activity activity, ChapterContract.View view) {
        this.activity = activity;
        this.view = view;
        this.model = new ChapterModel();
    }

    @Override
    public void loadContent() {
//        Intent intent = activity.getIntent();
//        String bookId = intent.getStringExtra("bookId");
        mBookId = "y6xBJBtnXHZtBcdO4RzB";

        model.loadContent(mBookId, new ChapterModel.OnLoadContentListener() {
            @Override
            public void onSuccess(Book book, List<Chapter> chapters) {
                Gson gson = new Gson();
                jsonBook = gson.toJson(book);
                jsonChapters = gson.toJson(chapters);

                view.onLoadContent(book, chapters);
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
        bundle.putString("jsonBook", jsonBook);
        bundle.putString("jsonChapters", jsonChapters);

        Intent intent = new Intent(activity, ContentActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}
