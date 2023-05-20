package com.example.podlagenepredrageapp;
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

public class ZmUporabnikKupiSkladbo_ extends AppCompatActivity {
    KNakupSkladbe Control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placilo);

        pricniNakup();

    }

    public void pricniNakup() {

        Control = new KNakupSkladbe();

        Intent intent = getIntent();
        Skladba s = (Skladba) intent.getSerializableExtra("skladba_placilo");
        String tip = intent.getStringExtra("tip");
        double cena;
        if(tip.equals("Najem"))
            cena = s.VrniCeno();
        else
            cena = s.VrniCenoEksluzivno();
        Kupec k = new Kupec(313,"Jaka","Uporakovnik", "jaka@upr.com",01231313,"instagram");

        Control.ustvariNakup(new Date(),cena,s.vrniStatus(),tip,s,k);
        preveriNaVoljo();
        System.out.println(Control.vrniPodatkeNakupa());
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
    public int potrdiNakup() {
        // TODO: implement
        return 0;
    }

    /** @pdOid 933ffac4-1bab-4722-82ec-af4ece3ecdc2 */
    public void vrniNapaka() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Izbrana podlaga je že bila ekskluzivno kupljena")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Intent intent = new Intent(ZmUporabnikKupiSkladbo_.this,PodatkiSkladbe.class);
                        intent.putExtra("skladba", Control.VrniSkladbo());
                        startActivity(intent);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    /** @pdOid e4280927-8f5e-49ca-85fd-2bed8137d75a */
    public int vrniSporociloOUspesnemNakupu() {
        // TODO: implement
        return 0;
    }

    /** @pdOid 8e35bc1b-0443-4775-9c6a-e99c202849c8 */
    public void prikaziPodatkeONakupu() {

        Bundle bundle = new Bundle();
        bundle.putString("podatki", Control.vrniPodatkeNakupa());
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
}