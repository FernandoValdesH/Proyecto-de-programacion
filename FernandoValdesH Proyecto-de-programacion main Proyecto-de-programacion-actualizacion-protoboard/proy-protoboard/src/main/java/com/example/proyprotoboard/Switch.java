package com.example.proyprotoboard;
public class Switch extends Indicador{
    Posicion posicion3 ;
    Posicion posicion4 ;
    //Switch prendido = true = pasa corriente, Switch apagado = false = no pasa corriente
    Boolean prendido ;
    public Switch(){
        super();
        this.posicion3=new Posicion();
        this.posicion4=new Posicion();
        this.prendido=false;
    }


}