package com.example.pc.themoviecatalog.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pc.themoviecatalog.R;
import com.example.pc.themoviecatalog.adapters.OnItemClickListener;
import com.example.pc.themoviecatalog.adapters.RVAdapter;
import com.example.pc.themoviecatalog.db.DbHelper;
import com.example.pc.themoviecatalog.models.MovieModel;

import java.util.List;

public class FragWatched extends Fragment {

    private RecyclerView recyclerView;
    private RVAdapter rvAdapter;
    private List<MovieModel> movieModels;
    private DbHelper dbHelper;

    public FragWatched() {
    }

    public static FragWatched newInstance() {
        FragWatched fragment = new FragWatched();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_frag_watched, container, false);

        recyclerView = inf.findViewById(R.id.recycler_view_watched);
        recyclerView.setHasFixedSize(true);

        dbHelper = new DbHelper(getContext());

        movieModels = dbHelper.getMovieDataToWatched();

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        rvAdapter = new RVAdapter(movieModels, getContext());

        rvAdapter.setOnRecyclerItemListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                dbHelper.deleteMovieData(movieModels.get(position).id);
                rvAdapter.deleteMovie(position);
                rvAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(rvAdapter);

        return inf;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            movieModels = dbHelper.getMovieDataToWatched();
            rvAdapter.addMovie(movieModels);
            rvAdapter.notifyDataSetChanged();
        }
    }
}
