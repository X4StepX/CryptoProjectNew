package com.example.eduard.cryptoprojectnew.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eduard.cryptoprojectnew.Model.Book;

import java.util.ArrayList;

/**
 * Created by eduard on 3/4/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    public Context _context;

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database name
    private static final String DATABASE_NAME = "crypto.db";

    //Tables
    public static final String TABLE_BOOK = "Books";
    //Cols
    public static final String COL_ID = "id";
    public static final String COL_BOOKNAME = "BookName";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
    }

    public SQLiteDatabase db = this.getWritableDatabase();

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_BOOKNAME + " TEXT " +
                ")";
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_BOOK);

        onCreate(db);
    }

    public void DeleteBook(int BookId){
        db.execSQL("DELETE FROM " + TABLE_BOOK + " WHERE " + COL_ID + " = " + BookId + "");
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        //select all query
        String selectQuery = "SELECT * FROM " + TABLE_BOOK;
        // return Book list
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setBookId(cursor.getInt(0));
                book.setBookName(cursor.getString(1));

                bookList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return book list

        return bookList;
    }

    public Book getBook(int BookId){
        Book book = new Book();

        String selectQuery = "SELECT * FROM " + TABLE_BOOK + " WHERE " + COL_ID + " = " + BookId + "" ;
        // return book list
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                book.setBookId(cursor.getInt(0));
                book.setBookName(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return book

        return book;
    }

    public void InsertBook(String bookName) {
        ContentValues values = new ContentValues();
        values.put(COL_BOOKNAME, bookName);
        db.insert(TABLE_BOOK, null, values);
    }
}

