package com.tyler.book_monitor.ui.download;

import android.content.Context;

import com.tyler.book_monitor.data.db.BookService;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class DownloadModel implements DownloadContract.Model {

    private final BookService bookService;

    public DownloadModel(Context context) {
        bookService = new BookService(context);
    }

    @Override
    public void loadContent(OnLoadContentListener listener) {
        List<Book> books = bookService.getAll();

        listener.onSuccess(books);
    }
}
