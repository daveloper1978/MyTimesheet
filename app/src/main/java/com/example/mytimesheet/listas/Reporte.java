package com.example.mytimesheet.listas;

public class Reporte {

    private String fechaRegistro;
    private String nombreProyecto;
    private double horas;


    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public Reporte(String fechaRegistro, String nombreProyecto, double horas) {
        this.fechaRegistro = fechaRegistro;
        this.nombreProyecto = nombreProyecto;
        this.horas = horas;
    }

}
