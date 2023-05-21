package com.example.podlagenepredrageapp.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.podlagenepredrageapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class Placilo_fragment extends Fragment {
    private boolean isFormatting = false;
    private EditText editTextCardNumber;
    private DataListener dataListener;
    private TextView cena;
    private EditText ime;
    TextInputEditText editTextExpiryDate;
    EditText cvv;
    public Placilo_fragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dataListener = (DataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.placilo_fragment, container, false);
        editTextExpiryDate = view.findViewById(R.id.editTextExpiryDate);
        cena = view.findViewById(R.id.cenaPlacila);
        ime = view.findViewById(R.id.imePlacilo);
        cvv = view.findViewById(R.id.editTextCVV);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Double data = bundle.getDouble("cena");
            cena.setText("Cena: " + data);
        }

//preverjanje ce je pravilna oblika vnosa za rok poteka
        editTextExpiryDate.addTextChangedListener(new TextWatcher() {
            private final String datePattern = "\\d{2}(\\d{2})?";
            private final String separator = "/";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }
            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                input = input.replace(separator, "");
                StringBuilder formattedInput = new StringBuilder();
                int length = input.length();
                for (int i = 0; i < length; i++) {
                    formattedInput.append(input.charAt(i));
                    if ((i == 1) && (length > 1)) {
                        formattedInput.append(separator);
                    }
                }
                editTextExpiryDate.removeTextChangedListener(this);
                editTextExpiryDate.setText(formattedInput.toString());
                editTextExpiryDate.setSelection(formattedInput.length());
                editTextExpiryDate.addTextChangedListener(this);
            }
        });
        editTextCardNumber = view.findViewById(R.id.editTextCardNumber);
        editTextCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editTextCardNumber.getText().length() < 19){
                if (isFormatting) {
                    return;
                }
                isFormatting = true;
                String cardNumber = s.toString().replaceAll("-", "");
                StringBuilder formattedCardNumber = new StringBuilder();
                int len = cardNumber.length();
                for (int i = 0; i < len; i += 4) {
                    int endIndex = Math.min(i + 4, len);
                    formattedCardNumber.append(cardNumber, i, endIndex);
                    if (endIndex < len) {
                        formattedCardNumber.append("-");
                    }
                }
                editTextCardNumber.setText(formattedCardNumber.toString());
                editTextCardNumber.setSelection(formattedCardNumber.length());
                isFormatting = false;
            }}
        });




        //pregled ce je katero polje prazno
        Button plac = view.findViewById(R.id.buttonPay);
        plac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(editTextCardNumber.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Polje Številka kartice nemore biti prazno")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
                else if(ime.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Polje Ime in Priimek nemore biti prazno")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
                else if(cvv.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Polje CVV nemore biti prazno")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
                else if(editTextExpiryDate.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Polje Rok poteka nemore biti prazno")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
                else{
                    if (dataListener != null) {
                        dataListener.onStringsReceived(ime.getText().toString(), editTextCardNumber.getText().toString(), editTextExpiryDate.getText().toString(), cvv.getText().toString());
                    }
                }
            }
        });

        return view;
    }

    public interface DataListener {
        void onStringsReceived(String string1, String string2, String string3, String string4);
    }

}