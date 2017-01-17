package com.example.kamranali.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kamran ALi on 10/11/2016.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList countries ;

    public DataAdapter(ArrayList countries) {
        this.countries = countries;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(countries.get(position)));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void addItem(String country){
        countries.add(country);
        notifyItemInserted(countries.size());
    }
    public void removeItem(int position){
        countries.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position,countries.size());
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_country);
        }
    }
}
