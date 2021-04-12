package com.example.pocketlibrary;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;

import com.example.pocketlibrary.database.BookDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.example.pocketlibrary.fragments.BooksFragment.adapter;
import static com.example.pocketlibrary.fragments.BooksFragment.db;
import static com.example.pocketlibrary.fragments.BooksFragment.myBooksRecyclerView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_welcome, R.id.nav_catalog, R.id.nav_publishers, R.id.nav_books, R.id.nav_credits)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings: { //change username
                EditText username = new EditText(MainActivity.this);
                username.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Change your username")
                        .setIcon(R.drawable.ic_baseline_person_24)
                        .setView(username) //the edit text where user will provide their rating
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("CANCEL", null) //user cancels the rating action
                        .show();
                return true;
            }
            case R.id.action_settings2:{ //change email
                EditText email = new EditText(MainActivity.this);
                email.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Change your email")
                        .setIcon(R.drawable.ic_baseline_email_24)
                        .setView(email) //the edit text where user will provide their rating
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("CANCEL", null) //user cancels the rating action
                        .show();
                return true;
            }
            case R.id.action_settings3:{ //reset ratings
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Reset ratings")
                        .setMessage("Are you sure you want to delete your ratings? All ratings will be reset.")
                        .setIcon(R.drawable.ic_baseline_star_24)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("CANCEL", null) //user cancels the rating action
                        .show();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}