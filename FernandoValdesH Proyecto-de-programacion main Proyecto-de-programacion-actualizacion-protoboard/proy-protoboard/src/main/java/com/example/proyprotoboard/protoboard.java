package com.example.proyprotoboard;

public class protoboard {

    private static protoboard instance;

    public logicalProtoboard[][] protoboard;


    private protoboard(logicalProtoboard[][] protoboard) {
        this.protoboard = protoboard;
        // rellenar matriz

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 14; j++) {
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


    public void ledInitiatorStart(protoboard _Protoboard, int posx_1, int posy_1,int auxx,int auxy, int cantidad_patas) {
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
            if (_Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion1.corriente && _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion2.corriente && _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion1.polaridad != _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion2.polaridad && cantidad_patas == 2) {
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led.encendido = true;
                System.out.println("prendio");
            }
        }




    }

    public void pasarCorriente(protoboard _Protoboard, cable _cable){
        if ((_cable.posicion1.coordenadax == -2 && _cable.posicion1.coordenaday == -2) || (_cable.posicion1.coordenadax == -3 && _cable.posicion1.coordenaday == -3)) {

            int posicion_final_x = _cable.posicion2.coordenadax;
            int posicion_final_y = _cable.posicion2.coordenaday;



            _Protoboard.protoboard[posicion_final_x][posicion_final_y]._cable = _cable;
            _Protoboard.protoboard[posicion_final_x][posicion_final_y].conexion = true;
            

            if ( posicion_final_y > 1 && posicion_final_y < 12) {

                int j = 2;
                while (j < 7) {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
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
                    if (_cable.posicion1.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    i++;
                }
            }else if (posicion_final_y > 7 && posicion_final_y<12){
                int j = 7;
                while (j < 12) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    if (_cable.posicion2.coordenadax == -2){
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
                    if (_cable.posicion2.coordenadax == -2){
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }

                    i++;
                }
            }else if (posicion_final_y > 7 && posicion_final_y<12){
                int j = 7;
                while (j < 12) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
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



            if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y > 6 && posicion_final_y < 12)) {

                int j = 7;
                while (j < 12) {
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
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y >=12)){
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

        _Protoboard.protoboard[pos_central_x][pos_central_y]._switch = _switch;
        _Protoboard.protoboard[pos_central_x][pos_central_y].conexion = true;

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




