package com.tyler.book_monitor.ui.author;

import android.content.Context;
import android.content.Intent;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;
import java.util.Vector;

public class AuthorPresenter implements AuthorContract.Presenter {

    private Context context;
    private AuthorContract.View view;

    public AuthorPresenter(Context context, AuthorContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadContent() {
        List<Book> books = new Vector<>();
        books.add(new Book("The Da Vinci Code", "Dan Brown", "https://firebasestorage.googleapis.com/v0/b/monitor-books.appspot.com/o/book%2Fmock_book_cover.jpg?alt=media&token=badfa394-407a-44f0-98bc-74fe355c9d80"));
        books.add(new Book("Harry Potter and the chamber of secrets", "J.K Rowling", "https://firebasestorage.googleapis.com/v0/b/monitor-books.appspot.com/o/book%2Fmock_book_cover.jpg?alt=media&token=badfa394-407a-44f0-98bc-74fe355c9d80"));
        books.add(new Book("Harry Potter and the stone", "J.K Rowling", "https://firebasestorage.googleapis.com/v0/b/monitor-books.appspot.com/o/book%2Fmock_book_cover.jpg?alt=media&token=badfa394-407a-44f0-98bc-74fe355c9d80"));
        books.add(new Book("Harry Potter and the Dead Hallow", "J.K Rowling", "https://firebasestorage.googleapis.com/v0/b/monitor-books.appspot.com/o/book%2Fmock_book_cover.jpg?alt=media&token=badfa394-407a-44f0-98bc-74fe355c9d80"));
        books.add(new Book("Harry Potter and the half blood price", "J.K Rowling", "https://firebasestorage.googleapis.com/v0/b/monitor-books.appspot.com/o/book%2Fmock_book_cover.jpg?alt=media&token=badfa394-407a-44f0-98bc-74fe355c9d80"));
        books.add(new Book("Harry Potter and the cursed child", "J.K Rowling", "https://firebasestorage.googleapis.com/v0/b/monitor-books.appspot.com/o/book%2Fmock_book_cover.jpg?alt=media&token=badfa394-407a-44f0-98bc-74fe355c9d80"));

        view.onLoadContent(books);
    }

    @Override
    public void toCoverActivity() {
        Intent intent = new Intent(context, CoverActivity.class);
        context.startActivity(intent);
    }
}
