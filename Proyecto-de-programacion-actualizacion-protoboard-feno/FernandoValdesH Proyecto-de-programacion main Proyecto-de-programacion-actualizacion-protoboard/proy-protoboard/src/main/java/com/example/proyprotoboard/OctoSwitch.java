package com.example.proyprotoboard;

public class OctoSwitch extends Switch{

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

    public void setMiniSwitch_OctoSwitch(OctoSwitch _octoswitch){
        for (int i = _octoswitch.posicion1.coordenadax; i < _octoswitch.posicion2.coordenadax+1; i++){
            if (i == _octoswitch.posicion1.coordenadax){
                _octoswitch.mini_switch_1.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday+1);

            } else if (i == _octoswitch.posicion1.coordenadax+1){
                _octoswitch.mini_switch_2.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday+1);
            } else if (i == _octoswitch.posicion1.coordenadax+2){
                _octoswitch.mini_switch_3.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday+1);
            } else if (i == _octoswitch.posicion1.coordenadax+3){
                _octoswitch.mini_switch_4.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday+1);
            } else if (i == _octoswitch.posicion1.coordenadax+4){
                _octoswitch.mini_switch_5.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday+1);
            } else if (i == _octoswitch.posicion1.coordenadax+5){
                _octoswitch.mini_switch_6.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday+1);
            } else if (i == _octoswitch.posicion1.coordenadax+6){
                _octoswitch.mini_switch_7.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday+1);
            } else if (i == _octoswitch.posicion1.coordenadax+7){
                _octoswitch.mini_switch_8.setPosMiniSwitch(i, _octoswitch.posicion1.coordenaday+1);
            }
        }
    }

    public void simplificadorPasarCorriente(protoboard _protoboard, int fila, int columna){
        if (columna < 7){
            for (int i = 8 ; i < 13 ; i++){
                _protoboard.protoboard[fila][i]._posicion.corriente = true;
                _protoboard.protoboard[fila][i]._posicion.polaridad = _protoboard.protoboard[fila][columna]._posicion.polaridad;
                if (_protoboard.protoboard[fila][i]._led!=null && _protoboard.protoboard[fila][i]._led.posicion1.coordenadax!=-1){
                    _protoboard.protoboard[fila][i]._posicion.corriente = true;
                    _protoboard.cambiarEstadoLed(_protoboard, _protoboard.protoboard[fila][i]._led);
                }
                if (_protoboard.protoboard[fila][i]._cable!=null && _protoboard.protoboard[fila][i]._cable.posicion1.coordenadax!=-1){
                    _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                    _protoboard.resetCables(_protoboard);
                }
            }

        } else if (columna > 7){
            for (int i = 2 ; i < 7 ; i++){
                _protoboard.protoboard[fila][i]._posicion.corriente = true;
                _protoboard.protoboard[fila][i]._posicion.polaridad = _protoboard.protoboard[fila][columna]._posicion.polaridad;
                if (_protoboard.protoboard[fila][i]._led!=null && _protoboard.protoboard[fila][i]._led.posicion1.coordenadax!=-1){
                    _protoboard.protoboard[fila][i]._posicion.corriente = true;
                    _protoboard.cambiarEstadoLed(_protoboard, _protoboard.protoboard[fila][i]._led);
                }
                if (_protoboard.protoboard[fila][i]._cable!=null && _protoboard.protoboard[fila][i]._cable.posicion1.coordenadax!=-1){
                    _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                    _protoboard.resetCables(_protoboard);
                }
            }
        }
    }
    public void pasarCorrientePosicion(protoboard _protoboard, int posicion_switch_x, int posicion_switch_y){
        int guarda_col_cable = 0;
        int guarda_fil_cable = 0 ;
        OctoSwitch _octoSwitch = (OctoSwitch) _protoboard.protoboard[posicion_switch_x][posicion_switch_y]._octoSwitch;
        int numero_switch_prendido = 0;

        for (int i = _octoSwitch.posicion1.coordenadax; i < _octoSwitch.posicion2.coordenadax+1; i++){
            if (_protoboard.protoboard[i][_octoSwitch.posicion1.coordenaday]._posicion.coordenaday+1 == posicion_switch_y && _protoboard.protoboard[i][_octoSwitch.posicion1.coordenaday]._posicion.coordenadax == posicion_switch_x){
                numero_switch_prendido =posicion_switch_x- _octoSwitch.posicion1.coordenadax +1;
            }
        }
        if (numero_switch_prendido==1){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_1.encendido = true;
            int coord_x = _octoSwitch.mini_switch_1.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_1.posicion.coordenaday;
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }

            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }

            }
        } else if (numero_switch_prendido==2){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_2.encendido = true;
            int coord_x = _octoSwitch.mini_switch_2.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_2.posicion.coordenaday;
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }

            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }
            }
        } else if (numero_switch_prendido==3){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_3.encendido = true;
            int coord_x = _octoSwitch.mini_switch_3.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_3.posicion.coordenaday;
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }

            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }

            }
        } else if (numero_switch_prendido==4){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_4.encendido = true;
            int coord_x = _octoSwitch.mini_switch_4.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_4.posicion.coordenaday;
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }
            }
        } else if (numero_switch_prendido==5){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_5.encendido = true;
            int coord_x = _octoSwitch.mini_switch_5.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_5.posicion.coordenaday;
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }

            }
        } else if (numero_switch_prendido==6){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_6.encendido = true;
            int coord_x = _octoSwitch.mini_switch_6.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_6.posicion.coordenaday;
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }
            }
        } else if (numero_switch_prendido==7){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_7.encendido = true;
            int coord_x = _octoSwitch.mini_switch_7.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_7.posicion.coordenaday;
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }
            }
        } else if (numero_switch_prendido==8){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_8.encendido = true;
            int coord_x = _octoSwitch.mini_switch_8.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_8.posicion.coordenaday;
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._posicion.corriente) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        simplificadorPasarCorriente(_protoboard, coord_x, guarda_col_cable);
                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }

            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                        _protoboard.resetCables(_protoboard);
                    }
                }

            }
        }

        for (int fil = 0; fil < 30; fil++) {
            for (int com = 0; com < 15; com++) {
                if (_protoboard.protoboard[fil][com]._cable!=null){
                    _protoboard.protoboard[fil][com]._cable.procesado=false;
                }

            }
        }
    }

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
            if (coord_y == 7){
                int guarda_col_cable = 0;
                int guarda_fil_cable = 0;// canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._posicion.corriente && _protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenadax!=-1 && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12 ) ) ) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 8, false);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12)  ) ) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {
                        _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 2, false);

                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
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
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 8, false);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {

                        _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 2, false);

                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
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
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 8, false);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {

                        _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 2, false);

                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
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
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 8, false);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {

                        _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 2, false);

                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
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
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 8, false);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {

                        _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 2, false);

                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
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
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 8, false);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {

                        _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 2, false);

                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
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
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 8, false);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {

                        _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 2, false);

                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
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
            if (coord_y == 7){ // canal central
                int j = 2;
                for (j = 2; j < 7; j++) {
                    if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                        guarda_col_cable = j;
                        guarda_fil_cable = coord_x;
                        break;
                    }
                }
                if (guarda_col_cable != 0) {
                    _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 8, false);
                } else {
                    for (j = 8; j < 13; j++) {
                        if (_protoboard.protoboard[coord_x][j]._cable != null && _protoboard.protoboard[coord_x][j]._cable.posicion1.corriente && ((_protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday < 2 || _protoboard.protoboard[coord_x][j]._cable.posicion1.coordenaday > 12) || (_protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday <2  || _protoboard.protoboard[coord_x][j]._cable.posicion2.coordenaday > 12))) {
                            guarda_col_cable = j;
                            guarda_fil_cable = coord_x;
                            break;
                        }
                    }
                    if (guarda_col_cable != 0) {

                        _protoboard.eliminarCorriente(_protoboard, guarda_fil_cable, 2, false);

                    }
                }
            }
            if (coord_y >1 && coord_y < 7){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 7; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j, false);
                    }
                }
            }
        }
        for (int fil = 0; fil < 30; fil++) {
            for (int com = 0; com < 15; com++) {
                if (_protoboard.protoboard[fil][com]._cable!=null){
                    _protoboard.protoboard[fil][com]._cable.procesado=false;
                }

            }
        }
    }
    public void pasarCorrienteAlEncontrar(protoboard _protoboard, int pos_x, int pos_y, int pos_inicio_x, int pos_inicio_y){
        if (pos_y>7){
            for (int l = 2; l < 7; l++) {
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
                    _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                    _protoboard.resetCables(_protoboard);
                }
            }
        } else if (pos_y<7){
            for (int l = 8; l < 13; l++) {
                _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                _protoboard.protoboard[pos_x][l]._posicion.polaridad = _protoboard.protoboard[pos_inicio_x][pos_inicio_y]._posicion.polaridad;
                if (_protoboard.protoboard[pos_x][l]._led != null && _protoboard.protoboard[pos_x][l]._led.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._posicion.corriente = true;
                    Led _led = _protoboard.protoboard[pos_x][l]._led;
                    _protoboard.cambiarEstadoLed(_protoboard, _led);
                }
                if (_protoboard.protoboard[pos_x][l]._cable != null && !_protoboard.protoboard[pos_x][l]._cable.procesado && _protoboard.protoboard[pos_x][l]._cable.posicion1.coordenadax != -1) {
                    _protoboard.protoboard[pos_x][l]._cable.procesado = true;
                    _protoboard.PasarCorriente(_protoboard,Controlador_Protoboard.cablelist);
                    _protoboard.resetCables(_protoboard);
                }
            }
        }
    }
    public void eliminarCorrienteAlEncontrar(protoboard _protoboard, int pos_x, int pos_y, boolean switch_bateria){
        if (pos_y>7){
            for (int l = 2; l < 7; l++) {
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
        } else if (pos_y<7){
            for (int l = 8; l < 13; l++) {
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
