package com.example.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItineraryPaAdapter extends RecyclerView.Adapter<ItineraryPaAdapter.ActivityViewHolder> {
    private List<ItineraryPa> activities;

    public ItineraryPaAdapter(List<ItineraryPa> activities) {
        this.activities = activities;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        ItineraryPa activity = activities.get(position);
        holder.tvActivityName.setText(activity.name);
        holder.tvActivityTime.setText(activity.time);
        holder.tvActivityDescription.setText(activity.description);
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked on: " + activity.getName(), Toast.LENGTH_SHORT).show();
            // You can also launch another activity here
//            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
//            intent.putExtra("activity_name", activity.getName());
//            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    static class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView tvActivityName, tvActivityTime, tvActivityDescription;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvActivityName = itemView.findViewById(R.id.tvActivityName);
            tvActivityTime = itemView.findViewById(R.id.tvActivityTime);
            tvActivityDescription = itemView.findViewById(R.id.tvActivityDescription);
        }
    }
}
