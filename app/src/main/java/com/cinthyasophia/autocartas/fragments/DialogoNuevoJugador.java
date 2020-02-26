package com.cinthyasophia.autocartas.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cinthyasophia.autocartas.ApiRest;
import com.cinthyasophia.autocartas.ApiRestService;
import com.cinthyasophia.autocartas.R;
import com.cinthyasophia.autocartas.modelos.Jugador;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogoNuevoJugador extends DialogFragment implements DialogInterface.OnClickListener {
    private final String TAG = this.getTag();
    private final String TITULO= "NUEVO JUGADOR";
    private EditText etNuevoNick;
    private EditText etNuevoPassword;
    private ApiRestService apiService;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        apiService = ApiRest.getAPIService();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialogo_nuevo_jugador, null);
        builder.setView(layout);
        builder.setTitle(TITULO);
        builder.setPositiveButton("Guardar", this);


        etNuevoNick = layout.findViewById(R.id.etNuevoNick);
        etNuevoPassword = layout.findViewById(R.id.etNuevoPassword);



        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(!etNuevoNick.getText().toString().isEmpty() || !etNuevoPassword.getText().toString().isEmpty()){
            insertarJugador(etNuevoNick.getText().toString(),etNuevoPassword.getText().toString());

        }else{
            Toast.makeText(getContext(),"Debe indicar algun dato.",Toast.LENGTH_LONG).show();
        }
    }

    public void insertarJugador(String nick, String password){
        apiService.insertarJugador(nick,password).enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                Gson g = new Gson();
                if(response.isSuccessful()){
                    //Toast.makeText(requi,"Nuevo jugador insertado, ahora inicie sesion.",Toast.LENGTH_LONG).show();
                    Jugador j = response.body();
                    Log.i("NUEVO JUGADOR AJA",j.getNick());
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Log.e(TAG, "Error del API." + t.getMessage());
            }
        });
    }
}
