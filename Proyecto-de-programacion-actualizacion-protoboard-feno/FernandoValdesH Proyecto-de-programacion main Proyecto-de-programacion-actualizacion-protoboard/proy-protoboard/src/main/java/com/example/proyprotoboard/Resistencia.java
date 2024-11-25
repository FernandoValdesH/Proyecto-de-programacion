package com.example.proyprotoboard;

public class Resistencia extends Indicador {
    boolean direccion; // se considera la direccion como polaridad
    int banda_1;
    int banda_2;
    int multiplicador;
    int tolerancia;

    public Resistencia() {
        super();
        this.direccion = false;
        this.banda_1 = -1;
        this.banda_2 = -1;
        this.multiplicador = -1;
        this.tolerancia = -1;
    }
    public void encontrarElementosColumna(protoboard _Protoboard, int pos_x, int pos_y) {
        if (pos_y < 8) {
            int j = 3;
            while (j < 8) {
                // si encuentra un cable
                if (_Protoboard.protoboard[pos_x][j]._cable != null && _Protoboard.protoboard[pos_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_x][j]._cable.procesado) {
                    _Protoboard.pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_x][j]._cable);
                }
                // si encuentra un ked
                if (_Protoboard.protoboard[pos_x][j]._led != null && _Protoboard.protoboard[pos_x][j]._led.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[pos_x][j]._posicion.corriente = true;
                    _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_x][j]._led);



                } // si encuentra un switch
                if (_Protoboard.protoboard[pos_x][j]._switch != null && _Protoboard.protoboard[pos_x][j]._switch.posicion1.coordenadax != -1) {
                    Switch _switch = _Protoboard.protoboard[pos_x][j]._switch;
                    if (_Protoboard.protoboard[pos_x][j]._switch.prendido) {
                        _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                        _Protoboard.toggleSwitch(_Protoboard, _switch, _switch.prendido);
                        break;
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
//                if (_Protoboard.protoboard[pos_x][j]._resistencia != null && _Protoboard.protoboard[pos_x][j]._resistencia.posicion1.coordenadax != -1) {
//                    Resistencia _resistencia = _Protoboard.protoboard[pos_x][j]._resistencia;
//                    _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);
//
//                }
                // si encuentra un chip
                if (_Protoboard.protoboard[pos_x][j]._chip != null && _Protoboard.protoboard[pos_x][j]._chip.posicion1.coordenadax != -1) {
                    Chip _chip = _Protoboard.protoboard[pos_x][j]._chip;
                    if (_chip.tipo_chip.equals("AND")){
                        _chip.pasarCorrienteAND(_Protoboard, _chip);
                    } else if (_chip.tipo_chip.equals("OR")){
                        _chip.pasarCorrienteOR(_Protoboard, _chip);
                    } else if (_chip.tipo_chip.equals("NOT")){
                        _chip.pasarCorrienteNOT(_Protoboard, _chip);
                    }

                }
                if (_Protoboard.protoboard[pos_x][j]._display!=null && _Protoboard.protoboard[pos_x][j]._display.posicion1.coordenadax != -1){
                    Display _display = _Protoboard.protoboard[pos_x][j]._display;
                    _display.activarLinea(_Protoboard, _display);
                }
                j++;
            }
        } else if (pos_y > 8) {
            int j = 9;
            while (j < 14) {
                // si encuentra un cable
                if (_Protoboard.protoboard[pos_x][j]._cable != null && _Protoboard.protoboard[pos_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_x][j]._cable.procesado) {

                    _Protoboard.pasarCorriente(_Protoboard, _Protoboard.protoboard[pos_x][j]._cable);
                }
                // si encuentra un ked
                if (_Protoboard.protoboard[pos_x][j]._led != null && _Protoboard.protoboard[pos_x][j]._led.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[pos_x][j]._posicion.corriente = true;
                    _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_x][j]._led);

                } // si encuentra un switch
                if (_Protoboard.protoboard[pos_x][j]._switch != null && _Protoboard.protoboard[pos_x][j]._switch.posicion1.coordenadax != -1) {
                    Switch _switch = _Protoboard.protoboard[pos_x][j]._switch;
                    if (_Protoboard.protoboard[pos_x][j]._switch.prendido) {
                        _switch.pasarCorriente(_Protoboard, _switch.posicion1.coordenadax + 1, _switch.posicion1.coordenaday + 1);
                        _Protoboard.toggleSwitch(_Protoboard, _switch, _switch.prendido);
                        break;

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
//                if (_Protoboard.protoboard[pos_x][j]._resistencia != null && _Protoboard.protoboard[pos_x][j]._resistencia.posicion1.coordenadax != -1) {
//                    Resistencia _resistencia = _Protoboard.protoboard[pos_x][j]._resistencia;
//                    _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);
//
//                }
                if (_Protoboard.protoboard[pos_x][j]._chip != null && _Protoboard.protoboard[pos_x][j]._chip.posicion1.coordenadax != -1) {
                    Chip _chip = _Protoboard.protoboard[pos_x][j]._chip;
                    // prender la corriente de la posicion del switch y asignarle polaridad

                    if (_chip.tipo_chip.equals("AND")){
                        _chip.pasarCorrienteAND(_Protoboard, _chip);
                    } else if (_chip.tipo_chip.equals("OR")){
                        _chip.pasarCorrienteOR(_Protoboard, _chip);
                    } else if (_chip.tipo_chip.equals("NOT")){
                        _chip.pasarCorrienteNOT(_Protoboard, _chip);
                    }

                }
                if (_Protoboard.protoboard[pos_x][j]._display!=null && _Protoboard.protoboard[pos_x][j]._display.posicion1.coordenadax != -1){
                    Display _display = _Protoboard.protoboard[pos_x][j]._display;
                    _display.activarLinea(_Protoboard, _display);
                }
                j++;
            }
        }

    }
    public void pasarCorriente(protoboard _Protoboard, int pos_1_x, int pos_1_y, int pos_2_x, int pos_2_y, Resistencia _resistencia) {
        if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente) {
            if (pos_2_y < 8 && pos_2_y > 2) {

                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
//                    if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
//                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
//                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_2_x][i]._led);
//                    }
//                    if (_Protoboard.protoboard[pos_2_x][i]._cable != null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax != -1) {
//                        cable _cable = _Protoboard.protoboard[pos_2_x][i]._cable;
//                        _Protoboard.pasarCorriente(_Protoboard, _cable);
//                    }


                }                    encontrarElementosColumna(_Protoboard, pos_2_x, 3);
            } else if (pos_2_y > 8 && pos_2_y < 14) {
                for (int i = 9; i < 14; i++) {
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
//                    if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
//                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
//                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_2_x][i]._led);
//                    }
//                    if (_Protoboard.protoboard[pos_2_x][i]._cable != null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax != -1) {
//                        cable _cable = _Protoboard.protoboard[pos_2_x][i]._cable;
//                        _Protoboard.pasarCorriente(_Protoboard, _cable);
//                    }

                } encontrarElementosColumna(_Protoboard, pos_2_x, 9);
            }
            _resistencia.direccion = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
        } else if (_Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.corriente) {
            if (pos_1_y < 8 && pos_1_y > 2) {
                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.polaridad;
//                    if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
//                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
//                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][i]._led);
//                    }
//                    if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
//                        cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
//                        _Protoboard.pasarCorriente(_Protoboard, _cable);
//                    }

                } encontrarElementosColumna(_Protoboard, pos_1_x, 3);
            } else if (pos_1_y > 8 && pos_1_y < 14) {
                for (int i = 9; i < 14; i++) {
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.polaridad;
//                    if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
//
//                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
//                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][i]._led);
//                    }
//                    if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
//                        cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
//                        _Protoboard.pasarCorriente(_Protoboard, _cable);
//                    }

                } encontrarElementosColumna(_Protoboard, pos_1_x, 9);
            }
            _resistencia.direccion = _Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.polaridad;
        }

    }

    public void eliminarCorriente(protoboard _Protoboard, int pos_1_x, int pos_1_y, int pos_2_x, int pos_2_y) {
        // primero buscar si hay un cable en alguna de las dos posiciones
        boolean cent_hay_cable_arriba_pos_1 = false;
        boolean cent_hay_cable_abajo_pos_1 = false;
        if (pos_1_y < 8) {
            for (int i = 3; i < 8; i++) {
                if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
                    cent_hay_cable_arriba_pos_1 = true;
                }
            }
        } else if (pos_1_y > 8) {
            for (int i = 9; i < 14; i++) {
                if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
                    cent_hay_cable_abajo_pos_1 = true;
                }
            }
        }
        if (cent_hay_cable_arriba_pos_1) {
            for (int i = 3; i < 8; i++) {
                _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                    _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_2_x][i]._led);
                }
                if (_Protoboard.protoboard[pos_2_x][i]._cable != null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax != -1) {
                    int pos_x_cable = _Protoboard.protoboard[pos_2_x][i]._cable.posicion2.coordenadax;
                    int pos_y_cable = _Protoboard.protoboard[pos_2_x][i]._cable.posicion2.coordenaday;
                    _Protoboard.eliminarCorriente(_Protoboard, pos_x_cable, pos_y_cable, false);
                }
            }
        } else if (cent_hay_cable_abajo_pos_1) {
            for (int i = 9; i < 14; i++) {
                _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                    _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_2_x][i]._led);
                }
                if (_Protoboard.protoboard[pos_2_x][i]._cable != null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax != -1) {
                    int pos_x_cable = _Protoboard.protoboard[pos_2_x][i]._cable.posicion2.coordenadax;
                    int pos_y_cable = _Protoboard.protoboard[pos_2_x][i]._cable.posicion2.coordenaday;
                    _Protoboard.eliminarCorriente(_Protoboard, pos_x_cable, pos_y_cable, false);
                }
            }
        } else {
            boolean cent_hay_cable_arriba_pos_2 = false;
            boolean cent_hay_cable_abajo_pos_2 = false;
            if (pos_2_y < 8) {
                for (int i = 3; i < 8; i++) {
                    if (_Protoboard.protoboard[pos_2_x][i]._cable != null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax != -1) {
                        cent_hay_cable_arriba_pos_2 = true;
                    }
                }
            } else if (pos_2_y > 8) {
                for (int i = 9; i < 14; i++) {
                    if (_Protoboard.protoboard[pos_2_x][i]._cable != null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax != -1) {
                        cent_hay_cable_abajo_pos_2 = true;
                    }
                }
            }
            if (cent_hay_cable_arriba_pos_2) {
                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][i]._led);
                    }
                    if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
                        int pos_x_cable = _Protoboard.protoboard[pos_1_x][i]._cable.posicion2.coordenadax;
                        int pos_y_cable = _Protoboard.protoboard[pos_1_x][i]._cable.posicion2.coordenaday;
                        _Protoboard.eliminarCorriente(_Protoboard, pos_x_cable, pos_y_cable, false);
                    }
                }
            } else if (cent_hay_cable_abajo_pos_2) {
                for (int i = 9; i < 14; i++) {
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    // si encuentra un led lo apaga
                    if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][i]._led);
                    }
                    // si encuentraun cable
                    if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
                        int pos_x_cable = _Protoboard.protoboard[pos_1_x][i]._cable.posicion2.coordenadax;
                        int pos_y_cable = _Protoboard.protoboard[pos_1_x][i]._cable.posicion2.coordenaday;
                        _Protoboard.eliminarCorriente(_Protoboard, pos_x_cable, pos_y_cable, false);
                    }
                }
            } else {
                // apagar la corriente de todo
                if (pos_1_y < 8) {
                    for (int i = 3; i < 8; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][i]._led);
                        }
                    }
                } else if (pos_1_y > 8) {
                    for (int i = 9; i < 14; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][i]._led);
                        }
                    }
                }
            }
        }

    }

    public void chequearDireccion(Resistencia _resistencia, protoboard _Protoboard) {

    }
}
