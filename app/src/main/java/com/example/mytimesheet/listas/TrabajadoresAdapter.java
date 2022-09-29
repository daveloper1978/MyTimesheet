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
           nombreTrabajador = (TextView) view.findViewById(R.id.ruc);
           apellidoTrabajador = (TextView) view.findViewById(R.id.nombreEmpresa);
           sueldoTrabajador = (TextView) view.findViewById(R.id.nombreEmpresa);
        }
    }

    public TrabajadoresAdapter(List<Clientes> ClientesList){

        this.TrabajadoresList = ClientesList;

    }


    @Override
    public ClientesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clientes_fila, parent, false);

        return new ClientesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClientesAdapter.MyViewHolder holder, int position) {

        Clientes clientes = ClientesList.get(position);
        holder.ruc.setText(clientes.getRUC());
        holder.nombreEmpresa.setText(clientes.getNombreEmpresa());

    }

    @Override
    public int getItemCount() {
        return ClientesList.size();
    }

}


