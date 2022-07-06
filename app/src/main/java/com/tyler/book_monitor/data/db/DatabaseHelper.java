package com.tyler.book_monitor.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "book_monitor";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_BOOK = "Book";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_INTRODUCTION = "introduction";
    public static final String COLUMN_AVATAR = "avatar";
    public static final String COLUMN_ADDED_AT = "added_at";
    public static final String COLUMN_CATEGORIES = "categories";

    public static final String TABLE_CHAPTER = "Chapter";
    public static final String COLUMN_CHAPTER_ID = "id";
    public static final String COLUMN_CHAPTER_TITLE = "title";
    public static final String COLUMN_CHAPTER_CONTENT = "content";
    public static final String COLUMN_CHAPTER_BOOK_ID = "book_id";

    public static final String TABLE_ARCHIVE_BOOK = "ArchiveBook";
    public static final String COLUMN_ARCHIVE_BOOK_ID = "id";

    private static final String CREATE_TABLE_BOOK =
            "CREATE TABLE " + TABLE_BOOK + "("
                    + COLUMN_ID + " TEXT PRIMARY KEY, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_AUTHOR + " TEXT, "
                    + COLUMN_INTRODUCTION + " TEXT, "
                    + COLUMN_AVATAR + " TEXT, "
                    + COLUMN_ADDED_AT + " TEXT, "
                    + COLUMN_CATEGORIES + " TEXT"
                    + ")";

    private static final String CREATE_TABLE_CHAPTER =
            "CREATE TABLE " + TABLE_CHAPTER + "("
                    + COLUMN_CHAPTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_CHAPTER_TITLE + " TEXT, "
                    + COLUMN_CHAPTER_CONTENT + " TEXT, "
                    + COLUMN_CHAPTER_BOOK_ID + " INTEGER"
                    + ")";

    private static final String CREATE_TABLE_ARCHIVE_BOOK =
            "CREATE TABLE " + TABLE_ARCHIVE_BOOK + "("
                    + COLUMN_ARCHIVE_BOOK_ID + " TEXT PRIMARY KEY"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOK);
        db.execSQL(CREATE_TABLE_CHAPTER);
        db.execSQL(CREATE_TABLE_ARCHIVE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAPTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARCHIVE_BOOK);
        onCreate(db);
    }
}
