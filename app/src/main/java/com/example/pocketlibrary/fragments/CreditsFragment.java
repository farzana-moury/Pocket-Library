package com.example.pocketlibrary.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pocketlibrary.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Credits fragment contains credits info and contact info of the creator of this app - me
 *
 * @author Farzana Moury
 * @since March 29 2021
 * @version 1.0
 */
public class CreditsFragment extends Fragment {

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credits, container, false);

        //credits
        //textview containing credits on information used
        TextView infoTextView = view.findViewById(R.id.information);
        infoTextView.setText("Publisher Descriptions\n\nWikipedia.org");

        //textview containing credits on images used
        TextView imagesTextView = view.findViewById(R.id.images);
        imagesTextView.setText("Color Scheme and App Logo\n\nCoolors.co\n\nCanva.com\n\n\n" +
                                "Publisher Logos \n\n" +
                                "https://thebookactivist.files.wordpress.com/2017/04/scholastic-logo-b316f651.png\n\n" +
                                "http://1.bp.blogspot.com/-VDLnBBG8cm0/T-iHGcOAtmI/AAAAAAAAGJ0/riMHobRLCOA/s1600/Harper-Collins-logo-portrait.jpg\n\n" +
                                "https://upload.wikimedia.org/wikipedia/en/thumb/1/1c/Penguin_logo.svg/1200px-Penguin_logo.svg.png\n\n" +
                                "https://cdn.freebiesupply.com/logos/thumbs/2x/academic-press-logo.png\n\n" +
                                "https://upload.wikimedia.org/wikipedia/fr/f/f7/Logo_Hachette_Livre_Actuel.svg\n\n" +
                                "http://www.randomhousebooks.com/wp-content/uploads/2015/01/Random_House_logo_bw2-1024x819.jpg\n\n" +
                                "https://americanlibrariesmagazine.org/wp-content/uploads/2019/07/macmillan-publishers.jpg\n\n" +
                                "https://images.squarespace-cdn.com/content/v1/5cc3d1b051f4d40415789cc2/1607422916001-31853QMVDY8A0LJI1PZI/ke17ZwdGBToddI8pDm48kDFgITcRoterXoQdllT5ciUUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYxCRW4BPu10St3TBAUQYVKcV7ZyRJyI8bwZiMJRrgPaAKqUaXS0tb9q_dTyNVba_kClt3J5x-w6oTQbPni4jzRa/simon-schuster.jpg?format=1500w\n\n" +
                                "https://cdn2.mhpbooks.com/2014/06/Screen-Shot-2014-06-03-at-5.10.33-PM.png");

        //textview containing credits on api used
        TextView apiTextView = view.findViewById(R.id.api);
        apiTextView.setText("Open Library Search API \n\nhttps://openlibrary.org/dev/docs/api/search");

        //common intents
        ImageView email = view.findViewById(R.id.email); //when email icon is pressed
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] email = {"farzm.business@pocketlibrary.com"};

                // Using common intents to store email data
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Ask me anything you'd like.");

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

        ImageView phone = view.findViewById(R.id.phone); //when phone icon is pressed
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // using common intents to store phone data
                Uri phoneNumber = Uri.parse("tel:1112223345");

                Intent intent = new Intent(Intent.ACTION_DIAL, phoneNumber);

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

        ImageView text = view.findViewById(R.id.textMe); //when text icon is pressed
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // using common intents to store text data
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:1112223345"));
                intent.putExtra("sms_body", "Hi, I'd like to contact you on behalf of");

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

        ImageView linkedIn = view.findViewById(R.id.linked_in); //when linkedIn icon is pressed
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // using common intents to store web data
                Uri linkedIn = Uri.parse("https://www.linkedin.com/in/farzana-moury-35b4121b3/");
                Intent intent = new Intent(Intent.ACTION_VIEW, linkedIn);

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