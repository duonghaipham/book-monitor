package com.tyler.book_monitor.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.SettingGlobal;
import com.tyler.book_monitor.data.prefs.SettingsManager;
import com.tyler.book_monitor.ui.all_authors.AllAuthorsActivity;
import com.tyler.book_monitor.ui.all_books.AllBooksActivity;
import com.tyler.book_monitor.ui.archive.ArchiveActivity;
import com.tyler.book_monitor.ui.author.AuthorActivity;
import com.tyler.book_monitor.ui.cover.CoverActivity;
import com.tyler.book_monitor.ui.search.SearchActivity;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private final Context context;
    private final MainContract.View view;
    private final MainContract.Model model;

    private List<Book> mContinuedBooks;
    private List<Author> mAuthors;

    public MainPresenter(Context context, MainContract.View view) {
        this.context = context;
        this.view = view;
        this.model = new MainModel();
    }

    @Override
    public void loadContent() {
        model.loadContent(new MainModel.OnLoadContentListener() {
            @Override
            public void onSuccess(List<Book> continuedBooks, List<Author> authors) {
                mContinuedBooks = continuedBooks;
                mAuthors = authors;

                view.onLoadContent(continuedBooks, authors);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void toArchiveActivity() {
        Intent intent = new Intent(context, ArchiveActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void toSearchActivity() {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
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

    @Override
    public void toCoverActivity(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("bookId", mContinuedBooks.get(position).getId());

        Intent intent = new Intent(context, CoverActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void viewAllPopularAuthors() {
        Intent intent = new Intent(context, AllAuthorsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void viewAllContinueReading() {
        Intent intent = new Intent(context, AllBooksActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showSettings() {
        int language = SettingsManager.getLanguage(context);
        int themeMode = SettingsManager.getThemeMode(context);

        view.onShowSettings(new SettingGlobal(language, themeMode));
    }

    @Override
    public void saveSettings(SettingGlobal setting) {
        SettingsManager.setLanguage(context, setting.getLanguage());
        SettingsManager.setThemeMode(context, setting.getThemeMode());
    }

    @Override
    public void showAbout() {
        view.onShowAbout();
    }
}
