package com.example.pocketlibrary.custom_adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Custom adapter class for the book recyclerview
 *
 * @author Farzana Moury
 * @since April 1 2021
 * @version 1.0
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.CustomBookViewHolder>{

    //properties

    //constructor
    public BookAdapter(){

    }

    //implemented methods
    /**
     * @param parent parent
     * @param viewType viewType
     * @return CustomBookViewHolder(view)
     */
    @NonNull
    @Override
    public CustomBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * @param holder holder
     * @param position position
     */
    @Override
    public void onBindViewHolder(@NonNull CustomBookViewHolder holder, int position) {

    }

    /**
     * @return item count
     */
    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * Custom viewHolder class
     *
     * @author Farzana Moury
     * @since April 1st 2021
     * @version 1.0
     */
    class CustomBookViewHolder extends RecyclerView.ViewHolder{

        /**
         * @param itemView itemView
         */
        public CustomBookViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
