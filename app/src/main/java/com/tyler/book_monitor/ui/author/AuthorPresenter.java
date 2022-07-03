package com.tyler.book_monitor.ui.author;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;

public class AuthorPresenter implements AuthorContract.Presenter {

    private final Activity activity;
    private final AuthorContract.View view;
    private final AuthorContract.Model model;

    private List<Book> mAuthorizedBooks;

    public AuthorPresenter(Activity activity, AuthorContract.View view) {
        this.activity = activity;
        this.view = view;
        this.model = new AuthorModel();
    }

    @Override
    public void loadContent() {
        String authorId = activity.getIntent().getStringExtra("authorId");
        String authorName = activity.getIntent().getStringExtra("authorName");

        model.loadContent(authorId, authorName, new AuthorModel.OnLoadContentListener() {
            @Override
            public void onSuccess(Author author, List<Book> books) {
                mAuthorizedBooks = books;

                view.onLoadContent(author, books);
            }

            @Override
            public void onFailure(String message) {
                view.onLoadContent(null, null);
            }
        });
    }

    @Override
    public void toCoverActivity(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("bookId", mAuthorizedBooks.get(position).getId());

        Intent intent = new Intent(activity, CoverActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}
