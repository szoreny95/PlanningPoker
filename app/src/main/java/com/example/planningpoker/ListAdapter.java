package com.example.planningpoker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{
    ArrayList<String> dataList;

    public ListAdapter(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_view, viewGroup, false);
        return new ListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder viewHolder, int i) {
        String data = dataList.get(i);
        viewHolder.tv_value.setText(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_value;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_value = itemView.findViewById((R.id.tv_value));
        }
    }
}
