package com.example.eduard.cryptoprojectnew.Activities;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eduard.cryptoprojectnew.Database.DBHandler;
import com.example.eduard.cryptoprojectnew.Fragments.BookFragment;
import com.example.eduard.cryptoprojectnew.Model.Book;
import com.example.eduard.cryptoprojectnew.R;

import java.math.BigInteger;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BookFragment.OnListFragmentInteractionListener {

    public Button mCancel;
    public Button mConfirm;
    public EditText mBookName;
    public EditText mBookOwner;
    BookFragment booksFragment;



    Blockchain chain = new Blockchain();//When program starts create a chain
    RSA key = new RSA(2051);//Add the RSA key to encrypt
    int id = 0;


    public DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        booksFragment = (BookFragment) getSupportFragmentManager().findFragmentById(R.id.bookFragment);
        dbHandler = new DBHandler(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBook(view);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @TargetApi(5)
    public void AddBook(View view){
        final View mView = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_book, null);

        mBookName = (EditText)mView.findViewById(R.id.BookName);
        mBookOwner = (EditText)mView.findViewById(R.id.BookOwner);//Add the owner of the book
        mCancel = (Button)mView.findViewById(R.id.cancelButton);
        mConfirm = (Button)mView.findViewById(R.id.confirmButton);
        id++;//everytime a new book gets created increment the id.

        //Add the owner to the book and book id


        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transaction tx = new Transaction(String.valueOf(mBookOwner.getText()),String.valueOf(mBookOwner.getText()),id);
                chain.AddTransaction(tx,key);

                booksFragment.updateBooks();

                ArrayList<Pair<Block, BigInteger>> read = chain.ReadChain();

                String data,date;
                int id1;
                data = read.get(0).first.getTransactions().get(0).first.getOwner();
                id1 = read.get(0).first.getTransactions().get(0).first.getId();
                date = read.get(0).first.getTransactions().get(0).first.getDate();
                Log.d("Block Chain", data + id1 + date);
                dialog.dismiss();
            }
        });



        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onListFragmentInteraction(Book book) {

    }
}
