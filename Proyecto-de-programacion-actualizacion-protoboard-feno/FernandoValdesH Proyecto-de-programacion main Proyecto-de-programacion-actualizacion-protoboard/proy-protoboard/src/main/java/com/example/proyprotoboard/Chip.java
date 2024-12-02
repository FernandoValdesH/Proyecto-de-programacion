package com.example.proyprotoboard;

public class Chip extends Indicador{
    // inicializar las 6 posiciones restantes del chip
    String tipo_chip;
    Posicion pos_3;
    Posicion pos_4;
    Posicion pos_5;
    Posicion pos_6;
    Posicion pos_7;
    Posicion pos_8;
    Posicion pos_9;
    Posicion pos_10;
    Posicion pos_11;
    Posicion pos_12;
    Posicion pos_13;
    Posicion pos_14;
    public Chip(){
        super();
        this.pos_3 = new Posicion();
        this.pos_4 = new Posicion();
        this.pos_5 = new Posicion();
        this.pos_6 = new Posicion();
        this.pos_7 = new Posicion();
        this.pos_8 = new Posicion();
        this.pos_9 = new Posicion();
        this.pos_10 = new Posicion();
        this.pos_11 = new Posicion();
        this.pos_12 = new Posicion();
        this.pos_13 = new Posicion();
        this.pos_14 = new Posicion();
        this.tipo_chip = "";
    }

    public void pasarCorrienteAND(protoboard _Protoboard, Chip _chip){
        // VCC seria la posicion 1 ( arriba a la izq)
        // GND seria la posicion 14 ( abajo a la derecha)
        _chip.posicion1.corriente = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.corriente;
        _chip.posicion1.polaridad = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.polaridad;

        _chip.posicion2.corriente = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.corriente;
        _chip.posicion2.polaridad = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.polaridad;
        _chip.pos_3.corriente = _Protoboard.protoboard[_chip.pos_3.coordenadax][_chip.pos_3.coordenaday]._posicion.corriente;
        _chip.pos_3.polaridad = _Protoboard.protoboard[_chip.pos_3.coordenadax][_chip.pos_3.coordenaday]._posicion.polaridad;

        _chip.pos_5.corriente = _Protoboard.protoboard[_chip.pos_5.coordenadax][_chip.pos_5.coordenaday]._posicion.corriente;
        _chip.pos_5.polaridad = _Protoboard.protoboard[_chip.pos_5.coordenadax][_chip.pos_5.coordenaday]._posicion.polaridad;
        _chip.pos_6.corriente = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.corriente;
        _chip.pos_6.polaridad = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.polaridad;

        _chip.pos_8.corriente = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.corriente;
        _chip.pos_8.polaridad = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.polaridad;
        _chip.pos_9.corriente = _Protoboard.protoboard[_chip.pos_9.coordenadax][_chip.pos_9.coordenaday]._posicion.corriente;
        _chip.pos_9.polaridad = _Protoboard.protoboard[_chip.pos_9.coordenadax][_chip.pos_9.coordenaday]._posicion.polaridad;

        _chip.pos_11.corriente = _Protoboard.protoboard[_chip.pos_11.coordenadax][_chip.pos_11.coordenaday]._posicion.corriente;
        _chip.pos_11.polaridad = _Protoboard.protoboard[_chip.pos_11.coordenadax][_chip.pos_11.coordenaday]._posicion.polaridad;
        _chip.pos_12.corriente = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.corriente;
        _chip.pos_12.polaridad = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.polaridad;

        _chip.pos_14.corriente = _Protoboard.protoboard[_chip.pos_14.coordenadax][_chip.pos_14.coordenaday]._posicion.corriente;

        if (!_chip.pos_14.corriente || _chip.pos_14.voltaje==0){
            if (_chip.posicion2.corriente && _chip.pos_3.corriente ){
                // pasar corriente a pos 4
                // _chip.pos_4.corriente = true;
                if ( (_chip.posicion2.polaridad && _chip.pos_3.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = true;
                    }
                    encontrarElementosColumna(_Protoboard,_chip.pos_4.coordenadax, 3 );
                }
                else if ( (!_chip.posicion2.polaridad && !_chip.pos_3.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
                    }
                    encontrarElementosColumna(_Protoboard,_chip.pos_4.coordenadax, 3 );
                }
                else if ( (_chip.posicion2.polaridad && !_chip.pos_3.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
                    }
                    encontrarElementosColumna(_Protoboard,_chip.pos_4.coordenadax, 3 );
                }
                else if ( (!_chip.posicion2.polaridad && _chip.pos_3.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
                    }
                    encontrarElementosColumna(_Protoboard,_chip.pos_4.coordenadax, 3 );
                }

            }
            if (_chip.pos_5.corriente && _chip.pos_6.corriente ){
                // pasar corriente a pos 7
                // _chip.pos_7.corriente = true;
                if ( (_chip.pos_5.polaridad && _chip.pos_6.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = true;
                    }
                    encontrarElementosColumna(_Protoboard,_chip.pos_7.coordenadax, 3 );
                }
                else if ( (!_chip.pos_5.polaridad && !_chip.pos_6.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_7.coordenadax, 3 );
                }
                else if ( (_chip.pos_5.polaridad && !_chip.pos_6.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_7.coordenadax, 3 );
                }
                else if ( (!_chip.pos_5.polaridad && _chip.pos_6.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_7.coordenadax, 3 );
                }
            }
            if (_chip.pos_8.corriente && _chip.pos_9.corriente){
                // pasar corriente a pos 10
                // _chip.pos_10.corriente = true;
                if ( (_chip.pos_8.polaridad && _chip.pos_9.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_10.coordenadax, 9 );
                }
                else if ( (!_chip.pos_8.polaridad && !_chip.pos_9.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_10.coordenadax, 9 );
                }
                else if ( (_chip.pos_8.polaridad && !_chip.pos_9.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_10.coordenadax, 9 );
                }
                else if ( (!_chip.pos_8.polaridad && _chip.pos_9.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_10.coordenadax, 9 );
                }
            }
            if (_chip.pos_11.corriente && _chip.pos_12.corriente){
                // pasar corriente a pos 13
                // _chip.pos_13.corriente = true;
                if ( (_chip.pos_11.polaridad && _chip.pos_12.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_13.coordenadax, 9 );
                }
                else if ( (!_chip.pos_11.polaridad && !_chip.pos_12.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_13.coordenadax, 9 );
                }
                else if ( (_chip.pos_11.polaridad && !_chip.pos_12.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_13.coordenadax, 9 );
                }
                else if ( (!_chip.pos_11.polaridad && _chip.pos_12.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_13.coordenadax, 9 );
                }
            }
        }
    }
    public void eliminarCorrienteAND(protoboard _Protoboard, Chip _chip){
        _chip.posicion1.corriente = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.corriente;
        _chip.posicion1.polaridad = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.polaridad;

        _chip.posicion2.corriente = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.corriente;
        _chip.posicion2.polaridad = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.polaridad;
        _chip.pos_3.corriente = _Protoboard.protoboard[_chip.pos_3.coordenadax][_chip.pos_3.coordenaday]._posicion.corriente;
        _chip.pos_3.polaridad = _Protoboard.protoboard[_chip.pos_3.coordenadax][_chip.pos_3.coordenaday]._posicion.polaridad;

        _chip.pos_5.corriente = _Protoboard.protoboard[_chip.pos_5.coordenadax][_chip.pos_5.coordenaday]._posicion.corriente;
        _chip.pos_5.polaridad = _Protoboard.protoboard[_chip.pos_5.coordenadax][_chip.pos_5.coordenaday]._posicion.polaridad;
        _chip.pos_6.corriente = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.corriente;
        _chip.pos_6.polaridad = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.polaridad;

        _chip.pos_8.corriente = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.corriente;
        _chip.pos_8.polaridad = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.polaridad;
        _chip.pos_9.corriente = _Protoboard.protoboard[_chip.pos_9.coordenadax][_chip.pos_9.coordenaday]._posicion.corriente;
        _chip.pos_9.polaridad = _Protoboard.protoboard[_chip.pos_9.coordenadax][_chip.pos_9.coordenaday]._posicion.polaridad;

        _chip.pos_11.corriente = _Protoboard.protoboard[_chip.pos_11.coordenadax][_chip.pos_11.coordenaday]._posicion.corriente;
        _chip.pos_11.polaridad = _Protoboard.protoboard[_chip.pos_11.coordenadax][_chip.pos_11.coordenaday]._posicion.polaridad;
        _chip.pos_12.corriente = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.corriente;
        _chip.pos_12.polaridad = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.polaridad;

        _chip.pos_14.corriente = _Protoboard.protoboard[_chip.pos_14.coordenadax][_chip.pos_14.coordenaday]._posicion.corriente;


        if (!_chip.posicion2.corriente || !_chip.pos_3.corriente){
            // eliminar corriente a pos 4
            for (int i = 3 ; i < 8 ; i++){
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
//                if (_Protoboard.protoboard[_chip.pos_4.coordenadax][i]._cable.posicion1.coordenadax!=-1){
//                    cable _cable = _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._cable;
//                    int pos_x = _cable.posicion2.coordenadax;
//                    int pos_y = _cable.posicion2.coordenaday;
//                    _Protoboard.eliminarCorriente(_Protoboard, pos_x, pos_y, false);
//                }
            } encontrarElementosColumna2(_Protoboard,_chip.pos_4.coordenadax, 3 );
        }
        if (!_chip.pos_5.corriente || !_chip.pos_6.corriente){
            // eliminar corriente a pos 7
            for (int i = 3 ; i < 8 ; i++){
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
//                if (_Protoboard.protoboard[_chip.pos_7.coordenadax][i]._cable.posicion1.coordenadax!=-1){
//                    cable _cable = _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._cable;
//                    int pos_x = _cable.posicion2.coordenadax;
//                    int pos_y = _cable.posicion2.coordenaday;
//                    _Protoboard.eliminarCorriente(_Protoboard, pos_x, pos_y, false);
//                }
            } encontrarElementosColumna2(_Protoboard,_chip.pos_7.coordenadax, 3 );
        }
        if (!_chip.pos_8.corriente || !_chip.pos_9.corriente){
            // eliminar corriente a pos 10
            for (int i = 9 ; i < 14 ; i++){
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
//                if (_Protoboard.protoboard[_chip.pos_10.coordenadax][i]._cable.posicion1.coordenadax!=-1){
//                    cable _cable = _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._cable;
//                    int pos_x = _cable.posicion2.coordenadax;
//                    int pos_y = _cable.posicion2.coordenaday;
//                    _Protoboard.eliminarCorriente(_Protoboard, pos_x, pos_y, false);
//                }
            } encontrarElementosColumna2(_Protoboard,_chip.pos_10.coordenadax, 9 );
        }
        if (!_chip.pos_11.corriente || !_chip.pos_12.corriente){
            // eliminar corriente a pos 13
            for (int i = 9 ; i < 14 ; i++){
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
//                if (_Protoboard.protoboard[_chip.pos_13.coordenadax][i]._cable.posicion1.coordenadax!=-1){
//                    cable _cable = _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._cable;
//                    int pos_x = _cable.posicion2.coordenadax;
//                    int pos_y = _cable.posicion2.coordenaday;
//                    _Protoboard.eliminarCorriente(_Protoboard, pos_x, pos_y, false);
//                }
            } encontrarElementosColumna2(_Protoboard,_chip.pos_13.coordenadax, 9 );
        }

    }

    public void pasarCorrienteOR(protoboard _Protoboard, Chip _chip){
        // VCC seria la posicion 1 ( arriba a la izq)
        // GND seria la posicion 14 ( abajo a la derecha)
        _chip.posicion1.corriente = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.corriente;
        _chip.posicion1.polaridad = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.polaridad;

        _chip.posicion2.corriente = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.corriente;
        _chip.posicion2.polaridad = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.polaridad;
        _chip.pos_3.corriente = _Protoboard.protoboard[_chip.pos_3.coordenadax][_chip.pos_3.coordenaday]._posicion.corriente;
        _chip.pos_3.polaridad = _Protoboard.protoboard[_chip.pos_3.coordenadax][_chip.pos_3.coordenaday]._posicion.polaridad;

        _chip.pos_5.corriente = _Protoboard.protoboard[_chip.pos_5.coordenadax][_chip.pos_5.coordenaday]._posicion.corriente;
        _chip.pos_5.polaridad = _Protoboard.protoboard[_chip.pos_5.coordenadax][_chip.pos_5.coordenaday]._posicion.polaridad;
        _chip.pos_6.corriente = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.corriente;
        _chip.pos_6.polaridad = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.polaridad;

        _chip.pos_8.corriente = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.corriente;
        _chip.pos_8.polaridad = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.polaridad;
        _chip.pos_9.corriente = _Protoboard.protoboard[_chip.pos_9.coordenadax][_chip.pos_9.coordenaday]._posicion.corriente;
        _chip.pos_9.polaridad = _Protoboard.protoboard[_chip.pos_9.coordenadax][_chip.pos_9.coordenaday]._posicion.polaridad;

        _chip.pos_11.corriente = _Protoboard.protoboard[_chip.pos_11.coordenadax][_chip.pos_11.coordenaday]._posicion.corriente;
        _chip.pos_11.polaridad = _Protoboard.protoboard[_chip.pos_11.coordenadax][_chip.pos_11.coordenaday]._posicion.polaridad;
        _chip.pos_12.corriente = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.corriente;
        _chip.pos_12.polaridad = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.polaridad;

        _chip.pos_14.corriente = _Protoboard.protoboard[_chip.pos_14.coordenadax][_chip.pos_14.coordenaday]._posicion.corriente;

        if (!_chip.pos_14.corriente || _chip.pos_14.voltaje==0){
            if (_chip.posicion2.corriente && _chip.pos_3.corriente ){
                // pasar corriente a pos 4
                // _chip.pos_4.corriente = true;
                if ( (_chip.posicion2.polaridad || _chip.pos_3.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = true;
                    }
                    encontrarElementosColumna(_Protoboard,_chip.pos_4.coordenadax, 3 );
                }
                else if ( (!_chip.posicion2.polaridad || !_chip.pos_3.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_4.coordenadax, 3 );
                }
                else if ( (_chip.posicion2.polaridad || !_chip.pos_3.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_4.coordenadax, 3 );
                }
                else if ( (!_chip.posicion2.polaridad || _chip.pos_3.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_4.coordenadax, 3 );
                }
            }
            if (_chip.pos_5.corriente && _chip.pos_6.corriente ){
                // pasar corriente a pos 7
                // _chip.pos_7.corriente = true;
                if ( (_chip.pos_5.polaridad || _chip.pos_6.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_7.coordenadax, 3 );
                }
                else if ( (!_chip.pos_5.polaridad || !_chip.pos_6.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_7.coordenadax, 3 );
                }
                else if ( (_chip.pos_5.polaridad || !_chip.pos_6.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_7.coordenadax, 3 );
                }
                else if ( (!_chip.pos_5.polaridad || _chip.pos_6.polaridad)){
                    for (int i = 3 ; i < 8 ; i++){
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_7.coordenadax, 3 );
                }
            }
            if (_chip.pos_8.corriente && _chip.pos_9.corriente){
                // pasar corriente a pos 10
                // _chip.pos_10.corriente = true;
                if ( (_chip.pos_8.polaridad || _chip.pos_9.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_10.coordenadax, 9 );
                }
                else if ( (!_chip.pos_8.polaridad || !_chip.pos_9.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_10.coordenadax, 9 );
                }
                else if ( (_chip.pos_8.polaridad || !_chip.pos_9.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_10.coordenadax, 9 );
                }
                else if ( (!_chip.pos_8.polaridad || _chip.pos_9.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_10.coordenadax, 9 );
                }
            }
            if (_chip.pos_11.corriente && _chip.pos_12.corriente){
                // pasar corriente a pos 13
                // _chip.pos_13.corriente = true;
                if ( (_chip.pos_11.polaridad || _chip.pos_12.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_13.coordenadax, 9 );
                }
                else if ( (!_chip.pos_11.polaridad || !_chip.pos_12.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_13.coordenadax, 9 );
                }
                else if ( (_chip.pos_11.polaridad || !_chip.pos_12.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_13.coordenadax, 9 );
                }
                else if ( (!_chip.pos_11.polaridad || _chip.pos_12.polaridad)){
                    for (int i = 9 ; i < 14 ; i++){
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                        _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = true;
                    }encontrarElementosColumna(_Protoboard,_chip.pos_13.coordenadax, 9 );
                }
            }
        }
    }
    public void eliminarCorrienteOR(protoboard _Protoboard, Chip _chip){
        // VCC seria la posicion 1 ( arriba a la izq)
        // GND seria la posicion 14 ( abajo a la derecha)
        _chip.posicion1.corriente = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.corriente;
        _chip.posicion1.polaridad = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.polaridad;

        _chip.posicion2.corriente = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.corriente;
        _chip.posicion2.polaridad = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.polaridad;
        _chip.pos_3.corriente = _Protoboard.protoboard[_chip.pos_3.coordenadax][_chip.pos_3.coordenaday]._posicion.corriente;
        _chip.pos_3.polaridad = _Protoboard.protoboard[_chip.pos_3.coordenadax][_chip.pos_3.coordenaday]._posicion.polaridad;

        _chip.pos_5.corriente = _Protoboard.protoboard[_chip.pos_5.coordenadax][_chip.pos_5.coordenaday]._posicion.corriente;
        _chip.pos_5.polaridad = _Protoboard.protoboard[_chip.pos_5.coordenadax][_chip.pos_5.coordenaday]._posicion.polaridad;
        _chip.pos_6.corriente = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.corriente;
        _chip.pos_6.polaridad = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.polaridad;

        _chip.pos_8.corriente = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.corriente;
        _chip.pos_8.polaridad = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.polaridad;
        _chip.pos_9.corriente = _Protoboard.protoboard[_chip.pos_9.coordenadax][_chip.pos_9.coordenaday]._posicion.corriente;
        _chip.pos_9.polaridad = _Protoboard.protoboard[_chip.pos_9.coordenadax][_chip.pos_9.coordenaday]._posicion.polaridad;

        _chip.pos_11.corriente = _Protoboard.protoboard[_chip.pos_11.coordenadax][_chip.pos_11.coordenaday]._posicion.corriente;
        _chip.pos_11.polaridad = _Protoboard.protoboard[_chip.pos_11.coordenadax][_chip.pos_11.coordenaday]._posicion.polaridad;
        _chip.pos_12.corriente = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.corriente;
        _chip.pos_12.polaridad = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.polaridad;

        _chip.pos_14.corriente = _Protoboard.protoboard[_chip.pos_14.coordenadax][_chip.pos_14.coordenaday]._posicion.corriente;

        if (!_chip.posicion2.corriente || !_chip.pos_3.corriente){
            // eliminar corriente a pos 4
            for (int i = 3 ; i < 8 ; i++){
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
            } encontrarElementosColumna2(_Protoboard,_chip.pos_4.coordenadax, 3 );
        }
        if (!_chip.pos_5.corriente || !_chip.pos_6.corriente){
            // eliminar corriente a pos 7
            for (int i = 3 ; i < 8 ; i++){
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
            } encontrarElementosColumna2(_Protoboard,_chip.pos_7.coordenadax, 3 );
        }
        if (!_chip.pos_8.corriente || !_chip.pos_9.corriente){
            // eliminar corriente a pos 10
            for (int i = 9 ; i < 14 ; i++){
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
            } encontrarElementosColumna2(_Protoboard,_chip.pos_10.coordenadax, 9 );
        }
        if (!_chip.pos_11.corriente || !_chip.pos_12.corriente){
            // eliminar corriente a pos 13
            for (int i = 9 ; i < 14 ; i++){
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
            } encontrarElementosColumna2(_Protoboard,_chip.pos_13.coordenadax, 9 );
        }
    }

    public void pasarCorrienteNOT(protoboard _Protoboard, Chip _chip){
        // VCC seria la posicion 1 ( arriba a la izq)
        // GND seria la posicion 14 ( abajo a la derecha)
        _chip.posicion1.corriente = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.corriente;
        _chip.posicion1.polaridad = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.polaridad;

        _chip.posicion2.corriente = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.corriente;
        _chip.posicion2.polaridad = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.polaridad;

        _chip.pos_4.corriente = _Protoboard.protoboard[_chip.pos_4.coordenadax][_chip.pos_4.coordenaday]._posicion.corriente;
        _chip.pos_4.polaridad = _Protoboard.protoboard[_chip.pos_4.coordenadax][_chip.pos_4.coordenaday]._posicion.polaridad;

        _chip.pos_6.corriente = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.corriente;
        _chip.pos_6.polaridad = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.polaridad;

        _chip.pos_8.corriente = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.corriente;
        _chip.pos_8.polaridad = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.polaridad;

        _chip.pos_10.corriente = _Protoboard.protoboard[_chip.pos_10.coordenadax][_chip.pos_10.coordenaday]._posicion.corriente;
        _chip.pos_10.polaridad = _Protoboard.protoboard[_chip.pos_10.coordenadax][_chip.pos_10.coordenaday]._posicion.polaridad;

        _chip.pos_12.corriente = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.corriente;
        _chip.pos_12.polaridad = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.polaridad;

        _chip.pos_14.corriente = _Protoboard.protoboard[_chip.pos_14.coordenadax][_chip.pos_14.coordenaday]._posicion.corriente;

        if (!_chip.pos_14.corriente || _chip.pos_14.voltaje==0){
            if (_chip.posicion2.corriente){
                // pasar corriente a pos 2 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.polaridad = !_chip.posicion2.polaridad;
                }encontrarElementosColumna(_Protoboard,_chip.pos_3.coordenadax, 3 );
            }
            if (_chip.pos_4.corriente){
                // pasar corriente a pos 5 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.polaridad = !_chip.pos_4.polaridad;
                }encontrarElementosColumna(_Protoboard,_chip.pos_5.coordenadax, 3 );
            }
            if (_chip.pos_6.corriente){
                // pasar corriente a pos 7 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = !_chip.pos_6.polaridad;
                }encontrarElementosColumna(_Protoboard,_chip.pos_7.coordenadax, 3 );
            }
            if (_chip.pos_8.corriente){
                // pasar corriente a pos 9 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.polaridad = !_chip.pos_8.polaridad;
                }encontrarElementosColumna(_Protoboard,_chip.pos_9.coordenadax, 9 );
            }
            if (_chip.pos_10.corriente){
                // pasar corriente a pos 11 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.polaridad = !_chip.pos_10.polaridad;
                }encontrarElementosColumna(_Protoboard,_chip.pos_11.coordenadax, 9 );
            }
            if (_chip.pos_12.corriente){
                // pasar corriente a pos 13 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = !_chip.pos_12.polaridad;
                }encontrarElementosColumna(_Protoboard,_chip.pos_13.coordenadax, 9 );
            }
        }
    }
    public void eliminarCorrienteNOT(protoboard _Protoboard, Chip _chip){
        // VCC seria la posicion 1 ( arriba a la izq)
        // GND seria la posicion 14 ( abajo a la derecha)
        _chip.posicion1.corriente = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.corriente;
        _chip.posicion1.polaridad = _Protoboard.protoboard[_chip.posicion1.coordenadax][_chip.posicion1.coordenaday]._posicion.polaridad;

        _chip.posicion2.corriente = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.corriente;
        _chip.posicion2.polaridad = _Protoboard.protoboard[_chip.posicion2.coordenadax][_chip.posicion2.coordenaday]._posicion.polaridad;

        _chip.pos_4.corriente = _Protoboard.protoboard[_chip.pos_4.coordenadax][_chip.pos_4.coordenaday]._posicion.corriente;
        _chip.pos_4.polaridad = _Protoboard.protoboard[_chip.pos_4.coordenadax][_chip.pos_4.coordenaday]._posicion.polaridad;

        _chip.pos_6.corriente = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.corriente;
        _chip.pos_6.polaridad = _Protoboard.protoboard[_chip.pos_6.coordenadax][_chip.pos_6.coordenaday]._posicion.polaridad;

        _chip.pos_8.corriente = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.corriente;
        _chip.pos_8.polaridad = _Protoboard.protoboard[_chip.pos_8.coordenadax][_chip.pos_8.coordenaday]._posicion.polaridad;

        _chip.pos_10.corriente = _Protoboard.protoboard[_chip.pos_10.coordenadax][_chip.pos_10.coordenaday]._posicion.corriente;
        _chip.pos_10.polaridad = _Protoboard.protoboard[_chip.pos_10.coordenadax][_chip.pos_10.coordenaday]._posicion.polaridad;

        _chip.pos_12.corriente = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.corriente;
        _chip.pos_12.polaridad = _Protoboard.protoboard[_chip.pos_12.coordenadax][_chip.pos_12.coordenaday]._posicion.polaridad;

        _chip.pos_14.corriente = _Protoboard.protoboard[_chip.pos_14.coordenadax][_chip.pos_14.coordenaday]._posicion.corriente;

        if (!_chip.pos_14.corriente || _chip.pos_14.voltaje==0){
            if (!_chip.posicion2.corriente){
                // pasar corriente a pos 2 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.polaridad = !_chip.posicion1.polaridad;
                } encontrarElementosColumna2(_Protoboard,_chip.pos_3.coordenadax, 3 );
            }
            if (!_chip.pos_4.corriente){
                // pasar corriente a pos 5 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.polaridad = !_chip.pos_4.polaridad;
                } encontrarElementosColumna2(_Protoboard,_chip.pos_5.coordenadax, 3 );
            }
            if (!_chip.pos_6.corriente){
                // pasar corriente a pos 7 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = !_chip.pos_6.polaridad;
                } encontrarElementosColumna2(_Protoboard,_chip.pos_7.coordenadax, 3 );
            }
            if (!_chip.pos_8.corriente){
                // pasar corriente a pos 9 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.polaridad = !_chip.pos_8.polaridad;
                } encontrarElementosColumna2(_Protoboard,_chip.pos_9.coordenadax, 9 );
            }
            if (!_chip.pos_10.corriente){
                // pasar corriente a pos 11 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.polaridad = !_chip.pos_10.polaridad;
                } encontrarElementosColumna2(_Protoboard,_chip.pos_11.coordenadax, 9 );
            }
            if (!_chip.pos_12.corriente){
                // pasar corriente a pos 13 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = !_chip.pos_12.polaridad;
                } encontrarElementosColumna2(_Protoboard,_chip.pos_13.coordenadax, 9 );
            }
        }

    }
    public void eliminarCorrienteAlEliminar(protoboard _Protoboard, String tipo_chip, Chip _chip) {
        if (tipo_chip.equals("AND")) {
            for (int i = 3; i < 8; i++) {
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
            }
            for (int i = 3; i < 8; i++) {
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
            }
            for (int i = 9; i < 14; i++) {
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
            }
            for (int i = 9; i < 14; i++) {
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
            }
        } else if (tipo_chip.equals("OR")) {
            for (int i = 3; i < 8; i++) {
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
            }
            for (int i = 3; i < 8; i++) {
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
            }
            for (int i = 9; i < 14; i++) {
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
            }
            for (int i = 9; i < 14; i++) {
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
            }
        } else if (tipo_chip.equals("NOT")) {
            for (int i = 3 ; i < 8 ; i++){
                _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.polaridad = !_chip.posicion1.polaridad;
            }
            for (int i = 3 ; i < 8 ; i++){
                _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.polaridad = !_chip.pos_4.polaridad;
            }
            for (int i = 3 ; i < 8 ; i++){
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = !_chip.pos_6.polaridad;
            }
            for (int i = 9 ; i < 14 ; i++){
                _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.polaridad = !_chip.pos_8.polaridad;
            }
            for (int i = 9 ; i < 14 ; i++){
                _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.polaridad = !_chip.pos_10.polaridad;
            }
            for (int i = 9 ; i < 14 ; i++){
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = !_chip.pos_12.polaridad;
            }
        }
    }
    public void encontrarElementosColumna2(protoboard _Protoboard, int pos_x, int pos_y) {
        if (pos_y < 8) {
            int j = 3;
            while (j < 8) {
                // si encuentra un cable
                if (_Protoboard.protoboard[pos_x][j]._cable != null && _Protoboard.protoboard[pos_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_x][j]._cable.procesado) {
                    cable _cable = _Protoboard.protoboard[pos_x][j]._cable;
                    _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                }
                // si encuentra un ked
                if (_Protoboard.protoboard[pos_x][j]._led != null && _Protoboard.protoboard[pos_x][j]._led.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[pos_x][j]._posicion.corriente = false;
                    _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_x][j]._led);


                } // si encuentra un switch
                if (_Protoboard.protoboard[pos_x][j]._switch != null && _Protoboard.protoboard[pos_x][j]._switch.posicion1.coordenadax != -1) {
                    Switch _switch = _Protoboard.protoboard[pos_x][j]._switch;
                    _switch.eliminarCorriente(_Protoboard, _switch.posicion1.coordenadax, _switch.posicion1.coordenaday);

                }
                // si encuentra un octoswitch
                if (_Protoboard.protoboard[pos_x][j]._octoSwitch != null && _Protoboard.protoboard[pos_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                    OctoSwitch _octoSwitch = _Protoboard.protoboard[pos_x][j]._octoSwitch;
                    if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    }
                }

                if (_Protoboard.protoboard[pos_x][j]._resistencia != null && _Protoboard.protoboard[pos_x][j]._resistencia.posicion1.coordenadax != -1) {
                    Resistencia _resistencia = _Protoboard.protoboard[pos_x][j]._resistencia;
                    _resistencia.eliminarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday);
                }
                if (_Protoboard.protoboard[pos_x][j]._display!=null && _Protoboard.protoboard[pos_x][j]._display.posicion1.coordenadax != -1){
                    Display _display = _Protoboard.protoboard[pos_x][j]._display;
                    _display.apagarLinea(_Protoboard, _display);
                }
                j++;
            }
        } else if (pos_y > 8) {
            int j = 9;
            while (j < 14) {
                // si encuentra un cable
                if (_Protoboard.protoboard[pos_x][j]._cable != null && _Protoboard.protoboard[pos_x][j]._cable.posicion1.coordenadax != -1 && !_Protoboard.protoboard[pos_x][j]._cable.procesado) {
                    cable _cable = _Protoboard.protoboard[pos_x][j]._cable;
                    _Protoboard.eliminarCorriente(_Protoboard, _cable.posicion2.coordenadax, _cable.posicion2.coordenaday, false);
                }
                // si encuentra un ked
                if (_Protoboard.protoboard[pos_x][j]._led != null && _Protoboard.protoboard[pos_x][j]._led.posicion1.coordenadax != -1) {
                    _Protoboard.protoboard[pos_x][j]._posicion.corriente = false;
                    _Protoboard.cambiarEstadoLed(_Protoboard, _Protoboard.protoboard[pos_x][j]._led);


                } // si encuentra un switch
                if (_Protoboard.protoboard[pos_x][j]._switch != null && _Protoboard.protoboard[pos_x][j]._switch.posicion1.coordenadax != -1) {
                    Switch _switch = _Protoboard.protoboard[pos_x][j]._switch;
                    _switch.eliminarCorriente(_Protoboard, _switch.posicion1.coordenadax, _switch.posicion1.coordenaday);

                }
                // si encuentra un octoswitch
                if (_Protoboard.protoboard[pos_x][j]._octoSwitch != null && _Protoboard.protoboard[pos_x][j]._octoSwitch.posicion1.coordenadax != -1) {
                    OctoSwitch _octoSwitch = _Protoboard.protoboard[pos_x][j]._octoSwitch;
                    if (pos_x == _octoSwitch.mini_switch_1.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_1.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_2.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_2.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_3.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_3.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_4.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_4.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_5.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_5.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_6.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_6.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_7.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_7.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    } else if (pos_x == _octoSwitch.mini_switch_8.posicion.coordenadax) {
                        int coord_x_mini_switch = _octoSwitch.mini_switch_8.posicion.coordenadax;
                        _octoSwitch.eliminarCorrienteAlEncontrar(_Protoboard, coord_x_mini_switch, pos_y, false);
                    }
                }

                if (_Protoboard.protoboard[pos_x][j]._resistencia != null && _Protoboard.protoboard[pos_x][j]._resistencia.posicion1.coordenadax != -1) {
                    Resistencia _resistencia = _Protoboard.protoboard[pos_x][j]._resistencia;
                    _resistencia.eliminarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday);
                }
                if (_Protoboard.protoboard[pos_x][j]._display!=null && _Protoboard.protoboard[pos_x][j]._display.posicion1.coordenadax != -1){
                    Display _display = _Protoboard.protoboard[pos_x][j]._display;
                    _display.apagarLinea(_Protoboard, _display);
                }
                j++;
            }
        }

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

                if (_Protoboard.protoboard[pos_x][j]._resistencia != null && _Protoboard.protoboard[pos_x][j]._resistencia.posicion1.coordenadax != -1) {
                    Resistencia _resistencia = _Protoboard.protoboard[pos_x][j]._resistencia;
                    _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

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
                if (_Protoboard.protoboard[pos_x][j]._resistencia != null && _Protoboard.protoboard[pos_x][j]._resistencia.posicion1.coordenadax != -1) {
                    Resistencia _resistencia = _Protoboard.protoboard[pos_x][j]._resistencia;
                    _resistencia.pasarCorriente(_Protoboard, _resistencia.posicion1.coordenadax, _resistencia.posicion1.coordenaday, _resistencia.posicion2.coordenadax, _resistencia.posicion2.coordenaday, _resistencia);

                }
                if (_Protoboard.protoboard[pos_x][j]._display!=null && _Protoboard.protoboard[pos_x][j]._display.posicion1.coordenadax != -1){
                    Display _display = _Protoboard.protoboard[pos_x][j]._display;
                    _display.activarLinea(_Protoboard, _display);
                }
                j++;
            }
        }

    }
}
