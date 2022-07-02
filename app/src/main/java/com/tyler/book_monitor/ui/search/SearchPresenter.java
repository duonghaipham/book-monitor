package com.tyler.book_monitor.ui.search;

import android.content.Context;
import android.content.Intent;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.IObject;
import com.tyler.book_monitor.data.prefs.SettingsManager;
import com.tyler.book_monitor.ui.author.AuthorActivity;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;
import java.util.Vector;

public class SearchPresenter implements SearchContract.Presenter {

    private Context context;
    private SearchContract.View view;

    public SearchPresenter(Context context, SearchContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void initialize() {
        int themeMode = SettingsManager.getThemeMode(context);

        view.onInitialize(themeMode);
    }

    @Override
    public void search(String query) {
        List<Book> books = new Vector<>();
        books.add(new Book("", "The Great Gatsby", "F. Scott Fitzgerald", null));
        books.add(new Book("", "The Great Gatsby", "F. Scott Fitzgerald", null));
        books.add(new Book("", "The Great Gatsby", "F. Scott Fitzgerald", null));

        List<Author> authors = new Vector<>();
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));
        authors.add(new Author("F. Scott Fitzgerald", ""));

        List<IObject> results = new Vector<>();
        results.addAll(books);
        results.addAll(authors);

        view.onSearch(results);
    }

    @Override
    public void toObjectActivity(IObject object) {
        Intent intent;

        if (object instanceof Book) {
            intent = new Intent(context, CoverActivity.class);
        } else {
            intent = new Intent(context, AuthorActivity.class);
        }

        context.startActivity(intent);
    }
}
