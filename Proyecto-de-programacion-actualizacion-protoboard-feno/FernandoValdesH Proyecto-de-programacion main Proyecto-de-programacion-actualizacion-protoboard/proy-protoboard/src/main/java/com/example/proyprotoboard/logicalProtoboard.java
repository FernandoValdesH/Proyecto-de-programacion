package com.example.proyprotoboard;
public class logicalProtoboard{
    Boolean conexion ;
    Posicion _posicion ;
    Led _led ;
    cable _cable ;
    Switch _switch ;
    OctoSwitch _octoSwitch ;
    Resistencia _resistencia ;
    public logicalProtoboard(){
        this.conexion = false ;
        this._posicion = new Posicion() ;
        this._led = new Led() ;
        this._cable = new cable() ;
        this._switch = new Switch() ;
        this._octoSwitch = new OctoSwitch() ;
        this._resistencia = new Resistencia() ;
    }

}
