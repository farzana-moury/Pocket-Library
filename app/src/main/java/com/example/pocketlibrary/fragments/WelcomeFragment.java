package com.example.pocketlibrary.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.pocketlibrary.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Welcome screen contains app overview - Pocket Library
 *
 * @author Farzana Moury
 * @since March 29 2021
 * @version 1.0
 */
public class WelcomeFragment extends Fragment {

    /**
     * implemented method onCreateView
     *
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState savedInstance state
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        //adding the transition animation
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.enter_anim); //welcome fragment fades in
        view.setAnimation(animation);

        //common intent - location
        ImageView locationIcon = view.findViewById(R.id.location);
        locationIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // using common intents to store location data
                Uri pocketLibrary = Uri.parse("geo:42.34251983898167, -83.05440817072393");

                Intent intent = new Intent(Intent.ACTION_VIEW, pocketLibrary);

                // see if there is a software that can resolve this activity
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    // as long as it does not equal null, do this task
                    // display error message here
                    Snackbar.make(getView(), "No app installed", Snackbar.LENGTH_SHORT).show();
                }else{
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}