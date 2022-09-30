package com.example.mytimesheet.listas;

import java.util.Date;

public class Reporte {

    private Date fechaRegistro;
    private String nombreProyecto;
    private double horas;


    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
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

    public Reporte(Date fechaRegistro, String nombreProyecto, double horas) {
        this.fechaRegistro = fechaRegistro;
        this.nombreProyecto = nombreProyecto;
        this.horas = horas;
    }

}
