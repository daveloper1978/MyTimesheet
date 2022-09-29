package com.example.mytimesheet.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytimesheet.R;

import java.util.List;

public class ProyectosAdapter extends
        RecyclerView.Adapter<ProyectosAdapter.MyViewHolder>{

    private List<Proyectos> ProyectosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView codProyecto, nombreProyecto;

        public MyViewHolder(View view) {
            super(view);
            codProyecto = (TextView) view.findViewById(R.id.codProyecto);
            nombreProyecto = (TextView) view.findViewById(R.id.nombreProyecto);
        }
    }

    public ProyectosAdapter(List<Proyectos> ProyectosList){

        this.ProyectosList = ProyectosList;

    }


    @Override
    public ProyectosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.proyectos_fila, parent, false);

        return new ProyectosAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProyectosAdapter.MyViewHolder holder, int position) {

        Proyectos proyectos = ProyectosList.get(position);
        holder.codProyecto.setText(proyectos.getProyecto());
        holder.nombreProyecto.setText(proyectos.getNombreProyecto());

    }

    @Override
    public int getItemCount() {
        return ProyectosList.size();
    }

}

