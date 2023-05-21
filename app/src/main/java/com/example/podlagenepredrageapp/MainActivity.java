package com.example.podlagenepredrageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.podlagenepredrageapp.Razredi.Skladba;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Skladba> Kupljene = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("kupljeno")) {
            Kupljene = (ArrayList<Skladba>) intent.getSerializableExtra("kupljeno");
        }
        Skladba s = new Skladba(120,75,"Rap","guack",9.99,"Podlaga","cover","Joeyy type beat - Glitz","Joeyy", 100.00,"Ni kupljen");
        ArrayList<Skladba> skladbe = new ArrayList<Skladba>();
        skladbe.add(s);
        s = new Skladba(124,75,"Rap","meow",5.99,"Podlaga","cover2","Joeyy type beat - Wok","Joeyy",150.00,"Najet");
        skladbe.add(s);
        s = new Skladba(11520,75,"Rap","shed",5.99,"Podlaga","cover3","Joeyy type beat - Shed","Joeyy", 200.00,"Ekskluzivno kupljen");
        skladbe.add(s);
        s = new Skladba(266,75,"Rap","holis",8.99,"Podlaga","cover4","2hollis type baeat - church","2hollis", 350.00,"Ni kupljen");
        skladbe.add(s);
        s = new Skladba(26678,75,"Rap","sou",13.99,"Podlaga","cover5","Destroy type beat - souls","DestroyLone", 550.00,"Ekskluzivno kupljen");
        skladbe.add(s);
        s = new Skladba(26678,75,"Rap","sou",13.99,"Podlaga","cover5","Destroy type beat - souls","fff", 550.00,"Ekskluzivno kupljen");
        skladbe.add(s);


        RecyclerView recycler = findViewById(R.id.Skladbe_View);
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        SkladbeAdapter sk = new SkladbeAdapter(skladbe,Kupljene,this);
        recycler.setAdapter(sk);

        }

    public void DodajButton(View view) {

    }

    public void ProfilButton(View view) {

        Intent pr = new Intent(MainActivity.this,activity_profil.class);
        pr.putExtra("kupljeno", Kupljene);
        startActivity(pr);
    }
}

