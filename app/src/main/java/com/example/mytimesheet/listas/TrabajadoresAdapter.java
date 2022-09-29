package com.example.mytimesheet.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytimesheet.R;

import java.util.List;

public class TrabajadoresAdapter extends
        RecyclerView.Adapter<TrabajadoresAdapter.MyViewHolder>{

    private List<Trabajadores>  TrabajadoresList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreTrabajador, apellidoTrabajador, sueldoTrabajador;

        public MyViewHolder(View view) {
            super(view);
            nombreTrabajador = (TextView) view.findViewById(R.id.nombreTrabajador);
            apellidoTrabajador = (TextView) view.findViewById(R.id.apellidoTrabajador);
            sueldoTrabajador = (TextView) view.findViewById(R.id.sueldoTrabajador);
        }
    }

    public TrabajadoresAdapter(List<Trabajadores> TrabajadoresList){

        this.TrabajadoresList = TrabajadoresList;

    }

    @Override
    public TrabajadoresAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trabajadores_fila, parent, false);

        return new TrabajadoresAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrabajadoresAdapter.MyViewHolder holder, int position) {

        Trabajadores trabajadores = TrabajadoresList.get(position);
        holder.nombreTrabajador.setText(trabajadores.getNombreTrabajador());
        holder.apellidoTrabajador.setText(trabajadores.getApellidoTrabajador());
        holder.sueldoTrabajador.setText(String.valueOf(trabajadores.getSueldoTrabajador()));

    }

    @Override
    public int getItemCount() {
        return TrabajadoresList.size();
    }

}


