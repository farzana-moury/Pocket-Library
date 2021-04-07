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
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        return view;
    }

    /**
     * Custom ViewPager2 adapter
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
                case 0: return PublisherFragment.newInstance("Publisher1", 0, "Description1", "https://www.canva.com");
                case 1: return PublisherFragment.newInstance("Publisher1", 0, "Description2", "url");
                case 2: return PublisherFragment.newInstance("Publisher1", 0, "Description3", "url");
                case 3: return PublisherFragment.newInstance("Publisher1", 0, "Description4", "url");
                case 4: return PublisherFragment.newInstance("Publisher1", 0, "Description5", "url");
                default: return PublisherFragment.newInstance("Publisher1", 0, "Description1", "url");
            }
        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }

    /**
     * Zoom Out Page Transformer - allows a nice transition between publishers
     *
     * @author Farzana Moury
     * @since April 7 2021
     * @version 1.0
     */
    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.55f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}