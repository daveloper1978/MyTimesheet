package com.example.mytimesheet.listas;

public class Clientes {

    private String RUC;
    private String nombreEmpresa;

    public Clientes(String RUC, String nombreEmpresa) {
        this.RUC = RUC;
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}
