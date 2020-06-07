package com.tugas.mylibraries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tugas.mylibraries.object.Book;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Library";

    private String TABLE_BOOK = "books";
    private String KEY_ID = "id_book";
    private String KEY_TITLE = "title";
    private String KEY_AUTHOR = "author";
    private String KEY_PUBLIHSER = "publisher";
    private String KEY_LANGUAGE = "language";
    private String KEY_YEAR = "year";
    private String KEY_PAGES = "pages";
    private String KEY_RATING = "rating";

    public SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOK + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_AUTHOR + " TEXT,"
                + KEY_PUBLIHSER + " TEXT,"
                + KEY_LANGUAGE + " TEXT,"
                + KEY_YEAR + " INTEGER,"
                + KEY_PAGES + " INTEGER,"
                + KEY_RATING + " INTEGER"
                + ")";
        db.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
    }

    public void addBookRecord(Book book){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_PUBLIHSER, book.getPublisher());
        values.put(KEY_LANGUAGE, book.getLanguage());
        values.put(KEY_YEAR, book.getYear());
        values.put(KEY_PAGES, book.getPages());
        values.put(KEY_RATING, book.getRating());

        db.insert(TABLE_BOOK, null, values);
        db.close();
    }

    public Book getBookRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BOOK, new String[]{
                        KEY_ID,
                        KEY_TITLE,
                        KEY_AUTHOR,
                        KEY_PUBLIHSER,
                        KEY_LANGUAGE,
                        KEY_YEAR,
                        KEY_PAGES,
                        KEY_RATING},
                KEY_ID + "=?", new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        Book book = new Book(
                toInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                toInt(cursor.getString(5)),
                toInt(cursor.getString(6)),
                toInt(cursor.getString(7)));
        return book;
    }

    public List<Book> getAllBookRecord(){
        List<Book> bookList = new ArrayList<>();
        String selectQuery = "Select * FROM " + TABLE_BOOK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                Book book = new Book(
                        toInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7));
                bookList.add(book);
            }while (cursor.moveToNext());
        }
        return bookList;
    }

    public int getBooksCount(){
        String countQuery = "SELECT * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

    public int updateBookRecord(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_PUBLIHSER, book.getPublisher());
        values.put(KEY_LANGUAGE, book.getLanguage());
        values.put(KEY_YEAR, book.getYear());
        values.put(KEY_PAGES, book.getPages());
        values.put(KEY_RATING, book.getRating());


        return db.update(TABLE_BOOK,
                values,
                KEY_ID + " = ?",
                new String[]{String.valueOf(book.getId_Books())});
    }

    public void deleteBookRecord(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK,
                KEY_ID + " = ?",
                new String[]{String.valueOf(book.getId_Books())});
        db.close();
    }

    public int toInt(String s){
        return Integer.parseInt(s);
    }
}
