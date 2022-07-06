package com.tyler.book_monitor.ui.cover;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tyler.book_monitor.data.db.BookService;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class CoverModel implements CoverContract.Model {

    private final FirebaseFirestore db;
    private final BookService bookService;

    public CoverModel(Context context) {
        db = FirebaseFirestore.getInstance();
        bookService = new BookService(context);
    }

    @Override
    public void loadContent(String bookId, OnLoadContentListener listener) {
        boolean isDownloaded = bookService.isDownloaded(bookId);

        db.collection("books")
                .document(bookId)
                .get()
                .addOnSuccessListener(document -> {
                    if (document.exists()) {
                        Book book = new Book(
                                document.getId(),
                                document.get("name").toString(),
                                document.get("author").toString(),
                                document.get("avatar").toString(),
                                document.get("introduction").toString(),
                                (List<String>)document.get("categories")
                        );

                        listener.onSuccess(book, isDownloaded);
                    } else {
                        listener.onFailure("Document does not exist!");
                    }
                });
    }

    @Override
    public void download(Book book) {
        bookService.insert(book);
    }

    @Override
    public void deleteDownload(String bookId) {
        bookService.delete(bookId);
    }
}
