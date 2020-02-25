package com.cinthyasophia.autocartas.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partida {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("jugadores")
    @Expose
    private Jugador jugador;
    @SerializedName("puntuacionJugador")
    @Expose
    private int puntuacionJugador;
    @SerializedName("puntuacionCpu")
    @Expose
    private int puntuacionCpu;
    @SerializedName("ganadorPartida")
    @Expose
    private String ganadorPartida;
    @SerializedName("sesion")
    @Expose
    private String sesion;
    @SerializedName("finalizada")
    @Expose
    private boolean finalizada;

    public Partida(int id, Jugador jugador, int puntuacionJugador, int puntuacionCpu, String ganadorPartida, String sesion,boolean finalizada) {
        this.id = id;
        this.jugador = jugador;
        this.puntuacionJugador = puntuacionJugador;
        this.puntuacionCpu = puntuacionCpu;
        this.ganadorPartida = ganadorPartida;
        this.sesion = sesion;
        this.finalizada = finalizada;
    }

    public int getId() {
        return id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getPuntuacionJugador() {
        return puntuacionJugador;
    }

    public int getPuntuacionCpu() {
        return puntuacionCpu;
    }

    public String getGanadorPartida() {
        return ganadorPartida;
    }

    public String getSesion() {
        return sesion;
    }

    public boolean isFinalizada() {
        return finalizada;
    }
}
