package com.tyler.book_monitor.ui.main;

import android.content.Context;
import android.content.Intent;

import com.tyler.book_monitor.R;
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
import java.util.Vector;

public class MainPresenter implements MainContract.Presenter {

    private Context context;
    private MainContract.View view;

    public MainPresenter(Context context, MainContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadContent() {
        List<Book> continuedBooks = new Vector<>();
        continuedBooks.add(new Book("The Da Vinci Code", "Dan Brown", "https://firebasestorage.googleapis.com/v0/b/monitor-books.appspot.com/o/book%2Fmock_book_cover.jpg?alt=media&token=badfa394-407a-44f0-98bc-74fe355c9d80"));
        continuedBooks.add(new Book("Harry Potter and the chamber of secrets", "J.K Rowling", "https://firebasestorage.googleapis.com/v0/b/monitor-books.appspot.com/o/book%2Fmock_book_cover.jpg?alt=media&token=badfa394-407a-44f0-98bc-74fe355c9d80"));
        continuedBooks.add(new Book("Harry Potter and the stone", "J.K Rowling", "https://firebasestorage.googleapis.com/v0/b/monitor-books.appspot.com/o/book%2Fmock_book_cover.jpg?alt=media&token=badfa394-407a-44f0-98bc-74fe355c9d80"));

        List<Author> authors = new Vector<>();
        authors.add(new Author("Dan Brown", ""));
        authors.add(new Author("J.K Rowling", ""));
        authors.add(new Author("Issac Newton", ""));
        authors.add(new Author("Malcolm Gladwell", ""));

        view.onLoadContent(continuedBooks, authors);
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
    public void toAuthorActivity() {
        Intent intent = new Intent(context, AuthorActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void toCoverActivity() {
        Intent intent = new Intent(context, CoverActivity.class);
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
