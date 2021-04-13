package com.example.pocketlibrary.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Pretty ordinary java object - Book
 *
 * @author Farzana Moury
 * @since March 30 2021
 * @version 1.0
 */
public class Book implements Parcelable {
    //properties
    private int id;
    private String title;
    private String author;
    private String description;
    private String cover;
    private double rating;

    /**
     * constructor. This constructor does not contain book id
     * @param title book title
     * @param author book author
     * @param description book description
     * @param cover book cover
     * @param rating book rating
     */
    public Book(String title, String author, String description, String cover, double rating) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.cover = cover;
        this.rating = rating;
    }

    /**
     * constructor. This constructor contain books id
     * @param id book id
     * @param title book title
     * @param author book author
     * @param description book description
     * @param cover book cover
     * @param rating book rating
     */
    public Book(int id, String title, String author, String description, String cover, double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.cover = cover;
        this.rating = rating;
    }

    //getters and setters

    /**
     * getter for book id
     * @return book id
     */
    public int getId() {
        return id;
    }
    /**
     * setter for book id
     * @param id book id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for book title
     * @return book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for book title
     * @param title book title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter for book author
     * @return book author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * setter for book author
     * @param author book author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * getter for book description
     * @return book description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for book description
     * @param description book description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter for book cover
     * @return book cover
     */
    public String getCover() {
        return cover;
    }

    /**
     * setter for book cover
     * @param cover book cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * getter for book rating
     * @return book rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * setter for book rating
     * @param rating book rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    //Parcelable implementation

    /**
     * implemented parcelable constructor.
     * @param in parcel in
     */
    protected Book(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.author = in.readString();
        this.description = in.readString();
        this.cover = in.readString();
        this.rating = in.readDouble();
    }

    /**
     * implemented method writeToParcel
     * @param dest destination
     * @param flags flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    /**
     * implemented method describeContents
     * @return int
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * implemented static constant
     */
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        /**
         * implemented method createFromParcel
         * @param in parcel in
         * @return book
         */
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        /**
         * implemented method newArray
         * @param size array size
         * @return size of the book array
         */
        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
