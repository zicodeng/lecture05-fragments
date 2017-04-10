package edu.uw.fragmentdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass representing the details for a Movie.
 */
public class DetailFragment extends Fragment {

    private static final String TITLE_PARAM_KEY = "title";
    private static final String IMDB_PARAM_KEY = "imdb_id";

    public DetailFragment() {
        // Required empty public constructor
    }

    //factory method for creating the Fragment
    public static DetailFragment newInstance(String title, String imdbId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_PARAM_KEY, title);
        args.putString(IMDB_PARAM_KEY, imdbId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = this.getArguments();

        if(bundle != null) {
            TextView txtTitle = (TextView) rootView.findViewById(R.id.txtMovieTitle);
            txtTitle.setText(bundle.getString(TITLE_PARAM_KEY));

            TextView txtImdb = (TextView) rootView.findViewById(R.id.txtMovieIMDB);
            txtImdb.append(bundle.getString(IMDB_PARAM_KEY));
            Linkify.addLinks(txtImdb, Linkify.WEB_URLS);
        }

        return rootView;
    }
}
