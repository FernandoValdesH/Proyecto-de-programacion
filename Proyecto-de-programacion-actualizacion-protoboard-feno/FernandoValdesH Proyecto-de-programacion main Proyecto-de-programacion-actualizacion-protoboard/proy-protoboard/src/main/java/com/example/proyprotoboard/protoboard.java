package com.example.proyprotoboard;

//import jdk.swing.interop.SwingInterOpUtils;

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


    public boolean cambiarEstadoLed(protoboard _Protoboard, Led _led) {

        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;

        if (_Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion1.corriente && _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion2.corriente && _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion1.polaridad != _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led.posicion2.polaridad && _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led.posicion1.polaridad == _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad) {
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led.encendido = true;
            return true;
        } else {
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led.encendido = false;
            return false;
        }

    }

    public Led ledInitiatorStart(protoboard _Protoboard, int posx_1, int posy_1, int auxx, int auxy, int cantidad_patas) {
        Led _led = new Led();
        if (cantidad_patas == 1) {

            _led.posicion1.coordenadax = posx_1;
            _led.posicion1.coordenaday = posy_1;
            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = _led;
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday].conexion = true;
        }
        if (cantidad_patas == 2) {
            _led.posicion2.coordenadax = posx_1;
            _led.posicion2.coordenaday = posy_1;
            _led.posicion1.coordenadax = auxx;
            _led.posicion1.coordenaday = auxy;
            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
            _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led = _led;
            _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = _led;
            _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday].conexion = true;

            cambiarEstadoLed(_Protoboard, _led);
            _led.chequearQuemado(_Protoboard, _led);
        }

        return _led;

    }

    public void eliminarElemento(protoboard _Protoboard, int pos_x, int pos_y) {
        // inicializar variables de posicion 2 en caso de que la posicion 1 este conectada a la bateria
        int pos_x_2 = pos_x;
        int pos_y_2 = pos_y;
        if (pos_x == 33) {
            pos_x = -2;
            // buscar el cable
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 15; j++) {
                    if (_Protoboard.protoboard[i][j]._cable != null && _Protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1) {
                        if (_Protoboard.protoboard[i][j]._cable.posicion1.coordenadax == -2 && _Protoboard.protoboard[i][j]._cable.posicion1.coordenaday == -2) {
                            pos_x_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                            pos_y_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                        }
                    }

                }
            }
        }
        if (pos_x == 34) {
            pos_x = -3;
            // buscar el cable
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 15; j++) {
                    if (_Protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1) {
                        if (_Protoboard.protoboard[i][j]._cable.posicion1.coordenadax == -3 && _Protoboard.protoboard[i][j]._cable.posicion1.coordenaday == -3) {
                            pos_x_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                            pos_y_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                        }
                    }
                }
            }
        }

        if (_Protoboard.protoboard[pos_x_2][pos_y_2]._cable.posicion1.coordenadax != -1) {
            cable _cable = _Protoboard.protoboard[pos_x_2][pos_y_2]._cable;
            // primero un if si la posicion 1 o 2 estan conectados a la bateria, es decir, coord -2 y -3
            if (_cable.posicion1.coordenadax == -2 || _cable.posicion1.coordenadax == -3) {
                // si la posicion 1 esta conectada a la bateria, eliminamos el cable de la posicion 2 (final)
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday].conexion = false;

                eliminarCorriente(_Protoboard, pos_x_2, pos_y_2, false);

            } else if (_cable.posicion2.coordenadax == -2 || _cable.posicion2.coordenadax == -3) {
                // si la posicion 2 esta conectada a la bateria, eliminamos el cable de la posicion 1 (inicio)
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday].conexion = false;

                eliminarCorriente(_Protoboard, pos_x_2, pos_y_2, false);
            }
            // si no esta conectado a la bateria, al eliminar una posicion tambien se elimina la otra
            else if (_cable.posicion1.coordenadax == pos_x_2 && _cable.posicion1.coordenaday == pos_y_2) {
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday].conexion = false;
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday].conexion = false;

                eliminarCorriente(_Protoboard, pos_x_2, pos_y_2, false);
            } else if (_cable.posicion2.coordenadax == pos_x_2 && _cable.posicion2.coordenaday == pos_y_2) {
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday].conexion = false;
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable = null;
                _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday].conexion = false;

                eliminarCorriente(_Protoboard, pos_x_2, pos_y_2, false);
            }

        } else if (_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._led.posicion1.coordenadax != -1) {
            Led _led = _Protoboard.protoboard[pos_x][pos_y]._led;
            if (_led.posicion1.coordenadax == pos_x && _led.posicion1.coordenaday == pos_y) {
                _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led = null;
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = null;
            } else {
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = null;
                _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led = null;
            }
        } else if (_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._switch.posicion1.coordenadax != -1) {
            Switch _switch = _Protoboard.protoboard[pos_x][pos_y]._switch;

            // si el switch esta prendido, se debe eliminar la corriente de la columna contraria

            int guarda_x_cable = 0;
            boolean encuentra_un_cable = false;
            System.out.println("pos_y: " + pos_y);
            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday]._posicion.corriente || _Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion2.coordenaday]._posicion.corriente || _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion4.coordenaday]._posicion.corriente || _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion3.coordenaday]._posicion.corriente) {
                _switch.eliminarCorriente_AlEliminarSwitch(_Protoboard, _switch, guarda_x_cable, encuentra_un_cable, pos_y);
            }

            _Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion2.coordenadax][_switch.posicion2.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion3.coordenadax][_switch.posicion3.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion4.coordenaday]._switch = null;

        } else if (_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._octoSwitch.posicion1.coordenadax != -1) {
            // si encuentra un octoswitch
            OctoSwitch _octoSwitch = _Protoboard.protoboard[pos_x][pos_y]._octoSwitch;
            int pos_1_x = _octoSwitch.posicion1.coordenadax;
            int pos_1_y = _octoSwitch.posicion1.coordenaday;
            int pos_2_x = _octoSwitch.posicion2.coordenadax;
            int pos_y_switch = pos_1_y + 1;
            for (int k = pos_1_x; k < pos_2_x + 1; k++) {
                if (_Protoboard.protoboard[k][pos_y_switch]._octoSwitch.mini_switch_1.encendido) {
                    // borrar la corriente abajo del miniswitch (si hay)
                    if (_Protoboard.protoboard[k][pos_y_switch - 1]._posicion.corriente) {
                        for (int l = 2; l < 7; l++) {
                            _Protoboard.protoboard[k][l]._posicion.corriente = false;
                        }

                    } else if (_Protoboard.protoboard[k][pos_y_switch + 1]._posicion.corriente) {
                        for (int l = 8; l < 13; l++) {
                            _Protoboard.protoboard[k][l]._posicion.corriente = false;
                        }
                    }

                }
            }
            for (int i = pos_1_x; i < pos_2_x + 1; i++) {
                for (int j = pos_1_y; j < 3; j++) {
                    _Protoboard.protoboard[i][j]._octoSwitch = null;
                }
            }

        } else if (_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._resistencia.posicion1.coordenadax != -1) {
            Resistencia _resistencia = _Protoboard.protoboard[pos_x][pos_y]._resistencia;
            if (_resistencia.posicion1.coordenadax == pos_x && _resistencia.posicion1.coordenaday == pos_y) {
                _Protoboard.protoboard[_resistencia.posicion2.coordenadax][_resistencia.posicion2.coordenaday]._resistencia = null;
                _Protoboard.protoboard[_resistencia.posicion1.coordenadax][_resistencia.posicion1.coordenaday]._resistencia = null;
            } else {
                _Protoboard.protoboard[_resistencia.posicion1.coordenadax][_resistencia.posicion1.coordenaday]._resistencia = null;
                _Protoboard.protoboard[_resistencia.posicion2.coordenadax][_resistencia.posicion2.coordenaday]._resistencia = null;
            }
            _resistencia.eliminarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday);
        }
    }

    public void eliminarCorriente(protoboard _Protoboard, int pos_x, int pos_y, boolean switch_bateria) {

        // este metodo recibe la posicion donde se elimino el elemento

        // al eliminar un elemento, se debe eliminar un cable, led, switch, del protoboard y eliminar la corriente de esa fila/columna
        if (switch_bateria) {
            if (pos_x == -3 || pos_x == -2) {
                System.out.println("entra negativo");
                for (int i = 0; i < 30; i++) {
                    for (int k = 0; k < 15; k++) {
                        if (_Protoboard.protoboard[i][k]._cable.conexionBateria) {
                            if (_Protoboard.protoboard[i][k]._cable.posicion1.coordenadax == -2 || _Protoboard.protoboard[i][k]._cable.posicion1.coordenadax == -3) {
                                pos_x = _Protoboard.protoboard[i][k]._cable.posicion2.coordenadax;
                                pos_y = _Protoboard.protoboard[i][k]._cable.posicion2.coordenaday;
                            } else {
                                pos_x = _Protoboard.protoboard[i][k]._cable.posicion1.coordenadax;
                                pos_y = _Protoboard.protoboard[i][k]._cable.posicion1.coordenaday;
                            }
                        }
                    }
                }
            }
        }

        if (pos_y <= 1 || pos_y > 12) {
            int j = pos_y;
            int i = 0;
            System.out.println("entro a el bus");
            while (i < 30) {

                _Protoboard.protoboard[i][j]._posicion.corriente = false;
                // si hay un led en la columna que se esta eliminando corriente, apagarlo
                if (_Protoboard.protoboard[i][j]._led != null && _Protoboard.protoboard[i][j]._led.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    _Protoboard.protoboard[i][j]._led.encendido = false;
                }

                // si se encuentra un cable en la fila/columna que se esta eliminando, hay que borrar la corriente de este cable tambien, es decir, llamar a eliminar corriente nuevamente
                if (_Protoboard.protoboard[i][j]._cable != null && !_Protoboard.protoboard[i][j]._cable.procesado && _Protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[i][j]._cable.procesado = true;
                    int pos_final_x = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                    int pos_final_y = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                    System.out.println("encontro un cable en la posicion " + pos_final_x + " " + pos_final_y);
                    eliminarCorriente(_Protoboard, pos_final_x, pos_final_y, switch_bateria);
                }

                i++;
            }
        } else if (pos_y > 1 && pos_y < 7) {
            int j = 2;
            int i = pos_x;
            System.out.println("entro columnas de arriba");
            while (j < 7) {

                _Protoboard.protoboard[i][j]._posicion.corriente = false;

                if (_Protoboard.protoboard[i][j]._led != null && _Protoboard.protoboard[i][j]._led.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    _Protoboard.protoboard[i][j]._led.encendido = false;

                }
                if (_Protoboard.protoboard[i][j]._cable != null && !_Protoboard.protoboard[i][j]._cable.procesado && _Protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[i][j]._cable.procesado = true;
                    int pos_final_x = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                    int pos_final_y = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                    eliminarCorriente(_Protoboard, pos_final_x, pos_final_y, switch_bateria);
                }
                // si encuentra un switch y este esta prendido debe eliminar la corriente que esta en la esquina contraria, si esta apagado elimina la corriente de forma normal
                if (_Protoboard.protoboard[i][j]._switch != null && _Protoboard.protoboard[i][j]._switch.posicion1.coordenadax != -1) {
                    Switch _switch = _Protoboard.protoboard[i][j]._switch;
                    _switch.eliminarCorriente(_Protoboard, _switch.posicion1.coordenadax, _switch.posicion1.coordenaday);
                }
                // si encuentra un octoswitch
                if (_Protoboard.protoboard[i][j]._octoSwitch != null && _Protoboard.protoboard[i][j]._octoSwitch.posicion1.coordenadax != -1 && switch_bateria) {
                    OctoSwitch _octoSwitch = _Protoboard.protoboard[i][j]._octoSwitch;
                    if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    }
                }
                // si encuentra una resistencia
                if (_Protoboard.protoboard[i][j]._resistencia != null && _Protoboard.protoboard[i][j]._resistencia.posicion1.coordenadax != -1) {
                    Resistencia _resistencia = _Protoboard.protoboard[i][j]._resistencia;
                    _resistencia.eliminarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday);
                }

                j++;
            }
        } else if (pos_y > 7) {
            int j = 8;
            int i = pos_x;
            System.out.println("entro columnas de abajo");
            while (j < 13) {

                _Protoboard.protoboard[i][j]._posicion.corriente = false;

                if (_Protoboard.protoboard[i][j]._led != null && _Protoboard.protoboard[i][j]._led.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[i][j]._posicion.corriente = false;
                    _Protoboard.protoboard[i][j]._led.encendido = false;

                }
                if (_Protoboard.protoboard[i][j]._cable != null && !_Protoboard.protoboard[i][j]._cable.procesado && _Protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[i][j]._cable.procesado = true;
                    int pos_final_x = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                    int pos_final_y = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                    eliminarCorriente(_Protoboard, pos_final_x, pos_final_y, switch_bateria);
                }
                // si encuentra un switch y este esta prendido debe eliminar la corriente que esta en la esquina contraria, si esta apagado elimina la corriente de forma normal

                if (_Protoboard.protoboard[i][j]._switch != null && _Protoboard.protoboard[i][j]._switch.posicion1.coordenadax != -1) {
                    // trabajamos con la esquina izquierda, osea la posicion 1
                    // si la posicion 1 tiene corriente entonces borramos la corriente de la columna entera en esa posicion
                    // ciclo
                    Switch _switch = _Protoboard.protoboard[i][j]._switch;

                    _switch.eliminarCorriente(_Protoboard, _switch.posicion1.coordenadax, _switch.posicion1.coordenaday);
                }

                // si encuentra un octoswitch
                // arreglar aca
                if (_Protoboard.protoboard[i][j]._octoSwitch != null && _Protoboard.protoboard[i][j]._octoSwitch.posicion1.coordenadax != -1 && switch_bateria) {
                    OctoSwitch _octoSwitch = _Protoboard.protoboard[i][j]._octoSwitch;
                    if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, switch_bateria);
                    }

                }

                if (_Protoboard.protoboard[i][j]._resistencia != null && _Protoboard.protoboard[i][j]._resistencia.posicion1.coordenadax != -1) {
                    Resistencia _resistencia = _Protoboard.protoboard[i][j]._resistencia;
                    _resistencia.eliminarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday);
                }
                j++;
            }
        }

    }

    int cuenta_leds = 0;

    /*public void pasarCorriente(protoboard _Protoboard, cable _cable) {

        if ((_cable.posicion1.coordenadax == -2 && _cable.posicion1.coordenaday == -2) || (_cable.posicion1.coordenadax == -3 && _cable.posicion1.coordenaday == -3)) {

            int posicion_final_x = _cable.posicion2.coordenadax;
            int posicion_final_y = _cable.posicion2.coordenaday;


            _Protoboard.protoboard[posicion_final_x][posicion_final_y]._cable = _cable;
            _Protoboard.protoboard[posicion_final_x][posicion_final_y].conexion = true;


            if (posicion_final_y > 1 && posicion_final_y < 7) {
                int j = 2;
                while (j < 7) {

                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                    if (_cable.posicion1.coordenadax == -2) {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.procesado = true;
                    j++;
                }j = 2;
                // bucle para buscar elementos
                while (j < 7){
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable != null && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[posicion_final_x][j]._cable.procesado) {
                        pasarCorriente(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._cable);
                    }
                    // si encuentra un ked
                    if (_Protoboard.protoboard[posicion_final_x][j]._led != null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led);
                            cuenta_leds = 0;
                        }

                    } // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch != null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            toggleSwitch(_Protoboard, _switch, _switch.prendido);
                        } else {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            break;
                        }

                    }
                    // si encuentra un octoswitch
                    if (_Protoboard.protoboard[posicion_final_x][j]._octoSwitch != null && _Protoboard.protoboard[posicion_final_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[posicion_final_x][j]._octoSwitch;
                        int pos_x = _cable.posicion2.coordenadax;

                        if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_1.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_2.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_3.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_4.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_5.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_6.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_7.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_8.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        }
                    }
                    // si encuentra una resistencia
                    if (_Protoboard.protoboard[posicion_final_x][j]._resistencia != null && _Protoboard.protoboard[posicion_final_x][j]._resistencia.posicion1.coordenadax != -1) {
                        Resistencia _resistencia = _Protoboard.protoboard[posicion_final_x][j]._resistencia;
                        _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

                    }
                    j++;
                }


            } else if (posicion_final_y <= 1) {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;


                    if (_cable.posicion1.coordenadax == -2) {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }

                    // si encuentra otro cable habria que llamar denuevo a la funcion de pasar corriente
                    if (_Protoboard.protoboard[i][posicion_final_y]._cable != null && _Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax != -1) {
                        // si el cable es distinto al que empezo a recorrer

                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if ((_Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax != _cable.posicion1.coordenadax || _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax != _cable.posicion2.coordenadax)) {
                            int pos_final_x = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }


                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led != null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[i][posicion_final_y]._led);
                            cuenta_leds = 0;
                        }

                    }
                    i++;
                }
            } else if (posicion_final_y > 7 && posicion_final_y < 13) {
                int j = 8;

                while (j < 13) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                    if (_cable.posicion1.coordenadax == -2) {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;}
                    // procesar el cable que esta pasando la corriente
                    _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.procesado = true;

                    j++;
                }j = 8;
                // bucle para buscar elementos
                while (j < 13){
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable != null && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[posicion_final_x][j]._cable.procesado) {
                        pasarCorriente(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._cable);
                    }
                    // si encuentra un ked
                    if (_Protoboard.protoboard[posicion_final_x][j]._led != null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led);
                            cuenta_leds = 0;
                        }

                    } // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch != null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            toggleSwitch(_Protoboard, _switch, _switch.prendido);
                        } else {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            break;
                        }

                    }
                    // si encuentra un octoswitch
                    if (_Protoboard.protoboard[posicion_final_x][j]._octoSwitch != null && _Protoboard.protoboard[posicion_final_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[posicion_final_x][j]._octoSwitch;
                        int pos_x = _cable.posicion2.coordenadax;

                        if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_1.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_2.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_3.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_4.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_5.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_6.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_7.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_8.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        }
                    }
                    // si encuentra una resistencia
                    if (_Protoboard.protoboard[posicion_final_x][j]._resistencia != null && _Protoboard.protoboard[posicion_final_x][j]._resistencia.posicion1.coordenadax != -1) {
                        Resistencia _resistencia = _Protoboard.protoboard[posicion_final_x][j]._resistencia;
                        _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

                    }
                    j++;
                }

            } else {

                int i = 0;
                while (i < 30) {

                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                    if (_cable.posicion1.coordenadax == -2) {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    // si encuentra otro cable habria que llamar denuevo a la funcion de pasar corriente
                    // si encuentra un cable
                    if (_Protoboard.protoboard[i][posicion_final_y]._cable != null && _Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax != -1) {
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (_Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax != _cable.posicion1.coordenadax || _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax != _cable.posicion2.coordenadax) {
                            int pos_final_x = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }

                    if (_cable.posicion1.coordenadax == -2) {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    if (_Protoboard.protoboard[i][posicion_final_y]._led != null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[i][posicion_final_y]._led);
                            cuenta_leds = 0;
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


            if (posicion_final_y > 1 && posicion_final_y < 7) {

                int j = 2;
                while (j < 7) {

                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                    if (_cable.posicion2.coordenadax == -2) {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.procesado = true;
                    j++;
                }j = 2;
                // bucle para buscar elementos
                while (j < 7){
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable != null && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[posicion_final_x][j]._cable.procesado) {
                        pasarCorriente(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._cable);
                    }
                    // si encuentra un ked
                    if (_Protoboard.protoboard[posicion_final_x][j]._led != null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led);
                            cuenta_leds = 0;
                        }

                    } // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch != null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            toggleSwitch(_Protoboard, _switch, _switch.prendido);
                        } else {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            break;
                        }

                    }
                    // si encuentra un octoswitch
                    if (_Protoboard.protoboard[posicion_final_x][j]._octoSwitch != null && _Protoboard.protoboard[posicion_final_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[posicion_final_x][j]._octoSwitch;
                        int pos_x = _cable.posicion2.coordenadax;

                        if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_1.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_2.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_3.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_4.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_5.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_6.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_7.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_8.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        }
                    }
                    // si encuentra una resistencia
                    if (_Protoboard.protoboard[posicion_final_x][j]._resistencia != null && _Protoboard.protoboard[posicion_final_x][j]._resistencia.posicion1.coordenadax != -1) {
                        Resistencia _resistencia = _Protoboard.protoboard[posicion_final_x][j]._resistencia;
                        _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

                    }
                    j++;
                }


            } else if (posicion_final_y <= 1) {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;


                    if (_cable.posicion2.coordenadax == -2) {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    // si encuentra un cable
                    if (_Protoboard.protoboard[i][posicion_final_y]._cable != null && _Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax != -1) {
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (_Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax != _cable.posicion1.coordenadax || _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax != _cable.posicion2.coordenadax) {
                            int pos_final_x = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led != null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[i][posicion_final_y]._led);
                            cuenta_leds = 0;
                        }

                    }

                    i++;
                }
            } else if (posicion_final_y > 7 && posicion_final_y < 13) {
                // bucle para pasar corriente
                int j = 8;
                while (j < 13) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;


                    if (_cable.posicion2.coordenadax == -2) {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                    }
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.procesado = true;
                    j++;
                } j = 8;
                // bucle para buscar elementos
                while (j < 13){
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable != null && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[posicion_final_x][j]._cable.procesado) {
                        pasarCorriente(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._cable);
                    }
                    // si encuentra un ked
                    if (_Protoboard.protoboard[posicion_final_x][j]._led != null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led);
                            cuenta_leds = 0;
                        }

                    } // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch != null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            toggleSwitch(_Protoboard, _switch, _switch.prendido);
                        } else {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            break;
                        }

                    }
                    // si encuentra un octoswitch
                    if (_Protoboard.protoboard[posicion_final_x][j]._octoSwitch != null && _Protoboard.protoboard[posicion_final_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[posicion_final_x][j]._octoSwitch;
                        int pos_x = _cable.posicion2.coordenadax;

                        if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_1.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_2.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_3.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_4.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_5.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_6.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_7.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_8.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_final_x, j);
                            }
                        }
                    }
                    // si encuentra una resistencia
                    if (_Protoboard.protoboard[posicion_final_x][j]._resistencia != null && _Protoboard.protoboard[posicion_final_x][j]._resistencia.posicion1.coordenadax != -1) {
                        Resistencia _resistencia = _Protoboard.protoboard[posicion_final_x][j]._resistencia;
                        _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

                    }
                    j++;
                }
            } else {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;


                    if (_cable.posicion2.coordenadax == -2) {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = true;
                    } else {
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = false;
                    }
                    // si encuentra un cable
                    if (_Protoboard.protoboard[i][posicion_final_y]._cable != null && _Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax != -1) {
                        // si el cable es distinto al que empezo a recorrer
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (_Protoboard.protoboard[i][posicion_final_y]._cable.posicion1.coordenadax != _cable.posicion1.coordenadax || _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax != _cable.posicion2.coordenadax) {
                            int pos_final_x = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenadax;
                            int pos_final_y = _Protoboard.protoboard[i][posicion_final_y]._cable.posicion2.coordenaday;
                            pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_final_x][pos_final_y]._cable);
                        }
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led != null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[i][posicion_final_y]._led);
                            cuenta_leds = 0;
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
                // primero pasa corriente
                while (j < 13) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    _cable.posicion1.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;

                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.procesado = true;
                    _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.procesado = true;

                    j++;
                }
                j = 8;
                while (j < 13) {
                    // si encuentra un cable
                    if (_Protoboard.protoboard[posicion_final_x][j]._cable != null && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[posicion_final_x][j]._cable.procesado) {
                        pasarCorriente(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._cable);
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[posicion_final_x][j]._led != null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds >= 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led);
                            cuenta_leds = 0;
                        }

                    }
                    // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch != null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            toggleSwitch(_Protoboard, _switch, _switch.prendido);
                            break;
                        } else {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            break;
                        }

                    }
                    // si encuentra un octoswitch
                    if (_Protoboard.protoboard[posicion_final_x][j]._octoSwitch != null && _Protoboard.protoboard[posicion_final_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                        System.out.println("encuentra un  octoswitch");
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[posicion_final_x][j]._octoSwitch;
                        int pos_y_switch = _octoSwitch.posicion1.coordenaday + 1;
                        int pos_x = _cable.posicion2.coordenadax;

                        if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_1.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_2.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_3.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_4.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_5.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_6.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_7.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_8.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        }
                    }
                    // si encuentra una resistencia
                    if (_Protoboard.protoboard[posicion_final_x][j]._resistencia != null && _Protoboard.protoboard[posicion_final_x][j]._resistencia.posicion1.coordenadax != -1) {
                        Resistencia _resistencia = _Protoboard.protoboard[posicion_final_x][j]._resistencia;
                        _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

                    }
                    j++;
                }

            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y <= 1)) {

                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    // si encuentra un cable

                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led != null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[i][posicion_final_y]._led);
                            cuenta_leds = 0;
                        }

                    }
                    i++;
                }
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y >= 13)) {
                int i = 0;
                while (i < 30) {
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                    _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    // si encuentra un led
                    if (_Protoboard.protoboard[i][posicion_final_y]._led != null && _Protoboard.protoboard[i][posicion_final_y]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[i][posicion_final_y]._posicion.corriente = true;
                        if (cuenta_leds > 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[i][posicion_final_y]._led);
                            cuenta_leds = 0;
                        }
                    }
                    i++;
                }
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y > 1 && posicion_final_y < 7)) {
                int j = 2;

                while (j < 7) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    _cable.posicion1.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.procesado = true;
                    _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.procesado = true;

                    j++;
                }
                j = 2;
                while (j < 7) {
                    // si encuentra un cable

                    if (_Protoboard.protoboard[posicion_final_x][j]._cable != null && _Protoboard.protoboard[posicion_final_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[posicion_final_x][j]._cable.procesado) {
                        pasarCorriente(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._cable);
                    }
                    // si encuentra un led
                    if (_Protoboard.protoboard[posicion_final_x][j]._led != null && _Protoboard.protoboard[posicion_final_x][j]._led.posicion1.coordenadax != -1) {
                        cuenta_leds++;
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                        if (cuenta_leds >= 1) {
                            cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[posicion_final_x][j]._led);
                            cuenta_leds = 0;
                        }

                    }
                    // si encuentra un switch
                    if (_Protoboard.protoboard[posicion_final_x][j]._switch != null && _Protoboard.protoboard[posicion_final_x][j]._switch.posicion1.coordenadax != -1) {
                        Switch _switch = _Protoboard.protoboard[posicion_final_x][j]._switch;
                        if (_Protoboard.protoboard[posicion_final_x][j]._switch.prendido) {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            toggleSwitch(_Protoboard, _switch, _switch.prendido);

                        } else {
                            _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                            break;
                        }

                    }
                    // si encuentra un octoswitch
                    if (_Protoboard.protoboard[posicion_final_x][j]._octoSwitch != null && _Protoboard.protoboard[posicion_final_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                        OctoSwitch _octoSwitch = _Protoboard.protoboard[posicion_final_x][j]._octoSwitch;
                        int pos_x = _cable.posicion2.coordenadax;

                        if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_1.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_2.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_3.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_4.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_5.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_6.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_7.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                            int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                            if (_octoSwitch.mini_switch_8.encendido) {
                                _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, posicion_inicio_x, posicion_inicio_y);
                            }
                        }
                    }
                    if (_Protoboard.protoboard[posicion_final_x][j]._resistencia != null && _Protoboard.protoboard[posicion_final_x][j]._resistencia.posicion1.coordenadax != -1) {
                        Resistencia _resistencia = _Protoboard.protoboard[posicion_final_x][j]._resistencia;
                        _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

                    }
                    j++;
                }
            }
        }
    }*/
    public void recCorriente(protoboard _Protoboard, Boolean Polaridad, int posx, int posy) {
        if (!((posx == -1 || posy == -1) || (posx == -2 || posy == -2) || (posx == -3 || posy == 3)) && !(_Protoboard.protoboard[posx][posy]._posicion.quemado)) {
            if (_Protoboard.protoboard[posx][posy]._cable.conectado) {
                if (posy <= 1) {
                    posx = 0;
                    while (posx < 30) {
                        if(!_Protoboard.protoboard[posx][posy]._posicion.quemado) {
                        _Protoboard.protoboard[posx][posy]._posicion.corriente = true;
                        _Protoboard.protoboard[posx][posy]._posicion.polaridad = Polaridad;
                        if (_Protoboard.protoboard[posx][posy].conexion) {
                            if(!_Protoboard.protoboard[posx][posy]._cable.procesado && _Protoboard.protoboard[posx][posy]._cable.conectado){
                            _Protoboard.protoboard[posx][posy]._cable.procesado = true;
                            if (!(_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax == posx && _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday == posy) || !(_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax == posx && _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday == posy)) {
                                if (_Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday]._posicion.corriente && _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday]._posicion.corriente && _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday]._posicion.polaridad != _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday]._posicion.polaridad) {
                                    _Protoboard.protoboard[posx][posy]._cable.quemado = true;
                                    System.out.println("se quemo");
                                    recQuemado(_Protoboard, _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday);
                                    recQuemado(_Protoboard, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday);
                                } else
                                    recCorriente(_Protoboard, Polaridad, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday);
                            }}

                        }
                        }posx++;
                    }
                } else if (posy < 7) {
                    posy = 2;
                    while (posy < 7) {
                        if(!_Protoboard.protoboard[posx][posy]._posicion.quemado){
                        _Protoboard.protoboard[posx][posy]._posicion.corriente = true;
                        _Protoboard.protoboard[posx][posy]._posicion.polaridad = Polaridad;
                        if (_Protoboard.protoboard[posx][posy].conexion) {
                            if(!_Protoboard.protoboard[posx][posy]._cable.procesado && _Protoboard.protoboard[posx][posy]._cable.conectado){
                                _Protoboard.protoboard[posx][posy]._cable.procesado = true;
                                if (!(_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax == posx && _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday == posy) || !(_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax == posx && _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday == posy)) {
                                    if (_Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday]._posicion.corriente && _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday]._posicion.corriente && _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday]._posicion.polaridad != _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday]._posicion.polaridad) {
                                        _Protoboard.protoboard[posx][posy]._cable.quemado = true;
                                        System.out.println("se quemo");
                                        recQuemado(_Protoboard, _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday);
                                        recQuemado(_Protoboard, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday);
                                    } else
                                        recCorriente(_Protoboard, Polaridad, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday);
                                }}

                        }}
                        posy++;
                    }
                } else if (posy > 7 && posy < 13) {
                    posy = 8;
                    while (posy < 13) {
                        if(!_Protoboard.protoboard[posx][posy]._posicion.quemado) {
                            _Protoboard.protoboard[posx][posy]._posicion.corriente = true;
                            _Protoboard.protoboard[posx][posy]._posicion.polaridad = Polaridad;
                            if (_Protoboard.protoboard[posx][posy].conexion) {
                                if (!_Protoboard.protoboard[posx][posy]._cable.procesado && _Protoboard.protoboard[posx][posy]._cable.conectado) {
                                    _Protoboard.protoboard[posx][posy]._cable.procesado = true;
                                    if (!(_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax == posx && _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday == posy) || !(_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax == posx && _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday == posy)) {
                                        if (_Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday]._posicion.corriente && _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday]._posicion.corriente && _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday]._posicion.polaridad != _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday]._posicion.polaridad) {
                                            _Protoboard.protoboard[posx][posy]._cable.quemado = true;
                                            System.out.println("se quemo");
                                            recQuemado(_Protoboard, _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday);
                                            recQuemado(_Protoboard, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday);
                                        } else
                                            recCorriente(_Protoboard, Polaridad, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday);
                                    }
                                }

                            }
                        }posy++;
                    }
                } else if (posy >= 13) {
                    posx = 0;
                    while (posx < 30) {
                        if(!_Protoboard.protoboard[posx][posy]._posicion.quemado) {
                            _Protoboard.protoboard[posx][posy]._posicion.corriente = true;
                            _Protoboard.protoboard[posx][posy]._posicion.polaridad = Polaridad;
                            if (_Protoboard.protoboard[posx][posy].conexion) {
                                if (!_Protoboard.protoboard[posx][posy]._cable.procesado && _Protoboard.protoboard[posx][posy]._cable.conectado) {
                                    _Protoboard.protoboard[posx][posy]._cable.procesado = true;
                                    if (!(_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax == posx && _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday == posy) || !(_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax == posx && _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday == posy)) {
                                        if (_Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday]._posicion.corriente && _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday]._posicion.corriente && _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday]._posicion.polaridad != _Protoboard.protoboard[_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax][_Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday]._posicion.polaridad) {
                                            _Protoboard.protoboard[posx][posy]._cable.quemado = true;
                                            System.out.println("se quemo");
                                            recQuemado(_Protoboard, _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion1.coordenaday);
                                            recQuemado(_Protoboard, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday);
                                        } else
                                            recCorriente(_Protoboard, Polaridad, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._cable.posicion2.coordenaday);
                                    }
                                }

                            }
                        }posx++;
                    }

                }
            } else if (_Protoboard.protoboard[posx][posy]._switch.conectado && !_Protoboard.protoboard[posx][posy]._switch.procesado) {
                if ((_Protoboard.protoboard[posx][posy]._switch.posicion1.coordenadax == posx && _Protoboard.protoboard[posx][posy]._switch.posicion1.coordenaday == posy) || (_Protoboard.protoboard[posx][posy]._switch.posicion2.coordenadax == posx && _Protoboard.protoboard[posx][posy]._switch.posicion2.coordenaday == posy)) {
                    if (_Protoboard.protoboard[posx][posy]._switch.prendido) {
                        if(!_Protoboard.protoboard[posx][posy]._posicion.quemado) {
                            _Protoboard.protoboard[posx][posy]._switch.procesado = true;
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion1.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion1.coordenaday);
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion2.coordenaday);
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion3.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion3.coordenaday);
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion4.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion4.coordenaday);
                        }} else {
                        if(!_Protoboard.protoboard[posx][posy]._posicion.quemado) {
                            _Protoboard.protoboard[posx][posy]._switch.procesado = true;
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion1.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion1.coordenaday);
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion2.coordenaday);
                        }}
                } else if ((_Protoboard.protoboard[posx][posy]._switch.posicion3.coordenadax == posx && _Protoboard.protoboard[posx][posy]._switch.posicion3.coordenaday == posy) || (_Protoboard.protoboard[posx][posy]._switch.posicion4.coordenadax == posx && _Protoboard.protoboard[posx][posy]._switch.posicion4.coordenaday == posy)) {
                    if (_Protoboard.protoboard[posx][posy]._switch.prendido) {
                        if(!_Protoboard.protoboard[posx][posy]._posicion.quemado) {
                            _Protoboard.protoboard[posx][posy]._switch.procesado = true;
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion1.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion1.coordenaday);
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion2.coordenaday);
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion3.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion3.coordenaday);
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion4.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion4.coordenaday);
                        }} else {
                        if(!_Protoboard.protoboard[posx][posy]._posicion.quemado) {
                            _Protoboard.protoboard[posx][posy]._switch.procesado = true;
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion1.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion1.coordenaday);
                            recCorriente(_Protoboard, _Protoboard.protoboard[posx][posy]._posicion.polaridad, _Protoboard.protoboard[posx][posy]._switch.posicion2.coordenadax, _Protoboard.protoboard[posx][posy]._switch.posicion2.coordenaday);
                        }}
                }
            }

        }
    }
    public void SacarCorriente(protoboard _Protoboard){
        int i=0;int j = 0;
        while(i<30) {
            while (j < 16){
                _Protoboard.protoboard[i][j]._posicion.corriente = false;
                j++;
            }j = 0;
            i++;
        }
    }
    public void PasarCorriente(protoboard _Protoboard, cable[] cables){
        int marcador = 0;
        int i=0;
        while(marcador < 2 && i < Controlador_Protoboard.countCable){
            if(cables[i].conexionBateria){
            if(cables[i].posicion1.coordenadax == -2 && cables[i].posicion1.coordenaday == -2){
                _Protoboard.protoboard[cables[i].posicion2.coordenadax][cables[i].posicion2.coordenaday]._cable.procesado = true;
                cables[i].procesado = true;
                recCorriente(_Protoboard, cables[i].posicion1.polaridad, cables[i].posicion2.coordenadax, cables[i].posicion2.coordenaday);

                marcador++;
            }else
            if(cables[i].posicion1.coordenadax == -3 && cables[i].posicion1.coordenaday == -3){
                cables[i].procesado = true;
                _Protoboard.protoboard[cables[i].posicion2.coordenadax][cables[i].posicion2.coordenaday]._cable.procesado = true;
                recCorriente(_Protoboard, cables[i].posicion1.polaridad, cables[i].posicion2.coordenadax, cables[i].posicion2.coordenaday);

                marcador++;
            }if(cables[i].posicion2.coordenadax == -2 && cables[i].posicion2.coordenaday == -2){
                    cables[i].procesado = true;
                    _Protoboard.protoboard[cables[i].posicion1.coordenadax][cables[i].posicion1.coordenaday]._cable.procesado = true;
                recCorriente(_Protoboard, cables[i].posicion2.polaridad, cables[i].posicion1.coordenadax, cables[i].posicion1.coordenaday);

                marcador++;
            }else
            if(cables[i].posicion2.coordenadax == -3 && cables[i].posicion2.coordenaday == -3){
                cables[i].procesado = true;
                _Protoboard.protoboard[cables[i].posicion1.coordenadax][cables[i].posicion1.coordenaday]._cable.procesado = true;
                recCorriente(_Protoboard, cables[i].posicion2.polaridad, cables[i].posicion1.coordenadax, cables[i].posicion1.coordenaday);

                marcador++;
            }}
            i++;
            QuemadoCheck(_Protoboard);
        }
    }
    public void resetCables(protoboard _Protoboard, cable[] cables){
        int i=0, j=0;
        while(i<30){
            while(j<15){
                if(_Protoboard.protoboard[i][j]._cable!=null){
                    _Protoboard.protoboard[i][j]._cable.procesado = false;
                    _Protoboard.protoboard[i][j]._switch.procesado = false;
                }
                j++;
            }
            i++;
        }
    i=0;
        while(i<Controlador_Protoboard.countCable){
            cables[i].procesado = false;
            i++;
        }
    }

    public void switchSet(protoboard _Protoboard, int pos_central_x, int pos_central_y, boolean encendido) {
        // inicializar switch
        Switch _switch = new Switch();
        // esquina arriba izq
        _switch.posicion1.coordenadax = pos_central_x - 1;
        _switch.posicion1.coordenaday = pos_central_y - 1;
        // esquina arriba der
        _switch.posicion2.coordenadax = pos_central_x + 1;
        _switch.posicion2.coordenaday = pos_central_y - 1;
        // esquina abajo izq
        _switch.posicion3.coordenadax = pos_central_x - 1;
        _switch.posicion3.coordenaday = pos_central_y + 1;
        // esquina bajo der
        _switch.posicion4.coordenadax = pos_central_x + 1;
        _switch.posicion4.coordenaday = pos_central_y + 1;

        _switch.prendido = encendido;
        _switch.procesado = false;
        _switch.conectado = true;
        _Protoboard.protoboard[pos_central_x + 1][pos_central_y - 1]._switch = _switch;
        _Protoboard.protoboard[pos_central_x + 1][pos_central_y - 1].conexion = true;

        _Protoboard.protoboard[pos_central_x - 1][pos_central_y - 1]._switch = _switch;
        _Protoboard.protoboard[pos_central_x - 1][pos_central_y - 1].conexion = true;

        _Protoboard.protoboard[pos_central_x + 1][pos_central_y + 1]._switch = _switch;
        _Protoboard.protoboard[pos_central_x + 1][pos_central_y + 1].conexion = true;

        _Protoboard.protoboard[pos_central_x - 1][pos_central_y + 1]._switch = _switch;
        _Protoboard.protoboard[pos_central_x - 1][pos_central_y + 1].conexion = true;

        // encender switch en cada posicion (esquina)
        _Protoboard.protoboard[pos_central_x + 1][pos_central_y - 1]._switch.prendido = encendido;
        _Protoboard.protoboard[pos_central_x - 1][pos_central_y - 1]._switch.prendido = encendido;
        _Protoboard.protoboard[pos_central_x + 1][pos_central_y + 1]._switch.prendido = encendido;
        _Protoboard.protoboard[pos_central_x - 1][pos_central_y + 1]._switch.prendido = encendido;

        PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);

    }

    public void toggleSwitch(protoboard _Protoboard, Switch _switch, boolean encendido) {

        int pos_central_x = _switch.posicion1.coordenadax + 1;
        int pos_central_y = _switch.posicion1.coordenaday + 1;

        // encender el switch (todas sus posiciones)
        if (encendido) {
            _Protoboard.protoboard[pos_central_x + 1][pos_central_y - 1]._switch.prendido = encendido;
            _Protoboard.protoboard[pos_central_x - 1][pos_central_y - 1]._switch.prendido = encendido;
            _Protoboard.protoboard[pos_central_x + 1][pos_central_y + 1]._switch.prendido = encendido;
            _Protoboard.protoboard[pos_central_x - 1][pos_central_y + 1]._switch.prendido = encendido;
        } else {
            _Protoboard.protoboard[pos_central_x + 1][pos_central_y - 1]._switch.prendido = false;
            _Protoboard.protoboard[pos_central_x - 1][pos_central_y - 1]._switch.prendido = false;
            _Protoboard.protoboard[pos_central_x + 1][pos_central_y + 1]._switch.prendido = false;
            _Protoboard.protoboard[pos_central_x - 1][pos_central_y + 1]._switch.prendido = false;
        }


        // consultar por las posiciones del switch
        // pos 1 seria la esquina izquierda
        int pos_1_x = _switch.posicion1.coordenadax;
        int pos_1_y = _switch.posicion1.coordenaday;
        int pos_4_x = _switch.posicion4.coordenadax;
        int pos_4_y = _switch.posicion4.coordenaday;
        int pos_2_x = _switch.posicion2.coordenadax;
        int pos_3_y = _switch.posicion3.coordenaday;

        if (pos_1_y == 6) {
            if (!_switch.prendido) {
                boolean encuentra_cable_arriba = false;
                int guarda_col_cable = 0;
                int guarda_fil_cable = 0;
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int k = 2; k < 7; k++) {
                    if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenaday < 2 || _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenaday > 12) || (_Protoboard.protoboard[pos_1_x][k]._cable.posicion2.coordenaday < 2 || _Protoboard.protoboard[pos_1_x][k]._cable.posicion2.coordenaday > 12))) {

                        encuentra_cable_arriba = true;
                        guarda_col_cable = k;
                        guarda_fil_cable = pos_1_x;
                    } else if (_Protoboard.protoboard[pos_2_x][k]._cable != null && _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenaday < 2 || _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenaday > 12) || (_Protoboard.protoboard[pos_2_x][k]._cable.posicion2.coordenaday < 2 || _Protoboard.protoboard[pos_2_x][k]._cable.posicion2.coordenaday > 12))) {

                        encuentra_cable_arriba = true;
                        guarda_col_cable = k;
                        guarda_fil_cable = pos_2_x;
                    }
                }
                if (encuentra_cable_arriba) {
                    for (int i = 8; i < 13; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion2.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion2.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                } else {
                    boolean encuentra_cable_abajo = false;
                    for (int k = 8; k < 13; k++) {
                        if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenaday < 2 || _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenaday > 12) || (_Protoboard.protoboard[pos_1_x][k]._cable.posicion2.coordenaday < 2 || _Protoboard.protoboard[pos_1_x][k]._cable.posicion2.coordenaday > 12))) {
                            guarda_col_cable = k;
                            guarda_fil_cable = pos_1_x;
                            encuentra_cable_abajo = true;
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenaday < 2 || _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenaday > 12) || (_Protoboard.protoboard[pos_2_x][k]._cable.posicion2.coordenaday < 2 || _Protoboard.protoboard[pos_2_x][k]._cable.posicion2.coordenaday > 12))) {
                            guarda_col_cable = k;
                            guarda_fil_cable = pos_4_x;
                            encuentra_cable_abajo = true;
                        }
                    }
                    if (encuentra_cable_abajo) {
                        for (int i = 2; i < 7; i++) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion2.polaridad;
                            _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                            _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion2.polaridad;

                            if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                                Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                                Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                            if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
                                cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                                _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                                eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                            } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                                cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                                _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                                eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                            }
                        }
                    }

                }
            } else {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.corriente || _Protoboard.protoboard[pos_2_x][pos_1_y - 1]._posicion.corriente) {
                    for (int i = 8; i < 13; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday >= 2 && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday <= 12))) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 2 && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 12))) {

                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }
                    }
                    for (int i = 2; i < 7; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday >= 2 && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday <= 12))) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 2 && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 12))) {

                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }
                    }

                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y + 1]._posicion.corriente || _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.corriente) {
                    for (int i = 2; i < 7; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday >= 2 || _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday <= 12) || (_Protoboard.protoboard[pos_1_x][i]._cable.posicion2.coordenaday >= 2 || _Protoboard.protoboard[pos_1_x][i]._cable.posicion2.coordenaday <= 12))) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 2 || _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 12) || (_Protoboard.protoboard[pos_4_x][i]._cable.posicion2.coordenaday >= 2 || _Protoboard.protoboard[pos_4_x][i]._cable.posicion2.coordenaday <= 12))) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }

                    }
                    for (int i = 8; i < 13; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday >= 2 && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday <= 12))) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 2 && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 12))) {

                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }
                    }
                }
            }
        }
        if (pos_1_y < 7 && pos_1_y > 1 && pos_1_y != 6) {
            if (_switch.prendido) {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_4_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = pos_3_y; k < 7; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][k]._led != null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._led != null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][k]._cable;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }
                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_1_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = pos_1_y; k > 1; k--) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][k]._led != null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._led != null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][k]._cable;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }
                    }
                }
            } else {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_4_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 7; k >= pos_3_y; k--) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][k]._led != null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._led != null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][k]._cable;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }

                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_1_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = pos_1_y; k > 1; k--) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][k]._led != null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._led != null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][k]._cable;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }
                    }
                }
            }

        } else if (pos_1_y > 7 && pos_1_y < 13) {
            if (_switch.prendido) {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_4_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 12; k > pos_3_y; k--) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][k]._led != null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._led != null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][k]._cable;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }
                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_1_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 8; k < pos_1_y; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][k]._led != null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._led != null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][k]._cable;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }
                    }
                }
            } else {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_4_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 12; k >= pos_3_y; k--) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][k]._led != null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._led != null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][k]._cable;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }

                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_1_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 8; k <= pos_1_y; k++) {
                        _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][k]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][k]._led != null && _Protoboard.protoboard[pos_1_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._led != null && _Protoboard.protoboard[pos_4_x][k]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][k]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][k]._cable;
                            _Protoboard.protoboard[pos_1_x][k]._posicion.corriente = false;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                        }
                    }
                }
            }
        }

    }

    public void octoSwitchSet(protoboard _Protoboard, int pos_central_x, int pos_central_y) {
        OctoSwitch _octoswitch = new OctoSwitch();
        // esquina arriba izq
        _octoswitch.posicion1.coordenadax = pos_central_x - 3;
        _octoswitch.posicion1.coordenaday = pos_central_y - 1;
        // esquina arriba der
        _octoswitch.posicion2.coordenadax = pos_central_x + 4;
        _octoswitch.posicion2.coordenaday = pos_central_y - 1;
        // esquina abajo izq
        _octoswitch.posicion3.coordenadax = pos_central_x - 3;
        _octoswitch.posicion3.coordenaday = pos_central_y + 1;
        // esquina bajo der
        _octoswitch.posicion4.coordenadax = pos_central_x + 4;
        _octoswitch.posicion4.coordenaday = pos_central_y + 1;

        // rellena los lugares que esta ocupando el octoswitch
        for (int i = pos_central_x - 3; i < pos_central_x + 5; i++) {
            for (int j = pos_central_y - 1; j < pos_central_y + 2; j++) {
                _Protoboard.protoboard[i][j]._octoSwitch = _octoswitch;
                _Protoboard.protoboard[i][j].conexion = true;
            }
        }

        _octoswitch.setMiniSwitch_OctoSwitch(_octoswitch);

    }

    public void resistenciaSet(protoboard _Protoboard, int pos_1_x, int pos_1_y, int pos_2_x, int pos_2_y, int banda_1, int banda_2, int multiplicador, int tolerancia) {
        Resistencia _Resistencia = new Resistencia();
        //Ingresar coordenadas y valor desde protoboard grafico
        _Resistencia.posicion1.coordenadax = pos_1_x;
        _Resistencia.posicion1.coordenaday = pos_1_y;
        _Resistencia.posicion2.coordenadax = pos_2_x;
        _Resistencia.posicion2.coordenaday = pos_2_y;
        _Resistencia.banda_1 = banda_1;
        _Resistencia.banda_2 = banda_2;
        _Resistencia.multiplicador = multiplicador;
        _Resistencia.tolerancia = tolerancia;

        _Protoboard.protoboard[pos_1_x][pos_1_y]._resistencia = _Resistencia;
        _Protoboard.protoboard[pos_2_x][pos_2_y]._resistencia = _Resistencia;
        _Protoboard.protoboard[pos_1_x][pos_1_y].conexion = true;
        _Protoboard.protoboard[pos_2_x][pos_2_y].conexion = true;

        // si una posicion tiene corriente pasarla a la otra
        _Resistencia.pasarCorriente(_Protoboard, pos_1_x, pos_1_y, pos_2_x, pos_2_y, _Resistencia);
        _Resistencia.chequearDireccion(_Resistencia, _Protoboard);

        // condiciones para ver las polaridades de las posiciones donde se esta poniendo la resistencia.
    }

    public void recQuemado(protoboard _Protoboard, int pox, int poy){
        if(poy<=1){
            pox = 0;
            while(pox < 30){
                _Protoboard.protoboard[pox][poy]._posicion.quemado = true;
                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                pox++;
            }
        } else if (poy<7) {
            poy = 2;
            while(poy < 6){
                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                _Protoboard.protoboard[pox][poy]._posicion.quemado = true;
                poy++;
            }
        } else if (poy>7 && poy<13) {
            poy=8;
            while(poy < 13){
                _Protoboard.protoboard[pox][poy]._posicion.quemado = true;
                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                poy++;
            }
        } else if (poy>=13) {
            pox = 0;
            while(pox < 30){
                _Protoboard.protoboard[pox][poy]._posicion.quemado = true;
                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                pox++;
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
                _Cable.conectado = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday].conexion = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.corriente = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._cable = _Cable;

                Controlador_Protoboard.cablelist[Controlador_Protoboard.countCable] = _Cable;
                Controlador_Protoboard.countCable++;
                PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                resetCables(_Protoboard, Controlador_Protoboard.cablelist);
            }

            if (_Cable.posicion2.coordenadax == -3 && _Cable.posicion2.coordenaday == -3) {
                _Cable.posicion1.corriente = true;
                _Cable.posicion2.corriente = true;
                _Cable.posicion1.polaridad = false;
                _Cable.posicion2.polaridad = false;
                _Cable.conectado = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday].conexion = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.corriente = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._cable = _Cable;
                Controlador_Protoboard.cablelist[Controlador_Protoboard.countCable] = _Cable;
                Controlador_Protoboard.countCable++;
                PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                resetCables(_Protoboard, Controlador_Protoboard.cablelist);
            }
            if (_Cable.posicion1.coordenadax == -3 && _Cable.posicion1.coordenaday == -3) {
                _Cable.posicion1.corriente = true;
                _Cable.posicion2.corriente = true;
                _Cable.posicion1.polaridad = false;
                _Cable.posicion2.polaridad = false;
                _Cable.conectado = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday].conexion = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.corriente = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._cable = _Cable;
                Controlador_Protoboard.cablelist[Controlador_Protoboard.countCable] = _Cable;
                Controlador_Protoboard.countCable++;
                PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                resetCables(_Protoboard, Controlador_Protoboard.cablelist);
            }

            if (_Cable.posicion2.coordenadax == -2 && _Cable.posicion2.coordenaday == -2) {
                _Cable.posicion1.corriente = true;
                _Cable.posicion2.corriente = true;
                _Cable.posicion1.polaridad = true;
                _Cable.posicion2.polaridad = true;
                _Cable.conectado = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday].conexion = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.corriente = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.polaridad = true;
                _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._cable = _Cable;
                Controlador_Protoboard.cablelist[Controlador_Protoboard.countCable] = _Cable;
                Controlador_Protoboard.countCable++;
                PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
                resetCables(_Protoboard, Controlador_Protoboard.cablelist);
            }

        } else {
            _Cable.conectado = true;
            _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._cable = _Cable;
            _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._cable = _Cable;
            _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday].conexion = true;
            _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday].conexion = true;
            Controlador_Protoboard.cablelist[Controlador_Protoboard.countCable] = _Cable;
            Controlador_Protoboard.countCable++;
            PasarCorriente(_Protoboard, Controlador_Protoboard.cablelist);
            resetCables(_Protoboard, Controlador_Protoboard.cablelist);


        }
    }
    public void QuemadoCheck(protoboard _Protoboard){
            int x =0,y = 0;
            while(x<30){
                while(y<15){
                    if(_Protoboard.protoboard[x][y]._posicion.quemado){
                        _Protoboard.protoboard[x][y]._posicion.corriente = false;
                    }
                    y++;
                }
                x++;
                y=0;
            }
    }
}


