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

    private List<Book> mBooks;

    public AllBooksPresenter(Context context, AllBooksContract.View view) {
        this.context = context;
        this.view = view;
        this.model = new AllBooksModel();
    }

    @Override
    public void loadContent() {
        model.loadContent(new AllBooksContract.Model.OnLoadContentListener() {
            @Override
            public void onSuccess(List<Book> books) {
                mBooks = books;

                view.onLoadContent(books);
            }

            @Override
            public void onFailure(String message) {
                view.onLoadContent(null);
            }
        });
    }

    @Override
    public void toCoverActivity(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("bookId", mBooks.get(position).getId());

        Intent intent = new Intent(context, CoverActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
