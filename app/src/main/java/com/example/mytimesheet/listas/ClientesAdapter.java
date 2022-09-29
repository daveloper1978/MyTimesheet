package com.example.mytimesheet.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytimesheet.R;

import java.util.List;

public class ClientesAdapter extends
        RecyclerView.Adapter<ClientesAdapter.MyViewHolder>{

    private List<Clientes> ClientesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView ruc, nombreEmpresa;

        public MyViewHolder(View view) {
            super(view);
            ruc = (TextView) view.findViewById(R.id.ruc);
            nombreEmpresa = (TextView) view.findViewById(R.id.nombreEmpresa);
        }
    }

    public ClientesAdapter(List<Clientes> ClientesList){

        this.ClientesList = ClientesList;

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
