package com.example.proyprotoboard;

public class Display extends Indicador{
    Posicion pos_3;
    Posicion pos_4;
    Posicion pos_5;
    Posicion pos_6;
    Posicion pos_7;
    Posicion pos_8;
    Posicion pos_9;
    Posicion pos_10;
    boolean linea_a;
    boolean linea_b;
    boolean linea_c;
    boolean linea_d;
    boolean linea_e;
    boolean linea_f;
    boolean linea_g;
    boolean punto;

    public Display(){
        super();
        this.pos_3 = new Posicion();
        this.pos_4 = new Posicion();
        this.pos_5 = new Posicion();
        this.pos_6 = new Posicion();
        this.pos_7 = new Posicion();
        this.pos_8 = new Posicion();
        this.pos_9 = new Posicion();
        this.pos_10 = new Posicion();
        this.linea_a = false;
        this.linea_b = false;
        this.linea_c = false;
        this.linea_d = false;
        this.linea_e = false;
        this.linea_f = false;
        this.linea_g = false;
        this.punto = false;
    }

    public void activarLinea(protoboard _Protoboard, Display _display){
        _display.posicion1.corriente = _Protoboard.protoboard[_display.posicion1.coordenadax][_display.posicion1.coordenaday]._posicion.corriente;
        _display.posicion1.polaridad = _Protoboard.protoboard[_display.posicion1.coordenadax][_display.posicion1.coordenaday]._posicion.polaridad;

        _display.posicion2.corriente = _Protoboard.protoboard[_display.posicion2.coordenadax][_display.posicion2.coordenaday]._posicion.corriente;
        _display.posicion2.polaridad = _Protoboard.protoboard[_display.posicion2.coordenadax][_display.posicion2.coordenaday]._posicion.polaridad;

        _display.pos_3.corriente = _Protoboard.protoboard[_display.pos_3.coordenadax][_display.pos_3.coordenaday]._posicion.corriente;
        _display.pos_3.polaridad = _Protoboard.protoboard[_display.pos_3.coordenadax][_display.pos_3.coordenaday]._posicion.polaridad;

        _display.pos_4.corriente = _Protoboard.protoboard[_display.pos_4.coordenadax][_display.pos_4.coordenaday]._posicion.corriente;
        _display.pos_4.polaridad = _Protoboard.protoboard[_display.pos_4.coordenadax][_display.pos_4.coordenaday]._posicion.polaridad;

        _display.pos_5.corriente = _Protoboard.protoboard[_display.pos_5.coordenadax][_display.pos_5.coordenaday]._posicion.corriente;
        _display.pos_5.polaridad = _Protoboard.protoboard[_display.pos_5.coordenadax][_display.pos_5.coordenaday]._posicion.polaridad;

        _display.pos_6.corriente = _Protoboard.protoboard[_display.pos_6.coordenadax][_display.pos_6.coordenaday]._posicion.corriente;
        _display.pos_6.polaridad = _Protoboard.protoboard[_display.pos_6.coordenadax][_display.pos_6.coordenaday]._posicion.polaridad;

        _display.pos_7.corriente = _Protoboard.protoboard[_display.pos_7.coordenadax][_display.pos_7.coordenaday]._posicion.corriente;
        _display.pos_7.polaridad = _Protoboard.protoboard[_display.pos_7.coordenadax][_display.pos_7.coordenaday]._posicion.polaridad;

        _display.pos_8.corriente = _Protoboard.protoboard[_display.pos_8.coordenadax][_display.pos_8.coordenaday]._posicion.corriente;
        _display.pos_8.polaridad = _Protoboard.protoboard[_display.pos_8.coordenadax][_display.pos_8.coordenaday]._posicion.polaridad;

        _display.pos_9.corriente = _Protoboard.protoboard[_display.pos_9.coordenadax][_display.pos_9.coordenaday]._posicion.corriente;
        _display.pos_9.polaridad = _Protoboard.protoboard[_display.pos_9.coordenadax][_display.pos_9.coordenaday]._posicion.polaridad;

        _display.pos_10.corriente = _Protoboard.protoboard[_display.pos_10.coordenadax][_display.pos_10.coordenaday]._posicion.corriente;
        _display.pos_10.polaridad = _Protoboard.protoboard[_display.pos_10.coordenadax][_display.pos_10.coordenaday]._posicion.polaridad;

        if (_display.posicion1.corriente){
            _display.linea_g = true;
        }
        if (_display.posicion2.corriente){
            _display.linea_f = true;
        }
        if (_display.pos_4.corriente){
            _display.linea_a = true;
        }
        if (_display.pos_5.corriente){
            _display.linea_b = true;
        }
        if (_display.pos_6.corriente){
            _display.linea_e = true;
        }
        if (_display.pos_7.corriente){
            _display.linea_d = true;
        }
        if (_display.pos_9.corriente){
            _display.linea_c = true;
        }
        if (_display.pos_10.corriente){
            _display.punto = true;
        }
    }
    public void apagarLinea(protoboard _Protoboard, Display _display){
        _display.posicion1.corriente = _Protoboard.protoboard[_display.posicion1.coordenadax][_display.posicion1.coordenaday]._posicion.corriente;
        _display.posicion1.polaridad = _Protoboard.protoboard[_display.posicion1.coordenadax][_display.posicion1.coordenaday]._posicion.polaridad;

        _display.posicion2.corriente = _Protoboard.protoboard[_display.posicion2.coordenadax][_display.posicion2.coordenaday]._posicion.corriente;
        _display.posicion2.polaridad = _Protoboard.protoboard[_display.posicion2.coordenadax][_display.posicion2.coordenaday]._posicion.polaridad;

        _display.pos_3.corriente = _Protoboard.protoboard[_display.pos_3.coordenadax][_display.pos_3.coordenaday]._posicion.corriente;
        _display.pos_3.polaridad = _Protoboard.protoboard[_display.pos_3.coordenadax][_display.pos_3.coordenaday]._posicion.polaridad;

        _display.pos_4.corriente = _Protoboard.protoboard[_display.pos_4.coordenadax][_display.pos_4.coordenaday]._posicion.corriente;
        _display.pos_4.polaridad = _Protoboard.protoboard[_display.pos_4.coordenadax][_display.pos_4.coordenaday]._posicion.polaridad;

        _display.pos_5.corriente = _Protoboard.protoboard[_display.pos_5.coordenadax][_display.pos_5.coordenaday]._posicion.corriente;
        _display.pos_5.polaridad = _Protoboard.protoboard[_display.pos_5.coordenadax][_display.pos_5.coordenaday]._posicion.polaridad;

        _display.pos_6.corriente = _Protoboard.protoboard[_display.pos_6.coordenadax][_display.pos_6.coordenaday]._posicion.corriente;
        _display.pos_6.polaridad = _Protoboard.protoboard[_display.pos_6.coordenadax][_display.pos_6.coordenaday]._posicion.polaridad;

        _display.pos_7.corriente = _Protoboard.protoboard[_display.pos_7.coordenadax][_display.pos_7.coordenaday]._posicion.corriente;
        _display.pos_7.polaridad = _Protoboard.protoboard[_display.pos_7.coordenadax][_display.pos_7.coordenaday]._posicion.polaridad;

        _display.pos_8.corriente = _Protoboard.protoboard[_display.pos_8.coordenadax][_display.pos_8.coordenaday]._posicion.corriente;
        _display.pos_8.polaridad = _Protoboard.protoboard[_display.pos_8.coordenadax][_display.pos_8.coordenaday]._posicion.polaridad;

        _display.pos_9.corriente = _Protoboard.protoboard[_display.pos_9.coordenadax][_display.pos_9.coordenaday]._posicion.corriente;
        _display.pos_9.polaridad = _Protoboard.protoboard[_display.pos_9.coordenadax][_display.pos_9.coordenaday]._posicion.polaridad;

        _display.pos_10.corriente = _Protoboard.protoboard[_display.pos_10.coordenadax][_display.pos_10.coordenaday]._posicion.corriente;
        _display.pos_10.polaridad = _Protoboard.protoboard[_display.pos_10.coordenadax][_display.pos_10.coordenaday]._posicion.polaridad;

        if (!_display.posicion1.corriente){
            _display.linea_g = false;
        }
        if (!_display.posicion2.corriente){
            _display.linea_f = false;
        }
        if (!_display.pos_4.corriente){
            _display.linea_a = false;
        }
        if (!_display.pos_5.corriente){
            _display.linea_b = false;
        }
        if (!_display.pos_6.corriente){
            _display.linea_e = false;
        }
        if (!_display.pos_7.corriente){
            _display.linea_d = false;
        }
        if (!_display.pos_9.corriente){
            _display.linea_c = false;
        }
        if (!_display.pos_10.corriente){
            _display.punto = false;
        }

    }
}
