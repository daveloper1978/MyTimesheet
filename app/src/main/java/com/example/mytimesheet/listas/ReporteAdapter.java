package com.example.mytimesheet.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytimesheet.R;

import java.util.List;

public class ReporteAdapter extends
            RecyclerView.Adapter<ReporteAdapter.MyViewHolder>{

        private List<Reporte> ReporteList;

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView fechaRegistro, nombreProyecto, horas;

            public MyViewHolder(View view) {
                super(view);
                fechaRegistro = (TextView) view.findViewById(R.id.fechaRegistro);
                nombreProyecto = (TextView) view.findViewById(R.id.nombreProyecto);
                horas = (TextView) view.findViewById(R.id.horas);
            }
        }

        public ReporteAdapter(List<Reporte> ReporteList){

            this.ReporteList = ReporteList;

        }

        @Override
        public ReporteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.reporte_fila, parent, false);

            return new ReporteAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ReporteAdapter.MyViewHolder holder, int position) {

            Reporte reporte = ReporteList.get(position);
            holder.fechaRegistro.setText(reporte.getFechaRegistro());
            holder.nombreProyecto.setText(reporte.getNombreProyecto());
            holder.horas.setText(String.valueOf(reporte.getHoras()));

        }

        @Override
        public int getItemCount() {
            return ReporteList.size();
        }

}
