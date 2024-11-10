package com.example.proyprotoboard;

public class MiniSwitch {
    public boolean encendido;
    public Posicion posicion;
    public String fuente_corriente;

    public MiniSwitch() {
        this.encendido = false;
        this.posicion = new Posicion();
        this.fuente_corriente = "";
    }

    public void setPosMiniSwitch(int x, int y){
        this.posicion.coordenadax = x;
        this.posicion.coordenaday = y;
    }
}
