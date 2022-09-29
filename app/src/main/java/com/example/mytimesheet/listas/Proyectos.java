package com.example.mytimesheet.listas;

public class Proyectos {

    private String proyecto;
    private String nombreProyecto;

    public Proyectos(String proyecto, String nombreProyecto) {
        this.proyecto = proyecto;
        this.nombreProyecto = nombreProyecto;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }
}
