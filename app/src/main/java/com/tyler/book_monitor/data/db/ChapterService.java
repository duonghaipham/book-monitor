package com.tyler.book_monitor.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tyler.book_monitor.data.models.Chapter;

import java.util.List;
import java.util.Vector;

public class ChapterService {
    private final SQLiteDatabase db;
    private final Context mContext;

    public ChapterService(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        mContext = context;
        db = dbHelper.getWritableDatabase();
    }

    public void insertChaptersWithBookId(List<Chapter> chapters, String bookId) {
        for (Chapter chapter : chapters) {
            ContentValues values = new ContentValues();

//            values.put(DatabaseHelper.COLUMN_ID, chapter.getId());
            values.put(DatabaseHelper.COLUMN_CHAPTER_TITLE, chapter.getTitle());
            values.put(DatabaseHelper.COLUMN_CHAPTER_CONTENT, chapter.getContent());
            values.put(DatabaseHelper.COLUMN_CHAPTER_BOOK_ID, bookId);

            db.insert(DatabaseHelper.TABLE_CHAPTER, null, values);
        }
    }

    public List<Chapter> getChaptersByBookId(String bookId) {
        String[] columns = {
                DatabaseHelper.COLUMN_CHAPTER_TITLE,
                DatabaseHelper.COLUMN_CHAPTER_CONTENT,
                DatabaseHelper.COLUMN_CHAPTER_BOOK_ID
        };

        String selection = DatabaseHelper.COLUMN_CHAPTER_BOOK_ID + " = ?";
        String[] selectionArgs = { bookId };

        Cursor cursor = db.query(DatabaseHelper.TABLE_CHAPTER, columns, selection, selectionArgs, null, null, null);

        List<Chapter> chapters = new Vector<>();

        while (cursor.moveToNext()) {
            Chapter chapter = new Chapter(
                    cursor.getString(0),
                    cursor.getString(1)
            );

            chapters.add(chapter);
        }

        cursor.close();

        return chapters;
    }

    public void deleteAllChaptersByBookId(String bookId) {
        String selection = DatabaseHelper.COLUMN_CHAPTER_BOOK_ID + " = ?";
        String[] selectionArgs = { bookId };

        db.delete(DatabaseHelper.TABLE_CHAPTER, selection, selectionArgs);
    }
}
