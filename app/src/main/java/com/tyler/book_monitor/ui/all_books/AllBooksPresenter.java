package com.tyler.book_monitor.ui.all_books;

import android.content.Context;
import android.content.Intent;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AllBooksPresenter implements AllBooksContract.Presenter {

    private Context context;
    private AllBooksContract.View view;

    public AllBooksPresenter(Context context, AllBooksContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadContent() {
        List<String> categories = new ArrayList<>();
        List<Book> continuedBooks = new Vector<>();
        continuedBooks.add(new Book("", "The Da Vinci Code", "Dan Brown", "", "", categories));
        continuedBooks.add(new Book("", "Harry Potter and the chamber of secrets", "J.K Rowling", "", "", categories));
        continuedBooks.add(new Book("", "Harry Potter and the stone", "J.K Rowling", "", "", categories));
        continuedBooks.add(new Book("", "Harry Potter and the Dead Hallow", "J.K Rowling", "", "", categories));
        continuedBooks.add(new Book("", "Harry Potter and the half blood price", "J.K Rowling", "", "", categories));
        continuedBooks.add(new Book("", "Harry Potter and the cursed child", "J.K Rowling", "", "", categories));

        view.onLoadContent(continuedBooks);
    }

    @Override
    public void toCoverActivity() {
        Intent intent = new Intent(context, CoverActivity.class);
        context.startActivity(intent);
    }
}
