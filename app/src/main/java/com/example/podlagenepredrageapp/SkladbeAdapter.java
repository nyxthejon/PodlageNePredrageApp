package com.example.podlagenepredrageapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.podlagenepredrageapp.Razredi.Skladba;

import java.util.ArrayList;


public class SkladbeAdapter extends RecyclerView.Adapter<SkladbeAdapter.ViewHolder> {

    private ArrayList<Skladba> data;
    private Context context;
    private ArrayList<Skladba> kupljene;
    private String backKam;
    public SkladbeAdapter(ArrayList<Skladba> data,ArrayList<Skladba> kupljene, Context context) {
        if(data == null){
            this.data = kupljene;
            this.kupljene = kupljene;
            this.backKam = "profil";
        }else{
        this.data = data;
        this.kupljene = kupljene;
        this.backKam = "domov";
        }
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skladba_view, parent, false);
        ViewHolder holders = new ViewHolder(view);
        View.OnClickListener clickListener = v -> {
            Intent intent = new Intent(parent.getContext(),PodatkiSkladbe.class);
            intent.putExtra("skladba", data.get(holders.getLayoutPosition()));
            intent.putExtra("Back",backKam);
            intent.putExtra("Kupljene",kupljene);
            parent.getContext().startActivity(intent);
        };

        view.setOnClickListener(clickListener);
        return holders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.naslov.setText(data.get(position).vrniNaslov());
        String imageName = data.get(position).vrniPotSlike();
        int resID = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.im.setImageResource(resID);
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