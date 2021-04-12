package com.example.pocketlibrary.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pocketlibrary.R;
import com.example.pocketlibrary.adapters.BorrowedBookAdapter;
import com.example.pocketlibrary.database.BookDatabase;
import com.example.pocketlibrary.pojo.Book;

import java.util.ArrayList;

/**
 * My Books fragment is a screen that contains a recyclerview of books that you've borrowed. These are stored in a
 * SQLite database where basic CRUD operations can be performed (Create, Read, Update, Delete)
 *
 * @author Farzana Moury
 * @since March 29 2021
 * @version 1.0
 */
public class BooksFragment extends Fragment {

    public static BookDatabase db;
    public static ArrayList<Book> myBooks = new ArrayList<>();
    public static RecyclerView myBooksRecyclerView;
    @SuppressLint("StaticFieldLeak")
    public static BorrowedBookAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        //setting the recyclerview for this fragment
        myBooksRecyclerView = view.findViewById(R.id.borrowedBookRecyclerView); //containing the books we borrowed

        myBooksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //setting the layout manager

        db = new BookDatabase(getContext()); //setting up the database

        myBooks = db.getAllBooks(); //retrieving all our books from the database and storing it into an array

        adapter = new BorrowedBookAdapter(myBooks, getContext());

        myBooksRecyclerView.setAdapter(adapter); //setting the adapter

        db.close(); //closing the database safely

        return view;
    }
}