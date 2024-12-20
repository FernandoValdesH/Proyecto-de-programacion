package com.example.proyprotoboard;

public class Switch extends Indicador {
    Posicion posicion3;
    Posicion posicion4;
    //Switch prendido = true = pasa corriente, Switch apagado = false = no pasa corriente
    Boolean prendido;

    public Switch() {
        super();
        this.posicion3 = new Posicion();
        this.posicion4 = new Posicion();
        this.prendido = false;
    }

    public void pasarCorriente(protoboard _Protoboard, int pos_central_x, int pos_central_y) {
        int pos_1_x = pos_central_x - 1;
        int pos_1_y = pos_central_y - 1;

        int pos_2_x = pos_central_x + 1;

        int pos_3_y = pos_central_y + 1;

        int pos_4_x = pos_central_x + 1;
        int pos_4_y = pos_central_y + 1;
        if (pos_central_y - 1 == 7) {
            boolean encuentra_cable_arriba = false;
            int guarda_col_cable = 0;
            int guarda_fil_cable = 0;
            // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
            for (int k = 3; k < 8; k++) {
                if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1 && (_Protoboard.protoboard[pos_1_x][k]._cable.posicion1.corriente || _Protoboard.protoboard[pos_1_x][k]._cable.posicion2.corriente)) {
                    encuentra_cable_arriba = true;
                    guarda_col_cable = k;
                    guarda_fil_cable = pos_1_x;
                } else if (_Protoboard.protoboard[pos_2_x][k]._cable != null && _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenadax != -1 && (_Protoboard.protoboard[pos_2_x][k]._cable.posicion1.corriente || _Protoboard.protoboard[pos_2_x][k]._cable.posicion2.corriente)) {
                    encuentra_cable_arriba = true;
                    guarda_col_cable = k;
                    guarda_fil_cable = pos_2_x;
                }
            }
            if (encuentra_cable_arriba) {
                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion1.polaridad;
                    if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            } else {
                boolean encuentra_cable_abajo = false;
                for (int k = 9; k < 14; k++) {
                    if (_Protoboard.protoboard[pos_1_x][k]._cable != null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax != -1 && (_Protoboard.protoboard[pos_1_x][k]._cable.posicion1.corriente || _Protoboard.protoboard[pos_1_x][k]._cable.posicion2.corriente)) {
                        guarda_col_cable = k;
                        guarda_fil_cable = pos_1_x;
                        encuentra_cable_abajo = true;
                    } else if (_Protoboard.protoboard[pos_4_x][k]._cable != null && _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenadax != -1 && (_Protoboard.protoboard[pos_2_x][k]._cable.posicion1.corriente || _Protoboard.protoboard[pos_2_x][k]._cable.posicion2.corriente)) {
                        guarda_col_cable = k;
                        guarda_fil_cable = pos_4_x;
                        encuentra_cable_abajo = true;
                    }
                }
                if (encuentra_cable_abajo) {


                    for (int i = 9; i < 14; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }

            }

        } else {

            // posicion arriba a la izquierda pasa a la de arriba a la derecha
            if (_Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable != null && _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                if (pos_central_y - 1 > 2 && pos_central_y - 1 < 8) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i = 3; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                    // pagar la corriente de arriba
                    for (int i  =7; i > pos_1_y; i--) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }

                } else if (pos_central_y - 1 > 8 && pos_central_y - 1 < 14) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 9; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                    // apagar corrientes de abajo
                    for (int i = 13; i > pos_1_y; i--) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }
                // posicion arriba a la derecha pasa a la de arriba a la izquierda
            } else if (_Protoboard.protoboard[pos_2_x][pos_1_y - 1]._cable != null && _Protoboard.protoboard[pos_2_x][pos_1_y - 1]._cable.posicion1.coordenadax != -1) {
                if (pos_central_y - 1 > 2 && pos_central_y - 1 < 8) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i = 3; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_1_y - 1]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_1_y - 1]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                    // borra la corriente de arriba
                    for (int i  =7; i > pos_1_y; i--) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                } else if (pos_central_y - 1 > 8 && pos_central_y - 1 < 14) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 9; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_1_y - 1]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_1_y - 1]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                    // apagar corrientes de abajo
                    for (int i = 13; i > pos_1_y; i--) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_2_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }

                    }
                }
                // posicion abajo a la izquierda pasa a la de abajo a la derecha
            } else if (_Protoboard.protoboard[pos_1_x][pos_3_y + 1]._cable != null && _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._cable.posicion1.coordenadax != -1) {
                if (pos_central_y + 1 > 2 && pos_central_y + 1 < 8) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i  =7; i >= pos_3_y; i--) {

                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }

                    }
                    // apagar las corrientes de arriba
                    for (int i = 3; i < pos_3_y; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                } else if (pos_central_y + 1 > 8 && pos_central_y + 1 < 14) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 13; i >= pos_3_y; i--) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                    // apagar las corrientes de abajo
                    for (int i = 9; i < pos_3_y; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }
                // posicion abajo a la derecha pasa a la de abajo a la izquierda
            } else if (_Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable != null && _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.coordenadax != -1) {
                if (pos_central_y + 1 > 2 && pos_central_y + 1 < 8) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i  =7; i >= pos_4_y; i--) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }

                    }

                    // apagar las corrientes de arriba
                    for (int i = 3; i < pos_4_y; i++) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                } else if (pos_central_y + 1 > 8 && pos_central_y + 1 < 14) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 13; i >= pos_4_y; i--) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                    // apagar las corrientes de abajo
                    for (int i = 9; i < pos_4_y; i++) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;

                        if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }
            }
        }

    }

    public void eliminarCorriente(protoboard _Protoboard, int pos_central_x, int pos_central_y) {
        pos_central_x++;
        pos_central_y++;
        int pos_1_x = pos_central_x - 1;
        int pos_1_y = pos_central_y - 1;

        int pos_2_x = pos_central_x + 1;

        int pos_3_y = pos_central_y + 1;

        int pos_4_x = pos_central_x + 1;
        int pos_4_y = pos_central_y + 1;
        if (pos_central_y - 1 == 7) {
            // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
            if (_Protoboard.protoboard[pos_1_x][pos_1_y]._switch.prendido) {
                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_2_x][i]._led;

                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                    if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday >= 3 && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday <= 13)) && !_Protoboard.protoboard[pos_1_x][i]._cable.procesado) {
                        cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 3 && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 13)) && !_Protoboard.protoboard[pos_4_x][i]._cable.procesado) {
                        cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                    }

                }
                for (int i = 9; i < 14; i++) {
                    _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                    if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday >= 3 && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenaday <= 13)) && !_Protoboard.protoboard[pos_1_x][i]._cable.procesado) {
                        cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _cable.procesado = true;
                        _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && ((_Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday >= 3 && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenaday <= 13)) && !_Protoboard.protoboard[pos_4_x][i]._cable.procesado) {
                        cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _cable.procesado = true;
                        _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                    }
                }
            } else {
                if (_Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.corriente) {
                    for (int i = 3; i < 8; i++) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_2_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_1_x][i]._cable.procesado) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            _cable.procesado = true;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_4_x][i]._cable.procesado) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _cable.procesado = true;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_1_y - 1]._posicion.corriente) {
                    for (int i = 3; i < 8; i++) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_2_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_1_x][i]._cable.procesado) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _cable.procesado=true;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_4_x][i]._cable.procesado) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _cable.procesado=true;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                } else if (_Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.corriente) {
                    for (int i = 9; i < 14; i++) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_1_x][i]._cable.procesado) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_4_x][i]._cable.procesado) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }


                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y + 1]._posicion.corriente) {
                    for (int i = 9; i < 14; i++) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_1_x][i]._cable.procesado) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_4_x][i]._cable.procesado) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                }
            }
        } else {
            // al eliminar corriente deberia borrar su propia corriente, ya que la otra ya fue eliminada
            if (_Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.corriente) {
                if (pos_central_y - 1 > 2 && pos_central_y - 1 < 8) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i = 3; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;

                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1 ) {
                            cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }

                    }

                } else if (pos_central_y - 1 > 8 && pos_central_y - 1 < 14) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 9; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
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
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }

                }
                // posicion arriba a la derecha pasa a la de arriba a la izquierda
            } else if (_Protoboard.protoboard[pos_2_x][pos_1_y - 1]._posicion.corriente) {
                if (pos_central_y - 1 > 2 && pos_central_y - 1 < 8) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i = 3; i <= pos_1_y; i++) {

                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

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
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                } else if (pos_central_y - 1 > 8 && pos_central_y - 1 < 14) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 9; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_1_y - 1]._posicion.polaridad;

                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

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
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                }
                // posicion abajo a la izquierda pasa a la de abajo a la derecha
            } else if (_Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.corriente) {
                if (pos_central_y + 1 > 2 && pos_central_y + 1 < 8) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i  =7; i >= pos_3_y; i--) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;

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
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                } else if (pos_central_y + 1 > 8 && pos_central_y + 1 < 14) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 13; i >= pos_3_y; i--) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;

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
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                }
                // posicion abajo a la derecha pasa a la de abajo a la izquierda
            } else if (_Protoboard.protoboard[pos_4_x][pos_4_y + 1]._posicion.corriente) {
                if (pos_central_y + 1 > 2 && pos_central_y + 1 < 8) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i = 7; i >= pos_4_y; i--) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;

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
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }

                } else if (pos_central_y + 1 > 8 && pos_central_y + 1 < 14) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 13; i >= pos_4_y; i--) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;

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
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._cable != null && _Protoboard.protoboard[pos_4_x][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[pos_4_x][i]._cable;
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                }
            }
        }
    }

    public void eliminarCorriente_AlEliminarSwitch(protoboard _Protoboard, Switch _switch, int guarda_x_cable, boolean encuentra_un_cable, int pos_y) {
        if (pos_y < 8 && pos_y !=7) {
            for (int i = 3; i < 8 && !encuentra_un_cable; i++) {
                // si encuentra un cable
                if ((_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) && (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1)) {
                    // en caso de que haya un cable en ambos lados
                    guarda_x_cable = 999;
                    break;
                }
                if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                    encuentra_un_cable = true;
                    guarda_x_cable = _switch.posicion1.coordenadax;
                } else if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                    encuentra_un_cable = true;
                    guarda_x_cable = _switch.posicion4.coordenadax;
                }
            }
            if (guarda_x_cable == _switch.posicion1.coordenadax) {
                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[guarda_x_cable][i]._posicion.corriente = true;
                    if (_Protoboard.protoboard[guarda_x_cable][i]._led != null && _Protoboard.protoboard[guarda_x_cable][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[guarda_x_cable][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[guarda_x_cable][i]._led;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // devolver corriente a la columna original
                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;

                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                    if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                        cable _cable = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable;
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                        _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                    }
                }
            } else if (guarda_x_cable == _switch.posicion4.coordenadax) {
                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[guarda_x_cable][i]._posicion.corriente = true;
                    if (_Protoboard.protoboard[guarda_x_cable][i]._led != null && _Protoboard.protoboard[guarda_x_cable][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[guarda_x_cable][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[guarda_x_cable][i]._led;

                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // devolver corriente a la columna original
                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                    if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                        cable _cable = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable;
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                    }
                }

            } else {
                System.out.println("no encontro un cable");
            }
        } else if (pos_y > 8) {
            for (int i = 9; i < 14 && !encuentra_un_cable; i++) {
                // si encuentra un cable
                if ((_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) && (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1)) {
                    // en caso de que haya un cable en ambos lados
                    guarda_x_cable = 999;
                    break;
                }
                if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                    encuentra_un_cable = true;
                    guarda_x_cable = _switch.posicion1.coordenadax;
                } else if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                    encuentra_un_cable = true;
                    guarda_x_cable = _switch.posicion4.coordenadax;
                }
            }
            if (guarda_x_cable == _switch.posicion1.coordenadax) {
                for (int i = 9; i < 14; i++) {
                    _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                    if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }

                }
                // devolver corriente a la columna original
                for (int i = 9; i < 14; i++) {

                    _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                    if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                        cable _cable = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable;
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                        _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                    }
                }
            } else if (guarda_x_cable == _switch.posicion4.coordenadax) {
                for (int i = 9; i < 14; i++) {
                    _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                    if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // devolver corriente a la columna original
                for (int i = 9; i < 14; i++) {
                    _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                    if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                        cable _cable = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable;
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                    }
                }
            } else {
                System.out.println("no encontro un cable");
            }

        } else if (pos_y == 7) {
            // si el switch esta en el canal central

            // ** falta agregar la condicion de cual tiene corriente, ya que no sabemos si la corriente viene de abajo para arriba o de arriba para abajo **
            if (_switch.prendido) {
                // si el switch esta prendido, se tendria que borrar las dos corrientes de arriba
                if (_Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday]._posicion.corriente) {
                    for (int i = 3; i < 8; i++) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                    }
                    for (int i = 9; i < 14 && !encuentra_un_cable; i++) {
                        // si encuentra un cable
                        if ((_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) && (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1)) {
                            // en caso de que haya un cable en ambos lados
                            guarda_x_cable = 999;
                            break;
                        }
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                            encuentra_un_cable = true;
                            guarda_x_cable = _switch.posicion1.coordenadax;
                        } else if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                            encuentra_un_cable = true;
                            guarda_x_cable = _switch.posicion4.coordenadax;
                        }

                    }
                    if (guarda_x_cable == _switch.posicion1.coordenadax) {
                        for (int i = 9; i < 14; i++) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                                Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                        // devolver corriente a la columna original
                        for (int i = 9; i < 14; i++) {

                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                                Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                                cable _cable = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable;
                                _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                                _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                            }
                        }
                    } else if (guarda_x_cable == _switch.posicion4.coordenadax) {
                        for (int i = 9; i < 14; i++) {
                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                                Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                        // devolver corriente a la columna original
                        for (int i = 9; i < 14; i++) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                                Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                                cable _cable = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable;
                                _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                                _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                            }
                        }
                    }

                    // borrar las 2 de abajo
                } else if (_Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion2.coordenaday]._posicion.corriente) {
                    for (int i = 9; i < 14; i++) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                    }
                    for (int i = 3; i < 8 && !encuentra_un_cable; i++) {
                        // si encuentra un cable
                        if ((_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) && (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1)) {
                            // en caso de que haya un cable en ambos lados
                            guarda_x_cable = 999;
                            break;
                        }
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                            encuentra_un_cable = true;
                            guarda_x_cable = _switch.posicion1.coordenadax;
                        } else if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                            encuentra_un_cable = true;
                            guarda_x_cable = _switch.posicion4.coordenadax;
                        }

                    }
                    if (guarda_x_cable == _switch.posicion1.coordenadax) {
                        for (int i = 3; i < 8; i++) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                                Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                        // devolver corriente a la columna original
                        for (int i = 3; i < 8; i++) {

                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                                Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                                cable _cable = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable;
                                _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                                _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                            }
                        }
                    } else if (guarda_x_cable == _switch.posicion4.coordenadax) {
                        for (int i = 3; i < 8; i++) {
                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                                Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }

                        }
                        // devolver corriente a la columna original
                        for (int i = 3; i < 8; i++) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                                Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                                cable _cable = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable;
                                _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                                _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                            }
                        }
                    }
                }

            } else {
                System.out.println("aaaaaaa");
                // si no esta prendido, se borran las de abajo que no tengan cable
                for (int i = 9; i < 14 && !encuentra_un_cable; i++) {
                    // si encuentra un cable
                    if ((_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) && (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1)) {
                        // en caso de que haya un cable en ambos lados
                        guarda_x_cable = 999;
                        break;
                    }
                    if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                        encuentra_un_cable = true;
                        guarda_x_cable = _switch.posicion1.coordenadax;
                        System.out.println("guarda cable pos 1");
                    } else if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                        encuentra_un_cable = true;
                        guarda_x_cable = _switch.posicion4.coordenadax;
                        System.out.println("guarda cable pos 4");
                    }
                }
                if (guarda_x_cable == _switch.posicion1.coordenadax) {
                    System.out.println("entro pos 1");
                    for (int i = 9; i < 14; i++) {
                        System.out.println("dando corriente pos 1");
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }

                    }
                    // borrar corriente a la columna original
                    for (int i = 9; i < 14; i++) {

                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._cable;
                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                } else if (guarda_x_cable == _switch.posicion4.coordenadax) {

                    for (int i = 9; i < 14; i++) {
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                        if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }

                    }
                    // borrar corriente a la columna original
                    for (int i = 9; i < 14; i++) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable.posicion1.coordenadax != -1) {
                            cable _cable = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._cable;
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                            _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                        }
                    }
                }
            }
        }
    }
}
