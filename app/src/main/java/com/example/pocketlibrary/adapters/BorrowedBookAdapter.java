package com.example.pocketlibrary.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketlibrary.R;
import com.example.pocketlibrary.database.BookDatabase;
import com.example.pocketlibrary.pojo.Book;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.pocketlibrary.fragments.BooksFragment.adapter;
import static com.example.pocketlibrary.fragments.BooksFragment.db;
import static com.example.pocketlibrary.fragments.BooksFragment.myBooksRecyclerView;

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

    public static final String BOOK = "Book";

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

        db = new BookDatabase(context); //setting up the database

        //setting the functionality of our delete "button" (which is actually a textview)
        holder.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete the book from the database according to its id
                    db.deleteBook(book.getId());
                    books.remove(position); //remove the book from the arraylist
                    notifyDataSetChanged(); //update the adapter
                    myBooksRecyclerView.setAdapter(adapter);
            }
        });

        db.close(); //close the database safely

        //upon clicking the textview "your rating"
        holder.ratingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText ratingEditText = new EditText(context);
                ratingEditText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                //show an alert dialogue box allowing user to rate
                new AlertDialog.Builder(context)
                        .setTitle("Rate your book")
                        .setMessage("Provide your rating in the form x.x and between 0.0 and 5.0")
                        .setIcon(R.drawable.ic_baseline_star_24)
                        .setView(ratingEditText) //the edit text where user will provide their rating
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //the action that occurs when user clicks "ok"
                                String yourRating = ratingEditText.getText().toString(); //grab string from edit text

                                db = new BookDatabase(context); //setting up the database

                                db.updateBook(book.getId(), yourRating); //update the book

                                book.setRating(Double.parseDouble(yourRating)); //setting the rating

                                notifyDataSetChanged(); //notify that data has changed

                                myBooksRecyclerView.setAdapter(adapter); //setting the adapter

                                db.close(); //close the database safely
                            }
                        }).setNegativeButton("CANCEL", null) //user cancels the rating action
                        .show();
            }
        });

        //the same applies when you click the rating number too
        holder.rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText ratingEditText = new EditText(context);
                ratingEditText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                //show an alert dialogue box allowing user to rate
                new AlertDialog.Builder(context)
                        .setTitle("Rate your book")
                        .setMessage("Provide your rating in the form x.x and between 0.0 and 5.0")
                        .setIcon(R.drawable.ic_baseline_star_24)
                        .setView(ratingEditText) //the edit text where user will provide their rating
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //the action that occurs when user clicks "ok"
                                String yourRating = ratingEditText.getText().toString(); //grab string from edit text

                                db = new BookDatabase(context); //setting up the database

                                db.updateBook(book.getId(), yourRating); //update the book

                                book.setRating(Double.parseDouble(yourRating)); //setting the rating

                                notifyDataSetChanged(); //notify that data has changed

                                myBooksRecyclerView.setAdapter(adapter); //setting the adapter

                                db.close(); //close the database safely
                            }
                        }).setNegativeButton("CANCEL", null) //user cancels the rating action
                        .show();
            }
        });



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
     * @since April 11 2021
     * @version 1.0
     */
    static class CustomBorrowedBookViewHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView author;
        protected TextView description;
        protected ImageView cover;
        protected TextView rating;
        protected Button returnButton;
        protected TextView ratingTextView;

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
            this.returnButton = itemView.findViewById(R.id.returnButton);
            this.ratingTextView = itemView.findViewById(R.id.your_rating);
        }
    }
}
