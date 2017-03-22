package com.example.eduard.cryptoprojectnew.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eduard.cryptoprojectnew.Activities.Block;
import com.example.eduard.cryptoprojectnew.Adapter.MyBookRecyclerViewAdapter;
import com.example.eduard.cryptoprojectnew.Database.DBHandler;
import com.example.eduard.cryptoprojectnew.Model.Book;
import com.example.eduard.cryptoprojectnew.R;

import java.math.BigInteger;
import java.util.ArrayList;

public class BookFragment extends Fragment {


    public DBHandler dbHandler;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public ArrayList<Pair<Block, BigInteger>> bookList;

    public MyBookRecyclerViewAdapter myBookRecyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BookFragment() {
    }

    public static BookFragment newInstance() {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        dbHandler = new DBHandler(getContext());
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            //getAllBooks();
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            myBookRecyclerViewAdapter = new MyBookRecyclerViewAdapter(getContext(), bookList, mListener);
            recyclerView.setAdapter(myBookRecyclerViewAdapter);

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    /*public void getAllBooks(){
        bookList = dbHandler.getAllBooks();
    }
*/
    public void updateBooks(ArrayList<Pair<Block, BigInteger>> read){//arrayb list
        ArrayList<Pair<Block, BigInteger>> newBookList = read;
        myBookRecyclerViewAdapter.updateDate(newBookList);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Book book);
    }
}
