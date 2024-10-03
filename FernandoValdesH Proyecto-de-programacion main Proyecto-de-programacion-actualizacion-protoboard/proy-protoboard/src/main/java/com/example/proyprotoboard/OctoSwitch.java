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
    public void pasarCorrientePosicion(protoboard _protoboard, int posicion_switch_x, int posicion_switch_y){

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

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
        } else if (numero_switch_prendido==2){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_2.encendido = true;
            int coord_x = _octoSwitch.mini_switch_2.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_2.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
        } else if (numero_switch_prendido==3){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_3.encendido = true;
            int coord_x = _octoSwitch.mini_switch_3.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_3.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
        } else if (numero_switch_prendido==4){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_4.encendido = true;
            int coord_x = _octoSwitch.mini_switch_4.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_4.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
        } else if (numero_switch_prendido==5){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_5.encendido = true;
            int coord_x = _octoSwitch.mini_switch_5.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_5.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
        } else if (numero_switch_prendido==6){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_6.encendido = true;
            int coord_x = _octoSwitch.mini_switch_6.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_6.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
        } else if (numero_switch_prendido==7){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_7.encendido = true;
            int coord_x = _octoSwitch.mini_switch_7.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_7.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }
            }
        } else if (numero_switch_prendido==8){
            boolean cent_si_hay_corriente = false;
            _octoSwitch.mini_switch_8.encendido = true;
            int coord_x = _octoSwitch.mini_switch_8.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_8.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
                }

            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.pasarCorriente(_protoboard, _protoboard.protoboard[coord_x][j]._cable);
                    }
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
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
        } else if (numero_switch_apagado == 2) {
            _octoSwitch.mini_switch_2.encendido = false;
            int coord_x = _octoSwitch.mini_switch_2.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_2.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
        } else if (numero_switch_apagado == 3) {
            _octoSwitch.mini_switch_3.encendido = false;
            int coord_x = _octoSwitch.mini_switch_3.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_3.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
        } else if (numero_switch_apagado == 4) {
            _octoSwitch.mini_switch_4.encendido = false;
            int coord_x = _octoSwitch.mini_switch_4.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_4.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
        } else if (numero_switch_apagado == 5) {
            _octoSwitch.mini_switch_5.encendido = false;
            int coord_x = _octoSwitch.mini_switch_5.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_5.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
        } else if (numero_switch_apagado == 6) {
            _octoSwitch.mini_switch_6.encendido = false;
            int coord_x = _octoSwitch.mini_switch_6.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_6.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
        } else if (numero_switch_apagado == 7) {
            _octoSwitch.mini_switch_7.encendido = false;
            int coord_x = _octoSwitch.mini_switch_7.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_7.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
        } else if (numero_switch_apagado == 8) {
            _octoSwitch.mini_switch_8.encendido = false;
            int coord_x = _octoSwitch.mini_switch_8.posicion.coordenadax;
            int coord_y = _octoSwitch.mini_switch_8.posicion.coordenaday;
            if (coord_y == 7){ // canal central

            }
            if (coord_y >2 && coord_y < 6){ // parte de arriba del protoboard
                int j = 2;
                for (j = 2; j < 6; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
            if (coord_y > 7 && coord_y < 13){ // parte de abajo del protoboard
                int j = 8 ;
                for (j = 8; j < 13; j++){
                    if (_protoboard.protoboard[coord_x][j]._cable.posicion1.corriente){
                        _protoboard.eliminarCorriente(_protoboard, coord_x, j);
                    }
                }
            }
        }
    }


}
