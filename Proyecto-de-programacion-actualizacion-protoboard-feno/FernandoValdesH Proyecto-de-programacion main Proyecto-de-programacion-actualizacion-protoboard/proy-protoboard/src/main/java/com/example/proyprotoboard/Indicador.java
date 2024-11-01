package com.example.proyprotoboard;
public class Indicador{
    Posicion posicion1 ;
    Posicion posicion2;
    String color ;
    Boolean conectado;
    Boolean procesado;
    public Indicador(){
        this.posicion1 = new Posicion();
        this.posicion2 = new Posicion();
        this.color = "";
        this.conectado = false;
        this.procesado = false;
    }
}