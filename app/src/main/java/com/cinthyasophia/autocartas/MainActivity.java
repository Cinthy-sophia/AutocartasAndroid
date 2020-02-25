package com.cinthyasophia.autocartas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cinthyasophia.autocartas.fragments.FragmentInicioSesion;
import com.cinthyasophia.autocartas.fragments.FragmentPrincipal;
import com.cinthyasophia.autocartas.modelos.Carta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fPrincipal = new FragmentPrincipal();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal,fPrincipal).commit();


    }


}
