package com.tyler.book_monitor.ui.download;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;
import java.util.Vector;

public class DownloadPresenter implements DownloadContract.Presenter {

    private final Activity activity;
    private final DownloadContract.View view;
//    private DownloadContract.Model model;

    public DownloadPresenter(Activity activity, DownloadContract.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void loadContent() {
        List<Book> downloadedBooks = new Vector<>();
        List<String> categories = new Vector<>();
        downloadedBooks.add(new Book("", "The Da Vinci Code", "Dan Brown", "", "", categories));
        downloadedBooks.add(new Book("", "Harry Potter and the chamber of secrets", "J.K Rowling", "", "", categories));
        downloadedBooks.add(new Book("", "Harry Potter and the stone", "J.K Rowling", "", "", categories));
        downloadedBooks.add(new Book("", "Harry Potter and the Dead Hallow", "J.K Rowling", "", "", categories));
        downloadedBooks.add(new Book("", "Harry Potter and the half blood price", "J.K Rowling", "", "", categories));
        downloadedBooks.add(new Book("", "Harry Potter and the cursed child", "J.K Rowling", "", "", categories));

        view.onLoadContent(downloadedBooks);
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
