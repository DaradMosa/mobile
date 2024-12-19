package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PaAdapter extends ArrayAdapter<pa> {
    private Context mContext;
    private int mResource;
    public PaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<pa> objects) {

        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;

    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        convertView= layoutInflater.inflate(mResource,parent,false);
        ImageView imageView = convertView.findViewById(R.id.image);
        TextView txtpackage= convertView.findViewById(R.id.txtpackage);
        TextView txtbud= convertView.findViewById(R.id.txtbudget);
        imageView.setImageResource(getItem(position).getImage());
        txtpackage.setText(getItem(position).getPackageName());
        txtbud.setText(getItem(position).getBudget());
        return convertView;

    }
}
