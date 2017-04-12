package edu.uw.fragmentdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    interface OnSearchListener {
        public void onSearchSubmitted(String searchTerm);
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        Button btnSearch = (Button) rootView.findViewById(R.id.btnSearch);
        final EditText txtSearch = (EditText) rootView.findViewById(R.id.txtSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchTerm = txtSearch.getText().toString();

                Log.v(TAG, "Search term is " + searchTerm);

                // Pass search term to main activity
                ((OnSearchListener) getActivity()).onSearchSubmitted(searchTerm);
            }
        });

        return rootView;
    }

}
