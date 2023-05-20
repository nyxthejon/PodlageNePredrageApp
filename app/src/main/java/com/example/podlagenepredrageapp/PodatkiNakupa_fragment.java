package com.example.podlagenepredrageapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PodatkiNakupa_fragment extends Fragment {

    public PodatkiNakupa_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.placilo_podatki_fragment, container, false);
        TextView podatki = view.findViewById(R.id.PodatkiTextVIew);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String data = bundle.getString("podatki");
            podatki.setText(data);
        }
        else
            podatki.setText("Napaka pri pridobivanju podatkov");
        return view;
    }
}