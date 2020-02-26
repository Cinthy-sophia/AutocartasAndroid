package com.cinthyasophia.autocartas;

import com.cinthyasophia.autocartas.modelos.Carta;
import com.cinthyasophia.autocartas.modelos.Jugada;
import com.cinthyasophia.autocartas.modelos.Jugador;
import com.cinthyasophia.autocartas.modelos.Partida;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRestService {
    @GET("AutocartasRest/rest/autocartas/baraja_cartas")
    Call<List<Carta>> obtenerYRepartirCartas();

    @GET("AutocartasRest/rest/jugadores")
    Call<ArrayList<Jugador>> obtenerJugadores();

    @GET("AutocartasRest/rest/jugadores")
    Call<ArrayList<Partida>> obtenerPartidas();

    @POST("AutocartasRest/rest/autocartas/sesion_nueva")
    @FormUrlEncoded
    Call<String> validarJugador(@Field("nick") String nick, @Field("password") String password);

    @POST("AutocartasRest/rest/autocartas/partidas")
    @FormUrlEncoded
    Call<Partida> nuevaPartida(@Field("idSesion") String idSesion);

    @POST("AutocartasRest/rest/jugadores")
    @FormUrlEncoded
    Call<Jugador> insertarJugador(@Field("nick") String nick, @Field("password") String password);

    @GET("AutocartasRest/rest/autorcartas/sorteo")
    Call<Integer> sorteoTurno();

    @POST("AutocartasRest/rest/autocartas/game_on")
    @FormUrlEncoded
    Call<Jugada> ready(@Field("idSesion") String idSesion);

    @POST("AutocartasRest/rest/autocartas/game_on")
    @FormUrlEncoded
    Call<Jugada> jugarCarta(@Body Jugada jugada, @Field("idSesion") String idSesion);




}


