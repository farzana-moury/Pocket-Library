package com.example.pocketlibrary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pocketlibrary.pojo.Book;

import java.util.ArrayList;

/**
 * The database class for the My Books fragment - contains the borrowed books from the catalog
 * SQLite is a file based database - a lite database with everything contained in a file rather than a server
 *
 * @author Farzana Moury
 * @since April 10 2021
 * @version 1.0
 **/
public class BookDatabase extends SQLiteOpenHelper {

    //database name
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "borrowed_books_db";

    //table name
    public static final String TABLE_BOOKS = "my_books";

    //column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_COVER = "book_cover";
    public static final String COLUMN_RATING = "rating";

    //create table SQL
    public static final String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_TITLE + " TEXT, " + COLUMN_AUTHOR + " TEXT, " + COLUMN_DESC + " TEXT, " + COLUMN_COVER + " TEXT, " + COLUMN_RATING + " TEXT)";

    //constructor
    public BookDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    //implemented methods
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOKS_TABLE); //executing the query to create the books table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //CRUD methods
    /**
     * create method
     * @param book this takes in a Book object
     */
    public void createBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase(); //we want to be able to write to the database

        //inserting into the database
        ContentValues values = new ContentValues();//content values (an associative array with name value pairs)

        //column names    //record values
        values.put(COLUMN_TITLE, book.getTitle()); //hashmap (name-value pairs)
        values.put(COLUMN_AUTHOR, book.getAuthor());
        values.put(COLUMN_DESC, book.getDescription());
        values.put(COLUMN_COVER, book.getCover());
        values.put(COLUMN_RATING, "" + book.getRating());

        db.insert(TABLE_BOOKS, null, values); //inserting the values into the table

        //cleaning up resource leaks (must close writable database upon opening)
        db.close();
    }

    /**
     * read method
     * @return ScheduleItem object (record from the schedule database)
     */
    public Book readBook(int id){
        SQLiteDatabase db = this.getReadableDatabase(); //reading a record out
        Book book = null;

        //a cursor object is similar to a fetchArray - gives you back a result set
        //the cursor begins before the dataset
        Cursor cursor = db.query(TABLE_BOOKS, //table name
                new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_AUTHOR, COLUMN_DESC, COLUMN_COVER, COLUMN_RATING}, //table columns
                COLUMN_ID + " = ?", //selection statement
                new String[]{String.valueOf(id)}, null, null, null); //selection arguments, groupBy, having, orderBy

        //will return true or false according to first position of the data
        if(cursor.moveToFirst()){
            book = new Book(
                    cursor.getInt(0), //id
                    cursor.getString(1), //book title
                    cursor.getString(2), //book author
                    cursor.getString(3), //book description
                    cursor.getString(4), //book cover
                    Double.parseDouble(cursor.getString(5))); //book rating
        }

        db.close();
        return book; //return the book object to be read from the table
    }

    /**
     * update method
     */
    public int updateBook(Book book){
        SQLiteDatabase db = getWritableDatabase(); //opening the database

        //content values (an associative array with name value pairs)
        ContentValues values = new ContentValues();

        //storing the values into the array
        values.put(COLUMN_TITLE, book.getTitle());
        values.put(COLUMN_AUTHOR, book.getAuthor());
        values.put(COLUMN_DESC, book.getDescription());
        values.put(COLUMN_COVER, book.getCover());
        values.put(COLUMN_RATING, "" + book.getRating());

        //using the update query to store the updated the information into the table
        return db.update(TABLE_BOOKS, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(book.getId())});
    }

    /**
     * delete method
     */
    public void deleteBook(int id){ //deletes by given id
        SQLiteDatabase db = this.getReadableDatabase(); //opening the database

        //query that will allow us to delete a record according to its id
        String deleteQuery = "DELETE FROM " + TABLE_BOOKS + " WHERE " + COLUMN_ID + " = " + id;

        //executing the query
        db.execSQL(deleteQuery);

        db.close(); //closing the database
    }

    /**
     * retrieve all records method -- this will allow us to display all of the borrowed inside the "my books" recyclerview (since it requires an arraylist)
     * @return the list of all borrowed book from the books table
     */
    public ArrayList<Book> getAllBooks(){
        SQLiteDatabase db = this.getReadableDatabase();

        //grabbing everything from the database
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);

        //storing the results in an arraylist
        ArrayList<Book> books = new ArrayList<>();
        while (cursor.moveToNext()){ //will go next as long as there is a next location within the list
            books.add(new Book(
                    //result set (iterator)
                    cursor.getInt(0), //id
                    cursor.getString(1), //book title
                    cursor.getString(2), //book author
                    cursor.getString(2), //book description
                    cursor.getString(2), //book cover image
                    Double.parseDouble(cursor.getString(2)) //book rating
            ));
        }
        db.close();
        return books;
    }

}

