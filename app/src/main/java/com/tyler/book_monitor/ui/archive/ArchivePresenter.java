package com.tyler.book_monitor.ui.archive;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.ui.cover.CoverActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ArchivePresenter implements ArchiveContract.Presenter {

    private Context context;
    private ArchiveContract.View view;

    public ArchivePresenter(Context context, ArchiveContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadContent() {
        List<Book> archivedBooks = new Vector<>();
        List<String> categories = new ArrayList<>();
        archivedBooks.add(new Book("", "The Da Vinci Code", "Dan Brown", "", "", categories));
        archivedBooks.add(new Book("", "Harry Potter and the chamber of secrets", "J.K Rowling", "", "", categories));
        archivedBooks.add(new Book("", "Harry Potter and the stone", "J.K Rowling", "", "", categories));
        archivedBooks.add(new Book("", "Harry Potter and the Dead Hallow", "J.K Rowling", "", "", categories));
        archivedBooks.add(new Book("", "Harry Potter and the half blood price", "J.K Rowling", "", "", categories));
        archivedBooks.add(new Book("", "Harry Potter and the cursed child", "J.K Rowling", "", "", categories));

        view.onLoadContent(archivedBooks);
    }

    @Override
    public void toCoverActivity(String bookId) {
        Intent intent = new Intent(context, CoverActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void removeBookFromArchive(String bookId) {
        Toast.makeText(context, "Book removed from archive", Toast.LENGTH_SHORT).show();
    }
}
