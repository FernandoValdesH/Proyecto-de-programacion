package com.example.proyprotoboard;

public class Chip extends Indicador{
    // inicializar las 6 posiciones restantes del chip
    String tipo_chip;
    Posicion pos_3;
    Posicion pos_4;
    Posicion pos_5;
    Posicion pos_6;
    Posicion pos_7;
    Posicion pos_8;
    Posicion pos_9;
    Posicion pos_10;
    Posicion pos_11;
    Posicion pos_12;
    Posicion pos_13;
    Posicion pos_14;
    public Chip(){
        super();
        this.pos_3 = new Posicion();
        this.pos_4 = new Posicion();
        this.pos_5 = new Posicion();
        this.pos_6 = new Posicion();
        this.pos_7 = new Posicion();
        this.pos_8 = new Posicion();
        this.pos_9 = new Posicion();
        this.pos_10 = new Posicion();
        this.pos_11 = new Posicion();
        this.pos_12 = new Posicion();
        this.pos_13 = new Posicion();
        this.pos_14 = new Posicion();
        this.tipo_chip = "";
    }
}
