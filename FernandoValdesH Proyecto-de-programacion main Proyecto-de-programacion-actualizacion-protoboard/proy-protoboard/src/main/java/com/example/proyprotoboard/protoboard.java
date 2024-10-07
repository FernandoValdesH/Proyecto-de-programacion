package com.example.proyprotoboard;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class protoboard {

    private static protoboard instance;

    public logicalProtoboard[][] protoboard;


    private protoboard(logicalProtoboard[][] protoboard) {
        this.protoboard = protoboard;
        // rellenar matriz

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 15; j++) {
                this.protoboard[i][j] = new logicalProtoboard();
                protoboard[i][j]._posicion.coordenadax = i;
                protoboard[i][j]._posicion.coordenaday = j;
            }
        }
    }

    public static protoboard getInstance(logicalProtoboard[][] protoboard) {
        if (instance == null) {
            instance = new protoboard(protoboard);
        }


        return instance;
    }


    public boolean cambiarEstadoLed(protoboard _Protoboard, Led _led){
        if (_Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion1.corriente && _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion2.corriente && _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion1.polaridad != _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion2.polaridad) {
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led.encendido = true;
            System.out.println("prendio");
            return true;
        } else{
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led.encendido = false;
            System.out.println("apago");
            return false;
        }


    }

    public Led ledInitiatorStart(protoboard _Protoboard, int posx_1, int posy_1,int auxx,int auxy, int cantidad_patas) {
        Led _led = new Led();
        if (cantidad_patas == 1) {

            _led.posicion1.coordenadax = posx_1;
            _led.posicion1.coordenaday = posy_1;
            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = _led;
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday].conexion=true;
        }
        if (cantidad_patas == 2) {
            _led.posicion2.coordenadax = posx_1;
            _led.posicion2.coordenaday = posy_1;
            _led.posicion1.coordenadax = auxx;
            _led.posicion1.coordenaday = auxy;
            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
            _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led = _led;
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = _led;
            _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday].conexion=true;

            cambiarEstadoLed(_Protoboard, _led);
        }

        return _led;

    }

    public void eliminarElemento(protoboard _Protoboard, int pos_x, int pos_y){
        // inicializar variables de posicion 2 en caso de que la posicion 1 este conectada a la bateria
        int pos_x_2 = pos_x;
        int pos_y_2 = pos_y;
        if (pos_x == 33){
            pos_x=-2;
            // buscar el cable
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 15; j++) {
                    if (_Protoboard.protoboard[i][j]._cable!=null && _Protoboard.protoboard[i][j]._cable.posicion1.coordenadax!=-1){
                        if (_Protoboard.protoboard[i][j]._cable.posicion1.coordenadax == -2 && _Protoboard.protoboard[i][j]._cable.posicion1.coordenaday == -2){
                            pos_x_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                            pos_y_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                        }
                    }

                }
            }
        } if (pos_x == 34){
            pos_x=-3;
            // buscar el cable
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 15; j++) {
                    if (_Protoboard.protoboard[i][j]._cable.posicion1.coordenadax!=-1){
                    if (_Protoboard.protoboard[i][j]._cable.posicion1.coordenadax == -3 && _Protoboard.protoboard[i][j]._cable.posicion1.coordenaday == -3){
                        pos_x_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                        pos_y_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                    }}
                }
            }
        }

        if ( _Protoboard.protoboard[pos_x_2][pos_y_2]._cable.posicion1.coordenadax!=-1 ) {
            cable _cable = _Protoboard.protoboard[pos_x_2][pos_y_2]._cable;
            // primero un if si la posicion 1 o 2 estan conectados a la bateria, es decir, coord -2 y -3
            if (_cable.posicion1.coordenadax == -2 || _cable.posicion1.coordenadax == -3) {
                // si la posicion 1 esta conectada a la bateria, eliminamos el cable de la posicion 2 (final)
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday].conexion = false;

                eliminarCorriente(_Protoboard, pos_x_2, pos_y_2);

            } else if (_cable.posicion2.coordenadax == -2 || _cable.posicion2.coordenadax == -3) {
                // si la posicion 2 esta conectada a la bateria, eliminamos el cable de la posicion 1 (inicio)
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday].conexion = false;

                eliminarCorriente(_Protoboard, pos_x_2, pos_y_2);
            }
            // si no esta conectado a la bateria, al eliminar una posicion tambien se elimina la otra
            else if (_cable.posicion1.coordenadax == pos_x_2 && _cable.posicion1.coordenaday == pos_y_2){
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday].conexion = false;
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday].conexion = false;

                eliminarCorriente(_Protoboard, pos_x_2, pos_y_2);
            } else if (_cable.posicion2.coordenadax == pos_x_2 && _cable.posicion2.coordenaday == pos_y_2){
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday].conexion = false;
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday].conexion = false;

                eliminarCorriente(_Protoboard, pos_x_2, pos_y_2);
            }

        } else if (_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._led.posicion1.coordenadax!=-1){
            Led _led = _Protoboard.protoboard[pos_x][pos_y]._led;
            if (_led.posicion1.coordenadax == pos_x && _led.posicion1.coordenaday == pos_y){
                _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led = null;
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = null;
            } else {
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = null;
                _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led = null;
            }
        } else if (_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._switch.posicion1.coordenadax!=-1) {
            Switch _switch = _Protoboard.protoboard[pos_x][pos_y]._switch;

                // si el switch esta prendido, se debe eliminar la corriente de la columna contraria

            int guarda_x_cable = 0;
            boolean encuentra_un_cable = false;
            System.out.println("pos_y: "+pos_y);
            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday]._posicion.corriente || _Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion2.coordenaday]._posicion.corriente || _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion4.coordenaday]._posicion.corriente || _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion3.coordenaday]._posicion.corriente){
                _switch.eliminarCorriente_AlEliminarSwitch(_Protoboard, _switch, guarda_x_cable, encuentra_un_cable, pos_y);
            }

            _Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion2.coordenadax][_switch.posicion2.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion3.coordenadax][_switch.posicion3.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion4.coordenaday]._switch = null;

        }

        else if (_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._octoSwitch.posicion1.coordenadax!=-1){
            // si encuentra un octoswitch
            System.out.println("borra un octoswitch");
            OctoSwitch _octoSwitch = _Protoboard.protoboard[pos_x][pos_y]._octoSwitch;
            int pos_1_x = _octoSwitch.posicion1.coordenadax;
            int pos_1_y = _octoSwitch.posicion1.coordenaday;
            int pos_2_x = _octoSwitch.posicion2.coordenadax;
            int pos_y_switch = pos_1_y+1;
            for (int k = pos_1_x; k < pos_2_x+1; k++){
                if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_1.encendido){
                    System.out.println("algo encontro");
                    // borrar la corriente abajo del miniswitch (si hay)
                    if (_Protoboard.protoboard[k][pos_y_switch-1]._posicion.corriente){
                        for (int l = 2; l < 7; l++){
                            _Protoboard.protoboard[k][l]._posicion.corriente = false;
                        }

                    } else if (_Protoboard.protoboard[k][pos_y_switch+1]._posicion.corriente){
                        for (int l = 8; l < 13; l++){
                            _Protoboard.protoboard[k][l]._posicion.corriente = false;
                        }
                    }

                }
            }
            for ( int i = pos_1_x; i < pos_2_x+1; i++){
                for (int j = pos_1_y; j < 3; j++){
                    _Protoboard.protoboard[i][j]._octoSwitch = null;
                }
            }

        }
    }

    public void eliminarCorriente(protoboard _Protoboard, int pos_x, int pos_y){

        // este metodo recibe la posicion donde se elimino el elemento

        // al eliminar un elemento, se debe eliminar un cable, led, switch, del protoboard y eliminar la corriente de esa fila/columna

            if (pos_y <=1 || pos_y > 12){
                int j = pos_y;
                int i = 0;
                while (i < 30){
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    // si hay un led en la columna que se esta eliminando corriente, apagarlo
                    if (_Protoboard.protoboard[i][j]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][j]._posicion.corriente = false;
                        _Protoboard.protoboard[i][j]._led.encendido=false;

                    }

                    // si se encuentra un cable en la fila/columna que se esta eliminando, hay que borrar la corriente de este cable tambien, es decir, llamar a eliminar corriente nuevamente
                    if (_Protoboard.protoboard[i][j]._cable!=null && !_Protoboard.protoboard[i][j]._cable.procesado && _Protoboard.protoboard[i][j]._cable.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][j]._cable.procesado=true;
                        int pos_final_x = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                        int pos_final_y = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                        eliminarCorriente(_Protoboard, pos_final_x, pos_final_y);
                    }

                    i++;
                }
            } else if (pos_y > 1 && pos_y < 7){
                int j = 2;
                int i = pos_x;
                while (j < 7){
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;

                    if (_Protoboard.protoboard[i][j]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][j]._posicion.corriente = false;
                        _Protoboard.protoboard[i][j]._led.encendido=false;

                    }
                    if (_Protoboard.protoboard[i][j]._cable!=null && !_Protoboard.protoboard[i][j]._cable.procesado && _Protoboard.protoboard[i][j]._cable.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][j]._cable.procesado=true;
                        int pos_final_x = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                        int pos_final_y = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                        eliminarCorriente(_Protoboard, pos_final_x, pos_final_y);
                    }
                    // si encuentra un switch y este esta prendido debe eliminar la corriente que esta en la esquina contraria, si esta apagado elimina la corriente de forma normal
                    if (_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax!=-1){
                            // trabajamos con la esquina izquierda, osea la posicion 1
                            // si la posicion 1 tiene corriente entonces borramos la corriente de la columna entera en esa posicion
                            // ciclo
                            Switch _switch = _Protoboard.protoboard[i][j]._switch;
                            if (_Protoboard.protoboard[i][j]._switch.posicion1.corriente){

                                _switch.eliminarCorriente(_Protoboard, _switch.posicion1.coordenadax, _switch.posicion1.coordenaday);
                                for (int k = 2 ; k < 7 ; k++){
                                    if (_Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax][k]._led!=null && _Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax][k]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax][k]._led;
                                        if (_Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax][k]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else {
                                _switch.eliminarCorriente(_Protoboard, _switch.posicion1.coordenadax, _switch.posicion1.coordenaday);
                                for (int k = 2 ; k < 7 ; k++){
                                    if (_Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion4.coordenadax][k]._led!=null && _Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion4.coordenadax][k]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion4.coordenadax][k]._led;
                                        if (_Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion4.coordenadax][k]._switch.posicion4.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }

                    }
                    // si encuentra un octoswitch
                    if (_Protoboard.protoboard[i][j]._octoSwitch.posicion1.coordenadax!=-1){
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[i][j]._octoSwitch;
                        int pos_1_x = _octoSwitch.posicion1.coordenadax;
                        int pos_2_x = _octoSwitch.posicion2.coordenadax;
                        int pos_y_switch = _octoSwitch.posicion1.coordenaday+1;
                        for (int k = pos_1_x; k < pos_2_x+1; k++){
                            if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_1.encendido){
                                // borrar la corriente arriba del miniswitch
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[k][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[k][l]._led!=null && _Protoboard.protoboard[k][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[k][l]._led;
                                        if (_Protoboard.protoboard[k][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_2.encendido){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[k][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[k][l]._led!=null && _Protoboard.protoboard[k][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[k][l]._led;
                                        if (_Protoboard.protoboard[k][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_3.encendido){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[k][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[k][l]._led!=null && _Protoboard.protoboard[k][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[k][l]._led;
                                        if (_Protoboard.protoboard[k][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_4.encendido){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[k][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[k][l]._led!=null && _Protoboard.protoboard[k][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[k][l]._led;
                                        if (_Protoboard.protoboard[k][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_5.encendido){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[k][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[k][l]._led!=null && _Protoboard.protoboard[k][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[k][l]._led;
                                        if (_Protoboard.protoboard[k][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_6.encendido){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[k][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[k][l]._led!=null && _Protoboard.protoboard[k][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[k][l]._led;
                                        if (_Protoboard.protoboard[k][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_7.encendido){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[k][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[k][l]._led!=null && _Protoboard.protoboard[k][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[k][l]._led;
                                        if (_Protoboard.protoboard[k][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_8.encendido){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[k][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[k][l]._led!=null && _Protoboard.protoboard[k][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[k][l]._led;
                                        if (_Protoboard.protoboard[k][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        }
                    }

                    j++;
                }
            } else if (pos_y > 7){
                int j = 8;
                int i = pos_x;
                while (j < 13){
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;

                    if (_Protoboard.protoboard[i][j]._led!=null && _Protoboard.protoboard[i][j]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][j]._posicion.corriente = false;
                        _Protoboard.protoboard[i][j]._led.encendido=false;

                    }
                    if (_Protoboard.protoboard[i][j]._cable!=null && !_Protoboard.protoboard[i][j]._cable.procesado && _Protoboard.protoboard[i][j]._cable.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][j]._cable.procesado=true;
                        int pos_final_x = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                        int pos_final_y = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                        eliminarCorriente(_Protoboard, pos_final_x, pos_final_y);
                    }
                    // si encuentra un switch y este esta prendido debe eliminar la corriente que esta en la esquina contraria, si esta apagado elimina la corriente de forma normal

                        if (_Protoboard.protoboard[i][j]._switch!=null && _Protoboard.protoboard[i][j]._switch.posicion1.coordenadax!=-1){
                            // trabajamos con la esquina izquierda, osea la posicion 1
                            // si la posicion 1 tiene corriente entonces borramos la corriente de la columna entera en esa posicion
                            // ciclo
                            Switch _switch = _Protoboard.protoboard[i][j]._switch;
                            if (_Protoboard.protoboard[i][j]._switch.posicion1.corriente){
                                _switch.eliminarCorriente(_Protoboard, _switch.posicion1.coordenadax, _switch.posicion1.coordenaday);
                                for (int k = 8 ; k < 13 ; k++){

                                    // si encuentra un led lo apaga
                                    if (_Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax][k]._led!=null && _Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax][k]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax][k]._led;
                                        if (_Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax][k]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else {
                                _switch.eliminarCorriente(_Protoboard, _switch.posicion1.coordenadax, _switch.posicion1.coordenaday);
                                for (int k = 8 ; k < 13 ; k++){

                                    if (_Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion4.coordenadax][k]._led!=null && _Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion4.coordenadax][k]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion4.coordenadax][k]._led;
                                        if (_Protoboard.protoboard[_Protoboard.protoboard[i][j]._switch.posicion4.coordenadax][k]._switch.posicion4.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }


                        }

                    if (_Protoboard.protoboard[i][j]._octoSwitch!=null && _Protoboard.protoboard[i][j]._octoSwitch.posicion1.coordenadax!=-1){
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[i][j]._octoSwitch;
                        int pos_y_switch = _octoSwitch.posicion1.coordenaday+1;


                        if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                            if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch-1]._posicion.corriente){
                                for (int l = 2; l < 7; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch+1]._posicion.corriente){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                            if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch - 1]._posicion.corriente) {
                                for (int l = 2; l < 7; l++) {
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led != null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax != -1) {
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                            _led.posicion1.corriente = false;
                                        } else {
                                            _led.posicion2.corriente = false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch+1]._posicion.corriente){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                            if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch - 1]._posicion.corriente) {
                                for (int l = 2; l < 7; l++) {
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led != null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax != -1) {
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                            _led.posicion1.corriente = false;
                                        } else {
                                            _led.posicion2.corriente = false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch+1]._posicion.corriente){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                            if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch - 1]._posicion.corriente) {
                                for (int l = 2; l < 7; l++) {
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led != null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax != -1) {
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                            _led.posicion1.corriente = false;
                                        } else {
                                            _led.posicion2.corriente = false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch+1]._posicion.corriente){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                            if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch-1]._posicion.corriente){
                                for (int l = 2; l < 7; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch+1]._posicion.corriente){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                            if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch-1]._posicion.corriente){
                                for (int l = 2; l < 7; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch+1]._posicion.corriente){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                            if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch-1]._posicion.corriente){
                                for (int l = 2; l < 7; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch+1]._posicion.corriente){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                            if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch-1]._posicion.corriente){
                                for (int l = 2; l < 7; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            } else if (_Protoboard.protoboard[coord_x_mini_switch][pos_y_switch+1]._posicion.corriente){
                                for (int l = 8; l < 13; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = false;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=false;
                                        } else{
                                            _led.posicion2.corriente=false;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        }

                    }


                    j++;
                }
            }


    }
    int cuenta_leds = 0;
    public void pasarCorriente(protoboard _Protoboard, cable _cable){

        if ((_cable.posicion1.coordenadax == -2 && _cable.posicion1.coordenaday == -2) || (_cable.posicion1.coordenadax == -3 && _cable.posicion1.coordenaday == -3)) {

            int posicion_final_x = _cable.posicion2.coordenadax;
            int posicion_final_y = _cable.posicion2.coordenaday;



            _Protoboard.protoboard[posicion_final_x][posicion_final_y]._cable = _cable;
            _Protoboard.protoboard[posicion_final_x][posicion_final_y].conexion = true;


            if ( posicion_final_y > 1 && posicion_final_y < 7) {

                int j = 2;
                while (j < 7) {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        
                    if (_cable.posicion1.coordenadax == -2){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    // si encuentra otro cable habria que llamar denuevo a la funcion de pasar corriente
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable!=null  && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!=-1){
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (_Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!= _cable.posicion1.coordenadax || _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax!= _cable.posicion2.coordenadax ) {
                            System.out.println("encontro un cable mientras daba corriente");
                            int pos_final_x = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[posicion_final_x][j]._led!=null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1){
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led)){
                                _Protoboard.protoboard[posicion_final_x][j]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    }
                    // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch!=null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            toggleSwitch(_Protoboard, posicion_final_x, j, _Protoboard.protoboard[posicion_final_x][j]._switch.prendido);
                        } else{
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax+1, _switch.posicion1.coordenaday+1);
                        }

                    }
                    j++;
                }


            } else if ( posicion_final_y<=1  ) {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;


                    if (_cable.posicion1.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    // si encuentra otro cable habria que llamar denuevo a la funcion de pasar corriente
                    if (_Protoboard.protoboard[i][posicion_final_y]._cable!=null  && _Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax!=-1){
                        // si el cable es distinto al que empezo a recorrer

                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if ((_Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax!= _cable.posicion1.coordenadax || _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax!= _cable.posicion2.coordenadax) ) {
                            int pos_final_x = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }


                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led!=null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1){

                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][posicion_final_y]._led)){
                                _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    }
                    i++;
                }
            }else if (posicion_final_y > 7 && posicion_final_y<13){
                int j = 8;

                while (j < 13) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                    if (_cable.posicion1.coordenadax == -2){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable!=null && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!=-1){
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (_Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!= _cable.posicion1.coordenadax || _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax!= _cable.posicion2.coordenadax ) {
                            int pos_final_x = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[posicion_final_x][j]._led!=null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1){

                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led)){
                                _Protoboard.protoboard[posicion_final_x][j]._led.encendido=true;
                            
                            }
                            cuenta_leds=0;
                        }

                    }
                    // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch!=null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            toggleSwitch(_Protoboard, posicion_final_x, j, _Protoboard.protoboard[posicion_final_x][j]._switch.prendido);
                        } else{
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax+1, _switch.posicion1.coordenaday+1);
                        }

                    }

                    // si encuentra un octoswitch
                    if (_Protoboard.protoboard[posicion_final_x][j]._octoSwitch!=null && _Protoboard.protoboard[posicion_final_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[posicion_final_x][j]._octoSwitch;
                        int pos_y_switch = _octoSwitch.posicion1.coordenaday+1;
                        int pos_x = _cable.posicion2.coordenadax;
                        System.out.println("entra");

                        if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_1.encendido){
                                for (int l = 2; l < 7; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = true;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=true;
                                        } else{
                                            _led.posicion2.corriente=true;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        }
                    }


                    j++;
                }

            }  else {

                int i = 0;
                while (i < 30) {

                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;

                    // si encuentra otro cable habria que llamar denuevo a la funcion de pasar corriente
                    // si encuentra un cable
                    if (_Protoboard.protoboard[i][posicion_final_y]._cable!=null && _Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax!=-1){
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (_Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax!= _cable.posicion1.coordenadax || _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax!= _cable.posicion2.coordenadax ) {
                            int pos_final_x = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }

                    if (_cable.posicion1.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    if (_Protoboard.protoboard[i][posicion_final_y]._led!=null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1){

                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][posicion_final_y]._led)){
                                _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    }
                    i++;
                }

            }

       } else if ((_cable.posicion2.coordenadax == -2 && _cable.posicion2.coordenaday == -2) || (_cable.posicion2.coordenadax == -3 && _cable.posicion2.coordenaday == -3)) {
            int posicion_final_x = _cable.posicion1.coordenadax;
            int posicion_final_y = _cable.posicion1.coordenaday;



            _Protoboard.protoboard[posicion_final_x][posicion_final_y]._cable = _cable;
            _Protoboard.protoboard[posicion_final_x][posicion_final_y].conexion = true;


            if ( posicion_final_y > 1 && posicion_final_y < 7) {

                int j = 2;
                while (j < 7) {

                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                    if (_cable.posicion2.coordenadax == -2){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable!=null  && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!=-1){
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (_Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!= _cable.posicion1.coordenadax || _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax!= _cable.posicion2.coordenadax ) {
                            int pos_final_x = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }

                    // si encuentra un led
                    if (_Protoboard.protoboard[posicion_final_x][j]._led!=null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1){

                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led)){
                                _Protoboard.protoboard[posicion_final_x][j]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    }
                    // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch!=null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            toggleSwitch(_Protoboard, posicion_final_x, j, _Protoboard.protoboard[posicion_final_x][j]._switch.prendido);
                        } else{
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax+1, _switch.posicion1.coordenaday+1);
                        }

                    }
                    j++;
                }


            } else if ( posicion_final_y<=1  ) {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;


                    if (_cable.posicion2.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    // si encuentra un cable
                    if (_Protoboard.protoboard[i][posicion_final_y]._cable!=null  && _Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax!=-1){
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (_Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax!= _cable.posicion1.coordenadax || _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax!= _cable.posicion2.coordenadax ) {
                            int pos_final_x = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led!=null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1){

                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][posicion_final_y]._led)){
                                _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    }

                    i++;
                }
            }else if (posicion_final_y > 7 && posicion_final_y<13){
                int j = 8;
                while (j < 13) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;


                    if (_cable.posicion2.coordenadax == -2){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable!=null  && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!=-1){
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (_Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!= _cable.posicion1.coordenadax || _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax!= _cable.posicion2.coordenadax ) {
                            int pos_final_x = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }
                    // si encuentra un ked
                    if (_Protoboard.protoboard[posicion_final_x][j]._led!=null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1){

                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led)){
                                _Protoboard.protoboard[posicion_final_x][j]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    } // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch!=null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            toggleSwitch(_Protoboard, posicion_final_x, j, _Protoboard.protoboard[posicion_final_x][j]._switch.prendido);
                        } else{
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax+1, _switch.posicion1.coordenaday+1);
                        }

                    }
                    j++;
                }
            } else {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;


                    if (_cable.posicion2.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    // si encuentra un cable
                    if (_Protoboard.protoboard[i][posicion_final_y]._cable!=null  && _Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax!=-1){
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (_Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax!= _cable.posicion1.coordenadax || _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax!= _cable.posicion2.coordenadax ) {
                            int pos_final_x = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led!=null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1){


                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][posicion_final_y]._led)){
                                _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    }
                    i++;
                }
            }

        } else {
            int posicion_inicio_x = _cable.posicion1.coordenadax;
            int posicion_inicio_y = _cable.posicion1.coordenaday;

            int posicion_final_x = _cable.posicion2.coordenadax;
            int posicion_final_y = _cable.posicion2.coordenaday;




            _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y].conexion = true;
            _Protoboard.protoboard[posicion_final_x][posicion_final_y].conexion = true;


            if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y > 7 && posicion_final_y < 13)) {
                int j = 8;
                while (j < 13) {

                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable!=null && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!=-1){
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._cable.posicion1.coordenadax!= _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax || _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._cable.posicion2.coordenadax!= _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax || _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._cable.posicion1.coordenaday!= _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenaday || _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._cable.posicion2.coordenaday!= _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenaday ) {
                            int pos_final_x = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[posicion_final_x][j]._led!=null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1) {

                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led)) {
                                _Protoboard.protoboard[posicion_final_x][j]._led.encendido = true;

                            }
                            cuenta_leds = 0;
                        }

                    }
                    // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch!=null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            toggleSwitch(_Protoboard, posicion_final_x, j, _Protoboard.protoboard[posicion_final_x][j]._switch.prendido);
                        } else{
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax+1, _switch.posicion1.coordenaday+1);
                        }

                    }

                    if (_Protoboard.protoboard[posicion_final_x][j]._octoSwitch!=null && _Protoboard.protoboard[posicion_final_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[posicion_final_x][j]._octoSwitch;
                        int pos_y_switch = _octoSwitch.posicion1.coordenaday+1;
                        int pos_x = _cable.posicion2.coordenadax;
                        System.out.println("entra");

                        if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_1.encendido){
                                for (int l = 2; l < 7; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = true;
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=true;
                                        } else{
                                            _led.posicion2.corriente=true;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_2.encendido){
                                for (int l = 2; l < 7; l++){
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = true;
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led!=null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax!=-1){
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax){
                                            _led.posicion1.corriente=true;
                                        } else{
                                            _led.posicion2.corriente=true;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_3.encendido) {
                                for (int l = 2; l < 7; l++) {
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = true;
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led != null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax != -1) {
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                            _led.posicion1.corriente = true;
                                        } else {
                                            _led.posicion2.corriente = true;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        }else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_4.encendido) {
                                for (int l = 2; l < 7; l++) {
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = true;
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led != null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax != -1) {
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                            _led.posicion1.corriente = true;
                                        } else {
                                            _led.posicion2.corriente = true;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        }else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_5.encendido) {
                                for (int l = 2; l < 7; l++) {
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = true;
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led != null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax != -1) {
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                            _led.posicion1.corriente = true;
                                        } else {
                                            _led.posicion2.corriente = true;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        }else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_6.encendido) {
                                for (int l = 2; l < 7; l++) {
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = true;
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led != null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax != -1) {
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                            _led.posicion1.corriente = true;
                                        } else {
                                            _led.posicion2.corriente = true;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        }else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_7.encendido) {
                                for (int l = 2; l < 7; l++) {
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = true;
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led != null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax != -1) {
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                            _led.posicion1.corriente = true;
                                        } else {
                                            _led.posicion2.corriente = true;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        }else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax){
                            int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_8.encendido) {
                                for (int l = 2; l < 7; l++) {
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.corriente = true;
                                    _Protoboard.protoboard[coord_x_mini_switch][l]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                                    if (_Protoboard.protoboard[coord_x_mini_switch][l]._led != null && _Protoboard.protoboard[coord_x_mini_switch][l]._led.posicion1.coordenadax != -1) {
                                        Led _led = _Protoboard.protoboard[coord_x_mini_switch][l]._led;
                                        if (_Protoboard.protoboard[coord_x_mini_switch][l]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                            _led.posicion1.corriente = true;
                                        } else {
                                            _led.posicion2.corriente = true;
                                        }
                                        cambiarEstadoLed(_Protoboard, _led);
                                    }
                                }
                            }
                        }
                    }
                    j++;
                }
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y <=1)) {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    // si encuentra un cable

                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led!=null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1){

                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][posicion_final_y]._led)){
                                _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    }
                    i++;
                }
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y >=13)){
                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    // si encuentra un cable
                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led!=null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1){

                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[i][posicion_final_y]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenadax][_Protoboard.protoboard[i][posicion_final_y]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][posicion_final_y]._led)){
                                _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    }
                    i++;
                }
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y > 1 && posicion_final_y < 7)){
                int j = 2;

                while (j < 7) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    // si encuentra un cable

                    if (_Protoboard.protoboard[posicion_final_x][j]._cable!=null && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax!=-1 ){
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._cable.posicion1.coordenadax!= _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax || _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._cable.posicion2.coordenadax!= _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax || _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._cable.posicion1.coordenaday!= _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenaday || _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._cable.posicion2.coordenaday!= _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenaday) {
                            int pos_final_x = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[posicion_final_x][j]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[posicion_final_x][j]._led!=null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax!=-1){
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1){

                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.corriente = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.corriente;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenaday]._posicion.polaridad;
                            _Protoboard.protoboard[posicion_final_x][j]._led.posicion2.polaridad = _Protoboard.protoboard[_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenadax][_Protoboard.protoboard[posicion_final_x][j]._led.posicion2.coordenaday]._posicion.polaridad;

                            if (cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led)){
                                _Protoboard.protoboard[posicion_final_x][j]._led.encendido=true;

                            }
                            cuenta_leds=0;
                        }

                    }
                    // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch!=null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            toggleSwitch(_Protoboard, posicion_final_x, j, _Protoboard.protoboard[posicion_final_x][j]._switch.prendido);
                        } else{
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax+1, _switch.posicion1.coordenaday+1);
                        }

                    }
                    j++;
                }
            }
        }
        for (int fil = 0; fil < 30; fil++) {
            for (int com = 0; com < 15; com++) {
                if (_Protoboard.protoboard[fil][com]._cable!=null){
                    _Protoboard.protoboard[fil][com]._cable.procesado=false;
                }

            }
        }
    }

    public void switchSet(protoboard _Protoboard, int pos_central_x, int pos_central_y, boolean encendido){
        // inicializar switch
        Switch _switch = new Switch();
        // esquina arriba izq
        _switch.posicion1.coordenadax = pos_central_x-1;
        _switch.posicion1.coordenaday = pos_central_y-1;
        // esquina arriba der
        _switch.posicion2.coordenadax = pos_central_x+1;
        _switch.posicion2.coordenaday = pos_central_y-1;
        // esquina abajo izq
        _switch.posicion3.coordenadax = pos_central_x-1;
        _switch.posicion3.coordenaday = pos_central_y+1;
        // esquina bajo der
        _switch.posicion4.coordenadax = pos_central_x+1;
        _switch.posicion4.coordenaday = pos_central_y+1;

        _switch.prendido = encendido;

        _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._switch = _switch;
        _Protoboard.protoboard[pos_central_x+1][pos_central_y-1].conexion = true;

        _Protoboard.protoboard[pos_central_x-1][pos_central_y-1]._switch = _switch;
        _Protoboard.protoboard[pos_central_x-1][pos_central_y-1].conexion = true;

        _Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._switch = _switch;
        _Protoboard.protoboard[pos_central_x+1][pos_central_y+1].conexion = true;

        _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._switch = _switch;
        _Protoboard.protoboard[pos_central_x-1][pos_central_y+1].conexion = true;

        // encender switch en cada posicion (esquina)
        _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._switch.prendido = encendido;
        _Protoboard.protoboard[pos_central_x-1][pos_central_y-1]._switch.prendido = encendido;
        _Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._switch.prendido = encendido;
        _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._switch.prendido = encendido;

        _switch.pasarCorriente(_Protoboard, pos_central_x, pos_central_y);

    }

    public void toggleSwitch(protoboard _Protoboard, int pos_x, int pos_y, boolean encendido){

        boolean cent_ciclo = true;
        int pos_central_x = pos_x;
        int pos_central_y = pos_y;
        // encender el switch (todas sus posiciones)
        if (encendido){
            _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._switch.prendido = encendido;
            _Protoboard.protoboard[pos_central_x-1][pos_central_y-1]._switch.prendido = encendido;
            _Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._switch.prendido = encendido;
            _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._switch.prendido = encendido;
        } else {
            _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._switch.prendido = false;
            _Protoboard.protoboard[pos_central_x-1][pos_central_y-1]._switch.prendido = false;
            _Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._switch.prendido = false;
            _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._switch.prendido = false;
        }

        // para la posicion de arriba a la izquierda (la 1 )
        pos_x--;
        pos_y--;

        // ciclo para buscar el switch con pox_x y pos_y dentro de la matriz
        int i = 0 ;
        int j = 0;
        for ( i = 0; i < 30 && cent_ciclo ; i++) {
            for ( j = 0; j < 15 && cent_ciclo; j++) {
                    if (_Protoboard.protoboard[i][j]._switch!=null && _Protoboard.protoboard[i][j]._switch.posicion1.coordenadax == pos_x && _Protoboard.protoboard[i][j]._switch.posicion1.coordenaday == pos_y){
                        cent_ciclo=false;
                }
            }
        } i--; j--;



            // consultar por las posiciones del switch
            // pos 1 seria la esquina izquierda
            int pos_1_x = _Protoboard.protoboard[i][j]._switch.posicion1.coordenadax;
            int pos_1_y = _Protoboard.protoboard[i][j]._switch.posicion1.coordenaday;
            int pos_4_x = _Protoboard.protoboard[i][j]._switch.posicion4.coordenadax;

            boolean tiene_corriente_1 = false;
            boolean tiene_corriente_3 = false;


            if (pos_1_y == 6){

                // si la esquina izquierda es 6 significa que esta en el canal central, por lo tanto debe pasar corriente desde la posicion 1 a la contraria o viceversa
                // recorrer a ver si la columna en la pos x de la esquina izquierda tiene corriente
                for (int k = 2; k < 7; k++) {
                    if (_Protoboard.protoboard[pos_1_x][k]._posicion.corriente){
                        tiene_corriente_1 = true;

                        break;
                    }
                }
                // si la posicion 1 x tiene corriente entonces la pasamos para abajo
                if (tiene_corriente_1){
                    // se pasa corriente al canal de abajo
                    for (int k = 8; k < 13; k++) {
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][2]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][2]._posicion.polaridad;
                        // si encuentra un led al ir poniendo corriente, comprobar sus posiciones y llamar a cambiarestadoled
                        if (_Protoboard.protoboard[pos_4_x][k]._led!=null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            if (pos_4_x == _Protoboard.protoboard[pos_4_x][k]._posicion.coordenadax){
                                _Protoboard.protoboard[pos_4_x][k]._led.posicion1.corriente = true;
                            } else {
                                _Protoboard.protoboard[pos_4_x][k]._led.posicion2.corriente = true;
                            }

                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_4_x][k]._led);
                        }

                    }
                    // se apaga la corriente de arriba
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_1_x][k]._led!=null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            if (pos_1_x == _Protoboard.protoboard[pos_1_x][k]._posicion.coordenadax){
                                _Protoboard.protoboard[pos_1_x][k]._led.posicion1.corriente = false;
                            } else {
                                _Protoboard.protoboard[pos_1_x][k]._led.posicion2.corriente = false;
                            }
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][k]._led);
                        }
                    }
                }
                else{
                    // comprobamos si hay corriente en la esquina contraria, es decir, la esquina de abajo a la derecha
                    for (int k = 8; k < 13; k++) {
                        if (_Protoboard.protoboard[pos_4_x][k]._posicion.corriente){
                            tiene_corriente_3 = true;
                            System.out.println(_Protoboard.protoboard[pos_4_x][k]._posicion.polaridad);

                            break;

                        }
                    }
                    if (tiene_corriente_3){
                        // pasamos la corriente para arriba a la izquierda
                        for (int k = 2; k < 7; k++) {

                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][8]._posicion.polaridad;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][8]._posicion.polaridad;
                            if (_Protoboard.protoboard[pos_1_x][k]._led!=null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax!=-1){
                                _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                                if (pos_1_x == _Protoboard.protoboard[pos_1_x][k]._posicion.coordenadax){
                                    _Protoboard.protoboard[pos_1_x][k]._led.posicion1.corriente = true;
                                } else {
                                    _Protoboard.protoboard[pos_1_x][k]._led.posicion2.corriente = true;
                                }
                                cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][k]._led);
                            }
                        }
                        // apagamos la corriente de abajo a la derecha
                        for (int k = 8; k < 13; k++) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            if (_Protoboard.protoboard[pos_4_x][k]._led!=null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax!=-1){
                                _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                                if (pos_4_x == _Protoboard.protoboard[pos_4_x][k]._posicion.coordenadax){
                                    _Protoboard.protoboard[pos_4_x][k]._led.posicion1.corriente = false;
                                } else {
                                    _Protoboard.protoboard[pos_4_x][k]._led.posicion2.corriente = false;
                                }

                                cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_4_x][k]._led);
                            }


                        }
                    }
                }
            }
            if ( pos_1_y < 7 && pos_1_y>1 && pos_1_y!=6){
                // recorre la columna por si tiene corriente
                for (int k = 2; k < 7; k++) {
                    if (_Protoboard.protoboard[pos_1_x][k]._posicion.corriente){
                        tiene_corriente_1 = true;
                        break;

                    } else if (_Protoboard.protoboard[pos_4_x][k]._posicion.corriente){
                        tiene_corriente_3 = true;
                        break;

                    }
                }
                // si la posicion 1 x tiene corriente entonces se la pasamos a la posicion 4 x
                if (tiene_corriente_1){
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_4_x][k]._led!=null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            if (pos_4_x == _Protoboard.protoboard[pos_4_x][k]._posicion.coordenadax){
                                _Protoboard.protoboard[pos_4_x][k]._led.posicion1.corriente = true;
                            } else {
                                _Protoboard.protoboard[pos_4_x][k]._led.posicion2.corriente = true;
                            }

                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_4_x][k]._led);
                        }

                    }
                    // apagamos la corriente de la posicion 1 x
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;

                        if (_Protoboard.protoboard[pos_1_x][k]._led!=null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            if (pos_1_x == _Protoboard.protoboard[pos_1_x][k]._posicion.coordenadax){
                                _Protoboard.protoboard[pos_1_x][k]._led.posicion1.corriente = false;
                            } else {
                                _Protoboard.protoboard[pos_1_x][k]._led.posicion2.corriente = false;
                            }
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][k]._led);
                        }

                    }
                } else if (tiene_corriente_3){
                    // si no tiene corriente esa posicion, buscamos en la posicion 4 x
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][k]._led!=null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            if (pos_1_x == _Protoboard.protoboard[pos_1_x][k]._posicion.coordenadax){
                                _Protoboard.protoboard[pos_1_x][k]._led.posicion1.corriente = true;
                            } else {
                                _Protoboard.protoboard[pos_1_x][k]._led.posicion2.corriente = true;
                            }

                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][k]._led);
                        }

                    }
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;

                        if (_Protoboard.protoboard[pos_4_x][k]._led!=null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                            if (pos_4_x == _Protoboard.protoboard[pos_4_x][k]._posicion.coordenadax){
                                _Protoboard.protoboard[pos_4_x][k]._led.posicion1.corriente = false;
                            } else {
                                _Protoboard.protoboard[pos_4_x][k]._led.posicion2.corriente = false;
                            }

                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_4_x][k]._led);
                        }

                    }
                }
            } else if (pos_1_y>7 && pos_1_y<13){
                // recorre la columna por si tiene corriente
                for (int k = 8; k < 13; k++) {
                    if (_Protoboard.protoboard[pos_1_x][k]._posicion.corriente){
                        tiene_corriente_1 = true;
                        break;

                    } else if (_Protoboard.protoboard[pos_4_x][k]._posicion.corriente){
                        tiene_corriente_3 = true;
                        break;
                    }
                }
                // si la posicion 1 x tiene corriente entonces se la pasamos a la posicion 3 x
                if (tiene_corriente_1){
                    for (int k = 8; k < 13; k++) {
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_4_x][k]._led!=null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            if (pos_4_x == _Protoboard.protoboard[pos_4_x][k]._posicion.coordenadax){
                                _Protoboard.protoboard[pos_4_x][k]._led.posicion1.corriente = true;
                            } else {
                                _Protoboard.protoboard[pos_4_x][k]._led.posicion2.corriente = true;
                            }

                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_4_x][k]._led);
                        }
                    }
                    // apagamos la corriente de la posicion 1 x
                    for (int k = 8; k < 13; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;

                        if (_Protoboard.protoboard[pos_1_x][k]._led!=null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            if (pos_1_x == _Protoboard.protoboard[pos_1_x][k]._posicion.coordenadax){
                                _Protoboard.protoboard[pos_1_x][k]._led.posicion1.corriente = false;
                            } else {
                                _Protoboard.protoboard[pos_1_x][k]._led.posicion2.corriente = false;
                            }
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][k]._led);
                        }
                    }
                } else if ( tiene_corriente_3){
                    // si no tiene corriente esa posicion, buscamos en la posicion 3 x

                    for (int k = 8; k < 13; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad;

                            if (_Protoboard.protoboard[pos_1_x][k]._led!=null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax!=-1){
                                _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                                if (pos_1_x == _Protoboard.protoboard[pos_1_x][k]._posicion.coordenadax){
                                    _Protoboard.protoboard[pos_1_x][k]._led.posicion1.corriente = true;
                                } else {
                                    _Protoboard.protoboard[pos_1_x][k]._led.posicion2.corriente = true;
                                }
                                cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][k]._led);
                            }
                    }
                    for (int k = 8; k < 13; k++) {
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;

                            if (_Protoboard.protoboard[pos_4_x][k]._led!=null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax!=-1){
                                _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                                if (pos_4_x == _Protoboard.protoboard[pos_4_x][k]._posicion.coordenadax){
                                    _Protoboard.protoboard[pos_4_x][k]._led.posicion1.corriente = false;
                                } else {
                                    _Protoboard.protoboard[pos_4_x][k]._led.posicion2.corriente = false;
                                }

                                cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_4_x][k]._led);
                            }
                    }
                }
            }


    }

    public void octoSwitchSet(protoboard _Protoboard, int pos_central_x, int pos_central_y){
        OctoSwitch _octoswitch = new OctoSwitch();
        // esquina arriba izq
        _octoswitch.posicion1.coordenadax = pos_central_x-3;
        _octoswitch.posicion1.coordenaday = pos_central_y-1;
        // esquina arriba der
        _octoswitch.posicion2.coordenadax = pos_central_x+4;
        _octoswitch.posicion2.coordenaday = pos_central_y-1;
        // esquina abajo izq
        _octoswitch.posicion3.coordenadax = pos_central_x-3;
        _octoswitch.posicion3.coordenaday = pos_central_y+1;
        // esquina bajo der
        _octoswitch.posicion4.coordenadax = pos_central_x+4;
        _octoswitch.posicion4.coordenaday = pos_central_y+1;

        // rellena los lugares que esta ocupando el octoswitch
        for ( int i = pos_central_x-3; i < pos_central_x+5; i++){
            for (int j = pos_central_y-1; j < pos_central_y+2; j++){
                _Protoboard.protoboard[i][j]._octoSwitch = _octoswitch;
                _Protoboard.protoboard[i][j].conexion = true;
            }
        }

        _octoswitch.setMiniSwitch_OctoSwitch(_octoswitch);

    }

    public void cableSet(protoboard _Protoboard, int pos_1_x, int pos_1_y, int pos_2_x, int pos_2_y, boolean conexion_bateria) {
        int i, j = 0;
        cable _Cable = new cable();
        //Ingresar coordenadas y color desde protoboard grafico
        _Cable.posicion1.coordenadax = pos_1_x;
        _Cable.posicion1.coordenaday = pos_1_y;
        _Cable.posicion2.coordenadax = pos_2_x;
        _Cable.posicion2.coordenaday = pos_2_y;
        _Cable.color = "rojo";

        _Cable.conexionBateria = conexion_bateria;


        if (_Cable.conexionBateria) {
            if (_Cable.posicion1.coordenadax == -2 && _Cable.posicion1.coordenaday == -2) {
                _Cable.posicion1.corriente = true;
                _Cable.posicion2.corriente = true;
                _Cable.posicion1.polaridad = true;
                _Cable.posicion2.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday].conexion = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.corriente = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._cable = _Cable;
                pasarCorriente(_Protoboard, _Cable);
            }

            if (_Cable.posicion2.coordenadax == -3 && _Cable.posicion2.coordenaday == -3) {
                _Cable.posicion1.corriente = true;
                _Cable.posicion2.corriente = true;
                _Cable.posicion1.polaridad = false;
                _Cable.posicion2.polaridad = false;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday].conexion = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.corriente = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._cable = _Cable;
                pasarCorriente(_Protoboard, _Cable);
            }
            if (_Cable.posicion1.coordenadax == -3 && _Cable.posicion1.coordenaday == -3) {
                _Cable.posicion1.corriente = true;
                _Cable.posicion2.corriente = true;
                _Cable.posicion1.polaridad = false;
                _Cable.posicion2.polaridad = false;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday].conexion = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.corriente = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._cable = _Cable;
                pasarCorriente(_Protoboard, _Cable);
            }

            if (_Cable.posicion2.coordenadax == -2 && _Cable.posicion2.coordenaday == -2) {
                _Cable.posicion1.corriente = true;
                _Cable.posicion2.corriente = true;
                _Cable.posicion1.polaridad = true;
                _Cable.posicion2.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday].conexion = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.corriente = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._cable = _Cable;
                pasarCorriente(_Protoboard, _Cable);
            }

        } else {
            _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._cable = _Cable;
            _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._cable = _Cable;
            _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday].conexion = true;
            _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday].conexion = true;
            pasarCorriente(_Protoboard, _Cable);
            if (_Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.corriente) {

                _Cable.posicion2.corriente = true;

                _Cable.posicion2.polaridad = _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.polaridad;
                pasarCorriente(_Protoboard, _Cable);
                if (_Cable.posicion1.polaridad != _Cable.posicion2.polaridad && _Cable.posicion1.corriente && _Cable.posicion2.corriente) {
                    _Cable.quemado = true;
                    System.out.println("Has quemado el cable");
                }
                if (_Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.corriente) {
                    _Cable.posicion1.corriente = true;
                    _Cable.posicion1.polaridad = _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.polaridad;
                    pasarCorriente(_Protoboard, _Cable);
                    if (_Cable.posicion1.polaridad != _Cable.posicion2.polaridad && _Cable.posicion1.corriente && _Cable.posicion2.corriente) {
                        _Cable.quemado = true;
                        System.out.println("Has quemado el cable");
                    }
                }
                //while("recorrer corriente basado en posicion, si es 0-1-12-13, significa que es un bus y se requiere recorrer horizontalmente, si es entre 2-6 se considera vertical superior y si es 7-11, vertical inferior, se debe realizar esta verificacion para cada uno de los cables")
            }
        }
    }
} //else"considerar el caso en donde ninguna de las conexiones del cable posean conexion"
//if("conexion a bateria cable == true" -> cambiar corriente cable = true, establecer polaridad) Check \(~u~)/
//if("luego de conectar cable, revisar donde se conecta y cambiar lugares adyacentes") Check \(`w`)/
//Considerar que en cada momento en el que se conecte un cable, se debera realizar el cambio a todo el protoboard, pero no es necesario realizar el cambio en todo el protoboard al mismo tiempo, solo en la zona existente
//Considerar posible fallo al conectar en 2 espacios con polaridades contrarias




