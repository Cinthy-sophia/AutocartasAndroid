package com.cinthyasophia.autocartas;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRestCliente {
    private static ApiRestCliente instance;
    private String baseUrl;
    private Retrofit retrofit;


    //Constructor privado para evitar que puedan construirse objetos de esta forma
    private ApiRestCliente() {
    }

    private ApiRestCliente(String baseUrl) {
        this.baseUrl = baseUrl;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiRestCliente getInstance(String baseUrl) {
        if(instance == null) {
            synchronized (ApiRestCliente.class) {
                if(instance == null) {
                    instance = new ApiRestCliente(baseUrl);
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
