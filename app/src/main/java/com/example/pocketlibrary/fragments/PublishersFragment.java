package com.example.pocketlibrary.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

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

        CustomViewPager2Adapter adapter = new CustomViewPager2Adapter(getActivity()); //making the adapter our custom adapter
        ViewPager2 viewPager = view.findViewById(R.id.publishers); //finding our viewpager
        viewPager.setAdapter(adapter); //setting the adapter for the viewpager

        return view;
    }

    /**
     * Customn ViewPager2 adapter
     *
     * @author Farzana Moury
     * @since April 6 2021
     * @version 1.0
     */
    public class CustomViewPager2Adapter extends FragmentStateAdapter{

        /**
         * constructor
         * @param fm fragment manager
         */
        public CustomViewPager2Adapter(@NonNull FragmentActivity fm) {
            super(fm);
        }

        //implemented methods
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position){
                case 0: return PublisherFragment.newInstance("Publisher1", 0, "Description1", "url");
                case 1: return PublisherFragment.newInstance("Publisher1", 0, "Description1", "url");
                case 2: return PublisherFragment.newInstance("Publisher1", 0, "Description1", "url");
                case 3: return PublisherFragment.newInstance("Publisher1", 0, "Description1", "url");
                case 4: return PublisherFragment.newInstance("Publisher1", 0, "Description1", "url");
                default: return PublisherFragment.newInstance("Publisher1", 0, "Description1", "url");
            }
        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }
}