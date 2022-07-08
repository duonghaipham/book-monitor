package com.tyler.book_monitor.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tyler.book_monitor.data.models.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ArchiveBookService {
    private final SQLiteDatabase db;

    public ArchiveBookService(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Book> getArchiveBooks() {
        List<Book> books = new Vector<>();
        Cursor cursor = db.query(DatabaseHelper.TABLE_ARCHIVE_BOOK, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        "",
                        new Vector<>()
                );
                books.add(book);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return books;
    }

    public void insert(Book book) {
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_ID, book.getId());
        values.put(DatabaseHelper.COLUMN_NAME, book.getTitle());
        values.put(DatabaseHelper.COLUMN_AUTHOR, book.getAuthor());
        values.put(DatabaseHelper.COLUMN_ARCHIVE_BOOK_AVATAR, book.getCover());

        db.insert(DatabaseHelper.TABLE_ARCHIVE_BOOK, null, values);
    }

    public void delete(String bookId) {
        db.delete(DatabaseHelper.TABLE_ARCHIVE_BOOK,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[] { bookId });
    }

    public boolean isArchived(String bookId) {
        Cursor cursor = db.query(DatabaseHelper.TABLE_ARCHIVE_BOOK,
                new String[] { DatabaseHelper.COLUMN_ID },
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[] { bookId },
                null, null, null);

        boolean isArchived = cursor.moveToNext();

        cursor.close();

        return isArchived;
    }
}
