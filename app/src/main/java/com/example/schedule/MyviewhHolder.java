package com.example.schedule;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyviewhHolder extends RecyclerView.ViewHolder {

ImageView imageView ;
TextView datessview,schedview;
    public MyviewhHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        datessview = itemView.findViewById(R.id.datez);
        schedview = itemView.findViewById(R.id.sched);

    }
}
