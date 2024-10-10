package com.example.proyprotoboard;
public class Switch extends Indicador{
    Posicion posicion3 ;
    Posicion posicion4 ;
    //Switch prendido = true = pasa corriente, Switch apagado = false = no pasa corriente
    Boolean prendido ;
    public Switch(){
        super();
        this.posicion3=new Posicion();
        this.posicion4=new Posicion();
        this.prendido=false;
    }

    public void pasarCorriente(protoboard _Protoboard, int pos_central_x, int pos_central_y){
        System.out.println("deberia pasar corriente");
        int pos_1_x = pos_central_x-1;
        int pos_1_y = pos_central_y-1;

        int pos_2_x = pos_central_x+1;

        int pos_3_y = pos_central_y+1;

        int pos_4_x = pos_central_x+1;
        int pos_4_y = pos_central_y+1;
        if (pos_central_y-1 == 6){
            System.out.println("entra canal central");
            boolean encuentra_cable_arriba = false;
            int guarda_col_cable = 0;
            int guarda_fil_cable = 0 ;
            // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
            for (int k = 2 ; k < 7 ; k ++){
                if (_Protoboard.protoboard[pos_1_x][k]._cable!=null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax!=-1){
                    encuentra_cable_arriba = true;
                    guarda_col_cable = k;
                    guarda_fil_cable = pos_1_x;
                } else if (_Protoboard.protoboard[pos_2_x][k]._cable!=null && _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenadax!=-1){
                    encuentra_cable_arriba = true;
                    guarda_col_cable = k;
                    guarda_fil_cable = pos_2_x;
                }
            }
            if (encuentra_cable_arriba){
                System.out.println("entra posiciones de arriba");
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion1.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            } else {
                System.out.println("entra posiciones de abajo");
                boolean encuentra_cable_abajo = false;
                for (int k = 8 ; k < 13 ; k ++){
                    if (_Protoboard.protoboard[pos_1_x][k]._cable!=null && _Protoboard.protoboard[pos_1_x][k]._cable.posicion1.coordenadax!=-1){
                        guarda_col_cable = k;
                        guarda_fil_cable = pos_1_x;
                        encuentra_cable_abajo = true;
                    } else if (_Protoboard.protoboard[pos_4_x][k]._cable!=null && _Protoboard.protoboard[pos_2_x][k]._cable.posicion1.coordenadax!=-1){
                        guarda_col_cable = k;
                        guarda_fil_cable = pos_4_x;
                        encuentra_cable_abajo = true;
                    }
                }
                if (encuentra_cable_abajo){
                    for (int i = 8 ; i < 13 ; i++){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion1.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[guarda_fil_cable][guarda_col_cable]._cable.posicion1.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }

            }

        } else{

        // posicion arriba a la izquierda pasa a la de arriba a la derecha
        if ( _Protoboard.protoboard[pos_1_x][pos_1_y-1]._cable!=null && _Protoboard.protoboard[pos_1_x][pos_1_y-1]._cable.posicion1.coordenadax!=-1){
            if (pos_central_y - 1 > 1 && pos_central_y - 1 < 7) {
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2 ; i <= pos_1_y ; i++){
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad =  _Protoboard.protoboard[pos_1_x][pos_1_y-1]._cable.posicion1.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // pagar la corriente de arriba
                for (int i = 6 ; i > pos_1_y ; i--){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }

            }
            else if (pos_central_y-1 > 7 && pos_central_y-1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i <= pos_1_y ; i++){
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._cable.posicion1.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // apagar corrientes de abajo
                for (int i = 12 ; i > pos_1_y ; i--){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            }
        // posicion arriba a la derecha pasa a la de arriba a la izquierda
        } else if (  _Protoboard.protoboard[pos_2_x][pos_1_y-1]._cable!=null && _Protoboard.protoboard[pos_2_x][pos_1_y-1]._cable.posicion1.coordenadax!=-1){
            if (pos_central_y-1 > 1 && pos_central_y-1<7){
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2 ; i <= pos_1_y ; i++){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_1_y-1]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad =_Protoboard.protoboard[pos_2_x][pos_1_y-1]._cable.posicion1.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // borra la corriente de arriba
                for (int i = 6 ; i > pos_1_y ; i--){
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                     if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            }
            else if (pos_central_y-1 > 7 && pos_central_y-1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i <= pos_1_y ; i++){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_1_y-1]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_1_y-1]._cable.posicion1.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // apagar corrientes de abajo
                for (int i = 12 ; i > pos_1_y ; i--){
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[pos_2_x][i]._led!=null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_2_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }

                }
            }
        // posicion abajo a la izquierda pasa a la de abajo a la derecha
        } else if ( _Protoboard.protoboard[pos_1_x][pos_3_y+1]._cable!=null && _Protoboard.protoboard[pos_1_x][pos_3_y+1]._cable.posicion1.coordenadax!=-1){
            if (pos_central_y+1 > 1 && pos_central_y+1<7) {
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 6; i >= pos_3_y; i--) {

                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y+1]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y+1]._cable.posicion1.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }

                }
                // apagar las corrientes de arriba
                for (int i = 2 ; i < pos_3_y ; i++){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            } else if (pos_central_y+1 > 7 && pos_central_y+1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 12 ; i >= pos_3_y ; i--){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y+1]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad =_Protoboard.protoboard[pos_1_x][pos_3_y+1]._cable.posicion1.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // apagar las corrientes de abajo
                for (int i = 8 ; i < pos_3_y ; i++){
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            }
            // posicion abajo a la derecha pasa a la de abajo a la izquierda
        } else if (_Protoboard.protoboard[pos_4_x][pos_4_y+1]._cable!=null && _Protoboard.protoboard[pos_4_x][pos_4_y+1]._cable.posicion1.coordenadax!=-1){
            if (pos_central_y+1 > 1 && pos_central_y+1<7){
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 6 ; i >= pos_4_y ; i--){
                    _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y+1]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y+1]._cable.posicion1.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }

                }

                // apagar las corrientes de arriba
                for (int i = 2 ; i < pos_4_y ; i++){
                    _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[pos_4_x][i]._led != null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax != -1) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            }
            else if (pos_central_y+1 > 7 && pos_central_y+1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 12 ; i >= pos_4_y ; i--){
                    _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y+1]._cable.posicion1.polaridad;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y+1]._cable.posicion1.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = true;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // apagar las corrientes de abajo
                for (int i = 8 ; i < pos_4_y ; i++){
                    _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;

                    if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            }
        }
        }

    }

    public void eliminarCorriente (protoboard _Protoboard, int pos_central_x, int pos_central_y){
        pos_central_x++;
        pos_central_y++;
        System.out.println("pos_central_x: " + pos_central_x);
        System.out.println("pos_central_y: " + pos_central_y);
        int pos_1_x = pos_central_x-1;
        int pos_1_y = pos_central_y-1;

        int pos_2_x = pos_central_x+1;

        int pos_3_y = pos_central_y+1;

        int pos_4_x = pos_central_x+1;
        int pos_4_y = pos_central_y+1;
        if (pos_central_y-1 == 6){
            // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
            if (_Protoboard.protoboard[pos_1_x][pos_1_y]._switch.prendido){
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_2_x][i]._led!=null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_2_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }

                }
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;

                    if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                        _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                        _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            } else{
                if (_Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.corriente){
                    for (int i = 2 ; i < 7; i++){
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_2_x][i]._led!=null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_2_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                } else if (_Protoboard.protoboard[pos_4_x][pos_1_y-1]._posicion.corriente){
                    for (int i = 2 ; i < 7; i++){
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_2_x][i]._led!=null && _Protoboard.protoboard[pos_2_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_2_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                } else if (_Protoboard.protoboard[pos_1_x][pos_3_y+1]._posicion.corriente){
                    for ( int i = 8 ; i < 13 ; i++){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }


                } else if (_Protoboard.protoboard[pos_4_x][pos_4_y+1]._posicion.corriente){
                    for ( int i = 8 ; i < 13 ; i++){
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y-1]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }
            }
        } else {
            // al eliminar corriente deberia borrar su propia corriente, ya que la otra ya fue eliminada
            if (_Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.corriente) {
                System.out.println("entra en la pos 1 x pos 1 y");
                 if (pos_central_y - 1 > 1 && pos_central_y - 1 < 7) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i = 2; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }

                } else if (pos_central_y - 1 > 7 && pos_central_y - 1 < 13) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 8; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;
                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }

                }
                // posicion arriba a la derecha pasa a la de arriba a la izquierda
            } else if (_Protoboard.protoboard[pos_2_x][pos_1_y - 1]._posicion.corriente) {
                System.out.println("entra en la pos2 x pos 2 y");
                if (pos_central_y - 1 > 1 && pos_central_y - 1 < 7) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i = 2; i <= pos_1_y; i++) {

                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                } else if (pos_central_y - 1 > 7 && pos_central_y - 1 < 13) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 8; i <= pos_1_y; i++) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_2_x][pos_1_y - 1]._posicion.polaridad;

                        _Protoboard.protoboard[pos_2_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_2_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_1_y - 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }
                // posicion abajo a la izquierda pasa a la de abajo a la derecha
            } else if (_Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.corriente) {
                if (pos_central_y + 1 > 1 && pos_central_y + 1 < 7) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i = 6; i >= pos_3_y; i--) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                } else if (pos_central_y + 1 > 7 && pos_central_y + 1 < 13) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 12; i >= pos_3_y; i--) {
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }
                // posicion abajo a la derecha pasa a la de abajo a la izquierda
            } else if (_Protoboard.protoboard[pos_4_x][pos_4_y + 1]._posicion.corriente) {
                System.out.println("entra en la pos 4 x pos 4 y");
                if (pos_central_y + 1 > 1 && pos_central_y + 1 < 7) {
                    // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                    for (int i = 6; i >= pos_4_y; i--) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }

                } else if (pos_central_y + 1 > 7 && pos_central_y + 1 < 13) {
                    // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                    for (int i = 12; i >= pos_4_y; i--) {
                        _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_1_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_4_x][pos_4_y + 1]._posicion.polaridad;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                        _Protoboard.protoboard[pos_4_x][i]._posicion.polaridad = _Protoboard.protoboard[pos_1_x][pos_3_y + 1]._posicion.polaridad;

                        if (_Protoboard.protoboard[pos_1_x][i]._led!=null && _Protoboard.protoboard[pos_1_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_1_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_1_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        } else if (_Protoboard.protoboard[pos_4_x][i]._led!=null && _Protoboard.protoboard[pos_4_x][i]._led.posicion1.coordenadax!=-1){
                            _Protoboard.protoboard[pos_4_x][i]._posicion.corriente = false;
                            Led _led = _Protoboard.protoboard[pos_4_x][i]._led;
                            _led.posicion1.corriente = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente;
                            _led.posicion2.corriente = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente;
                            _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
                            _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.polaridad;
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }
            }
        }
    }

    public void eliminarCorriente_AlEliminarSwitch(protoboard _Protoboard, Switch _switch, int guarda_x_cable, boolean encuentra_un_cable, int pos_y) {
        System.out.println("pos_y: " + pos_y);
        if (pos_y < 7 && pos_y!=6) {
            for (int i = 2; i < 7 && !encuentra_un_cable; i++) {
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
                for (int i = 2; i < 7; i++) {
                    _Protoboard.protoboard[guarda_x_cable][i]._posicion.corriente = true;
                    if (_Protoboard.protoboard[guarda_x_cable][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        Led _led = _Protoboard.protoboard[guarda_x_cable][i]._led;
                        if (_Protoboard.protoboard[guarda_x_cable][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                            _led.posicion1.corriente = true;
                        } else {
                            _led.posicion2.corriente = true;
                        }
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // devolver corriente a la columna original
                for (int i = 2; i < 7; i++) {
                    _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                        if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                            _led.posicion1.corriente = false;
                        } else {
                            _led.posicion2.corriente = false;
                        }
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            } else if (guarda_x_cable == _switch.posicion4.coordenadax) {
                for (int i = 2; i < 7; i++) {
                    _Protoboard.protoboard[guarda_x_cable][i]._posicion.corriente = true;
                    if (_Protoboard.protoboard[guarda_x_cable][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        Led _led = _Protoboard.protoboard[guarda_x_cable][i]._led;
                        if (_Protoboard.protoboard[guarda_x_cable][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                            _led.posicion1.corriente = true;
                        } else {
                            _led.posicion2.corriente = true;
                        }
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // devolver corriente a la columna original
                for (int i = 2; i < 7; i++) {
                    _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                            _led.posicion1.corriente = false;
                        } else {
                            _led.posicion2.corriente = false;
                        }
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }

            } else {
                System.out.println("no encontro un cable");
            }
        } else if (pos_y > 7) {
            for (int i = 8; i < 13 && !encuentra_un_cable; i++) {
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
            System.out.println("guarda_x_cable: " + guarda_x_cable);
            if (guarda_x_cable == _switch.posicion1.coordenadax) {
                System.out.println("entro aca");
                for (int i = 8; i < 13; i++) {
                    _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                    if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                            _led.posicion1.corriente = true;
                        } else {
                            _led.posicion2.corriente = true;
                        }
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // devolver corriente a la columna original
                for (int i = 8; i < 13; i++) {

                    _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                        if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                            _led.posicion1.corriente = false;
                        } else {
                            _led.posicion2.corriente = false;
                        }
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            } else if (guarda_x_cable == _switch.posicion4.coordenadax) {
                System.out.println("entro aca2");
                for (int i = 8; i < 13; i++) {
                    _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                    if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                        if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                            _led.posicion1.corriente = true;
                        } else {
                            _led.posicion2.corriente = true;
                        }
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
                // devolver corriente a la columna original
                for (int i = 8; i < 13; i++) {
                    _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                    if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                        Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                            _led.posicion1.corriente = false;
                        } else {
                            _led.posicion2.corriente = false;
                        }
                        _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                    }
                }
            } else {
                System.out.println("no encontro un cable");
            }

        } else if (pos_y==6){
            System.out.println("deberia estar en el canal central");
            // si el switch esta en el canal central

            // ** falta agregar la condicion de cual tiene corriente, ya que no sabemos si la corriente viene de abajo para arriba o de arriba para abajo **
            if (_switch.prendido) {
                // si el switch esta prendido, se tendria que borrar las dos corrientes de arriba
                if (_Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion1.coordenaday]._posicion.corriente){
                    for (int i = 2; i < 7; i++) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                    }
                    for (int i = 8; i < 13 && !encuentra_un_cable; i++) {
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
                    System.out.println("guarda_x_cable: " + guarda_x_cable);
                    if (guarda_x_cable == _switch.posicion1.coordenadax) {
                        System.out.println("entro aca");
                        for (int i = 8; i < 13; i++) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                                if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                    _led.posicion1.corriente = true;
                                } else {
                                    _led.posicion2.corriente = true;
                                }
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                        // devolver corriente a la columna original
                        for (int i = 8; i < 13; i++) {

                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                                if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                                    _led.posicion1.corriente = false;
                                } else {
                                    _led.posicion2.corriente = false;
                                }
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                    } else if (guarda_x_cable == _switch.posicion4.coordenadax) {
                        System.out.println("entro aca2");
                        for (int i = 8; i < 13; i++) {
                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                                if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                                    _led.posicion1.corriente = true;
                                } else {
                                    _led.posicion2.corriente = true;
                                }
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                        // devolver corriente a la columna original
                        for (int i = 8; i < 13; i++) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                                if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                    _led.posicion1.corriente = false;
                                } else {
                                    _led.posicion2.corriente = false;
                                }
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                    }

                // borrar las 2 de abajo
                } else if (_Protoboard.protoboard[_switch.posicion1.coordenadax][_switch.posicion2.coordenaday]._posicion.corriente){
                    for (int i = 8; i < 13; i++) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                    }
                    for (int i = 2; i < 7 && !encuentra_un_cable; i++) {
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
                    System.out.println("guarda_x_cable: " + guarda_x_cable);
                    if (guarda_x_cable == _switch.posicion1.coordenadax) {
                        System.out.println("entro aca");
                        for (int i = 2; i < 7; i++) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                                if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                    _led.posicion1.corriente = true;
                                } else {
                                    _led.posicion2.corriente = true;
                                }
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                        // devolver corriente a la columna original
                        for (int i = 2; i < 7; i++) {

                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                                if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                                    _led.posicion1.corriente = false;
                                } else {
                                    _led.posicion2.corriente = false;
                                }
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                    } else if (guarda_x_cable == _switch.posicion4.coordenadax) {
                        System.out.println("entro aca2");
                        for (int i = 2; i < 7; i++) {
                            _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                                if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                                    _led.posicion1.corriente = true;
                                } else {
                                    _led.posicion2.corriente = true;
                                }
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                        // devolver corriente a la columna original
                        for (int i = 2; i < 7; i++) {
                            _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                                Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                                if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                    _led.posicion1.corriente = false;
                                } else {
                                    _led.posicion2.corriente = false;
                                }
                                _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                            }
                        }
                    }
                }

            } else {
                // si no esta prendido, se borran las de abajo que no tengan cable
                for (int i = 8; i < 13 && !encuentra_un_cable; i++) {
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
                System.out.println("guarda_x_cable: " + guarda_x_cable);
                if (guarda_x_cable == _switch.posicion1.coordenadax) {
                    System.out.println("entro aca");
                    for (int i = 8; i < 13; i++) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = true;
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                            Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                _led.posicion1.corriente = true;
                            } else {
                                _led.posicion2.corriente = true;
                            }
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                    // devolver corriente a la columna original
                    for (int i = 8; i < 13; i++) {

                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                            Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                                _led.posicion1.corriente = false;
                            } else {
                                _led.posicion2.corriente = false;
                            }
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                } else if (guarda_x_cable == _switch.posicion4.coordenadax) {
                    System.out.println("entro aca2");
                    for (int i = 8; i < 13; i++) {
                        _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._posicion.corriente = true;
                        if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led.posicion1.coordenadax != -1) {
                            Led _led = _Protoboard.protoboard[_switch.posicion4.coordenadax][i]._led;
                            if (_Protoboard.protoboard[_switch.posicion4.coordenadax][i]._switch.posicion4.coordenadax == _led.posicion1.coordenadax) {
                                _led.posicion1.corriente = true;
                            } else {
                                _led.posicion2.corriente = true;
                            }
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                    // devolver corriente a la columna original
                    for (int i = 8; i < 13; i++) {
                        _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._posicion.corriente = false;
                        if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led != null && _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led.posicion1.coordenadax != -1) {
                            Led _led = _Protoboard.protoboard[_switch.posicion1.coordenadax][i]._led;
                            if (_Protoboard.protoboard[_switch.posicion1.coordenadax][i]._switch.posicion1.coordenadax == _led.posicion1.coordenadax) {
                                _led.posicion1.corriente = false;
                            } else {
                                _led.posicion2.corriente = false;
                            }
                            _Protoboard.cambiarEstadoLed(_Protoboard, _led);
                        }
                    }
                }
            }
        }
    }
}
