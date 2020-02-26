package com.cinthyasophia.autocartas;

public class ApiRest {

    //Evitamos que se puedan crear objetos ya que queremos que la clase sea estática
    private ApiRest() {
    }

    public static final String BASE_URL = "http://192.168.1.103:8080/"; //ip casa
    //public static final String BASE_URL = "http://192.168.105.14:9090/";//ip instituto
    //public static final String BASE_URL = "http://10.206.228.120:8080/";//ip instituto


    public static ApiRestService getAPIService() {
        return ApiRestCliente.getInstance(BASE_URL).getRetrofit().create(ApiRestService.class);
    }

}
