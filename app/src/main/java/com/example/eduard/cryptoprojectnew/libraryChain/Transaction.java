package com.example.eduard.cryptoprojectnew.libraryChain;

import java.text.SimpleDateFormat;

public class Transaction
{
    private String BookID;
    private String Borrower;
    private LocalDateTime CheckedOutTimestamp;
    private LocalDateTime DueByTimestamp;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Transaction(String BookID, String Borrower, LocalDateTime CheckedOutTimestamp, LocalDateTime DueByTimestamp)
    {
        this.BookID = BookID;
        this.Borrower = Borrower;
        this.CheckedOutTimestamp = CheckedOutTimestamp;
        this.DueByTimestamp = DueByTimestamp;
    }

    public String getBookID() {
        return BookID;
    }

    public String getBorrower(){
        return Borrower;
    }

    public String getCheckedOutTimestamp(){//Return the Checked out timestamp
        return CheckedOutTimestamp.format(formatter); //convert to string and return
    }

    public String getDueByTimestamp() {//return due by timestamp
        return DueByTimestamp.format(formatter); //convert to string and return
    }
}
