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
    private String title;
    private String author;
    private String description;
    private String cover;
    private double rating;

    //constructor
    public Book(String title, String author, String description, String cover, double rating) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.cover = cover;
        this.rating = rating;
    }

    //getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    //Parcelable implementation
    protected Book(Parcel in) {
        this.title = in.readString();
        this.author = in.readString();
        this.description = in.readString();
        this.cover = in.readString();
        this.rating = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
