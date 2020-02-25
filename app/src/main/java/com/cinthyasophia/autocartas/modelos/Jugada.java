package com.cinthyasophia.autocartas.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jugada {

    @SerializedName("idJugada")
    @Expose
    private int idJugada;
    @SerializedName("cartas")
    @Expose
    private Carta cartas;
    @SerializedName("sesion")
    @Expose
    private String sesion;
    @SerializedName("caracteristica")
    @Expose
    private String caracteristica;
    @SerializedName("jugador")
    @Expose
    private String jugador;
    @SerializedName("mano")
    @Expose
    private int mano;

    public Jugada( Carta cartas, String sesion, String caracteristica, String jugador, int mano) {
        this.cartas = cartas;
        this.sesion = sesion;
        this.caracteristica = caracteristica;
        this.jugador = jugador;
        this.mano = mano;
    }

    public void setIdJugada(int idJugada) {
        this.idJugada = idJugada;
    }

    public int getIdJugada() {
        return idJugada;
    }

    public Carta getCartas() {
        return cartas;
    }

    public String getSesion() {
        return sesion;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public String getJugador() {
        return jugador;
    }

    public int getMano() {
        return mano;
    }
}
