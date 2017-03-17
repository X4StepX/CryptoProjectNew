package com.example.eduard.cryptoprojectnew.Model;

/**
 * Created by eduard on 3/6/2017.
 */

public class Book {
    public int BookId;
    public String BookName;

    public Book(){

    }

    public Book(int bookId, String bookName) {
        BookId = bookId;
        BookName = bookName;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }
}

