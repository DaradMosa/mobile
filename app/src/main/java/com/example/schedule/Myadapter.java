package com.example.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<MyviewhHolder> {

    Context context;
    List<item> items;

    public Myadapter(Context context ,List<item> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyviewhHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewhHolder holder, int position) {
holder.schedview.setText(items.get(position).getSched());
holder.imageView.setImageResource(items.get(position).getImage());
        holder.datessview.setText(items.get(position).getDatess());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
