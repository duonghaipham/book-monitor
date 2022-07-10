package com.tyler.book_monitor.ui.all_books;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;

public class AllBooksPresenter implements AllBooksContract.Presenter {

    private final Context context;
    private final AllBooksContract.View view;
    private final AllBooksContract.Model model;

    public AllBooksPresenter(Context context, AllBooksContract.View view) {
        this.context = context;
        this.view = view;
        this.model = new AllBooksModel();
    }

    @Override
    public void loadContent() {
        model.loadContent(new AllBooksContract.Model.OnLoadContentListener() {
            @Override
            public void onSuccess(List<Book> books, List<String> pages) {
                view.onLoadContent(books, pages);
            }

            @Override
            public void onFailure(String message) {
                view.onLoadContent(null, null);
            }
        });
    }

    @Override
    public void loadContentByPage(int page) {
        model.loadContentByPage(page, new AllBooksContract.Model.OnLoadContentByPageListener() {
            @Override
            public void onSuccess(List<Book> books) {
                view.onLoadContentByPage(books);
            }

            @Override
            public void onFailure(String message) {
                view.onLoadContentByPage(null);
            }
        });
    }

    @Override
    public void toCoverActivity(String bookId) {
        Bundle bundle = new Bundle();
        bundle.putString("bookId", bookId);

        Intent intent = new Intent(context, CoverActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
