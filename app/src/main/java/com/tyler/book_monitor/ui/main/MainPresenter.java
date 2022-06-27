package com.tyler.book_monitor.ui.main;

import android.content.Context;
import android.content.Intent;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Author;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.all_authors.AllAuthorsActivity;
import com.tyler.book_monitor.ui.all_books.AllBooksActivity;
import com.tyler.book_monitor.ui.all_books.AllBooksContract;
import com.tyler.book_monitor.ui.author.AuthorActivity;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;
import java.util.Vector;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void loadContent() {
        List<Book> continuedBooks = new Vector<>();
        continuedBooks.add(new Book("The Da Vinci Code", "Dan Brown", R.drawable.mock_book_cover));
        continuedBooks.add(new Book("Harry Potter and the chamber of secrets", "J.K Rowling", R.drawable.mock_book_cover_1));
        continuedBooks.add(new Book("Harry Potter and the stone", "J.K Rowling", R.drawable.mock_book_cover_2));

        List<Author> authors = new Vector<>();
        authors.add(new Author("Dan Brown", ""));
        authors.add(new Author("J.K Rowling", ""));
        authors.add(new Author("Issac Newton", ""));
        authors.add(new Author("Malcolm Gladwell", ""));

        view.onLoadContent(continuedBooks, authors);
    }

    @Override
    public void toAuthorActivity(Context context) {
        Intent intent = new Intent(context, AuthorActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void toCoverActivity(Context context) {
        Intent intent = new Intent(context, CoverActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void viewAllPopularAuthors(Context context) {
        Intent intent = new Intent(context, AllAuthorsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void viewAllContinueReading(Context context) {
        Intent intent = new Intent(context, AllBooksActivity.class);
        context.startActivity(intent);
    }
}