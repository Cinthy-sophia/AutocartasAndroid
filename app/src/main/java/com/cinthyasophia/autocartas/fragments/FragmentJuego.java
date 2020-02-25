package com.cinthyasophia.autocartas.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.autocartas.ApiRest;
import com.cinthyasophia.autocartas.ApiRestService;
import com.cinthyasophia.autocartas.R;
import com.cinthyasophia.autocartas.modelos.Carta;
import com.cinthyasophia.autocartas.modelos.Jugada;
import com.cinthyasophia.autocartas.modelos.Partida;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentJuego extends Fragment {
    private TextView tvTurno;
    private TextView tvMano;
    private TextView tvGanador;
    private ImageView ivCartaCPU;
    private ImageView ivCartaJugador;
    private RecyclerView rvCartas;
    private ApiRestService apiService;
    private String sesion;
    private ArrayList<Carta> cartas;
    private String nick;
    private int idPartida;
    private int turno;
    private int mano;
    private boolean ready;
    AdapterCarta adapter;
    private Jugada jugadaCPU;
    private Jugada jugadaJugador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        apiService = ApiRest.getAPIService();
        sesion = getArguments().getString("idSesion");
        nick = getArguments().getString("nick");
        cartas = new ArrayList<>();
        return inflater.inflate(R.layout.fragment_juego,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvTurno= getView().findViewById(R.id.tvTurno);
        tvMano= getView().findViewById(R.id.tvMano);
        tvGanador = getView().findViewById(R.id.tvGanador);
        ivCartaCPU= getView().findViewById(R.id.ivCartaCPU);
        ivCartaJugador =getView().findViewById(R.id.ivCartaJugador);
        rvCartas = getView().findViewById(R.id.rvCartas);

        adapter = new AdapterCarta(cartas,getContext());
        rvCartas.setAdapter(adapter);
        rvCartas.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvCartas.setEnabled(false);
        mano = 0;


        nuevaPartida(sesion);

        if(turno == 0){
            //Empieza la CPU
            ready(sesion);

            Toast.makeText(getContext(),"LE TOCA AL OTRO, TE ME ESPERAS ",Toast.LENGTH_LONG).show();

        }else if(turno == 1){
            Toast.makeText(getContext(),"ES TU TURNO MIRREY/RREINA, SELECCIONA UNA CARTA. ",Toast.LENGTH_LONG).show();
            rvCartas.setEnabled(true);
        }

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String caracteristica;
            DialogoCaracteristica dialogoCaracteristica =  new DialogoCaracteristica();
            caracteristica= dialogoCaracteristica.getCaracteristica();
            jugadaJugador = new Jugada(cartas.get(rvCartas.getChildAdapterPosition(v)),sesion,caracteristica,nick,mano);

            //jugarCarta();
            }
        });

    }

    public void nuevaPartida(String sesion){

        apiService.nuevaPartida(sesion).enqueue(new Callback<Partida>() {
            @Override
            public void onResponse(Call<Partida> call, Response<Partida> response) {

                if (response.isSuccessful()){
                    idPartida = response.body().getId();
                    obtenerCartasJugador();
                    turno();
                    Toast.makeText(getContext(),"SIMON CARTAS CARGADAS MI WERA",Toast.LENGTH_LONG).show();

                    for (Carta c:cartas) {
                        Log.e("CORRECTISIMO", c.getMarca());

                    }

                }


            }

            @Override
            public void onFailure(Call<Partida> call, Throwable t) {
                Toast.makeText(getContext(),"NEIIIIIII NO SE PUEDE ",Toast.LENGTH_LONG).show();
                Log.e("ERROR MASIVO", "Error del API." + t.getMessage());


            }

        });

    }

    public void obtenerCartasJugador(){
        apiService.obtenerCartasJugador().enqueue(new Callback<ArrayList<Carta>>() {
            @Override
            public void onResponse(Call<ArrayList<Carta>> call, Response<ArrayList<Carta>> response) {
                if (response.isSuccessful()){
                    cartas = response.body();
                    adapter.swap(cartas);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Carta>> call, Throwable t) {
                Toast.makeText(getContext(),"NEIIIIIII NO SE PUEDE ",Toast.LENGTH_LONG).show();
                Log.e("ERROR MASIVO", "Error del API." + t.getMessage());
            }
        });

    }

    public void turno(){
        // TODO: 24/02/20 turno
        apiService.sorteoTurno().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    turno = response.body();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void jugarCarta(Jugada jugada, String sesion){
        // TODO: 24/02/20 jugar carta


    }

    public void ready(String idSesion){

        // TODO: 25/02/20 ready
        apiService.ready(idSesion).enqueue(new Callback<Jugada>() {
            @Override
            public void onResponse(Call<Jugada> call, Response<Jugada> response) {
                if(response.isSuccessful()){
                    jugadaCPU = response.body();

                }
            }

            @Override
            public void onFailure(Call<Jugada> call, Throwable t) {

            }
        });
    }
}
