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

        /**
         * createFragment method will return a new instance of type Publisher in order of position in viewpager2
         *
         * @param position the position of the object in the viewpager2
         * @return
         */
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position){
                case 0: return PublisherFragment.newInstance("Scholastic", R.drawable.scholastic, "Scholastic Corporation is an American multinational publishing, education and media company that publishes and distributes comics, books and educational materials for schools, parents and children.", "https://www.scholastic.com/home");
                case 1: return PublisherFragment.newInstance("HarperCollins", R.drawable.harper_collins, "HarperCollins Publishers LLC is one of the world's largest publishing companies and is one of the Big Five English-language publishing companies.", "https://www.harpercollins.com/");
                case 2: return PublisherFragment.newInstance("Penguin Books", R.drawable.penguin_books, "Penguin Books is a British publishing house. It was co-founded in 1935 by Sir Allen Lane with his brothers Richard and John, as a line of the publishers The Bodley Head, only becoming a separate company the following year.", "https://www.penguin.com/");
                case 3: return PublisherFragment.newInstance("Academic Press", R.drawable.academic_press, "Academic Press is an academic book publisher founded in 1941. It was acquired by Harcourt, Brace & World in 1969. Reed Elsevier bought Harcourt in 2000, and Academic Press is now an imprint of Elsevier.", "https://www.elsevier.com/books-and-journals/academic-press");
                case 4: return PublisherFragment.newInstance("Hatchette Livre", R.drawable.hatchette_livre, "Hachette Book Group (HBG) is a publishing company owned by Hachette Livre, the largest publishing company in France, and the third largest trade and educational publisher in the world. Hachette Livre is a wholly owned subsidiary of Lagardère Group.", "https://www.hachette.com/en/homepage/");
                case 5: return PublisherFragment.newInstance("Random House", R.drawable.random_house, "Random House is an American book publisher and the largest general-interest paperback publisher in the world. It is part of Penguin Random House, which is owned by German media conglomerate Bertelsmann. ", "https://www.randomhousebooks.com/");
                case 6: return PublisherFragment.newInstance("Macmillan Publishers", R.drawable.macmillan_publishers, "Macmillan Publishers Ltd (occasionally known as the Macmillan Group) is a British publishing company traditionally considered to be one of the 'Big Five' English language publishers.", "https://us.macmillan.com/");
                case 7: return PublisherFragment.newInstance("Simon & Schuster", R.drawable.simon_schuster, "Simon & Schuster is an American publishing company and a subsidiary of ViacomCBS founded in New York City in 1924 by Richard L. Simon and M. Lincoln Schuster. As of 2016, Simon & Schuster was the third largest publisher in the United States.", "https://www.simonandschuster.com/");
                case 8: return PublisherFragment.newInstance("Penguin Random House", R.drawable.penguin_random, "Penguin Random House LLC is a multinational conglomerate publishing company formed in 2013 from the merger of Penguin Group and Random House.", "https://www.penguinrandomhouse.com/");
                default: return PublisherFragment.newInstance("No Publisher", 0, "No description", "https://www.google.com/");
            }
        }

        /**
         * @return the size of the items together
         */
        @Override
        public int getItemCount() {
            return 9;
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