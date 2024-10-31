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

    public void pasarCorriente(protoboard _Protoboard, int pos_1_x, int pos_1_y, int pos_2_x, int pos_2_y, Resistencia _resistencia) {
        if (_Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.corriente) {
            if (pos_2_y < 8 && pos_2_y > 2) {

                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                    if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_2_x][i]._led);
                    }
                    if (_Protoboard.protoboard[pos_2_x][i]._cable != null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax != -1) {
                        cable _cable = _Protoboard.protoboard[pos_2_x][i]._cable;
                        _Protoboard.pasarCorriente(_Protoboard, _cable);
                    }

                }
            } else if (pos_2_y > 8 && pos_2_y < 14) {
                for (int i = 9; i < 14; i++) {
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
                    if (_Protoboard.protoboard[pos_2_x][i]._led != null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_2_x][i]._led);
                    }
                    if (_Protoboard.protoboard[pos_2_x][i]._cable != null && _Protoboard.protoboard[pos_2_x][i]._cable.posicion1.coordenadax != -1) {
                        cable _cable = _Protoboard.protoboard[pos_2_x][i]._cable;
                        _Protoboard.pasarCorriente(_Protoboard, _cable);
                    }

                }
            }
            _resistencia.direccion = _Protoboard.protoboard[pos_1_x][pos_1_y]._posicion.polaridad;
        } else if (_Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.corriente) {
            if (pos_1_y < 8 && pos_1_y > 2) {
                for (int i = 3; i < 8; i++) {
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.polaridad;
                    if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][i]._led);
                    }
                    if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
                        cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                        _Protoboard.pasarCorriente(_Protoboard, _cable);
                    }

                }
            } else if (pos_1_y > 8 && pos_1_y < 14) {
                for (int i = 9; i < 14; i++) {
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_2_y]._posicion.polaridad;
                    if (_Protoboard.protoboard[pos_1_x][i]._led != null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax != -1) {

                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_1_x][i]._led);
                    }
                    if (_Protoboard.protoboard[pos_1_x][i]._cable != null && _Protoboard.protoboard[pos_1_x][i]._cable.posicion1.coordenadax != -1) {
                        cable _cable = _Protoboard.protoboard[pos_1_x][i]._cable;
                        _Protoboard.pasarCorriente(_Protoboard, _cable);
                    }

                }
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
