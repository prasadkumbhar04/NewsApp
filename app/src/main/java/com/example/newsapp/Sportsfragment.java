package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sportsfragment extends Fragment {

    String api = "4d34176fcbbf47f1beb23350ae28b5fc";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "in";

    private String category = "sports";

    private RecyclerView recyclerViewofEntertainment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.sportsfragment,null);

        recyclerViewofEntertainment = v.findViewById(R.id.recyclerviewofsports);
        modelClassArrayList = new ArrayList<ModelClass>();

        recyclerViewofEntertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext() , modelClassArrayList);
        recyclerViewofEntertainment.setAdapter(adapter);

        findNews();
        return v;
    }

    private void findNews() {
        ApiUtils.getApiInterface().getCategoryNews(country , category,100 , api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }

}
