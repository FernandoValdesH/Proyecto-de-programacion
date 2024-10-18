package com.example.proyprotoboard;

public class Chip {
    Posicion pata1;
    Posicion pata2;
    Posicion pata3;
    Posicion pata4;
    Posicion pata5;
    Posicion pata6;
    Posicion pata7;
    Posicion pata8;
    Boolean quemado;

    public Chip() {
        super();
        this.pata1 = new Posicion();
        this.pata2 = new Posicion();
        this.pata3 = new Posicion();
        this.pata4 = new Posicion();
        this.pata5 = new Posicion();
        this.pata6 = new Posicion();
        this.pata7 = new Posicion();
        this.pata8 = new Posicion();
    }

}
