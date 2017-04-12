package edu.uw.fragmentdemo;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private static final String TAG = "MoviesFragment";

    private static final String SEARCH_ARG_KEY = "search_key";

    private ArrayAdapter<Movie> adapter;

    // Inner interface
    interface OnMovieClickListener {
        public void onMovieClick(Movie movie);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    // Constructor with no parameter
    public MoviesFragment() {
        // Required empty public constructor
    }

    public static MoviesFragment newInstance(String searchTerm) {

        Bundle args = new Bundle();
        args.putString(SEARCH_ARG_KEY, searchTerm);

        MoviesFragment fragment = new MoviesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // rootView = the root of a fragment tree
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        // Fragment itself does not have any context because it is not an activity, it is just a part of an activity
        // getActivity() returns which activity this fragment is attached to
        adapter = new ArrayAdapter<Movie>(getActivity(),
                R.layout.list_item, R.id.txtItem, new ArrayList<Movie>());

        // Search views from rootView instead of activity
        ListView listView = (ListView)rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie)parent.getItemAtPosition(position);
                Log.v(TAG, "You clicked on: "+movie);

                // Show detail view
                ((OnMovieClickListener)getActivity()).onMovieClick(movie);
            }
        });

        String searchTerm = getArguments().getString(SEARCH_ARG_KEY);
        downloadMovieData(searchTerm);

        return rootView;
    }

    //helper method for downloading the data via the MovieDownloadTask
    public void downloadMovieData(String searchTerm){
        Log.v(TAG, "You searched for: "+searchTerm);
        MovieDownloadTask task = new MovieDownloadTask();
        task.execute(searchTerm);
    }

    //A task to download movie data from the internet on a background thread
    public class MovieDownloadTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(String... params) {
            ArrayList<Movie> data = MovieDownloader.downloadMovieData(params[0]);
            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);

            adapter.clear();
            for(Movie movie : movies){
                adapter.add(movie);
            }
        }
    }
}
