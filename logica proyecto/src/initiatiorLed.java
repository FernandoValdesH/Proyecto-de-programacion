public void ledInitiatorStart (protoboard _Protoboard){
    Led _led = new Led();
    int i,j=0;
    _led.posicion1.coordenadax = 1;
    _led.posicion1.coordenaday = 1;
    _led.posicion2.coordenadax = 1;
    _led.posicion2.coordenaday = 1;
    if(_Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.corriente || _Protoboard.protoboard[_led.posicion2.coordenadax][_led.posicion2.coordenaday]._posicion.corriente){
        _led.posicion1.corriente = true;
        _led.posicion2.corriente = true;
        _led.posicion1.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
        _led.posicion2.polaridad = _Protoboard.protoboard[_led.posicion1.coordenadax][_led.posicion1.coordenaday]._posicion.polaridad;
    }
}
//Repetir lo mismo que en cable, pero realizando la comprobacion adicional de requerir polaridades inversas en cada "pata"