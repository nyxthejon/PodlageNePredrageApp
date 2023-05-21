package com.example.podlagenepredrageapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.podlagenepredrageapp.R;

public class PodatkiNakupa_fragment extends Fragment {

    public PodatkiNakupa_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.placilo_podatki_fragment, container, false);
        TextView podatki = view.findViewById(R.id.PodatkiTextVIew);
        ImageView cover = view.findViewById(R.id.potrdiSlika);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String data = bundle.getString("podatki");
            podatki.setText(data);
            String imageName = bundle.getString("PotSlike");
            int resID = requireContext().getResources().getIdentifier(imageName, "drawable",requireContext().getPackageName());
            cover.setImageResource(resID);
        }
        else
            podatki.setText("Napaka pri pridobivanju podatkov");
        return view;
    }
}