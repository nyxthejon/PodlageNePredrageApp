package com.example.podlagenepredrageapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SkladbeAdapter extends RecyclerView.Adapter<SkladbeAdapter.ViewHolder> {

    private ArrayList<Skladba> data;
    private Context context;

    public SkladbeAdapter(ArrayList<Skladba> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skladba_view, parent, false);
        ViewHolder holders = new ViewHolder(view);
        View.OnClickListener clickListener = v -> {
            Intent intent = new Intent(parent.getContext(),PodatkiSkladbe.class);
            intent.putExtra("skladba", data.get(holders.getLayoutPosition()));
            parent.getContext().startActivity(intent);            };

        view.setOnClickListener(clickListener);
        return holders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.naslov.setText(data.get(position).vrniNaslov());
        /*
        double c = data.get(position).VrniCeno();
        String ce = Double.toString(c);
        holder.cena.setText(ce);*/
        holder.cena.setText(data.get(position).VrniAvtorja());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView naslov;
        TextView cena;
        ImageView im;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             im = itemView.findViewById(R.id.slikaSkladbe);
             cena = itemView.findViewById(R.id.Skladba_cena);
             naslov =  itemView.findViewById(R.id.imeskladbe);

        }
    }
}