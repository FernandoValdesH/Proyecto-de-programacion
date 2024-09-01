public Led ledInitiatorStart (Led _led){
    if(_led.posicion1.coordenadax != null & _led.posicion1.coordenaday != null & _led.posicion2.coordenadax != null & _led.posicion2.coordenaday != null){
        if(_led.posicion1.corriente & _led.posicion2.corriente){
            if(_led.posicion1.polaridad != _led.posicion2.polaridad){
                _led.encendido = true;
            }
        }
    }
}
