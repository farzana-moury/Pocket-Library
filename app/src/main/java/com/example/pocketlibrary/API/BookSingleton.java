package com.example.pocketlibrary.API;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Book Singleton
 *
 * @author Farzana Moury
 * @since March 30 2021
 * @version 1.0
 */
public class BookSingleton {

    //properties
    public static BookSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    /**
     * constructor.
     * @param context context
     */
    private BookSingleton(Context context){
        this.context = context;
    }

    /**
     * getInstance method
     */
    public static BookSingleton getInstance(Context context) {
        if(instance == null){
            instance = new BookSingleton(context); //unless we don't have the instance and need to create it
        }
        return instance; //we'll always use the same instance
    }

    /**
     * getRequestQueue method
     * @return requestQueue
     */
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
}
