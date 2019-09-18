package com.ariemay.entertainmentlistmade3.views;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.adapters.MoviesAdapter;
import com.ariemay.entertainmentlistmade3.models.Movies;
import android.arch.lifecycle.ViewModelProviders;
import com.ariemay.entertainmentlistmade3.services.viewmodels.MoviesViewModel;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private ArrayList<Movies> listMovies = new ArrayList<>();
    private MoviesAdapter adapter;
    private Context context;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.loading));

        if (listMovies.size() <= 0) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }

        MoviesViewModel moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getMovies().observe(this, getMovies);
        moviesViewModel.setMovies();

        RecyclerView rvFragmentMovies = view.findViewById(R.id.movies_list);
        rvFragmentMovies.setHasFixedSize(true);
        rvFragmentMovies.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MoviesAdapter(view.getContext());
        adapter.setMovies(listMovies);
        rvFragmentMovies.setAdapter(adapter);
    }

    private Observer<ArrayList<Movies>> getMovies = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(ArrayList<Movies> moviesItem) {
            if (moviesItem != null) {
                listMovies = moviesItem;
                adapter.setMovies(moviesItem);
            }
            progressDialog.dismiss();
        }
    };
}
