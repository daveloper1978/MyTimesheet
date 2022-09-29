package com.example.mytimesheet.listas;

public class Actividades {
    private int actividad;
    private String nombreActividad;

    public Actividades(int actividad, String nombreActividad) {
        this.actividad = actividad;
        this.nombreActividad = nombreActividad;
    }

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
}
