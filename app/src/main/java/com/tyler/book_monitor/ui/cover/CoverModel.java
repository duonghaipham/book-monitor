package com.tyler.book_monitor.ui.cover;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tyler.book_monitor.data.db.ArchiveBookService;
import com.tyler.book_monitor.data.db.BookService;
import com.tyler.book_monitor.data.db.ChapterService;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.utils.ImageHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class CoverModel implements CoverContract.Model {

    private final FirebaseFirestore db;
    private final BookService bookService;
    private final ChapterService chapterService;
    private final ArchiveBookService archiveBookService;

    public CoverModel(Context context) {
        db = FirebaseFirestore.getInstance();
        bookService = new BookService(context);
        chapterService = new ChapterService(context);
        archiveBookService = new ArchiveBookService(context);
    }

    @Override
    public void loadContent(String bookId, OnLoadContentListener listener) {
        boolean isDownloaded = bookService.isDownloaded(bookId);
        boolean isArchived = archiveBookService.isArchived(bookId);

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

                        List<HashMap<String, String>> mapChapters = (ArrayList<HashMap<String, String>>) document.get("chapters");
                        List<Chapter> chapters = new Vector<>();
                        for (HashMap<String, String> mapChapter : mapChapters) {
                            chapters.add(new Chapter(
                                    mapChapter.get("title"),
                                    mapChapter.get("content")
                            ));
                        }

                        listener.onSuccess(book, chapters, isDownloaded, isArchived);
                    } else {
                        listener.onFailure("Document does not exist!");
                    }
                });
    }

    @Override
    public void download(Book book, List<Chapter> chapters) {
        bookService.insert(book);
        chapterService.insertChaptersWithBookId(chapters, book.getId());
    }

    @Override
    public void deleteDownload(Book book) {
        ImageHandler.getInstance().deleteImage(book.getCover());
        bookService.delete(book);
        chapterService.deleteAllChaptersByBookId(book.getId());
    }

    @Override
    public void archive(Book book) {
        archiveBookService.insert(book);
    }

    @Override
    public void deleteArchive(String bookId) {
        archiveBookService.delete(bookId);
    }
}
