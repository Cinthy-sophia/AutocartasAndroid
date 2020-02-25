package com.cinthyasophia.autocartas.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cinthyasophia.autocartas.R;

import java.util.ArrayList;


public class DialogoCaracteristica extends DialogFragment implements Dialog.OnClickListener{
    public enum Caracteristicas{ MOTOR,POTENCIA,VELOCIDAD_MAXIMA,CILINDRADA,REVOLUCIONES_POR_MINUTO,CONSUMO}
    private ArrayAdapter<String> adaptador;
    private String caracteristica = null;
    private ListView listCaracteristicas;
    private ArrayList<String> items;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialogo_caracteristicas, null);
        builder.setView(layout);
        builder.setTitle("Selecciona una característica para la jugada: ");
        builder.setPositiveButton("Listo", this);

        listCaracteristicas = getView().findViewById(R.id.listCaracteristicas);

        items = new ArrayList<>();
        for (Caracteristicas c:Caracteristicas.values()) {
            items.add(c.name());
        }

        adaptador = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_single_choice, items);
        listCaracteristicas.setAdapter(adaptador);


        listCaracteristicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caracteristica= v.toString();
            }
        });

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(caracteristica==null){
            Toast.makeText(getContext(),"Debe indicar algun dato.",Toast.LENGTH_LONG).show();
        }

    }
    public String getCaracteristica(){

        return caracteristica;
    }
}
