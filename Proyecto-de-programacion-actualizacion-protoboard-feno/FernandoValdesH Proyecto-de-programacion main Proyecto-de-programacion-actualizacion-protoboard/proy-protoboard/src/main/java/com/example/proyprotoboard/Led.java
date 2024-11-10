package com.example.proyprotoboard;
public class Led extends Indicador{
    Boolean encendido;
    Boolean revisado;
    Boolean quemado;
    String color;
    public Led(){
        super();
        this.encendido=false;
        this.revisado=false;
        this.quemado = false;
        this.color = " ";
        this.posicion1.polaridad= true;
        this.posicion2.polaridad= false;
    }

    public void chequearQuemado(protoboard _Protoboard, Led _led){
        int pos_1_x_led = _led.posicion1.coordenadax;
        int pos_1_y_led = _led.posicion1.coordenaday;
        int pos_2_x_led = _led.posicion2.coordenadax;
        int pos_2_y_led = _led.posicion2.coordenaday;
        if (_Protoboard.protoboard[pos_1_x_led][pos_1_y_led]._posicion.corriente && _led.posicion1.polaridad != _Protoboard.protoboard[pos_1_x_led][pos_1_y_led]._posicion.polaridad ||_Protoboard.protoboard[pos_2_x_led][pos_2_y_led]._posicion.corriente && _led.posicion2.polaridad != _Protoboard.protoboard[pos_2_x_led][pos_2_y_led]._posicion.polaridad){
            _led.quemado = true;
        }
    }
}
