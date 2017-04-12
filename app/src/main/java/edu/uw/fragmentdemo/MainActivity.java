package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity implements MoviesFragment.OnMovieClickListener, SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";

    private ViewPager mainViewPager;

    private PagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a ViewPager and a PagerAdapter
        mainViewPager = (ViewPager) findViewById(R.id.container);
        mainPagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainPagerAdapter);

        // Create the fragment
//        SearchFragment searchFragment = SearchFragment.newInstance();
//
//        // FragmentManager manages all your fragments
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        // Construct FragmentTransaction
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.replace(R.id.container, searchFragment, "SearchFragment");
//        fragmentTransaction.commit();
    }

    @Override
    public void onMovieClick(Movie movie) {

        DetailFragment detailFragment = DetailFragment.newInstance(movie);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment, "DetailFragment").addToBackStack(null).commit();
    }

    @Override
    public void onSearchSubmitted(String searchTerm) {
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

    }

    @Override
    public void onBackPressed() {
        if (mainViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mainViewPager.setCurrentItem(mainViewPager.getCurrentItem() - 1);
        }
    }

    private class MoviePagerAdapter extends FragmentStatePagerAdapter {


        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                return new SearchFragment();
            } else if(position == 1) {
                return new MoviesFragment();
            } else {
                return new DetailFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
