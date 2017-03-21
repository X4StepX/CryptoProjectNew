package com.example.eduard.cryptoprojectnew.Activities;
import android.annotation.TargetApi;
import android.text.format.Time;

import com.example.eduard.cryptoprojectnew.Model.Book;

public class Transaction {

    private String BookID;
    private String date;
    private String BookOwner;
    private int id;

    /**
     * Get current time in human-readable form.
     *
     * @return current time as a string.
     */
    @TargetApi(3)
    public static String getNow() {

        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%Y_%m_%d %T");
        return sTime;
    }

    public Transaction(String BookOwner, int id){
        date = getNow();
        this.BookOwner = BookOwner;
        this.id = id;
    }

    public String getBookID() {
        return BookID;
    }

    public String getOwner(){
        return BookOwner;
    }

    public String getDate(){
        return date;
    }

    public int getId(){
        return id;
    }

    public void addBookID(String id){
        BookID = id;
    }
}
