package com.example.mytimesheet.listas;

public class Trabajadores {

    private String nombreTrabajador;
    private String apellidoTrabajador;
    private double sueldoTrabajador;


    public Trabajadores(String nombreTrabajador, String apellidoTrabajador, double sueldoTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
        this.apellidoTrabajador = apellidoTrabajador;
        this.sueldoTrabajador = sueldoTrabajador;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public String getApellidoTrabajador() {
        return apellidoTrabajador;
    }

    public void setApellidoTrabajador(String apellidoTrabajador) {
        this.apellidoTrabajador = apellidoTrabajador;
    }

    public double getSueldoTrabajador() {
        return sueldoTrabajador;
    }

    public void setSueldoTrabajador(double sueldoTrabajador) {
        this.sueldoTrabajador = sueldoTrabajador;
    }
}
