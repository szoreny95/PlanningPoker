package com.example.planningpoker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder>{
    ArrayList<String> gridDataList;

    public GridAdapter(ArrayList<String> gridDataList) {
        this.gridDataList = gridDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item_view, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        String data = gridDataList.get(i);
        viewHolder.btn_value.setText(data);
    }

    @Override
    public int getItemCount() {
        return gridDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        Button btn_value;
        public MyViewHolder(View itemView) {
            super(itemView);
            btn_value = itemView.findViewById((R.id.btn_value));
        }
    }
}
