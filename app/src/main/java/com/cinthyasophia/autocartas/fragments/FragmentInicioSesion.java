package com.cinthyasophia.autocartas.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cinthyasophia.autocartas.ApiRest;
import com.cinthyasophia.autocartas.ApiRestService;
import com.cinthyasophia.autocartas.R;
import com.cinthyasophia.autocartas.modelos.Jugador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentInicioSesion extends Fragment {
    private final String TAG = this.getTag();
    private EditText etNick;
    private EditText etPassword;
    private Button bEntrar;
    private ApiRestService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        apiService = ApiRest.getAPIService();
        return inflater.inflate(R.layout.fragment_inicio_sesion,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        etNick = getView().findViewById(R.id.etNick);
        etPassword = getView().findViewById(R.id.etPassword);
        bEntrar = getView().findViewById(R.id.bEntrar);

        bEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validarJugador(etNick.getText().toString(),etPassword.getText().toString());

            }
        });

        super.onActivityCreated(savedInstanceState);
    }
    public void validarJugador(final String nick, String password){
        apiService.validarJugador(nick,password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()){

                    if(response.body() == null) {
                        Toast.makeText(getContext(),"Usuario incorrecto, registrate antes de continuar.",Toast.LENGTH_LONG).show();
                        //Abrir dialogo de nuevo usuario
                        DialogoNuevoJugador dialogoNuevoJugador = new DialogoNuevoJugador();
                        dialogoNuevoJugador.show(getActivity().getSupportFragmentManager(), "error_dialog_mapview");

                    }else{
                        String sesion =response.body();
                        Toast.makeText(getContext(),"Bienvenido, "+ nick + ".\nSesion: "+ sesion + " iniciada.",Toast.LENGTH_LONG).show();
                        //Abrir fragment de juego
                        Fragment fJuego = new FragmentJuego();
                        Bundle b = new Bundle();
                        b.putString("idSesion",sesion);
                        b.putString("nick",nick);
                        fJuego.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal,fJuego).commit();

                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Error del API." + t.getMessage());
                Log.e(TAG, "Error del API." + Arrays.toString(t.getStackTrace()));
                Log.e(TAG, "Error del API." + t.getCause());
            }
        });
    }
}
