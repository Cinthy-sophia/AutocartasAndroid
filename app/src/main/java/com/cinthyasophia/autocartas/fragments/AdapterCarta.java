package com.cinthyasophia.autocartas.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.autocartas.R;
import com.cinthyasophia.autocartas.modelos.Carta;

import java.util.ArrayList;


public class AdapterCarta extends RecyclerView.Adapter<AdapterCarta.CartasHolder> implements View.OnClickListener {
    private ArrayList<Carta> cartas;
    private Context context;
    private View.OnClickListener listener;

    public AdapterCarta(ArrayList<Carta> cartas, Context context) {
        this.cartas = cartas;
        this.context = context;
    }
    @NonNull
    @Override
    public CartasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.carta_item,parent,false);
        item.setOnClickListener(this);
        return new CartasHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull CartasHolder holder, int position) {
        Carta carta = cartas.get(position);
        holder.bindCarta(carta);
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public void swap(ArrayList<Carta> cartas) {
        this.cartas = cartas;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }

    }

    public class CartasHolder extends RecyclerView.ViewHolder{
        private ImageView ivCarta;

        public CartasHolder(@NonNull View itemView) {
            super(itemView);
            ivCarta = itemView.findViewById(R.id.ivCarta);

        }

        public void bindCarta(Carta carta){

            int iResource= context.getResources().getIdentifier(carta.getFoto(),null, context.getPackageName());
            ivCarta.setImageResource(iResource);
        }


    }
}
