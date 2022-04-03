package PaqC03;

import java.io.Serializable;

public class Cliente implements Serializable{
    private String nombre;
    private String apellidos;
    private int DNI;
    private int numTelef;
    private int numTarjet;
    private String fechaEntrada;
    private String fechaSalida;
    private String regAlimenticio;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, int DNI, int numTelef, int numTarjet, String fechaEntrada, String fechaSalida, String regAlimenticio) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
        this.numTelef = numTelef;
        this.numTarjet = numTarjet;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.regAlimenticio = regAlimenticio;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getNumTelef() {
        return numTelef;
    }

    public void setNumTelef(int numTelef) {
        this.numTelef = numTelef;
    }

    public int getNumTarjet() {
        return numTarjet;
    }

    public void setNumTarjet(int numTarjet) {
        this.numTarjet = numTarjet;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getRegAlimenticio() {
        return regAlimenticio;
    }

    public void setRegAlimenticio(String regAlimenticio) {
        this.regAlimenticio = regAlimenticio;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return this.DNI == cliente.DNI;
    }
}
