package com.example.pocketlibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import static com.example.pocketlibrary.fragments.BooksFragment.db;

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

    /**
     * constructor.
     * @param books books
     * @param context context
     */
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
        //setting the book to the current book item
        Book book = books.get(position);

        //assigning the information of the book to the view objects in the holder
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.description.setText(book.getDescription());

        //handling the book cover image
        if(!book.getCover().equals("")){ //if it is not empty
            new Picasso.Builder(context)
                    //we set it to the book's image (OKHttp3Downloader takes care of redirects)
                    .downloader(new OkHttp3Downloader(context))
                    .build()
                    .load(book.getCover()) //loading the image
                    .placeholder(R.drawable.side_nav_bar)
                    .resize(130, 200)
                    .into(holder.cover);
        }else{
            holder.cover.setImageResource(R.drawable.side_nav_bar); //otherwise we have a placeholder image
        }

        db = new BookDatabase(context); //setting up the database

        //when we decide to borrow a book...
        holder.borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //the book is added to the books database, to be shown in the My books fragment
                db.createBook(new Book(book.getTitle(), book.getAuthor(), book.getDescription(), book.getCover(), book.getRating()));
            }
        });

        db.close(); //closing the database safely
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
    static class CustomBookViewHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView author;
        protected TextView description;
        protected ImageView cover;
        protected Button borrowButton;

        /**
         * @param itemView itemView
         */
        public CustomBookViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.bookTitle);
            this.author = itemView.findViewById(R.id.bookAuthor);
            this.description = itemView.findViewById(R.id.bookDesc);
            this.cover = itemView.findViewById(R.id.bookCover);
            this.borrowButton = itemView.findViewById(R.id.borrowButton);
        }
    }
}
