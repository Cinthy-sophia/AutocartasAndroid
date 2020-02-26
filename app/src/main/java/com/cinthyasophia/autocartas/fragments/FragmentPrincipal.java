package com.cinthyasophia.autocartas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cinthyasophia.autocartas.R;

/**
 * Esta clase se encarga de mostrarle al usuario la pantalla de inicio del juego.
 * Contiene un botón que al presionarlo, lleva a la pagina de iniciar sesion.
 * @author cinthya rodriguez
 */
public class FragmentPrincipal extends Fragment {
   private Button bIniciar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_principal,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        bIniciar = getView().findViewById(R.id.bIniciar);

        bIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fIniciarSesion = new FragmentInicioSesion();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal,fIniciarSesion).commit();

            }
        });
        super.onActivityCreated(savedInstanceState);

    }
}
