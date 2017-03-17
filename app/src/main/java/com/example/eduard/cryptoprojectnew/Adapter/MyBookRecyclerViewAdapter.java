package com.example.eduard.cryptoprojectnew.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eduard.cryptoprojectnew.Activities.BookInfoActivity;
import com.example.eduard.cryptoprojectnew.Fragments.BookFragment.OnListFragmentInteractionListener;
import com.example.eduard.cryptoprojectnew.Model.Book;
import com.example.eduard.cryptoprojectnew.R;

import java.util.ArrayList;
import java.util.List;


public class MyBookRecyclerViewAdapter extends RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder> {

    private  ArrayList<Book> mBooks;
    private final OnListFragmentInteractionListener mListener;
    private Context _context;

    public MyBookRecyclerViewAdapter(Context context, ArrayList<Book> bookList, OnListFragmentInteractionListener listener) {
        mBooks = bookList;
        mListener = listener;
        _context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_book, parent, false);
        return new ViewHolder(view);
    }
    public void updateDate(ArrayList<Book> newBookList){
        mBooks = newBookList;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mBook = mBooks.get(position);
        holder.mIdView.setText(Integer.toString(mBooks.get(position).getBookId()));
        holder.mBookName.setText(mBooks.get(position).getBookName());


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
                intent.putExtra("bookId", holder.mBook.getBookId());
                _context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mBookName;
        public Book mBook;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.bookId);
            mBookName = (TextView) view.findViewById(R.id.bookName);
        }
    }
}
