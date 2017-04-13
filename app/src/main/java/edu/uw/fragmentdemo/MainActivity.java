package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieClickListener, SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";

    private ViewPager mainViewPager;
    private PagerAdapter mainPagerAdapter;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchFragment searchFragment = SearchFragment.newInstance();

        fragments = new ArrayList<Fragment>();

        fragments.add(searchFragment);

        // Instantiate a ViewPager and a PagerAdapter
        mainViewPager = (ViewPager) findViewById(R.id.container);
        mainPagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainPagerAdapter);

//        // Create the fragment
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
        if(fragments.size() == 2) {
            fragments.add(detailFragment);
        } else {
            fragments.set(2, detailFragment);
        }
        mainPagerAdapter.notifyDataSetChanged();
        mainViewPager.setCurrentItem(2);


//        getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment, "DetailFragment").addToBackStack(null).commit();
    }

    @Override
    public void onSearchSubmitted(String searchTerm) {

        Log.v(TAG, "Query is " + searchTerm);

        // Create the fragment
        MoviesFragment moviesFragment = MoviesFragment.newInstance(searchTerm);

        if(fragments.size() == 1) {
            fragments.add(moviesFragment);
        } else {
            fragments.set(1, moviesFragment);
        }

        mainPagerAdapter.notifyDataSetChanged();
        mainViewPager.setCurrentItem(1);

//        // FragmentManager manages all your fragments
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        // Construct FragmentTransaction
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        // P1: the id of a layout we want our fragment live in
//        // P2: which fragment should live inside of the layout
//        // P2: a short description of what this transaction
//        fragmentTransaction.replace(R.id.container, moviesFragment, "MoviesFragment");
//        fragmentTransaction.commit();

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
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }
}
