package com.example.podlagenepredrageapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.podlagenepredrageapp.MainActivity;
import com.example.podlagenepredrageapp.PodatkiSkladbe;
import com.example.podlagenepredrageapp.R;
import com.example.podlagenepredrageapp.Razredi.Skladba;

import java.util.ArrayList;

public class RezultatNakupaFragment extends Fragment {

    public RezultatNakupaFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rezultanakupa_fragment, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            boolean rez = bundle.getBoolean("StatusNakupa");
            Skladba s = (Skladba) bundle.getSerializable("Skladba");
            ArrayList<Skladba> k = (ArrayList<Skladba>) bundle.getSerializable("Kupljeno");
            TextView izpis = view.findViewById(R.id.StatusText);
            ImageView cover = view.findViewById(R.id.RezultatSlika);
            Button b = view.findViewById(R.id.StatusButton);
            Button naz = view.findViewById(R.id.buttonDomovStatus);
            if(rez) {
                izpis.setText("Z veseljem vam sporočamo, da je vaš nakup uspešno opravljen! \n" +
                        "Zahvaljujemo se vam za vaše zaupanje in izbiro naših izdelkov.\n" +
                        " Podlaga, ki ste jo naročili, se bo med vašimi kupljenimi podlagami.");
                b.setText("Vrni domov");
                String imageName = s.vrniPotSlike();
                int resID = requireContext().getResources().getIdentifier(imageName, "drawable",requireContext().getPackageName());
                cover.setImageResource(resID);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        k.add(s);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("kupljeno", k);
                        startActivity(intent);
                    }
                });
            }
            else {
                izpis.setText("Obveščamo vas, da je prišlo do težav pri obdelavi vašega nakupa. \n" +
                        "Žal vam sporočamo, da nakup ni bil uspešno opravljen. \n" +
                        " Razumemo, da ste se veselili izdelka, vendar se je pojavila nepredvidena ovira.");
                b.setText("Vrni nazaj na plačilo");
                naz.setVisibility(View.VISIBLE);
                naz.setText("Vrni domov");
                naz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        intent.putExtra("kupljeno", k);
                        startActivity(intent);
                    }
                });
                b.setText("Ponovno poskusi Nakup");
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), PodatkiSkladbe.class);
                        intent.putExtra("Back","domov");
                        intent.putExtra("skladba",s);
                        intent.putExtra("Kupljene", s);
                        startActivity(intent);
                    }
                });
            }
        }
        return view;
    }
}