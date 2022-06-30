package com.tyler.book_monitor.ui.archive;

import com.tyler.book_monitor.data.models.Book;

import java.util.List;

public class ArchiveContract {
    public interface View {
        void onLoadContent(List<Book> archivedBooks);
    }

    public interface Presenter {
        void loadContent();
        void toCoverActivity(String bookId);
        void removeBookFromArchive(String bookId);
    }
}
