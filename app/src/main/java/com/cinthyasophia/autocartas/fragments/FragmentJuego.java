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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Esta clase es la pantalla de juego principal, muestra las cartas totales del usuario,
 * y es aqui donde el usuario realiza el juego con la CPU.
 * @author cinthya rodriguez
 */
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
        obtenerCartasJugador();


        /*while(mano<6){
            //jugarCarta();
        }
*/
        //Al hacer click en una carta se abre un dialogo para que el usuario seleccione la caracteristica
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caracteristica;
                DialogoCaracteristica dialogoCaracteristica =  new DialogoCaracteristica();
                dialogoCaracteristica.show(getActivity().getSupportFragmentManager(), "error_dialog_caract");
                caracteristica= dialogoCaracteristica.getCaracteristica();
                jugadaJugador = new Jugada(cartas.get(rvCartas.getChildAdapterPosition(v)),sesion,caracteristica,nick,mano);
                int resFotoJug = getResources().getIdentifier(cartas.get(rvCartas.getChildAdapterPosition(v)).getFoto(),null,getContext().getPackageName());
                ivCartaJugador.setImageResource(resFotoJug);
                rvCartas.setEnabled(false);

            }
        });


    }

    /**
     * Recibe la sesion actual y crea una partida nueva, para que el jugador pueda jugar.
     * Carga las cartas de juego, y luego selecciona quien inicia primero.
     * @param sesion
     */

    public void nuevaPartida(String sesion){

        apiService.nuevaPartida(sesion).enqueue(new Callback<Partida>() {
            @Override
            public void onResponse(Call<Partida> call, Response<Partida> response) {

                if (response.isSuccessful()){
                    idPartida = response.body().getId();

                    for (Carta c:cartas) {
                        Log.e("CORRECTISIMO", c.getMarca());

                    }

                }


            }

            @Override
            public void onFailure(Call<Partida> call, Throwable t) {
                Toast.makeText(getContext(),"NO SE PUEDE ",Toast.LENGTH_LONG).show();
                Log.e("ERROR MASIVO", "Error del API." + t.getMessage());


            }

        });

    }

    /**
     * Se encarga de recibir las cartas del apiRest, lsa guarda y actualiza el RecyclerView
     * para que el usuario pueda acceder a ellas.
     */
    public void obtenerCartasJugador() {
        apiService.obtenerYRepartirCartas().enqueue(new Callback<List<Carta>>() {
            @Override
            public void onResponse(Call<List<Carta>> call, Response<List<Carta>> response) {
                if (response.isSuccessful()){
                    cartas = (ArrayList<Carta>) response.body();
                    adapter.swap(cartas);
                    if(cartas.size()!=0){
                        turno();

                        if(turno == 0){
                            //Empieza la CPU
                            ready(sesion);
                            //int resFotoCPU = getResources().getIdentifier(jugadaCPU.getCartas().getFoto(),null,getContext().getPackageName());
                            //ivCartaCPU.setImageResource(resFotoCPU);

                            Toast.makeText(getContext(),"LE TOCA AL OTRO.",Toast.LENGTH_LONG).show();

                        }else if(turno == 1){
                            //Empieza el jugador
                            Toast.makeText(getContext(),"ES TU TURNO, SELECCIONA UNA CARTA. ",Toast.LENGTH_LONG).show();
                            rvCartas.setEnabled(true);
                        }
                    }

                }else{
                    Log.e("ERROR MASIVO", "Error del API. NO PUEDO OBTENER LAS CARTAS. ");
                }
            }

            @Override
            public void onFailure(Call<List<Carta>> call, Throwable t) {

            }
        });
    }

    /**
     * Recibe quien inicia primero desde el apiRest y lo guarda en la variable turno.
     */
    public void turno(){

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

    /**
     * En caso de que inicie el CPU la partida, le enviamos el id de sesion y nos devuelve
     * la jugada de la CPU.
     * @param idSesion
     */
    public void ready(String idSesion){
        apiService.ready(idSesion).enqueue(new Callback<Jugada>() {
            @Override
            public void onResponse(Call<Jugada> call, Response<Jugada> response) {
                if (response.isSuccessful()){
                    jugadaCPU = response.body();
                }else{
                    try {
                        Log.e("ERROR MASIVO", "Error del API. CPU NO PUEDE ENVIAR NADA. "+ response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<Jugada> call, Throwable t) {
                Log.e("ERROR MASIVO", "Error del API. CPU NO PUEDE ENVIAR NADA. "+t.getMessage());

            }
        });

    }
}
