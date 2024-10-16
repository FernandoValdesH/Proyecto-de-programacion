package com.example.proyprotoboard;

public class Resistencia extends Indicador{
    boolean direccion; // se considera la direccion como polaridad
    int banda_1;
    int banda_2;
    int multiplicador;
    int tolerancia;

    public Resistencia(){
        super();
        this.direccion = false;
        this.banda_1 = -1;
        this.banda_2 = -1;
        this.multiplicador = -1;
        this.tolerancia = -1;
    }
    public void pasarCorriente(protoboard _Protoboard, int pos_1_x, int pos_1_y, int pos_2_x, int pos_2_y, Resistencia _resistencia){
        System.out.println("pos_1_X = " + pos_1_x + " pos_1_Y = " + pos_1_y + " pos_2_X = " + pos_2_x + " pos_2_Y = " + pos_2_y);
        if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente){
            System.out.println("entra aca");
            if (pos_1_y < 7 && pos_1_y > 1){

                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;

                }
            } else if (pos_1_y > 7 && pos_1_y < 13){
                System.out.println("entra aca2");
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;

                }
            }_resistencia.direccion = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
        } else if (_Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.corriente){
            if (pos_1_y < 7 && pos_1_y > 1){
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.polaridad;

                }
            } else if (pos_1_y > 7 && pos_1_y < 13){
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.polaridad;

                }
            }_resistencia.direccion = _Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.polaridad;
        }

    }
    public void eliminarCorriente(protoboard _Protoboard, int pos_1_x, int pos_1_y, int pos_2_x, int pos_2_y){
        // primero buscar si hay un cable en alguna de las dos posiciones
        boolean cent_hay_cable_arriba_pos_1 = false;
        boolean cent_hay_cable_abajo_pos_1 = false;
        if (pos_1_y <7){
            for (int i = 2 ; i < 7 ; i++){
                if (_Protoboard.protoboard[pos_1_x][i]._cable!=null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax!=-1){
                    cent_hay_cable_arriba_pos_1 = true;
                }
            }
        } else if (pos_1_y > 7){
            for (int i = 8 ; i < 13 ; i++){
                if (_Protoboard.protoboard[pos_1_x][i]._cable!=null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax!=-1){
                    cent_hay_cable_abajo_pos_1 = true;
                }
            }
        }
        if (cent_hay_cable_arriba_pos_1){
            for (int i = 2 ; i < 7 ; i++){
                _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
            }
        }
        else if (cent_hay_cable_abajo_pos_1){
            for (int i = 8 ; i < 13 ; i++){
                _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
            }
        } else{
            boolean cent_hay_cable_arriba_pos_2 = false;
            boolean cent_hay_cable_abajo_pos_2 = false;
            if (pos_2_y <7){
                for (int i = 2 ; i < 7 ; i++){
                    if (_Protoboard.protoboard[pos_2_x][i]._cable!=null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax!=-1){
                        cent_hay_cable_arriba_pos_2 = true;
                    }
                }
            } else if (pos_2_y > 7){
                for (int i = 8 ; i < 13 ; i++){
                    if (_Protoboard.protoboard[pos_2_x][i]._cable!=null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax!=-1){
                        cent_hay_cable_abajo_pos_2 = true;
                    }
                }
            }
            if (cent_hay_cable_arriba_pos_2){
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                }
            }
            else if (cent_hay_cable_abajo_pos_2){
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                }
            }
        }

    }
    public void chequearDireccion(Resistencia _resistencia, protoboard _Protoboard){

    }
}
