package com.example.pocketlibrary.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pocketlibrary.API.BookSingleton;
import com.example.pocketlibrary.R;
import com.example.pocketlibrary.adapters.BookAdapter;
import com.example.pocketlibrary.pojo.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Catalog Fragment - contains a collection of books
 *
 * @author Farzana Moury
 * @since March 29 2021
 * @version 1.0
 */
public class CatalogFragment extends Fragment {
    //static properties that can be accessed throughout classes
    @SuppressLint("StaticFieldLeak")
    public static BookAdapter adapter;
    public static RecyclerView recyclerView;
    public static String url;
    public static String title;
    public static String image;
    public static String authors;
    public static String description;

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
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);

        ArrayList<Book> books = new ArrayList<>(); //the list of books

        SearchView searchView = view.findViewById(R.id.search_bar); //our search bar
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //the action performed when user makes a search

                books.clear(); //first we clear the books arrayList

                //request url using the open library search API
                url = "https://openlibrary.org/search.json?title=" + query.replace(" ", "%20");

                //Make a request
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    //retrieving the array containing book information
                                    JSONArray booksArray =  response.getJSONArray("docs");

                                    //JSON conversion
                                    for(int i=0; i<booksArray.length(); i++){

                                        //obtaining the title of the book
                                        title = booksArray.getJSONObject(i).getString("title");

                                        try {
                                            //safely obtaining the isbn array, to get the book's isbn (for the book cover)
                                            JSONArray isbnArray = booksArray.getJSONObject(i).getJSONArray("isbn");
                                            image = isbnArray.getString(1);
                                        }catch(JSONException e){
                                            image = "";
                                        }

                                        try{
                                            //safely obtaining the author array, to get the book's author
                                            JSONArray authorArray = booksArray.getJSONObject(i).getJSONArray("author_name");
                                            authors = authorArray.get(0).toString();
                                        }catch(JSONException e){
                                            authors = "No author provided";
                                        }

                                        try{
                                            //safely obtaining the first_sentence array, to provide a description
                                            JSONArray descArray = booksArray.getJSONObject(i).getJSONArray("first_sentence");
                                            description = descArray.getString(0);
                                        }catch(JSONException e){
                                            description = "No description provided";
                                        }

                                        //finally adding a new book to the books arraylist with these obtained values
                                        books.add(new Book(title, authors, description, "http://covers.openlibrary.org/b/isbn/" + image + "-M.jpg", 0.0));
                                    }

                                    //setting the adapter to the recyclerview to display the books
                                    adapter = new BookAdapter(books, getContext());
                                    recyclerView = view.findViewById(R.id.bookRecyclerView);
                                    recyclerView.setAdapter(adapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("VOLLEY_ERROR", error.getLocalizedMessage());
                            }
                        });

                BookSingleton.getInstance(getContext()).getRequestQueue().add(request);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }
}