package com.example.podlagenepredrageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Skladba s = new Skladba(120,75,"Rap","pot",5.99,"Podlaga","testslika.png","Test1");
        ArrayList<Skladba> skladbe = new ArrayList<Skladba>();

        skladbe.add(s);
        s = new Skladba(120,75,"Rap","pot",5.99,"Podlaga","potslike","Test2");
        skladbe.add(s);
        s = new Skladba(120,75,"Rap","pot",5.99,"Podlaga","potslike","Test3");
        skladbe.add(s);




        RecyclerView recycler = findViewById(R.id.Skladbe_View);
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        SkladbeAdapter sk = new SkladbeAdapter(skladbe);
        recycler.setAdapter(sk);



        }
    }

