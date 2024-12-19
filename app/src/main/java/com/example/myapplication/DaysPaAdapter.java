package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DaysPaAdapter extends RecyclerView.Adapter<DaysPaAdapter.DayViewHolder> {
    private List<DaysPa> days;

    public DaysPaAdapter(List<DaysPa> days) {
        this.days = days;
    }

    public void setDays(List<DaysPa> days) {
        this.days = days;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days_activity, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        DaysPa day = days.get(position);
        holder.tvDay.setText("Day " + day.dayNumber);
        holder.tvDate.setText(day.date);

        ItineraryPaAdapter activityAdapter = new ItineraryPaAdapter(day.activities);
        holder.rvActivities.setAdapter(activityAdapter);
        holder.rvActivities.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvDate;
        RecyclerView rvActivities;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvDate = itemView.findViewById(R.id.tvDate);
            rvActivities = itemView.findViewById(R.id.rvActivities);
        }
    }
}
