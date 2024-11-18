package com.example.proyprotoboard;
public class Posicion{
    int coordenadax ;
    int coordenaday ;
    Boolean corriente ;
    //Positivo true, Negativo false
    Boolean polaridad ;
    boolean quemado;
    double voltaje;
    public Posicion(){
        this.coordenadax = -1;
        this.coordenaday = -1;
        this.corriente = false;
        this.polaridad = false;
        this.quemado = false;
        this.voltaje = 0;
    }
}
