package com.example.planningpoker;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentGrid extends Fragment {
    private RecyclerView recyclerView;
    private GridAdapter gridAdapter;
    private ArrayList<String> gridDataList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grid,container,false);

        recyclerView = v.findViewById(R.id.recyclerView);
        gridAdapter = new GridAdapter(gridDataList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(gridAdapter);
        GridDataPrepare();

        return v;
    }

    private void GridDataPrepare() {
        gridDataList.add("\u2615");
        gridDataList.add("1");
        gridDataList.add("2");
        gridDataList.add("3");
        gridDataList.add("5");
        gridDataList.add("8");
        gridDataList.add("13");
        gridDataList.add("20");
        gridDataList.add("?");
    }

}
