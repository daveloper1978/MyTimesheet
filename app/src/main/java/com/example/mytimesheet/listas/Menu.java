package com.example.mytimesheet.listas;

public class Menu {
    private String descripcion;
    private String menuValor;

    public Menu(String descripcion, String menuValor) {
        this.descripcion = descripcion;
        this.menuValor = menuValor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMenuValor() {
        return menuValor;
    }

    public void setMenuValor(String menuValor) {
        this.menuValor = menuValor;
    }
}
