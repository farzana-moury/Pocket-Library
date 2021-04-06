package com.example.pocketlibrary.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pocketlibrary.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * What each publisher would look like in the viewpager2 - information it will contain
 *
 * @author Farzana Moury
 * @since April 6 2021
 * @version 1.0
 */
public class PublisherFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NAME = "name"; // the name of the publisher
    private static final String IMAGE = "image"; // their image
    private static final String DESCRIPTION = "description"; // their image
    private static final String URL = "url"; // their website url

    // types of parameters
    private String name;
    private int image;
    private String description;
    private String url;

    /**
     * newInstance method - contains types and number of parameters
     *
     * @param name publisher name
     * @param image publisher image
     * @param url publisher url
     * @return A new instance of fragment
     */
    public static PublisherFragment newInstance( String name, int image, String description, String url) {
        PublisherFragment fragment = new PublisherFragment();

        //transfers publisher info to the bundle, where the parameters are the keys
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putInt(IMAGE, image);
        args.putString(DESCRIPTION, description);
        args.putString(URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //if there is information in the bundle
        if (getArguments() != null) {
            //we retrieve it
            name = getArguments().getString(NAME);
            image = getArguments().getInt(IMAGE);
            description = getArguments().getString(DESCRIPTION);
            url = getArguments().getString(URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publisher, container, false);

        //setting the view objects to their information
        if(name != null){
            TextView publisherName = view.findViewById(R.id.name);
            publisherName.setText(name);
        }
        if(image != 0){
            ImageView publisherImage = view.findViewById(R.id.image);
        }
        if(description != null){
            TextView publisherDesc = view.findViewById(R.id.description);
            publisherDesc.setText(name);
        }

        Button urlButton = view.findViewById(R.id.urlButton);
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //using common intent to store web data
                Uri webURL = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webURL);

                // see if there is a software that can resolve this activity
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    Snackbar.make(getView(), "No app installed", Snackbar.LENGTH_SHORT).show();
                }else{
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}