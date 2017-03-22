package com.example.eduard.cryptoprojectnew.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.eduard.cryptoprojectnew.Activities.Block;
import com.example.eduard.cryptoprojectnew.Activities.BookInfoActivity;
import com.example.eduard.cryptoprojectnew.Activities.Transaction;
import com.example.eduard.cryptoprojectnew.Database.DBHandler;
import com.example.eduard.cryptoprojectnew.Fragments.BookFragment.OnListFragmentInteractionListener;
import com.example.eduard.cryptoprojectnew.Model.Book;
import com.example.eduard.cryptoprojectnew.R;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class MyBookRecyclerViewAdapter extends RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Pair<Block, BigInteger>> read;

    private final OnListFragmentInteractionListener mListener;
    private Context _context;
    public DBHandler dbHandler;


    public MyBookRecyclerViewAdapter(Context context, ArrayList<Pair<Block, BigInteger>> bookList, OnListFragmentInteractionListener listener) {
        read = bookList;
        mListener = listener;
        _context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_book, parent, false);
        dbHandler = new DBHandler(_context);
        return new ViewHolder(view);
    }
    public void updateDate(ArrayList<Pair<Block, BigInteger>> newBookList){
        read = newBookList;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mBook = read.get(position).first.getTransactions().get(0).first;
        holder.mBookName.setText(holder.mBook.getBookName());

        /* holder.deleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.DeleteBook(.get(position).getBookId());
                mBooks.remove(position);
                notifyDataSetChanged();
           }
        });

            holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mBook);
                }
                Intent intent = new Intent(_context, BookInfoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                _context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return read.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mBookName;
        public Transaction mBook;


        public final Button deleteBook;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mBookName = (TextView) view.findViewById(R.id.bookName);
            deleteBook = (Button)view.findViewById(R.id.BookItemDelete);
        }
    }
}
