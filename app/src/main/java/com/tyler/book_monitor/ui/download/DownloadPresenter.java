package com.tyler.book_monitor.ui.download;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;

public class DownloadPresenter implements DownloadContract.Presenter {

    private final Activity activity;
    private final DownloadContract.View view;
    private final DownloadContract.Model model;

    public DownloadPresenter(Activity activity, DownloadContract.View view) {
        this.activity = activity;
        this.view = view;
        model = new DownloadModel(activity);
    }

    @Override
    public void loadContent() {
        model.loadContent(new DownloadModel.OnLoadContentListener() {
            @Override
            public void onSuccess(List<Book> downloadedBooks) {
                view.onLoadContent(downloadedBooks);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void toCoverActivity(String bookId) {
        Bundle bundle = new Bundle();
        bundle.putString("bookId", bookId);

        Intent intent = new Intent(activity, CoverActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public void removeBookFromDownload(String bookId) {
    }
}
