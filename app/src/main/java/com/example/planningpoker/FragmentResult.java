package com.example.planningpoker;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentResult extends Fragment {
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private ArrayList<String> dataList = new ArrayList<>();
    private DbHelper mydb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result,container,false);

        mydb = new DbHelper(getActivity());

        //list votes
        Bundle arguments = getArguments();
        String task = arguments.getString("task");
        Cursor res = mydb.who_what_voted(task);
        if(res.getCount() == 0)
        {
            Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
            return v;
        }
        while(res.moveToNext()){
            int userId = res.getInt(0);
            Cursor resUser = mydb.getUserName(userId);
            if(resUser.getCount() == 0)
            {
                Toast.makeText(getActivity(), "Error at loading username!", Toast.LENGTH_SHORT).show();
                return v;
            }
            resUser.moveToNext();
            String user = resUser.getString(0);
            String vote = res.getString(1);
            dataList.add(user + " - " + vote);
        }

        //initialise recyclerview
        recyclerView = v.findViewById(R.id.recyclerView_list);
        listAdapter = new ListAdapter(dataList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());;
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(listAdapter);

        return v;
    }

}
