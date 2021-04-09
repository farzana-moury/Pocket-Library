package com.example.pocketlibrary.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pocketlibrary.R;

/**
 * My Books fragment is a screen that contains a recyclerview of books that you've borrowed. These are stored in a
 * SQLite database where basic CRUD operations can be performed (Create, Read, Update, Delete)
 *
 * @author Farzana Moury
 * @since March 29 2021
 * @version 1.0
 */
public class BooksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false);
    }
}