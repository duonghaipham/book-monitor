package com.tyler.book_monitor.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tyler.book_monitor.data.models.Book;
import com.tyler.book_monitor.utils.ImageHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class BookService {
    private final SQLiteDatabase db;
    private final Context mContext;

    public BookService(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        mContext = context;
        db = dbHelper.getWritableDatabase();
    }

    public void insert(Book book) {
        Picasso.get().load(book.getCover()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                String localCover = ImageHandler.getInstance().saveToInternalStorage(bitmap, mContext);

                ContentValues values = new ContentValues();

                values.put(DatabaseHelper.COLUMN_ID, book.getId());
                values.put(DatabaseHelper.COLUMN_NAME, book.getTitle());
                values.put(DatabaseHelper.COLUMN_AUTHOR, book.getAuthor());
                values.put(DatabaseHelper.COLUMN_INTRODUCTION, book.getIntroduction());
                values.put(DatabaseHelper.COLUMN_AVATAR, localCover);
                values.put(DatabaseHelper.COLUMN_CATEGORIES, String.join(", ", book.getCategories()));

                db.insert(DatabaseHelper.TABLE_BOOK, null, values);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.println(Log.ERROR, "Picasso", e.getMessage());
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    public List<Book> getAll() {
        List<Book> books = new Vector<>();

        String[] columns = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_AUTHOR,
                DatabaseHelper.COLUMN_INTRODUCTION,
                DatabaseHelper.COLUMN_AVATAR,
                DatabaseHelper.COLUMN_CATEGORIES
        };

        String sortOrder = DatabaseHelper.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(DatabaseHelper.TABLE_BOOK, columns, null, null, null, null, sortOrder);

        while (cursor.moveToNext()) {
            Book book = new Book(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(4),
                    cursor.getString(3),
                    Arrays.asList(cursor.getString(5).split(", "))
            );

            books.add(book);
        }

        cursor.close();

        return books;
    }

    public Book getById(String id) {
        String[] columns = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_AUTHOR,
                DatabaseHelper.COLUMN_INTRODUCTION,
                DatabaseHelper.COLUMN_AVATAR,
                DatabaseHelper.COLUMN_CATEGORIES
        };

        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = { id };

        Cursor cursor = db.query(DatabaseHelper.TABLE_BOOK, columns, selection, selectionArgs, null, null, null);

        Book book = null;

        if (cursor.moveToNext()) {
            book = new Book(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(4),
                    cursor.getString(3),
                    Arrays.asList(cursor.getString(5).split(", "))
            );

            cursor.close();
        }

        cursor.close();

        return book;
    }

    public boolean isDownloaded(String bookId) {
        String[] columns = {
                DatabaseHelper.COLUMN_ID
        };

        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = { bookId };

        Cursor cursor = db.query(DatabaseHelper.TABLE_BOOK, columns, selection, selectionArgs, null, null, null);

        boolean exists = cursor.moveToNext();

        cursor.close();

        return exists;
    }

    public void delete(Book book) {
        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = { book.getId() };

        ImageHandler.getInstance().deleteImage(book.getCover());

        db.delete(DatabaseHelper.TABLE_BOOK, selection, selectionArgs);
    }
}
