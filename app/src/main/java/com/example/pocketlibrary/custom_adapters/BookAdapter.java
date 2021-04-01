package com.example.pocketlibrary.custom_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketlibrary.R;
import com.example.pocketlibrary.pojo.Book;

import java.util.ArrayList;

/**
 * Custom adapter class for the book recyclerview
 *
 * @author Farzana Moury
 * @since April 1 2021
 * @version 1.0
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.CustomBookViewHolder>{

    //properties
    private ArrayList<Book> books;
    private Context context;

    //constructor
    public BookAdapter(ArrayList<Book> books, Context context){
        this.books = books;
        this.context = context;
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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item_view, parent, false);
        return new CustomBookViewHolder(view);
    }

    /**
     * @param holder holder
     * @param position position
     */
    @Override
    public void onBindViewHolder(@NonNull CustomBookViewHolder holder, int position) {
        Book book = books.get(position);
    }

    /**
     * @return item count
     */
    @Override
    public int getItemCount() {
        return books.size();
    }

    /**
     * Custom viewHolder class
     *
     * @author Farzana Moury
     * @since April 1st 2021
     * @version 1.0
     */
    class CustomBookViewHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView author;
        protected TextView description;
        protected ImageView cover;
        protected TextView rating;

        /**
         * @param itemView itemView
         */
        public CustomBookViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
