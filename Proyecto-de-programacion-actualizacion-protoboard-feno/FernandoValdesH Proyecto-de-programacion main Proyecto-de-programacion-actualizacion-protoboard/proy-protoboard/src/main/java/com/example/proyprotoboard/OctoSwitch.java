package com.example.proyprotoboard;

public class OctoSwitch extends Switch {

    public MiniSwitch mini_switch_1;
    public MiniSwitch mini_switch_2;
    public MiniSwitch mini_switch_3;
    public MiniSwitch mini_switch_4;
    public MiniSwitch mini_switch_5;
    public MiniSwitch mini_switch_6;
    public MiniSwitch mini_switch_7;
    public MiniSwitch mini_switch_8;


    public OctoSwitch() {
        super();
        this.mini_switch_1 = new MiniSwitch();
        this.mini_switch_2 = new MiniSwitch();
        this.mini_switch_3 = new MiniSwitch();
        this.mini_switch_4 = new MiniSwitch();
        this.mini_switch_5 = new MiniSwitch();
        this.mini_switch_6 = new MiniSwitch();
        this.mini_switch_7 = new MiniSwitch();
        this.mini_switch_8 = new MiniSwitch();

    }

    public void setMiniSwitch_OctoSwitch(OctoSwitch _octoswitch) {
        for (int i = _octoswitch.posicion1.coordenadax; i < _octoswitch.posicion2.coordenadax + 1; i++) {
            if (i == _octoswitch.posicion1.coordenadax) {
                _octoswitch.mini_switch_1.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday + 1);

            } else if (i == _octoswitch.posicion1.coordenadax + 1) {
                _octoswitch.mini_switch_2.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday + 1);
            } else if (i == _octoswitch.posicion1.coordenadax + 2) {
                _octoswitch.mini_switch_3.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday + 1);
            } else if (i == _octoswitch.posicion1.coordenadax + 3) {
                _octoswitch.mini_switch_4.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday + 1);
            } else if (i == _octoswitch.posicion1.coordenadax + 4) {
                _octoswitch.mini_switch_5.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday + 1);
            } else if (i == _octoswitch.posicion1.coordenadax + 5) {
                _octoswitch.mini_switch_6.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday + 1);
            } else if (i == _octoswitch.posicion1.coordenadax + 6) {
                _octoswitch.mini_switch_7.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday + 1);
            } else if (i == _octoswitch.posicion1.coordenadax + 7) {
                _octoswitch.mini_switch_8.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday + 1);
            }
        }
    }

    public void simplificadorPasarCorriente(protoboard _protoboard, int fila, int columna, boolean surco) {
        if (surco){
        if (columna == 2){
            for (int i = 3; i < 8; i++) {
                _protoboard.protoboard[fila][i]._posicion.corriente = true;
                _protoboard.protoboard[fila][i]._posicion.polaridad = _protoboard.protoboard[fila][columna-1]._posicion.polaridad;
                if (_protoboard.protoboard[fila][i]._led != null && _protoboard.protoboard[fila][i]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[fila][i]._posicion.corriente = true;
                    _protoboard.cambiarEstadoLed(_protoboard, _protoboard.protoboard[fila][i]._led);
                }
                if (_protoboard.protoboard[fila][i]._cable != null && _protoboard.protoboard[fila][i]._cable.posicion1.coordenadax != -1) {
                    _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[fila][i]._cable);
                }
            }
        }
        else if (columna == 14){
            for (int i = 9; i < 14; i++) {
                _protoboard.protoboard[fila][i]._posicion.corriente = true;
                _protoboard.protoboard[fila][i]._posicion.polaridad = _protoboard.protoboard[fila][columna+1]._posicion.polaridad;
                if (_protoboard.protoboard[fila][i]._led != null && _protoboard.protoboard[fila][i]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[fila][i]._posicion.corriente = true;
                    _protoboard.cambiarEstadoLed(_protoboard, _protoboard.protoboard[fila][i]._led);
                }
                if (_protoboard.protoboard[fila][i]._cable != null && _protoboard.protoboard[fila][i]._cable.posicion1.coordenadax != -1) {
                    _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[fila][i]._cable);
                }
            }
        }} else{
         if (columna < 8 && columna > 2) {
            for (int i = 9; i < 14; i++) {
                _protoboard.protoboard[fila][i]._posicion.corriente = true;
                _protoboard.protoboard[fila][i]._posicion.polaridad = _protoboard.protoboard[fila][columna]._posicion.polaridad;
                if (_protoboard.protoboard[fila][i]._led != null && _protoboard.protoboard[fila][i]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[fila][i]._posicion.corriente = true;
                    _protoboard.cambiarEstadoLed(_protoboard, _protoboard.protoboard[fila][i]._led);
                }
                if (_protoboard.protoboard[fila][i]._cable != null && _protoboard.protoboard[fila][i]._cable.posicion1.coordenadax != -1) {
                    _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[fila][i]._cable);
                }
            }

        } else if (columna > 8 && columna < 14) {
            for (int i = 3; i < 8; i++) {
                _protoboard.protoboard[fila][i]._posicion.corriente = true;
                _protoboard.protoboard[fila][i]._posicion.polaridad = _protoboard.protoboard[fila][columna]._posicion.polaridad;
                if (_protoboard.protoboard[fila][i]._led != null && _protoboard.protoboard[fila][i]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[fila][i]._posicion.corriente = true;
                    _protoboard.cambiarEstadoLed(_protoboard, _protoboard.protoboard[fila][i]._led);
                }
                if (_protoboard.protoboard[fila][i]._cable != null && _protoboard.protoboard[fila][i]._cable.posicion1.coordenadax != -1) {
                    _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[fila][i]._cable);
                }
            }
        }}
    }

    public void pasarCorrientePosicion(protoboard _protoboard, int posicion_switch_x, int posicion_switch_y) {
        int guarda_col_cable = 0;
        int guarda_fil_cable = 0;
        OctoSwitch _octoSwitch = (OctoSwitch) _protoboard.protoboard[posicion_switch_x][posicion_switch_y]._octoSwitch;
        int numero_switch_prendido = 0;

        for (int i = _octoSwitch.posicion1.coordenadax; i < _octoSwitch.posicion2.coordenadax + 1; i++) {
            if (_protoboard.protoboard[i][_octoSwitch.posicion1.coordenaday]._posicion.coordenaday + 1 == posicion_switch_y && _protoboard.protoboard[i][_octoSwitch.posicion1.coordenaday]._posicion.coordenadax == posicion_switch_x) {
                numero_switch_prendido = posicion_switch_x - _octoSwitch.posicion1.coordenadax + 1;
            }
        }

        if (numero_switch_prendido == 1) {
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_1.encendido = true;
            int coord_x = _octoSwitch.mini_switch_1.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_1.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y, true);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y , true);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                } else {
                    for (j = 9; j < 14; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
        } else if (numero_switch_prendido == 2) {
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_2.encendido = true;
            int coord_x = _octoSwitch.mini_switch_2.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_2.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y, true);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y , true);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                } else {
                    for (j = 9; j < 14; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
        } else if (numero_switch_prendido == 3) {
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_3.encendido = true;
            int coord_x = _octoSwitch.mini_switch_3.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_3.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y, true);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y , true);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                } else {
                    for (j = 9; j < 14; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
        } else if (numero_switch_prendido == 4) {
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_4.encendido = true;
            int coord_x = _octoSwitch.mini_switch_4.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_4.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y, true);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y , true);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                } else {
                    for (j = 9; j < 14; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
        } else if (numero_switch_prendido == 5) {
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_5.encendido = true;
            int coord_x = _octoSwitch.mini_switch_5.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_5.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y, true);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y , true);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                } else {
                    for (j = 9; j < 14; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
        } else if (numero_switch_prendido == 6) {
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_6.encendido = true;
            int coord_x = _octoSwitch.mini_switch_6.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_6.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y, true);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y , true);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                } else {
                    for (j = 9; j < 14; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
        } else if (numero_switch_prendido == 7) {
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_7.encendido = true;
            int coord_x = _octoSwitch.mini_switch_7.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_7.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y, true);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y , true);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                } else {
                    for (j = 9; j < 14; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
        } else if (numero_switch_prendido == 8) {
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_8.encendido = true;
            int coord_x = _octoSwitch.mini_switch_8.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_8.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y, true);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    simplificadorPasarCorriente(_protoboard, coord_x, coord_y , true);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                } else {
                    for (j = 9; j < 14; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable , false);
                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
        }

        for (int fil = 0; fil < 30; fil++) {
            for (int com = 0; com < 17; com++) {
                if (_protoboard.protoboard[fil][com]._cable != null) {
                    _protoboard.protoboard[fil][com]._cable.procesado = false;
                }

            }
        }
    }

    /* metodo para buscar un cable conectado a bus
     * busca en la columna si hay un cable conectado al bus (que seria el que alimenta)
     * si lo encuentra, comprobar la posicion y de este cable y ver si la fuente de alimentacion viene de arriba o abajo
     * si no hay cable conectado al bus, busca en la columna anterior, pero sin salirse de los limites del octoswitch
     * primero busca arriba, y si no encuentra nada arriba busca abajo
     * */


    public void eliminarCorrientePosicion(protoboard _protoboard, int posicion_switch_x, int posicion_switch_y) {
        OctoSwitch _octoSwitch = (OctoSwitch) _protoboard.protoboard[posicion_switch_x][posicion_switch_y]._octoSwitch;
        int numero_switch_apagado = 0;

        for (int i = _octoSwitch.posicion1.coordenadax; i < _octoSwitch.posicion2.coordenadax + 1; i++) {
            if (_protoboard.protoboard[i][_octoSwitch.posicion1.coordenaday]._posicion.coordenaday + 1 == posicion_switch_y && _protoboard.protoboard[i][_octoSwitch.posicion1.coordenaday]._posicion.coordenadax == posicion_switch_x) {
                numero_switch_apagado = posicion_switch_x - _octoSwitch.posicion1.coordenadax + 1;

            }
        }
        if (numero_switch_apagado == 1) {
            _octoSwitch.mini_switch_1.encendido = false;
            int coord_x = _octoSwitch.mini_switch_1.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_1.posicion.coordenaday;

            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 13, false);
                }
            }


            if (coord_y == 8) {
                // canal central
                int j = 3;
                for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[i][j]._posicion.corriente && _protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1 && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                        _octoSwitch.mini_switch_1.fuente_corriente="arriba";
                        break;
                    }
                }}
                if (_octoSwitch.mini_switch_1.fuente_corriente.equals("arriba")) {
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 9, false);
                } else {
                    for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                    for (j = 9; j < 14; j++) {
                        if (_protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.corriente && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                            _octoSwitch.mini_switch_1.fuente_corriente="abajo";
                            break;
                        }
                    }}
                    if (_octoSwitch.mini_switch_1.fuente_corriente.equals("abajo")) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);

                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
        } else if (numero_switch_apagado == 2) {

            int guarda_col_cable = 0;
            int guarda_fil_cable = 0;
            _octoSwitch.mini_switch_2.encendido = false;
            int coord_x = _octoSwitch.mini_switch_2.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_2.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 13, false);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                    for (j = 3; j < 8; j++) {
                        if (_protoboard.protoboard[i][j]._posicion.corriente && _protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1 && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                            _octoSwitch.mini_switch_2.fuente_corriente="arriba";
                            break;
                        }
                    }}
                if (_octoSwitch.mini_switch_2.fuente_corriente.equals("arriba")) {
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 9, false);
                } else {
                    for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                        for (j = 9; j < 14; j++) {
                            if (_protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.corriente && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                                _octoSwitch.mini_switch_2.fuente_corriente="abajo";
                                break;
                            }
                        }}
                    if (_octoSwitch.mini_switch_2.fuente_corriente.equals("abajo")) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);

                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
        } else if (numero_switch_apagado == 3) {
            int guarda_col_cable = 0;
            int guarda_fil_cable = 0;
            _octoSwitch.mini_switch_3.encendido = false;
            int coord_x = _octoSwitch.mini_switch_3.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_3.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 13, false);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                    for (j = 3; j < 8; j++) {
                        if (_protoboard.protoboard[i][j]._posicion.corriente && _protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1 && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                            _octoSwitch.mini_switch_3.fuente_corriente="arriba";
                            break;
                        }
                    }}
                if (_octoSwitch.mini_switch_3.fuente_corriente.equals("arriba")) {
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 9, false);
                } else {
                    for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                        for (j = 9; j < 14; j++) {
                            if (_protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.corriente && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                                _octoSwitch.mini_switch_3.fuente_corriente="abajo";
                                break;
                            }
                        }}
                    if (_octoSwitch.mini_switch_3.fuente_corriente.equals("abajo")) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);

                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
        } else if (numero_switch_apagado == 4) {
            int guarda_col_cable = 0;
            int guarda_fil_cable = 0;
            _octoSwitch.mini_switch_4.encendido = false;
            int coord_x = _octoSwitch.mini_switch_4.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_4.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 13, false);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                    for (j = 3; j < 8; j++) {
                        if (_protoboard.protoboard[i][j]._posicion.corriente && _protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1 && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                            _octoSwitch.mini_switch_4.fuente_corriente="arriba";
                            break;
                        }
                    }}
                if (_octoSwitch.mini_switch_4.fuente_corriente.equals("arriba")) {
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 9, false);
                } else {
                    for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                        for (j = 9; j < 14; j++) {
                            if (_protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.corriente && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                                _octoSwitch.mini_switch_4.fuente_corriente="abajo";
                                break;
                            }
                        }}
                    if (_octoSwitch.mini_switch_4.fuente_corriente.equals("abajo")) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);

                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
        } else if (numero_switch_apagado == 5) {
            int guarda_col_cable = 0;
            int guarda_fil_cable = 0;
            _octoSwitch.mini_switch_5.encendido = false;
            int coord_x = _octoSwitch.mini_switch_5.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_5.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 13, false);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                    for (j = 3; j < 8; j++) {
                        if (_protoboard.protoboard[i][j]._posicion.corriente && _protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1 && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                            _octoSwitch.mini_switch_5.fuente_corriente="arriba";
                            break;
                        }
                    }}
                if (_octoSwitch.mini_switch_5.fuente_corriente.equals("arriba")) {
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 9, false);
                } else {
                    for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                        for (j = 9; j < 14; j++) {
                            if (_protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.corriente && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                                _octoSwitch.mini_switch_5.fuente_corriente="abajo";
                                break;
                            }
                        }}
                    if (_octoSwitch.mini_switch_5.fuente_corriente.equals("abajo")) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);

                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
        } else if (numero_switch_apagado == 6) {
            int guarda_col_cable = 0;
            int guarda_fil_cable = 0;
            _octoSwitch.mini_switch_6.encendido = false;
            int coord_x = _octoSwitch.mini_switch_6.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_6.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 13, false);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                    for (j = 3; j < 8; j++) {
                        if (_protoboard.protoboard[i][j]._posicion.corriente && _protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1 && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                            _octoSwitch.mini_switch_6.fuente_corriente="arriba";
                            break;
                        }
                    }}
                if (_octoSwitch.mini_switch_6.fuente_corriente.equals("arriba")) {
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 9, false);
                } else {
                    for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                        for (j = 9; j < 14; j++) {
                            if (_protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.corriente && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                                _octoSwitch.mini_switch_6.fuente_corriente="abajo";
                                break;
                            }
                        }}
                    if (_octoSwitch.mini_switch_6.fuente_corriente.equals("abajo")) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);

                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
        } else if (numero_switch_apagado == 7) {
            int guarda_col_cable = 0;
            int guarda_fil_cable = 0;
            _octoSwitch.mini_switch_7.encendido = false;
            int coord_x = _octoSwitch.mini_switch_7.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_7.posicion.coordenaday;

            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 13, false);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                    for (j = 3; j < 8; j++) {
                        if (_protoboard.protoboard[i][j]._posicion.corriente && _protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1 && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                            _octoSwitch.mini_switch_7.fuente_corriente="arriba";
                            break;
                        }
                    }}
                if (_octoSwitch.mini_switch_7.fuente_corriente.equals("arriba")) {
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 9, false);
                } else {
                    for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                        for (j = 9; j < 14; j++) {
                            if (_protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.corriente && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                                _octoSwitch.mini_switch_7.fuente_corriente="abajo";
                                break;
                            }
                        }}
                    if (_octoSwitch.mini_switch_7.fuente_corriente.equals("abajo")) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);

                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
        } else if (numero_switch_apagado == 8) {
            int guarda_col_cable = 0;
            int guarda_fil_cable = 0;
            _octoSwitch.mini_switch_8.encendido = false;
            int coord_x = _octoSwitch.mini_switch_8.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_8.posicion.coordenaday;
            if (coord_y == 2){
                if (_protoboard.protoboard[coord_x][coord_y-1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);
                }
            }
            if (coord_y == 14){
                if (_protoboard.protoboard[coord_x][coord_y+1]._posicion.corriente){
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 13, false);
                }
            }
            if (coord_y == 8) { // canal central
                int j = 3;
                for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                    for (j = 3; j < 8; j++) {
                        if (_protoboard.protoboard[i][j]._posicion.corriente && _protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.coordenadax != -1 && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                            _octoSwitch.mini_switch_8.fuente_corriente="arriba";
                            break;
                        }
                    }}
                if (_octoSwitch.mini_switch_8.fuente_corriente.equals("arriba")) {
                    _protoboard.eliminarCorriente(_protoboard, coord_x, 9, false);
                } else {
                    for (int i = _octoSwitch.posicion1.coordenadax ; i < _octoSwitch.posicion2.coordenadax ; i++){
                        for (j = 9; j < 14; j++) {
                            if (_protoboard.protoboard[i][j]._cable != null && _protoboard.protoboard[i][j]._cable.posicion1.corriente && ((_protoboard.protoboard[i][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion1.coordenaday > 15) || (_protoboard.protoboard[i][j]._cable.posicion2.coordenaday < 2 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday > 15))) {
                                _octoSwitch.mini_switch_8.fuente_corriente="abajo";
                                break;
                            }
                        }}
                    if (_octoSwitch.mini_switch_8.fuente_corriente.equals("abajo")) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, 3, false);

                    }
                }
            }
            if (coord_y > 2 && coord_y < 8) { // parte de arriba del protoboard
                int j = 3;
                for (j = 3; j < 8; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 8 && coord_y < 14) { // parte de abajo del protoboard
                int j = 9;
                for (j = 9; j < 14; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente) {
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
        }
        for (int fil = 0; fil < 30; fil++) {
            for (int com = 0; com < 15; com++) {
                if (_protoboard.protoboard[fil][com]._cable != null) {
                    _protoboard.protoboard[fil][com]._cable.procesado = false;
                }

            }
        }
    }

    public void pasarCorrienteAlEncontrar(protoboard _protoboard, int pos_x, int pos_y, int pos_inicio_x, int pos_inicio_y) {
        if (pos_y == 2){
//            pos_inicio_y+=1;
            for (int l = 3; l < 8; l++) {
                _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                _protoboard.protoboard[pos_x][l]._posicion.polaridad = _protoboard.protoboard[pos_inicio_x][pos_inicio_y]._posicion.polaridad;
                if (_protoboard.protoboard[pos_x][l]._led != null && _protoboard.protoboard[pos_x][l]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                    Led _led = _protoboard.protoboard[pos_x][l]._led;
                    _protoboard.cambiarEstadoLed(_protoboard, _led);
                }
                if (_protoboard.protoboard[pos_x][l]._cable != null && !_protoboard.protoboard[pos_x][l]._cable.procesado && _protoboard.protoboard[pos_x][l]._cable.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._cable.procesado = true;
                    // pasar corriente recibe protoboard y un cable
                    _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[pos_x][l]._cable);
                }
            }
        }
        else if (pos_y == 14){
//            pos_inicio_y-=1;
            for (int l = 9; l < 14; l++) {
                _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                _protoboard.protoboard[pos_x][l]._posicion.polaridad = _protoboard.protoboard[pos_inicio_x][pos_inicio_y]._posicion.polaridad;
                if (_protoboard.protoboard[pos_x][l]._led != null && _protoboard.protoboard[pos_x][l]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                    Led _led = _protoboard.protoboard[pos_x][l]._led;
                    _protoboard.cambiarEstadoLed(_protoboard, _led);
                }
                if (_protoboard.protoboard[pos_x][l]._cable != null && !_protoboard.protoboard[pos_x][l]._cable.procesado && _protoboard.protoboard[pos_x][l]._cable.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._cable.procesado = true;
                    _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[pos_x][l]._cable);
                }
            }
        }
        if (pos_y > 8 && pos_y < 14) {
            for (int l = 3; l < 8; l++) {
                _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                _protoboard.protoboard[pos_x][l]._posicion.polaridad = _protoboard.protoboard[pos_inicio_x][pos_inicio_y]._posicion.polaridad;
                if (_protoboard.protoboard[pos_x][l]._led != null && _protoboard.protoboard[pos_x][l]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                    Led _led = _protoboard.protoboard[pos_x][l]._led;
                    _protoboard.cambiarEstadoLed(_protoboard, _led);
                }
                if (_protoboard.protoboard[pos_x][l]._cable != null && !_protoboard.protoboard[pos_x][l]._cable.procesado && _protoboard.protoboard[pos_x][l]._cable.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._cable.procesado = true;
                    // pasar corriente recibe protoboard y un cable
                    _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[pos_x][l]._cable);
                }
            }
        } else if (pos_y < 8 && pos_y > 2) {
            for (int l = 9; l < 14; l++) {
                _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                _protoboard.protoboard[pos_x][l]._posicion.polaridad = _protoboard.protoboard[pos_inicio_x][pos_inicio_y]._posicion.polaridad;
                if (_protoboard.protoboard[pos_x][l]._led != null && _protoboard.protoboard[pos_x][l]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                    Led _led = _protoboard.protoboard[pos_x][l]._led;
                    _protoboard.cambiarEstadoLed(_protoboard, _led);
                }
                if (_protoboard.protoboard[pos_x][l]._cable != null && !_protoboard.protoboard[pos_x][l]._cable.procesado && _protoboard.protoboard[pos_x][l]._cable.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._cable.procesado = true;
                    _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[pos_x][l]._cable);
                }
            }
        }
    }

    public void eliminarCorrienteAlEncontrar(protoboard _protoboard, int pos_x, int pos_y, boolean switch_bateria) {
        if (pos_y == 2){
            for (int l = 3; l < 8; l++) {
                _protoboard.protoboard[pos_x][l]._posicion.corriente = false;
                if (_protoboard.protoboard[pos_x][l]._led != null && _protoboard.protoboard[pos_x][l]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._posicion.corriente = false;
                    Led _led = _protoboard.protoboard[pos_x][l]._led;
                    _protoboard.cambiarEstadoLed(_protoboard, _led);
                }
                if (_protoboard.protoboard[pos_x][l]._cable != null && !_protoboard.protoboard[pos_x][l]._cable.procesado && _protoboard.protoboard[pos_x][l]._cable.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._cable.procesado = true;
                    int pos_final_x = _protoboard.protoboard[pos_x][l]._cable.posicion2.coordenadax;
                    int pos_final_y = _protoboard.protoboard[pos_x][l]._cable.posicion2.coordenaday;
                    _protoboard.eliminarCorriente(_protoboard, pos_final_x, pos_final_y, switch_bateria);
                }
            }
        }
        else if (pos_y == 14){
            for (int l = 9; l < 14; l++) {
                _protoboard.protoboard[pos_x][l]._posicion.corriente = false;
                if (_protoboard.protoboard[pos_x][l]._led != null && _protoboard.protoboard[pos_x][l]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._posicion.corriente = false;
                    Led _led = _protoboard.protoboard[pos_x][l]._led;
                    _protoboard.cambiarEstadoLed(_protoboard, _led);
                }
                if (_protoboard.protoboard[pos_x][l]._cable != null && !_protoboard.protoboard[pos_x][l]._cable.procesado && _protoboard.protoboard[pos_x][l]._cable.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._cable.procesado = true;
                    int pos_final_x = _protoboard.protoboard[pos_x][l]._cable.posicion2.coordenadax;
                    int pos_final_y = _protoboard.protoboard[pos_x][l]._cable.posicion2.coordenaday;
                    _protoboard.eliminarCorriente(_protoboard, pos_final_x, pos_final_y, switch_bateria);
                }
            }
        }
        if (pos_y > 8 && pos_y < 14) {
            for (int l = 3; l < 8; l++) {
                _protoboard.protoboard[pos_x][l]._posicion.corriente = false;
                if (_protoboard.protoboard[pos_x][l]._led != null && _protoboard.protoboard[pos_x][l]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._posicion.corriente = false;
                    Led _led = _protoboard.protoboard[pos_x][l]._led;
                    _protoboard.cambiarEstadoLed(_protoboard, _led);
                }
                if (_protoboard.protoboard[pos_x][l]._cable != null && !_protoboard.protoboard[pos_x][l]._cable.procesado && _protoboard.protoboard[pos_x][l]._cable.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._cable.procesado = true;
                    int pos_final_x = _protoboard.protoboard[pos_x][l]._cable.posicion2.coordenadax;
                    int pos_final_y = _protoboard.protoboard[pos_x][l]._cable.posicion2.coordenaday;
                    _protoboard.eliminarCorriente(_protoboard, pos_final_x, pos_final_y, switch_bateria);
                }
            }
        } else if (pos_y < 8 && pos_y > 2) {
            for (int l = 9; l < 14; l++) {
                _protoboard.protoboard[pos_x][l]._posicion.corriente = false;
                if (_protoboard.protoboard[pos_x][l]._led != null && _protoboard.protoboard[pos_x][l]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._posicion.corriente = false;
                    Led _led = _protoboard.protoboard[pos_x][l]._led;
                    _protoboard.cambiarEstadoLed(_protoboard, _led);
                }
                if (_protoboard.protoboard[pos_x][l]._cable != null && !_protoboard.protoboard[pos_x][l]._cable.procesado && _protoboard.protoboard[pos_x][l]._cable.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._cable.procesado = true;
                    int pos_final_x = _protoboard.protoboard[pos_x][l]._cable.posicion2.coordenadax;
                    int pos_final_y = _protoboard.protoboard[pos_x][l]._cable.posicion2.coordenaday;
                    _protoboard.eliminarCorriente(_protoboard, pos_final_x, pos_final_y, switch_bateria);
                }
            }
        }

    }


}
