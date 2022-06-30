package com.tyler.book_monitor.ui.all_authors;

import android.content.Context;
import android.content.Intent;

import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.ui.author.AuthorActivity;

import java.util.List;
import java.util.Vector;

public class AllAuthorsPresenter implements AllAuthorsContract.Presenter {

    private Context context;
    private AllAuthorsContract.View view;

    public AllAuthorsPresenter(Context context, AllAuthorsContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadContent() {
        List<Author> authors = new Vector<>();
        authors.add(new Author("Dan Brown", ""));
        authors.add(new Author("J.K Rowling", ""));
        authors.add(new Author("Issac Newton", ""));
        authors.add(new Author("Malcolm Gladwell", ""));
        authors.add(new Author("Dale", ""));
        authors.add(new Author("Chip", ""));
        authors.add(new Author("Donald Duck", ""));
        authors.add(new Author("Tom", ""));
        authors.add(new Author("Jerry", ""));

        view.onLoadContent(authors);
    }

    @Override
    public void toAuthorActivity() {
        Intent intent = new Intent(context, AuthorActivity.class);
        context.startActivity(intent);
    }
}
