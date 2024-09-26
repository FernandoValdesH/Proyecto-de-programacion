package com.example.proyprotoboard;

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
                    if (_Protoboard.protoboard[i][j]._cable.posicion1.coordenadax!=-1){
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

        } else if (!_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._led.posicion1.coordenadax!=-1){
            Led _led = _Protoboard.protoboard[pos_x][pos_y]._led;
            if (_led.posicion1.coordenadax == pos_x && _led.posicion1.coordenaday == pos_y){
                _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led = null;
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = null;
            } else {
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = null;
                _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led = null;
            }
        } else if (!_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._switch.posicion1.coordenadax!=-1) {
            Switch _switch = _Protoboard.protoboard[pos_x][pos_y]._switch;
            _Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion2.coordenadax][_switch.posicion2.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion3.coordenadax][_switch.posicion3.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion4.coordenaday]._switch = null;
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

                    // si hay un switch en la columna que se esta eliminando corriente, eliminar la corriente de la columna de su posicion, pero comprobando si esta apretado o no

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
                    j++;
                }
            } else {
                int j = 8;
                int i = pos_x;
                while (j < 13){
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
                    j++;
                }
            }




            // si habia un led en esa posicion, se debe apagar ya que perdio la corriente

        // eliminar led
        /*if (_Protoboard.protoboard[pos_x][pos_y]._led != null){


            // eliminar corriente de este led
            if (pos_y < 2 || pos_y > 11){
                int j = pos_y;
                int i = 0;
                while (i < 30){
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    i++;
                }
            } else if (pos_y > 1 && pos_y < 7){
                int j = 2;
                int i = pos_x;
                while (j < 7){
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    j++;
                }
            } else {
                int j = 7;
                int i = pos_x;
                while (j < 12){
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    j++;
                }
            }
        } */
        // eliminar led ( 4 pos )
        /*
        if (_Protoboard.protoboard[pos_x][pos_y]._switch != null){

            // eliminar corriente de este switch
            if (_switch.posicion1.coordenaday < 2 || _switch.posicion1.coordenaday > 11){
                int j = _switch.posicion1.coordenaday;
                int i = 0;
                while (i < 30){
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    i++;
                }
            } else if (_switch.posicion1.coordenaday > 1 && _switch.posicion1.coordenaday < 7){
                int j = 2;
                int i = _switch.posicion1.coordenadax;
                while (j < 7){
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    j++;
                }
            } else {
                int j = 7;
                int i = _switch.posicion1.coordenadax;
                while (j < 12){
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    j++;
                }
            }
        }*/
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 15; j++) {
                if (_Protoboard.protoboard[i][j]._cable!=null){
                    _Protoboard.protoboard[i][j]._cable.procesado=false;
                }

            }
        }
    }

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

                    if (_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        _Protoboard.protoboard[posicion_final_x][j]._led.encendido=true;
                    }
                    if (_cable.posicion1.coordenadax == -2){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                        j++;
                }


            } else if ( posicion_final_y<=1  ) {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                    if (_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;
                    }

                    if (_cable.posicion1.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    i++;
                }
            }else if (posicion_final_y > 7 && posicion_final_y<13){
                int j = 8;
                while (j < 13) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    if (_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        _Protoboard.protoboard[posicion_final_x][j]._led.encendido=true;
                    }

                    if (_cable.posicion1.coordenadax == -2){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    j++;
                }
            }  else {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;

                    if (_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;
                    }

                    if (_cable.posicion1.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
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


                    if (_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        _Protoboard.protoboard[posicion_final_x][j]._led.encendido=true;
                    }

                    if (_cable.posicion2.coordenadax == -2){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    j++;
                }


            } else if ( posicion_final_y<=1  ) {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;

                    if (_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;
                    }
                    if (_cable.posicion2.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }

                    i++;
                }
            }else if (posicion_final_y > 7 && posicion_final_y<13){
                int j = 8;
                while (j < 13) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                    if (_Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        _Protoboard.protoboard[posicion_final_x][j]._led.encendido=true;
                    }

                    if (_cable.posicion2.coordenadax == -2){
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    j++;
                }
            } else {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                    if (_Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        _Protoboard.protoboard[i][posicion_final_y]._led.encendido=true;
                    }

                    if (_cable.posicion2.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    i++;
                }
            }
            
        } else {
            
            int posicion_inicio_x = _cable.posicion1.coordenadax;
            int posicion_inicio_y = _cable.posicion1.coordenaday;
            
            int posicion_final_x = _cable.posicion2.coordenadax;
            int posicion_final_y = _cable.posicion2.coordenaday;



            _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._cable = _cable;
            _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y].conexion = true;


            _Protoboard.protoboard[posicion_final_x][posicion_final_y]._cable = _cable;
            _Protoboard.protoboard[posicion_final_x][posicion_final_y].conexion = true;



            if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y > 7 && posicion_final_y < 13)) {

                int j = 8;
                while (j < 13) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    j++;
                }
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y <=1)) {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    i++;
                }
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y >=13)){
                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    i++;
                }
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y > 1 && posicion_final_y < 7)){
                int j = 2;
                while (j < 7) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    j++;
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
                    if (_Protoboard.protoboard[i][j]._switch.posicion1.coordenadax == pos_x && _Protoboard.protoboard[i][j]._switch.posicion1.coordenaday == pos_y){
                        cent_ciclo=false;
                }
            }
        } i--; j--;

        if (encendido){

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

                    }
                    // se apaga la corriente de arriba
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
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
                            System.out.println("pos_1_x: "+pos_1_x+" k: "+k +"corriente" + _Protoboard.protoboard[pos_1_x][k]._posicion.corriente + " polaridad: "+_Protoboard.protoboard[pos_1_x][k]._posicion.polaridad);
                        }
                        // apagamos la corriente de abajo a la derecha
                        for (int k = 8; k < 13; k++) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                        }
                    }
                }
            }
            if ( pos_1_y < 7 && pos_1_y>1 && pos_1_y!=6){
                // recorre la columna por si tiene corriente
                for (int k = 2; k < 7; k++) {
                    if (_Protoboard.protoboard[pos_1_x][k]._posicion.corriente){
                        tiene_corriente_1 = true;

                    } else if (_Protoboard.protoboard[pos_4_x][k]._posicion.corriente){
                        tiene_corriente_3 = true;

                    }
                }
                // si la posicion 1 x tiene corriente entonces se la pasamos a la posicion 4 x
                if (tiene_corriente_1){
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad;
                    }
                    // apagamos la corriente de la posicion 1 x
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                    }
                } else if (tiene_corriente_3){
                    // si no tiene corriente esa posicion, buscamos en la posicion 4 x
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad;
                    }
                    for (int k = 2; k < 7; k++) {
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                    }
                }
            } else if (pos_1_y>=8 && pos_1_y<13){
                // recorre la columna por si tiene corriente
                for (int k = 8; k < 13; k++) {
                    if (_Protoboard.protoboard[pos_1_x][k]._posicion.corriente){
                        tiene_corriente_1 = true;

                    } else if (_Protoboard.protoboard[pos_4_x][k]._posicion.corriente){
                        tiene_corriente_3 = true;

                    }
                }
                // si la posicion 1 x tiene corriente entonces se la pasamos a la posicion 3 x
                if (tiene_corriente_1){
                    for (int k = 8; k < 13; k++) {
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad;

                    }
                    // apagamos la corriente de la posicion 1 x
                    for (int k = 8; k < 13; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                    }
                } else if ( tiene_corriente_3){
                    // si no tiene corriente esa posicion, buscamos en la posicion 3 x

                    for (int k = 8; k < 13; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad;
                    }
                    for (int k = 8; k < 13; k++) {
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                    }
                }
            }
        }

    }

    public void checkCorriente(protoboard _Protoboard, cable _cable) {
        //recursion
        int i = 0;
        int j = 0;
        if (_cable.conexionBateria) {
            //Si conexion a bateria
            if (_cable.posicion1.coordenadax == -2 && _cable.posicion1.coordenaday == -2 || _cable.posicion1.coordenadax == -3 && _cable.posicion1.coordenaday == -3) {

                if (_Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday <= 1 || _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday >= 12) {
                    j = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday;
                    i = 0;
                    while (i < 30) {
                        System.out.println("b");
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.posicion2.polaridad;
                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        i++;
                    }
                }
                if (_Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday > 1 && _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday < 7) {
                    j = 2;
                    i = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax;
                    while (j < 7) {
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.posicion2.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        j++;
                    }
                }
                if (_Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday > 6 && _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday < 12) {
                    j = 7;
                    i = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax;
                    while (j < 12) {
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.posicion2.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        j++;
                    }
                }
            }
            if (_cable.posicion2.coordenadax == -2 && _cable.posicion2.coordenaday == -2 || _cable.posicion2.coordenadax == -3 && _cable.posicion2.coordenaday == -3) {
                if (_Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday <= 1 || _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday >= 12) {
                    j = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.coordenaday;
                    i = 0;
                    while (i < 30) {
                        System.out.println("b");
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.polaridad;

                        //if(_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenadax){checkCorriente(_Protoboard,_Protoboard.protoboard[i][j]._cable);}
                        i++;
                    }
                }
                if (_Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.coordenaday > 1 && _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.coordenaday < 7) {
                    j = 2;
                    i = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenadax;
                    while (j < 7) {
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        j++;
                    }
                }
                if (_Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.coordenaday > 6 && _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.coordenaday < 12) {
                    j = 7;
                    i = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.coordenadax;
                    while (j < 12) {
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        j++;
                    }
                }
            }
        } else {
            if (_Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.corriente) {
                if (_Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday <= 1 || _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday >= 12) {
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.corriente = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.corriente;
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.polaridad;
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion2.corriente = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.corriente;
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion2.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.polaridad;
                    j = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday;
                    i = 0;
                    while (i < 30) {
                        System.out.println("b" + i);
                        _Protoboard.protoboard[i][j]._posicion.corriente = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.corriente;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenadax && _Protoboard.protoboard[i][j]._posicion.coordenaday != _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        i++;
                    }
                }
                if (_Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday > 1 && _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday < 7) {
                    j = 2;
                    i = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenadax;
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.corriente = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.corriente;
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion1.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.polaridad;
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion2.corriente = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.corriente;
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.posicion2.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.polaridad;
                    while (j < 7) {
                        _Protoboard.protoboard[i][j]._posicion.corriente = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.corriente;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        j++;
                    }
                }
                if (_Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday > 6 && _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenaday < 12) {
                    j = 7;
                    i = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenadax;
                    while (j < 12) {
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        j++;
                    }
                }
            }
            if (_Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.corriente) {
                if (_Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday <= 1 || _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday >= 12) {
                    j = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday;
                    i = 0;
                    while (i < 30) {
                        System.out.println("b");
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        i++;
                    }
                }
                if (_Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday > 1 && _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday < 7) {
                    j = 2;
                    i = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax;
                    while (j < 7) {
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        j++;
                    }
                }
                if (_Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday > 6 && _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenaday < 12) {
                    j = 7;
                    i = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax;
                    while (j < 12) {
                        _Protoboard.protoboard[i][j]._posicion.corriente = true;
                        _Protoboard.protoboard[i][j]._posicion.polaridad = _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.polaridad;

                        //if (_Protoboard.protoboard[i][j].conexion && _Protoboard.protoboard[i][j]._posicion.coordenadax != _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._posicion.coordenadax) {checkCorriente(_Protoboard, _Protoboard.protoboard[i][j]._cable);}
                        j++;
                    }
                }

            }
        }

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




