package com.example.pocketlibrary.fragments;

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
import com.example.pocketlibrary.custom_adapters.BookAdapter;
import com.example.pocketlibrary.pojo.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Catalog Fragment - contains a collection of books
 *
 * @author Farzana Moury
 * @since March 29 2021
 * @version 1.0
 */
public class CatalogFragment extends Fragment {
    //static properties that can be accessed throughout classes
    public static BookAdapter adapter;
    public static RecyclerView recyclerView;
    public static String url;

    /**
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

        //TODO: Be able to take string from the search bar and append to the url...otherwise display "no search results"

        SearchView searchView = view.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //the action performed when user makes a search

                books.clear();

                url = "https://openlibrary.org/search.json?author=" + query.replace(" ", "%20");
                Log.d("URL", url);

                //Make a request
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray booksArray =  response.getJSONArray("docs");

                                    for(int i=0; i<booksArray.length(); i++){
                                        String titleObj = booksArray.getJSONObject(i).getString("title");
                                        //TODO: Fix API Functionality - display author, description, rating and book cover
                                        try {
                                            JSONArray isbnArray = booksArray.getJSONObject(i).getJSONArray("isbn");
                                            String image = "";
                                            image = isbnArray.getString(0);

                                            books.add(new Book(titleObj, "author goes here", "description goes heredescription goes heredescription goes heredescription goes heredescription goes here", "http://covers.openlibrary.org/b/isbn/" + image + "-M.jpg", 0.0));
                                        }catch(JSONException e){
                                            books.add(new Book(titleObj, "author goes here", "description goes heredescription goes heredescription goes heredescription goes heredescription goes here", "", 0.0));
                                        }
                                    }

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