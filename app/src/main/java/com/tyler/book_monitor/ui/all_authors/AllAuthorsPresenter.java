package com.tyler.book_monitor.ui.all_authors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.ui.author.AuthorActivity;

import java.util.List;
import java.util.Vector;

public class AllAuthorsPresenter implements AllAuthorsContract.Presenter {

    private final Context context;
    private final AllAuthorsContract.View view;
    private final AllAuthorsContract.Model model;

    private List<Author> mAuthors;

    public AllAuthorsPresenter(Context context, AllAuthorsContract.View view) {
        this.context = context;
        this.view = view;
        this.model = new AllAuthorsModel();
    }

    @Override
    public void loadContent() {
        model.loadContent(new AllAuthorsContract.Model.OnLoadContentListener() {
            @Override
            public void onSuccess(List<Author> authors) {
                mAuthors = authors;

                view.onLoadContent(authors);
            }

            @Override
            public void onFailure(String message) {
                view.onLoadContent(new Vector<>());
            }
        });
    }

    @Override
    public void toAuthorActivity(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("authorId", mAuthors.get(position).getId());
        bundle.putString("authorName", mAuthors.get(position).getName());

        Intent intent = new Intent(context, AuthorActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
