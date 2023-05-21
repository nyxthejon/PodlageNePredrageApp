package com.example.podlagenepredrageapp.MejniRazredi;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.podlagenepredrageapp.KontrolniRazredi.KNakupSkladbe;
import com.example.podlagenepredrageapp.Fragments.Placilo_fragment;
import com.example.podlagenepredrageapp.Fragments.PodatkiNakupa_fragment;
import com.example.podlagenepredrageapp.PodatkiSkladbe;
import com.example.podlagenepredrageapp.R;
import com.example.podlagenepredrageapp.Razredi.Kupec;
import com.example.podlagenepredrageapp.Razredi.Skladba;
import com.example.podlagenepredrageapp.Fragments.RezultatNakupaFragment;

public class ZmUporabnikKupiSkladbo_ extends AppCompatActivity implements Placilo_fragment.DataListener {
    KNakupSkladbe Control;
     ArrayList<Skladba> Kupljene;
    Button preklici;
    Button potrdi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placilo);
        preklici = findViewById(R.id.buttonpreklici);
        potrdi = findViewById(R.id.buttonpotrdi);
        pricniNakup();
    }


    public void pricniNakup() {
        Control = new KNakupSkladbe();
        Intent intent = getIntent();
        Skladba s = (Skladba) intent.getSerializableExtra("skladba_placilo");
        Kupljene = (ArrayList<Skladba>) intent.getSerializableExtra("Kupljeno");
        String tip = intent.getStringExtra("tip");
        double cena;
        if(tip.equals("Najem"))
            cena = s.VrniCeno();
        else
            cena = s.VrniCenoEksluzivno();
        Kupec k = new Kupec(313,"Jaka","Uporakovnik", "jaka@upr.com",01231313,"instagram","Jako123");
        Control.ustvariNakup(new Date(),cena,s.vrniStatus(),tip,s,k);
        preveriNaVoljo();
    }


    /** @pdOid 207f0ae1-acf4-4e42-a6e0-aa37bfa4e30b */
    public void preveriNaVoljo() {
        boolean status = Control.vrniNaVoljo();
        if(status)
            prikaziPodatkeONakupu();
        else
            vrniNapaka();
    }


    /** @pdOid b94e523a-e3bf-4a8e-99c4-04078230545c */
    public boolean potrdiNakup(String ime, String Number, String Exp, String cvv) {
    if(Control.IzvediNakup(ime,Number,Exp,cvv)){
        return true;
    }else{
        return false;
    }
    }


    /** @pdOid 933ffac4-1bab-4722-82ec-af4ece3ecdc2 */
    public void vrniNapaka() {

        preklici.setVisibility(View.GONE);
        potrdi.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Izbrana podlaga je že bila ekskluzivno kupljena")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Intent intent = new Intent(ZmUporabnikKupiSkladbo_.this, PodatkiSkladbe.class);
                        intent.putExtra("skladba", Control.VrniSkladbo());
                        intent.putExtra("Back","domov");
                        intent.putExtra("Kupljene",Kupljene);
                        startActivity(intent);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    /** @pdOid 8e35bc1b-0443-4775-9c6a-e99c202849c8 */
    public void prikaziPodatkeONakupu() {

        Bundle bundle = new Bundle();
        bundle.putString("podatki", Control.vrniPodatkeNakupa());
        bundle.putString("PotSlike",Control.VrniSkladbo().vrniPotSlike());

        PodatkiNakupa_fragment fragment = new PodatkiNakupa_fragment();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.podatkifragmentcontainer, fragment);
        transaction.commit();
    }


    public void PrekliciNakup(View view) {
        Intent intent = new Intent(ZmUporabnikKupiSkladbo_.this,PodatkiSkladbe.class);
        intent.putExtra("skladba", Control.VrniSkladbo());
        intent.putExtra("Back","domov");
        intent.putExtra("Kupljene",Kupljene);
        startActivity(intent);
    }

    public void VnesiPodatke(View view) {
        Bundle bundle = new Bundle();
        bundle.putDouble("cena",Control.VrniCeno());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment existingFragment = fragmentManager.findFragmentById(R.id.podatkifragmentcontainer);
        if (existingFragment != null) {
            transaction.remove(existingFragment);
        }
        Placilo_fragment fragment = new Placilo_fragment();
        fragment.setArguments(bundle);

        transaction.add(R.id.podatkifragmentcontainer, fragment);
        transaction.commit();

        Button preklici = findViewById(R.id.buttonpreklici);
        preklici.setVisibility(view.GONE);

        Button potrdi = findViewById(R.id.buttonpotrdi);
        potrdi.setVisibility(view.GONE);

    }


    //Zaključi nakup
    @Override
    public void onStringsReceived(String Ime, String Number, String Expire, String CVV) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("StatusNakupa",potrdiNakup(Ime,Number,Expire,CVV));
        bundle.putSerializable("Skladba",Control.VrniSkladbo());
        bundle.putSerializable("Kupljeno",Kupljene);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment existingFragment = fragmentManager.findFragmentById(R.id.podatkifragmentcontainer);
        if (existingFragment != null) {
            transaction.remove(existingFragment);
        }
        RezultatNakupaFragment fragment = new RezultatNakupaFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.podatkifragmentcontainer, fragment);
        transaction.commit();
    }
}