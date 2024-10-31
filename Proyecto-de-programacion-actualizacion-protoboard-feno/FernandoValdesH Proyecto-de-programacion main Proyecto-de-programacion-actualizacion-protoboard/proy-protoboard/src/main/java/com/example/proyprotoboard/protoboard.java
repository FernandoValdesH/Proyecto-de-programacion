package com.example.proyprotoboard;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jdk.swing.interop.SwingInterOpUtils;

public class protoboard {

    private static protoboard instance;

    public logicalProtoboard[][] protoboard;


    private protoboard(logicalProtoboard[][] protoboard) {
        this.protoboard = protoboard;
        // rellenar matriz

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 17; j++) {
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
                for (int j = 0; j < 17; j++) {
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
                for (int j = 0; j < 17; j++) {
                    if (_Protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1) {
                        if (_Protoboard.protoboard[i][j]._cable.posicion1.coordenadax == -3 && _Protoboard.protoboard[i][j]._cable.posicion1.coordenaday == -3) {
                            pos_x_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenadax;
                            pos_y_2 = _Protoboard.protoboard[i][j]._cable.posicion2.coordenaday;
                        }
                    }
                }
            }
        }

        if (_Protoboard.protoboard[pos_x_2][pos_y_2]._cable!=null && _Protoboard.protoboard[pos_x_2][pos_y_2]._cable.posicion1.coordenadax != -1) {
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
                // resetear la conexion
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday].conexion = false;
                _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday].conexion = false;
            } else {
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._led = null;
                _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._led = null;
                // resetear la conexion
                _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday].conexion = false;
                _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday].conexion = false;
            }
        } else if (_Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._switch.posicion1.coordenadax != -1) {
            Switch _switch = _Protoboard.protoboard[pos_x][pos_y]._switch;

            // si el switch esta prendido, se debe eliminar la corriente de la columna contraria

            int guarda_x_cable = 0;
            boolean encuentra_un_cable = false;
            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday]._posicion.corriente || _Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion2.coordenaday]._posicion.corriente || _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion4.coordenaday]._posicion.corriente || _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion3.coordenaday]._posicion.corriente) {
                _switch.eliminarCorriente_AlEliminarSwitch(_Protoboard, _switch, guarda_x_cable, encuentra_un_cable, pos_y);
            }

            _Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion2.coordenadax][_switch.posicion2.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion3.coordenadax][_switch.posicion3.coordenaday]._switch = null;
            _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion4.coordenaday]._switch = null;
            // resetear la conexion
            _Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday].conexion = false;
            _Protoboard.protoboard[_switch.posicion2.coordenadax][_switch.posicion2.coordenaday].conexion = false;
            _Protoboard.protoboard[_switch.posicion3.coordenadax][_switch.posicion3.coordenaday].conexion = false;
            _Protoboard.protoboard[_switch.posicion4.coordenadax][_switch.posicion4.coordenaday].conexion = false;

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
                        for (int l = 3; l < 8; l++) {
                            _Protoboard.protoboard[k][l]._posicion.corriente = false;
                        }

                    } else if (_Protoboard.protoboard[k][pos_y_switch + 1]._posicion.corriente) {
                        for (int l = 9; l < 14; l++) {
                            _Protoboard.protoboard[k][l]._posicion.corriente = false;
                        }
                    }

                }
            }
            for (int i = pos_1_x; i < pos_2_x + 1; i++) {
                for (int j = pos_1_y; j < 3; j++) {
                    _Protoboard.protoboard[i][j]._octoSwitch = null;
                    // resetear la conexion
                    _Protoboard.protoboard[i][j].conexion = false;
                }
            }

        } else if (_Protoboard.protoboard[pos_x][pos_y]._resistencia!=null && _Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._resistencia.posicion1.coordenadax != -1) {
            Resistencia _resistencia = _Protoboard.protoboard[pos_x][pos_y]._resistencia;
            if (_resistencia.posicion1.coordenadax == pos_x && _resistencia.posicion1.coordenaday == pos_y) {
                _Protoboard.protoboard[_resistencia.posicion2.coordenadax][_resistencia.posicion2.coordenaday]._resistencia = null;
                _Protoboard.protoboard[_resistencia.posicion1.coordenadax][_resistencia.posicion1.coordenaday]._resistencia = null;
                // resetear la conexion
                _Protoboard.protoboard[_resistencia.posicion1.coordenadax][_resistencia.posicion1.coordenaday].conexion = false;
                _Protoboard.protoboard[_resistencia.posicion2.coordenadax][_resistencia.posicion2.coordenaday].conexion = false;
            } else {
                _Protoboard.protoboard[_resistencia.posicion1.coordenadax][_resistencia.posicion1.coordenaday]._resistencia = null;
                _Protoboard.protoboard[_resistencia.posicion2.coordenadax][_resistencia.posicion2.coordenaday]._resistencia = null;
                // resetear la conexion
                _Protoboard.protoboard[_resistencia.posicion1.coordenadax][_resistencia.posicion1.coordenaday].conexion = false;
                _Protoboard.protoboard[_resistencia.posicion2.coordenadax][_resistencia.posicion2.coordenaday].conexion = false;
            }
            _resistencia.eliminarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday);
        } else if (_Protoboard.protoboard[pos_x][pos_y]._chip!=null && _Protoboard.protoboard[pos_x][pos_y].conexion && _Protoboard.protoboard[pos_x][pos_y]._chip.posicion1.coordenadax != -1){
            Chip _chip = _Protoboard.protoboard[pos_x][pos_y]._chip;
            if (_chip.posicion1.coordenadax == pos_x && _chip.posicion1.coordenaday == pos_y) {
                _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_7.coordenadax][_chip.pos_7.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_5.coordenadax][_chip.pos_5.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_4.coordenadax][_chip.pos_4.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_3.coordenadax][_chip.pos_3.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_9.coordenadax][_chip.pos_9.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_10.coordenadax][_chip.pos_10.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_11.coordenadax][_chip.pos_11.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_13.coordenadax][_chip.pos_13.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.pos_14.coordenadax][_chip.pos_14.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._chip = null;
                _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._chip = null;
            }
            // resetear la conexion
            for (int i = _chip.posicion1.coordenadax; i < _chip.pos_7.coordenadax + 1; i++) {
                for (int j = _chip.posicion1.coordenaday; j < _chip.pos_8.coordenaday + 1; j++) {
                    _Protoboard.protoboard[i][j].conexion = false;
                }
            }
        }
    }

    public void eliminarCorriente(protoboard _Protoboard, int pos_x, int pos_y, boolean switch_bateria) {

        // este metodo recibe la posicion donde se elimino el elemento

        // al eliminar un elemento, se debe eliminar un cable, led, switch, del protoboard y eliminar la corriente de esa fila/columna
        if (switch_bateria) {
            if (pos_x == -3 || pos_x == -2) {
                System.out.println("entra negativo");
                for (int i = 0; i < 30; i++) {
                    for (int k = 0; k < 17; k++) {
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

        if (pos_y <= 1 || pos_y > 13) {
            int j = pos_y;
            int i = 0;
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
                    eliminarCorriente(_Protoboard, pos_final_x, pos_final_y, switch_bateria);
                }

                i++;
            }
        } else if (pos_y > 2 && pos_y < 8) {
            int j = 3;
            int i = pos_x;
            while (j < 8) {

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
        } else if (pos_y > 8) {
            int j = 9;
            int i = pos_x;
            while (j < 14) {

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

    public void encontrarElementosColumna(protoboard _Protoboard, int pos_x, int pos_y){
        if (pos_y < 8){
            int j = 3;
            while (j < 8){
                // si encuentra un cable
                if (_Protoboard.protoboard[pos_x][j]._cable != null && _Protoboard.protoboard[pos_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_x][j]._cable.procesado) {
                    pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_x][j]._cable);
                }
                // si encuentra un ked
                if (_Protoboard.protoboard[pos_x][j]._led != null && _Protoboard.protoboard[pos_x][j]._led.posicion1.coordenadax != -1) {
                    cuenta_leds++;
                    _Protoboard.protoboard[pos_x][j]._posicion.corriente = true;
                    if (cuenta_leds > 1) {
                        cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_x][j]._led);
                        cuenta_leds = 0;
                    }

                } // si encuentra un switch
                if (_Protoboard.protoboard[pos_x][j]._switch != null && _Protoboard.protoboard[pos_x][j]._switch.posicion1.coordenadax != -1) {
                    Switch _switch = _Protoboard.protoboard[pos_x][j]._switch;
                    if (_Protoboard.protoboard[pos_x][j]._switch.prendido) {
                        _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                        toggleSwitch(_Protoboard, _switch, _switch.prendido);
                    } else {
                        _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                        break;
                    }

                }
                // si encuentra un octoswitch
                if (_Protoboard.protoboard[pos_x][j]._octoSwitch != null && _Protoboard.protoboard[pos_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                    OctoSwitch _octoSwitch = _Protoboard.protoboard[pos_x][j]._octoSwitch;
                    int pos_x_fin = _Protoboard.protoboard[pos_x][pos_y]._cable.posicion2.coordenadax;

                    if (pos_x_fin == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_1.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_2.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_3.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_4.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_5.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_6.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_7.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_8.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    }
                }
                // si encuentra una resistencia
                if (_Protoboard.protoboard[pos_x][j]._resistencia != null && _Protoboard.protoboard[pos_x][j]._resistencia.posicion1.coordenadax != -1) {
                    Resistencia _resistencia = _Protoboard.protoboard[pos_x][j]._resistencia;
                    _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

                }
                j++;
            }
        } else if (pos_y>8){
            int j = 9;
            while (j < 14){
                // si encuentra un cable
                if (_Protoboard.protoboard[pos_x][j]._cable != null && _Protoboard.protoboard[pos_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_x][j]._cable.procesado) {
                    pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_x][j]._cable);
                }
                // si encuentra un ked
                if (_Protoboard.protoboard[pos_x][j]._led != null && _Protoboard.protoboard[pos_x][j]._led.posicion1.coordenadax != -1) {
                    cuenta_leds++;
                    _Protoboard.protoboard[pos_x][j]._posicion.corriente = true;
                    if (cuenta_leds > 1) {
                        cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_x][j]._led);
                        cuenta_leds = 0;
                    }

                } // si encuentra un switch
                if (_Protoboard.protoboard[pos_x][j]._switch != null && _Protoboard.protoboard[pos_x][j]._switch.posicion1.coordenadax != -1) {
                    Switch _switch = _Protoboard.protoboard[pos_x][j]._switch;
                    if (_Protoboard.protoboard[pos_x][j]._switch.prendido) {
                        _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                        toggleSwitch(_Protoboard, _switch, _switch.prendido);
                    } else {
                        _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                        break;
                    }

                }
                // si encuentra un octoswitch
                if (_Protoboard.protoboard[pos_x][j]._octoSwitch != null && _Protoboard.protoboard[pos_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                    OctoSwitch _octoSwitch = _Protoboard.protoboard[pos_x][j]._octoSwitch;
                    int pos_x_fin = _Protoboard.protoboard[pos_x][pos_y]._cable.posicion2.coordenadax;

                    if (pos_x_fin == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_1.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_2.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_3.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_4.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_5.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_6.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_7.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    } else if (pos_x_fin == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                        if (_octoSwitch.mini_switch_8.encendido) {
                            _octoSwitch.pasarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, j, pos_x, j);
                        }
                    }
                }
                // si encuentra una resistencia
                if (_Protoboard.protoboard[pos_x][j]._resistencia != null && _Protoboard.protoboard[pos_x][j]._resistencia.posicion1.coordenadax != -1) {
                    Resistencia _resistencia = _Protoboard.protoboard[pos_x][j]._resistencia;
                    _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

                }
                j++;
            }
        }

    }
    public void pasarCorriente(protoboard _Protoboard, cable _cable) {

        if ((_cable.posicion1.coordenadax == -2 && _cable.posicion1.coordenaday == -2) || (_cable.posicion1.coordenadax == -3 && _cable.posicion1.coordenaday == -3)) {

            int posicion_final_x = _cable.posicion2.coordenadax;
            int posicion_final_y = _cable.posicion2.coordenaday;


            _Protoboard.protoboard[posicion_final_x][posicion_final_y]._cable = _cable;
            _Protoboard.protoboard[posicion_final_x][posicion_final_y].conexion = true;


            if (posicion_final_y > 2 && posicion_final_y < 8) {
                int j = 3;
                if (!_Protoboard.protoboard[posicion_final_x][j]._posicion.quemado){
                    while (j < 8) {

                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                        if (_cable.posicion1.coordenadax == -2) {
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                        } else {
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                        }
                        _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.procesado = true;
                        j++;
                    }j = 3;
                    // bucle para buscar elementos
                    encontrarElementosColumna(_Protoboard, posicion_final_x, posicion_final_y);
                }

            } else if (posicion_final_y <= 1) {

                int i = 0;
                if (!_Protoboard.protoboard[i][posicion_final_y]._posicion.quemado){
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
                }

            } else if (posicion_final_y > 8 && posicion_final_y < 14) {
                int j = 9;
                if (!_Protoboard.protoboard[posicion_final_x][j]._posicion.quemado){
                    while (j < 14) {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                        if (_cable.posicion1.coordenadax == -2) {
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                        } else {
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;}
                        // procesar el cable que esta pasando la corriente
                        _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.procesado = true;

                        j++;
                    }j = 9;
                    // bucle para buscar elementos
                    encontrarElementosColumna(_Protoboard, posicion_final_x, posicion_final_y);
                }


            } else {

                int i = 0;
                if (!_Protoboard.protoboard[i][posicion_final_y]._posicion.quemado){
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


            }

        } else if ((_cable.posicion2.coordenadax == -2 && _cable.posicion2.coordenaday == -2) || (_cable.posicion2.coordenadax == -3 && _cable.posicion2.coordenaday == -3)) {
            int posicion_final_x = _cable.posicion1.coordenadax;
            int posicion_final_y = _cable.posicion1.coordenaday;


            _Protoboard.protoboard[posicion_final_x][posicion_final_y]._cable = _cable;
            _Protoboard.protoboard[posicion_final_x][posicion_final_y].conexion = true;


            if (posicion_final_y > 2 && posicion_final_y < 8) {

                int j = 3;
                if (!_Protoboard.protoboard[posicion_final_x][j]._posicion.quemado){
                    while (j < 8) {

                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                        if (_cable.posicion2.coordenadax == -2) {
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                        } else {
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                        }
                        _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.procesado = true;
                        j++;
                    }j = 3;
                    // bucle para buscar elementos
                    encontrarElementosColumna(_Protoboard, posicion_final_x, posicion_final_y);
                }



            } else if (posicion_final_y <= 1) {

                int i = 0;
                if (!_Protoboard.protoboard[i][posicion_final_y]._posicion.quemado){
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

            } else if (posicion_final_y > 8 && posicion_final_y < 14) {
                // bucle para pasar corriente
                int j = 9;
                if (!_Protoboard.protoboard[posicion_final_x][j]._posicion.quemado){
                    while (j < 14) {
                        _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;

                        if (_cable.posicion2.coordenadax == -2) {
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = true;
                        } else {
                            _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = false;
                        }
                        _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.procesado = true;
                        j++;
                    }j = 9;
                    // bucle para buscar elementos
                    encontrarElementosColumna(_Protoboard, posicion_final_x, posicion_final_y);
                }
            } else {

                int i = 0;
                if (!_Protoboard.protoboard[i][posicion_final_y]._posicion.quemado) {
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
            }

        } else {
            int posicion_inicio_x = _cable.posicion1.coordenadax;
            int posicion_inicio_y = _cable.posicion1.coordenaday;

            int posicion_final_x = _cable.posicion2.coordenadax;
            int posicion_final_y = _cable.posicion2.coordenaday;

            _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y].conexion = true;
            _Protoboard.protoboard[posicion_final_x][posicion_final_y].conexion = true;

            if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y > 8 && posicion_final_y < 14)) {
                int j = 9;
                if (!_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.quemado){
                // primero pasa corriente
                _cable.posicion1.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                _cable.posicion1.corriente=true;
                _cable.posicion2.corriente=true;
                _cable.posicion2.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                while (j < 14) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;


                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.procesado = true;
                    _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.procesado = true;

                    j++;
                }
            encontrarElementosColumna(_Protoboard, posicion_final_x, posicion_final_y);}

            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y <= 1)) {

                int i = 0;
                if (!_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.quemado){
                _cable.posicion1.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                _cable.posicion2.polaridad = _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad;
                _cable.posicion1.corriente=true;
                _cable.posicion2.corriente=true;
                if (!_Protoboard.protoboard[i][posicion_final_y]._posicion.corriente){
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
                }}}
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y >= 13)) {
                int i = 0;
                if (!_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.quemado){
                _cable.posicion1.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                _cable.posicion2.polaridad = _Protoboard.protoboard[i][posicion_final_y]._posicion.polaridad;
                _cable.posicion1.corriente=true;
                _cable.posicion2.corriente=true;
                if (!_Protoboard.protoboard[i][posicion_final_y]._posicion.corriente){
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
                }}}
            } else if (_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.corriente && (posicion_final_y > 2 && posicion_final_y < 8)) {
                int j = 3;
                if (!_Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.quemado){
                _cable.posicion1.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                _cable.posicion2.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                _cable.posicion1.corriente=true;
                _cable.posicion2.corriente=true;
                while (j < 8) {
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.corriente = true;
                    _Protoboard.protoboard[posicion_final_x][j]._posicion.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    _cable.posicion1.polaridad = _Protoboard.protoboard[posicion_inicio_x][posicion_inicio_y]._posicion.polaridad;
                    _Protoboard.protoboard[_cable.posicion1.coordenadax][_cable.posicion1.coordenaday]._cable.procesado = true;
                    _Protoboard.protoboard[_cable.posicion2.coordenadax][_cable.posicion2.coordenaday]._cable.procesado = true;

                    j++;
                }
                encontrarElementosColumna(_Protoboard, posicion_final_x, posicion_final_y);
            }}
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

        _switch.pasarCorriente(_Protoboard, pos_central_x, pos_central_y);

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

        if (pos_1_y == 7) {
            if (!_switch.prendido) {
                boolean encuentra_cable_arriba = false;
                int guarda_col_cable = 0;
                int guarda_fil_cable = 0;
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int k = 3; k < 8; k++) {
                    if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenaday < 2 || _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenaday > 13) || (_Protoboard.protoboard[pos_1_x][k]._cable.posicion2.coordenaday < 2 || _Protoboard.protoboard[pos_1_x][k]._cable.posicion2.coordenaday > 13))) {

                        encuentra_cable_arriba = true;
                        guarda_col_cable = k;
                        guarda_fil_cable = pos_1_x;
                    } else if (_Protoboard.protoboard[pos_2_x][k]._cable != null && _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenaday < 2 || _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenaday > 13) || (_Protoboard.protoboard[pos_2_x][k]._cable.posicion2.coordenaday < 2 || _Protoboard.protoboard[pos_2_x][k]._cable.posicion2.coordenaday > 13))) {

                        encuentra_cable_arriba = true;
                        guarda_col_cable = k;
                        guarda_fil_cable = pos_2_x;
                    }
                }
                if (encuentra_cable_arriba) {
                    for (int i = 9; i < 14; i++) {
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
                    for (int k = 9; k < 14; k++) {
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
                        for (int i = 3; i < 8; i++) {
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
                    for (int i = 9; i < 14; i++) {
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
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday >= 3 && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday <= 13))) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 3 && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 13))) {

                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }
                    }
                    for (int i = 2; i < 8; i++) {
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
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday >= 3 && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday <= 13))) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 3 && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 13))) {

                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }
                    }

                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y + 1]._posicion.corriente || _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.corriente) {
                    for (int i = 3; i < 8; i++) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 2 || _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 12) || (_Protoboard.protoboard[pos_4_x][i]._cable.posicion2.coordenaday >= 2 || _Protoboard.protoboard[pos_4_x][i]._cable.posicion2.coordenaday <= 12))) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }

                    }
                    for (int i = 9; i < 14; i++) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 2 && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 12))) {

                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }
                    }
                }
            }
        }
        if (pos_1_y < 8 && pos_1_y > 2 && pos_1_y != 7) {
            if (_switch.prendido) {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_4_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = pos_3_y; k < 8; k++) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }
                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_1_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = pos_1_y; k > 2; k--) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }
                    }
                }
            } else {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_4_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 9; k >= pos_3_y; k--) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }

                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_1_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = pos_1_y; k > 2; k--) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }
                    }
                }
            }

        } else if (pos_1_y > 8 && pos_1_y < 14) {
            if (_switch.prendido) {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_4_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 13; k > pos_3_y; k--) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }
                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_1_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 9; k < pos_1_y; k++) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }
                    }
                }
            } else {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_4_x][pos_1_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 13; k >= pos_3_y; k--) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = true;
                            pasarCorriente(_Protoboard, _cable);
                        }

                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1 || _Protoboard.protoboard[pos_1_x][pos_4_y]._posicion.corriente && _Protoboard.protoboard[pos_1_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1) {
                    for (int k = 9; k <= pos_1_y; k++) {
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
                            pasarCorriente(_Protoboard, _cable);
                        } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_4_x][k]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][k]._cable;
                            _Protoboard.protoboard[pos_4_x][k]._posicion.corriente = false;
                            pasarCorriente(_Protoboard, _cable);
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

    public void chipSet(protoboard _Protoboard, int pos_x, int pos_y, String tipo_chip){
        Chip _Chip = new Chip();
        _Chip.tipo_chip = tipo_chip;
        //Ingresar coordenadas desde protoboard grafico
        // asignar coordenada x e y a todas las posiciones del chip
        _Chip.posicion1.coordenadax = pos_x;
        _Chip.posicion1.coordenaday = pos_y;

        _Chip.posicion2.coordenadax = pos_x + 1;
        _Chip.posicion2.coordenaday = pos_y;

        _Chip.pos_3.coordenadax = pos_x +2;
        _Chip.pos_3.coordenaday = pos_y;

        _Chip.pos_4.coordenadax = pos_x + 3;
        _Chip.pos_4.coordenaday = pos_y ;

        _Chip.pos_5.coordenadax = pos_x + 4;
        _Chip.pos_5.coordenaday = pos_y;

        _Chip.pos_6.coordenadax = pos_x+5;
        _Chip.pos_6.coordenaday = pos_y;

        _Chip.pos_7.coordenadax = pos_x+6;
        _Chip.pos_7.coordenaday = pos_y;

        _Chip.pos_8.coordenadax = pos_x;
        _Chip.pos_8.coordenaday = pos_y+2;

        _Chip.pos_9.coordenadax = pos_x+1;
        _Chip.pos_9.coordenaday = pos_y+2;

        _Chip.pos_10.coordenadax = pos_x+2;
        _Chip.pos_10.coordenaday = pos_y+2;

        _Chip.pos_11.coordenadax = pos_x+3;
        _Chip.pos_11.coordenaday = pos_y+2;

        _Chip.pos_12.coordenadax = pos_x+4;
        _Chip.pos_12.coordenaday = pos_y+2;

        _Chip.pos_13.coordenadax = pos_x+5;
        _Chip.pos_13.coordenaday = pos_y+2;

        _Chip.pos_14.coordenadax = pos_x+6;
        _Chip.pos_14.coordenaday = pos_y+2;


        _Protoboard.protoboard[pos_x][pos_y]._chip = _Chip;
        _Protoboard.protoboard[pos_x][pos_y].conexion = true;

        // rellenar los espacios que esta ocupando el chip
        for (int i = pos_x; i < pos_x + 8; i++) {
            for (int j = pos_y; j < pos_y + 3; j++) {
                _Protoboard.protoboard[i][j]._chip = _Chip;
                _Protoboard.protoboard[i][j].conexion = true;
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
            System.out.println("entra aca");
            _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._cable = _Cable;
            _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._cable = _Cable;
            _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday].conexion = true;
            _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday].conexion = true;
            pasarCorriente(_Protoboard, _Cable);
            _Cable.posicion1.polaridad = _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.polaridad;
            _Cable.posicion2.polaridad = _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.polaridad;

                if (_Cable.posicion1.polaridad != _Cable.posicion2.polaridad && _Cable.posicion1.corriente && _Cable.posicion2.corriente) {
                    _Cable.quemado = true;
                    System.out.println("Has quemado el cable");
                    recQuemado(_Protoboard, _Cable.posicion1.coordenadax, _Cable.posicion1.coordenaday);
                    recQuemado(_Protoboard, _Cable.posicion2.coordenadax, _Cable.posicion2.coordenaday);
                }
                //while("recorrer corriente basado en posicion, si es 0-1-12-13, significa que es un bus y se requiere recorrer horizontalmente, si es entre 2-6 se considera vertical superior y si es 7-11, vertical inferior, se debe realizar esta verificacion para cada uno de los cables")

            System.out.println("cable conectado en las posiciones: " + _Cable.posicion1.coordenadax + " " + _Cable.posicion1.coordenaday + " y " + _Cable.posicion2.coordenadax + " " + _Cable.posicion2.coordenaday);
            System.out.println("polaridad del cable: " + _Cable.posicion1.polaridad + " " + _Cable.posicion2.polaridad);
        }
        QuemadoCheck(_Protoboard);
    }
    public void recQuemado(protoboard _Protoboard, int pox, int poy){
        if(poy<=1){
            pox = 0;
            while(pox < 30){

                _Protoboard.protoboard[pox][poy]._posicion.quemado = true;
                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                _Protoboard.protoboard[pox][poy]._posicion.polaridad = false;
                pox++;
            }
        } else if (poy>2 && poy<8) {
            poy = 3;
            while(poy < 8){
//                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                _Protoboard.protoboard[pox][poy]._posicion.quemado = true;
                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                _Protoboard.protoboard[pox][poy]._posicion.polaridad = false;
                poy++;
            }
        } else if (poy>8 && poy<14) {
            poy=9;
            while(poy < 14){
//                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                _Protoboard.protoboard[pox][poy]._posicion.quemado = true;
                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                _Protoboard.protoboard[pox][poy]._posicion.polaridad = false;
                poy++;
            }
        } else if (poy>14) {
            pox = 0;
            while(pox < 30){
//                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                _Protoboard.protoboard[pox][poy]._posicion.quemado = true;
                _Protoboard.protoboard[pox][poy]._posicion.corriente = false;
                _Protoboard.protoboard[pox][poy]._posicion.polaridad = false;
                pox++;
            }

        }
    }
    public void QuemadoCheck(protoboard _Protoboard){
        int x =0,y = 0;
        while(x<30){
            while(y<17){
                if(_Protoboard.protoboard[x][y]._posicion.quemado){
                    _Protoboard.protoboard[x][y]._posicion.corriente = false;
                    _Protoboard.protoboard[x][y]._posicion.polaridad = false;
                }
                y++;
            }
            x++;
            y=0;
        }

    }} //else"considerar el caso en donde ninguna de las conexiones del cable posean conexion"
//if("conexion a bateria cable == true" -> cambiar corriente cable = true, establecer polaridad) Check \(~u~)/
//if("luego de conectar cable, revisar donde se conecta y cambiar lugares adyacentes") Check \(`w`)/
//Considerar que en cada momento en el que se conecte un cable, se debera realizar el cambio a todo el protoboard, pero no es necesario realizar el cambio en todo el protoboard al mismo tiempo, solo en la zona existente
//Considerar posible fallo al conectar en 2 espacios con polaridades contrarias



