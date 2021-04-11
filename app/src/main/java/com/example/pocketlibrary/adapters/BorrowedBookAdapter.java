package com.example.pocketlibrary.adapters;

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
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Custom book adapter for My books fragment recyclerview
 *
 * @author Farzana Moury
 * @since April 11 2021
 * @version 1.0
 */
public class BorrowedBookAdapter extends RecyclerView.Adapter<BorrowedBookAdapter.CustomBorrowedBookViewHolder>{
    //properties
    private ArrayList<Book> books;
    private Context context;

    //constructor
    public BorrowedBookAdapter(ArrayList<Book> books, Context context){
        this.books = books;
        this.context = context;
    }

    //implemented methods
    /**
     * @param parent parent
     * @param viewType viewType
     * @return CustomBorrowedBookViewHolder(view)
     */
    @NonNull
    @Override
    public CustomBorrowedBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.borrowed_book_item_view, parent, false);
        return new CustomBorrowedBookViewHolder(view);
    }

    /**
     * @param holder holder
     * @param position position
     */
    @Override
    public void onBindViewHolder(@NonNull CustomBorrowedBookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.description.setText(book.getDescription());

        if(!book.getCover().equals("")){
            new Picasso.Builder(context)
                    .downloader(new OkHttp3Downloader(context))
                    .build()
                    .load(book.getCover())
                    .placeholder(R.drawable.side_nav_bar)
                    .resize(130, 200)
                    .into(holder.cover);
        }else{
            holder.cover.setImageResource(R.drawable.side_nav_bar);
        }

        holder.rating.setText(String.format("%s", book.getRating()));
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
    static class CustomBorrowedBookViewHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView author;
        protected TextView description;
        protected ImageView cover;
        protected TextView rating;

        /**
         * @param itemView itemView
         */
        public CustomBorrowedBookViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.borrowedTitle);
            this.author = itemView.findViewById(R.id.borrowedAuthor);
            this.description = itemView.findViewById(R.id.borrowedDesc);
            this.cover = itemView.findViewById(R.id.borrowedCover);
            this.rating = itemView.findViewById(R.id.yourRating);
        }
    }
}
