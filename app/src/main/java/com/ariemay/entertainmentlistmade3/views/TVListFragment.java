package com.ariemay.entertainmentlistmade3.views;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.adapters.MoviesAdapter;
import com.ariemay.entertainmentlistmade3.adapters.TVAdapter;
import com.ariemay.entertainmentlistmade3.models.Movies;
import com.ariemay.entertainmentlistmade3.models.TV;
import com.ariemay.entertainmentlistmade3.services.viewmodels.MoviesViewModel;
import com.ariemay.entertainmentlistmade3.services.viewmodels.TVViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVListFragment extends Fragment {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private ArrayList<TV> listTV = new ArrayList<>();
    private TVAdapter adapter;
    private Context context;

    public TVListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tvlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.loading));

        if (listTV.size() <= 0) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }

        TVViewModel tvViewModel = ViewModelProviders.of(this).get(TVViewModel.class);
        tvViewModel.getTVShow().observe(this, getTv);
        tvViewModel.setTV();

        RecyclerView rvFragmentTV = view.findViewById(R.id.tv_list);
        rvFragmentTV.setHasFixedSize(true);
        rvFragmentTV.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new TVAdapter(view.getContext());
        adapter.setTV(listTV);
        rvFragmentTV.setAdapter(adapter);
    }

    private Observer<ArrayList<TV>> getTv = new Observer<ArrayList<TV>>() {
        @Override
        public void onChanged(ArrayList<TV> tvItems) {
            if (tvItems != null) {
                listTV = tvItems;
                adapter.setTV(tvItems);
            }
            progressDialog.dismiss();
        }
    };
}
