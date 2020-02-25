package com.cinthyasophia.autocartas.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jugador {
    @SerializedName("nick")
    @Expose
    private String nick;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("partidasGanadas")
    @Expose
    private int partidas_ganadas;
    @SerializedName("partidasPerdidas")
    @Expose
    private int partidas_perdidas;
    @SerializedName("partidasEmpatadas")
    @Expose
    private int partidas_empatadas;
    @SerializedName("sesionActual")
    @Expose
    private String sesionActual;


    public Jugador(String nick, String password, int partidas_ganadas, int partidas_perdidas, int partidas_empatadas, String sesionActual) {
        this.nick = nick;
        this.password = password;
        this.partidas_ganadas = partidas_ganadas;
        this.partidas_perdidas = partidas_perdidas;
        this.partidas_empatadas = partidas_empatadas;
        this.sesionActual = sesionActual;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public int getPartidas_ganadas() {
        return partidas_ganadas;
    }

    public int getPartidas_perdidas() {
        return partidas_perdidas;
    }

    public int getPartidas_empatadas() {
        return partidas_empatadas;
    }

    public String getSesionActual() {
        return sesionActual;
    }

    public void setSesionActual(String sesionActual) {
        this.sesionActual = sesionActual;
    }
}
