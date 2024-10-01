package com.example.proyprotoboard;

public class cable extends Indicador{
    Boolean quemado;
    Boolean conexionBateria ;
    Boolean procesado;
    public cable(){
        super();
        this.quemado=false;
        this.conexionBateria=false;
        this.procesado=false;
    }

}
