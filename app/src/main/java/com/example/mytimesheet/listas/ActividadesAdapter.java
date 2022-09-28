package com.example.mytimesheet.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytimesheet.R;

import java.util.List;

public class ActividadesAdapter extends
        RecyclerView.Adapter<ActividadesAdapter.MyViewHolder> {

    private List<Actividades> ActividadesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView descripcion, codigo;

        public MyViewHolder(View view) {
            super(view);
            descripcion = (TextView) view.findViewById(R.id.descripcion);
            codigo = (TextView) view.findViewById(R.id.codigo);
        }
    }

    public ActividadesAdapter(List<Actividades> ActividadesList){

        this.ActividadesList = ActividadesList;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actividades_fila, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Actividades actividades = ActividadesList.get(position);
        holder.descripcion.setText(actividades.getNombreActividad());
        holder.codigo.setText(String.valueOf(actividades.getActividad()));

    }

    @Override
    public int getItemCount() {
        return ActividadesList.size();
    }

}
