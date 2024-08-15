package com.example.proyprotoboard;

public class Cable{

    String conexion1;
    String conexion2;
    Boolean estadoCorriente;
    Boolean polaridad;

    private void compruebaConexion (){
        if(this.conexion1.equals(conexion2)){
            System.out.println("Error, valor invalido");
        }
    }

}
