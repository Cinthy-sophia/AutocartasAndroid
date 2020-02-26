package com.cinthyasophia.autocartas.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Carta {
    @SerializedName("idCarta")
    @Expose
    private int id;
    @SerializedName("marca")
    @Expose
    private String marca;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("motor")
    @Expose
    private int motor;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("potencia")
    @Expose
    private int potencia;
    @SerializedName("velocidadMaxima")
    @Expose
    private int velocidad_maxima;
    @SerializedName("cilindrada")
    @Expose
    private int cilindrada;
    @SerializedName("revolucionesPorMinuto")
    @Expose
    private int revoluciones_minuto;
    @SerializedName("consumo")
    @Expose
    private float consumo;

    public Carta(int id, String marca, String modelo, int motor, String foto, int potencia, int velocidad_maxima, int cilindrada, int revoluciones_minuto, float consumo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.foto = foto;
        this.potencia = potencia;
        this.velocidad_maxima = velocidad_maxima;
        this.cilindrada = cilindrada;
        this.revoluciones_minuto = revoluciones_minuto;
        this.consumo = consumo;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getMotor() {
        return motor;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getVelocidad_maxima() {
        return velocidad_maxima;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public int getRevoluciones_minuto() {
        return revoluciones_minuto;
    }

    public float getConsumo() {
        return consumo;
    }

    public String getFoto() {
        return foto;
    }
}


