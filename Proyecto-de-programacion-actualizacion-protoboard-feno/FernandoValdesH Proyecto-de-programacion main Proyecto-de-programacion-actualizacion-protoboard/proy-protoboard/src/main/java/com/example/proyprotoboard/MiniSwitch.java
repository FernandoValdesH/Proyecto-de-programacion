package com.example.proyprotoboard;

public class MiniSwitch {
    public boolean encendido;
    public Posicion posicion;

    public MiniSwitch() {
        this.encendido = false;
        this.posicion = new Posicion();
    }

    public void setPosMiniSwitch(int x, int y){
        this.posicion.coordenadax = x;
        this.posicion.coordenaday = y;
    }
}
