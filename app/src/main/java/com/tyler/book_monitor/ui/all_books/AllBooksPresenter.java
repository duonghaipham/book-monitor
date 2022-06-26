package com.tyler.book_monitor.ui.all_books;

import android.content.Context;
import android.content.Intent;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.List;
import java.util.Vector;

public class AllBooksPresenter implements AllBooksContract.Presenter {

    private AllBooksContract.View view;

    public AllBooksPresenter(AllBooksContract.View view) {
        this.view = view;
    }

    @Override
    public void loadContent() {
        List<Book> continuedBooks = new Vector<>();
        continuedBooks.add(new Book("The Da Vinci Code", "Dan Brown", R.drawable.mock_book_cover));
        continuedBooks.add(new Book("Harry Potter and the chamber of secrets", "J.K Rowling", R.drawable.mock_book_cover_1));
        continuedBooks.add(new Book("Harry Potter and the stone", "J.K Rowling", R.drawable.mock_book_cover_2));
        continuedBooks.add(new Book("Harry Potter and the Dead Hallow", "J.K Rowling", R.drawable.mock_book_cover_2));
        continuedBooks.add(new Book("Harry Potter and the half blood price", "J.K Rowling", R.drawable.mock_book_cover_2));
        continuedBooks.add(new Book("Harry Potter and the cursed child", "J.K Rowling", R.drawable.mock_book_cover_2));

        view.onLoadContent(continuedBooks);
    }

    @Override
    public void toCoverActivity(Context context) {
        Intent intent = new Intent(context, CoverActivity.class);
        context.startActivity(intent);
    }
}
