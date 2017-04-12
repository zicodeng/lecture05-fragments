package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieClickListener {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //respond to search button clicking
    public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txtSearch);
        String searchTerm = text.getText().toString();

        // Create the fragment
        MoviesFragment moviesFragment = MoviesFragment.newInstance(searchTerm);

        // FragmentManager manages all your fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Construct FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // P1: the id of a layout we want our fragment live in
        // P2: which fragment should live inside of the layout
        // P2: a short description of what this transaction
        fragmentTransaction.replace(R.id.container, moviesFragment, "MoviesFragment");
        fragmentTransaction.commit();

        // Get a specific fragment we want to manage
//        MoviesFragment moviesFragment = (MoviesFragment) fragmentManager.findFragmentById(R.id.myFragment);
//
//        moviesFragment.downloadMovieData(searchTerm);
    }

    @Override
    public void onMovieClick(Movie movie) {

        DetailFragment detailFragment = DetailFragment.newInstance(movie);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment, "DetailFragment").addToBackStack(null).commit();
    }
}
