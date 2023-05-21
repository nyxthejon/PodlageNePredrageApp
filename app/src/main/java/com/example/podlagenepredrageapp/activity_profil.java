package com.example.podlagenepredrageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.podlagenepredrageapp.Razredi.Kupec;
import com.example.podlagenepredrageapp.Razredi.Skladba;

import java.util.ArrayList;

public class activity_profil extends AppCompatActivity {

    ArrayList<Skladba> kupljene = new ArrayList<>();
    Kupec k = new Kupec(313,"Jaka","Uporakovnik", "jaka@upr.com",01231313,"instagram","Jako123");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        //id
        TextView name = findViewById(R.id.name);
        TextView surname = findViewById(R.id.surname);
        TextView phone = findViewById(R.id.phoneNumber);
        TextView email = findViewById(R.id.email);
        TextView username = findViewById(R.id.username);
        //text
        name.setText(k.vrniIme());
        surname.setText(k.vrniPriimek());
        phone.setText(k.vrniTelefon().toString());
        email.setText(k.vrniEposto());
        username.setText(k.vrniUporabniskoIme());

        //intent
        Intent intent = getIntent();
        kupljene = (ArrayList<Skladba>) intent.getSerializableExtra("kupljeno");
        TextView podatki = findViewById(R.id.KupljeneSkladbe);
        String Kup = "";
        if(kupljene.size() == 0){
            Kup = "Niste še kupili nobene skladbe";
        }
        else{
            Kup = "Kupljene skladbe: \n \n";
        for(Skladba s : kupljene){
            Kup += s.vrniNaslov() + "\n";

        }}

        //Kupljene Skladbe
        podatki.setText(Kup);
        RecyclerView recycler = findViewById(R.id.KupljeneRecycler);
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        SkladbeAdapter sk = new SkladbeAdapter(null,kupljene,this);
        recycler.setAdapter(sk);
    }

    public void Nazaj(View view) {
        Intent intent = new Intent(activity_profil.this, MainActivity.class);
        intent.putExtra("kupljeno",kupljene);
        startActivity(intent);
    }
}