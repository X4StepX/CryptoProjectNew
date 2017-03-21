package com.example.eduard.cryptoprojectnew.libraryChain;
import android.annotation.TargetApi;
import android.text.format.Time;

public class Transaction
{

    private String BookID;
    private String Borrower;
    private String date;
    /**
     * Get current time in human-readable form.
     * @return current time as a string.
     */
    @TargetApi(3)
    public static String getNow() {

        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%Y_%m_%d %T");
        return sTime;
    }

    public Transaction(String BookID, String Borrower) // have to add datatime and due date
    {
        this.BookID = BookID;
        this.Borrower = Borrower;
        date = getNow();
    }

    public String getBookID() {
        return BookID;
    }

        public String getBorrower(){
        return Borrower;
    }

    public String getCheckedOutTimestamp(){//Return the Checked out timestamp
        return date; //convert to string and return
    }

}
