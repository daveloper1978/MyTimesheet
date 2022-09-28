package com.example.mytimesheet.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytimesheet.R;

import java.util.List;

public class MenuAdapter extends
        RecyclerView.Adapter<MenuAdapter.MyViewHolder>
implements View.OnClickListener{

    private List<Menu> MenuList;
    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener){

        this.listener = listener;

    }

    @Override
    public void onClick(View view) {

        if (listener!=null){
            listener.onClick(view);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView descripcion, menuValor;

        public MyViewHolder(View view) {
            super(view);
            descripcion = (TextView) view.findViewById(R.id.descripcion);
            menuValor = (TextView) view.findViewById(R.id.menuValor);
        }
    }

    public MenuAdapter(List<Menu> MenuList){

        this.MenuList = MenuList;

    }

    @Override
    public MenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actividad_menu, parent, false);

        itemView.setOnClickListener(this);

        return new MenuAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.MyViewHolder holder, int position) {

        Menu menu = MenuList.get(position);
        holder.descripcion.setText(menu.getDescripcion());
        holder.menuValor.setText(String.valueOf(menu.getMenuValor()));

    }

    @Override
    public int getItemCount() {
        return MenuList.size();
    }

}
