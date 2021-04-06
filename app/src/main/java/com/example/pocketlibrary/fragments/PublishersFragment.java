package com.example.pocketlibrary.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pocketlibrary.R;

/**
 * Displays a viewpager2 containing publishers
 *
 * @author Farzana Moury
 * @since April 6 2021
 * @version 1.0
 */
public class PublishersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publishers, container, false);



        return view;
    }
}