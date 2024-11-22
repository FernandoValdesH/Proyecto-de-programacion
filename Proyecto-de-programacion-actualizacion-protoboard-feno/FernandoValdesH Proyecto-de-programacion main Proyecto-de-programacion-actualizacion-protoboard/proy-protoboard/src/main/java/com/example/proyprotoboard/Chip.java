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

        if (!_chip.pos_14.corriente){
        if (_chip.posicion2.corriente && _chip.pos_3.corriente ){
            // pasar corriente a pos 4
            // _chip.pos_4.corriente = true;
            if ( (_chip.posicion2.polaridad && _chip.pos_3.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.posicion2.polaridad && !_chip.pos_3.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (_chip.posicion2.polaridad && !_chip.pos_3.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (!_chip.posicion2.polaridad && _chip.pos_3.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
                }
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
            }
            else if ( (!_chip.pos_5.polaridad && !_chip.pos_6.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (_chip.pos_5.polaridad && !_chip.pos_6.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (!_chip.pos_5.polaridad && _chip.pos_6.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
                }
            }
        }
        if (_chip.pos_8.corriente && _chip.pos_9.corriente){
            // pasar corriente a pos 10
            // _chip.pos_10.corriente = true;
            if ( (_chip.pos_8.polaridad && _chip.pos_9.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.pos_8.polaridad && !_chip.pos_9.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (_chip.pos_8.polaridad && !_chip.pos_9.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (!_chip.pos_8.polaridad && _chip.pos_9.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
                }
            }
        }
        if (_chip.pos_11.corriente && _chip.pos_12.corriente){
            // pasar corriente a pos 13
            // _chip.pos_13.corriente = true;
            if ( (_chip.pos_11.polaridad && _chip.pos_12.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.pos_11.polaridad && !_chip.pos_12.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (_chip.pos_11.polaridad && !_chip.pos_12.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (!_chip.pos_11.polaridad && _chip.pos_12.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
                }
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
                }
            }
            if (!_chip.pos_5.corriente || !_chip.pos_6.corriente){
                // eliminar corriente a pos 7
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
                }
            }
            if (!_chip.pos_8.corriente || !_chip.pos_9.corriente){
                // eliminar corriente a pos 10
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
                }
            }
            if (!_chip.pos_11.corriente || !_chip.pos_12.corriente){
                // eliminar corriente a pos 13
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
                }
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

        if (!_chip.pos_14.corriente){
        if (_chip.posicion2.corriente && _chip.pos_3.corriente ){
            // pasar corriente a pos 4
            // _chip.pos_4.corriente = true;
            if ( (_chip.posicion2.polaridad || _chip.pos_3.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.posicion2.polaridad || !_chip.pos_3.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (_chip.posicion2.polaridad || !_chip.pos_3.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.posicion2.polaridad || _chip.pos_3.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = true;
                }
            }
        }
        if (_chip.pos_5.corriente && _chip.pos_6.corriente ){
            // pasar corriente a pos 7
            // _chip.pos_7.corriente = true;
            if ( (_chip.pos_5.polaridad || _chip.pos_6.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.pos_5.polaridad || !_chip.pos_6.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (_chip.pos_5.polaridad || !_chip.pos_6.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.pos_5.polaridad || _chip.pos_6.polaridad)){
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = true;
                }
            }
        }
        if (_chip.pos_8.corriente && _chip.pos_9.corriente){
            // pasar corriente a pos 10
            // _chip.pos_10.corriente = true;
            if ( (_chip.pos_8.polaridad || _chip.pos_9.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.pos_8.polaridad || !_chip.pos_9.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (_chip.pos_8.polaridad || !_chip.pos_9.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.pos_8.polaridad || _chip.pos_9.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = true;
                }
            }
        }
        if (_chip.pos_11.corriente && _chip.pos_12.corriente){
            // pasar corriente a pos 13
            // _chip.pos_13.corriente = true;
            if ( (_chip.pos_11.polaridad || _chip.pos_12.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.pos_11.polaridad || !_chip.pos_12.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
                }
            }
            else if ( (_chip.pos_11.polaridad || !_chip.pos_12.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = true;
                }
            }
            else if ( (!_chip.pos_11.polaridad || _chip.pos_12.polaridad)){
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = true;
                }
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

        if (!_chip.posicion2.corriente && !_chip.pos_3.corriente){
            // eliminar corriente a pos 4
            for (int i = 3 ; i < 8 ; i++){
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_4.coordenadax][i]._posicion.polaridad = false;
            }
        }
        if (!_chip.pos_5.corriente && !_chip.pos_6.corriente){
            // eliminar corriente a pos 7
            for (int i = 3 ; i < 8 ; i++){
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = false;
            }
        }
        if (!_chip.pos_8.corriente && !_chip.pos_9.corriente){
            // eliminar corriente a pos 10
            for (int i = 9 ; i < 14 ; i++){
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_10.coordenadax][i]._posicion.polaridad = false;
            }
        }
        if (!_chip.pos_11.corriente && !_chip.pos_12.corriente){
            // eliminar corriente a pos 13
            for (int i = 9 ; i < 14 ; i++){
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = false;
                _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = false;
            }
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

        if (!_chip.pos_14.corriente){
            if (_chip.posicion2.corriente){
                // pasar corriente a pos 2 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.polaridad = !_chip.posicion1.polaridad;
                }
            }
            if (_chip.pos_4.corriente){
                // pasar corriente a pos 5 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.polaridad = !_chip.pos_4.polaridad;
                }
            }
            if (_chip.pos_6.corriente){
                // pasar corriente a pos 7 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = !_chip.pos_6.polaridad;
                }
            }
            if (_chip.pos_8.corriente){
                // pasar corriente a pos 9 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.polaridad = !_chip.pos_8.polaridad;
                }
            }
            if (_chip.pos_10.corriente){
                // pasar corriente a pos 11 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.polaridad = !_chip.pos_10.polaridad;
                }
            }
            if (_chip.pos_12.corriente){
                // pasar corriente a pos 13 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = true;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = !_chip.pos_12.polaridad;
                }
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

        if (!_chip.pos_14.corriente){
            if (!_chip.posicion2.corriente){
                // pasar corriente a pos 2 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_3.coordenadax][i]._posicion.polaridad = !_chip.posicion1.polaridad;
                }
            }
            if (!_chip.pos_4.corriente){
                // pasar corriente a pos 5 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_5.coordenadax][i]._posicion.polaridad = !_chip.pos_4.polaridad;
                }
            }
            if (!_chip.pos_6.corriente){
                // pasar corriente a pos 7 y la polaridad contraria
                for (int i = 3 ; i < 8 ; i++){
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_7.coordenadax][i]._posicion.polaridad = !_chip.pos_6.polaridad;
                }
            }
            if (!_chip.pos_8.corriente){
                // pasar corriente a pos 9 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_9.coordenadax][i]._posicion.polaridad = !_chip.pos_8.polaridad;
                }
            }
            if (!_chip.pos_10.corriente){
                // pasar corriente a pos 11 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_11.coordenadax][i]._posicion.polaridad = !_chip.pos_10.polaridad;
                }
            }
            if (!_chip.pos_12.corriente){
                // pasar corriente a pos 13 y la polaridad contraria
                for (int i = 9 ; i < 14 ; i++){
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.corriente = false;
                    _Protoboard.protoboard[_chip.pos_13.coordenadax][i]._posicion.polaridad = !_chip.pos_12.polaridad;
                }
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
}
