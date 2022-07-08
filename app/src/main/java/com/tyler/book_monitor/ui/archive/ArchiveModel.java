package com.tyler.book_monitor.ui.archive;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tyler.book_monitor.data.db.ArchiveBookService;
import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class ArchiveModel implements ArchiveContract.Model {

    private final FirebaseFirestore db;
    private final ArchiveBookService archiveBookService;

    public ArchiveModel(Context context) {
        db = FirebaseFirestore.getInstance();
        archiveBookService = new ArchiveBookService(context);
    }

    @Override
    public void loadContent(OnLoadContentListener listener) {
        List<Book> archiveBooks = archiveBookService.getArchiveBooks();

        listener.onLoadContent(archiveBooks);
    }

    @Override
    public void removeBookFromArchive(String bookId) {
        archiveBookService.delete(bookId);
    }
}
