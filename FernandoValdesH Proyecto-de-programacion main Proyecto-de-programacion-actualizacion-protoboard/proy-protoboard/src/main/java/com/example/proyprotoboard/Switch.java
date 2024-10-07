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

        if (_Protoboard.protoboard[pos_central_x-1][pos_central_y-1]._posicion.corriente){
            if (pos_central_y-1 == 6){
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = true;
                }
            } else if (pos_central_y-1 > 1 && pos_central_y-1<7){
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = true;
                }
            }
            else if (pos_central_y-1 > 7 && pos_central_y-1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x-1][pos_central_y-1]._posicion.polaridad;
                }
            }

        } else if (_Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._posicion.corriente){
            if (pos_central_y-1 == 6){
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._posicion.polaridad;
                }
            } else if (pos_central_y-1 > 1 && pos_central_y-1<7){
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._posicion.polaridad;
                }
            }
            else if (pos_central_y-1 > 7 && pos_central_y-1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._posicion.polaridad;
                }
            }
        } else if (_Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._posicion.corriente){
            if (pos_central_y+1 == 6){
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._posicion.polaridad;
                }
            } else if (pos_central_y+1 > 1 && pos_central_y+1<7) {
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2; i < 7; i++) {
                    _Protoboard.protoboard[pos_central_x - 1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x - 1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x + 1][pos_central_y + 1]._posicion.polaridad;
                }
            } else if (pos_central_y+1 > 7 && pos_central_y+1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._posicion.polaridad;
                }
            }
        } else if (_Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._posicion.corriente){
            if (pos_central_y+1 == 6){
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._posicion.polaridad;
                }
            } else if (pos_central_y+1 > 1 && pos_central_y+1<7){
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._posicion.polaridad;
                }
            }
            else if (pos_central_y+1 > 7 && pos_central_y+1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = true;
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._posicion.polaridad;
                }
            }
        }

    }

    public void eliminarCorriente (protoboard _Protoboard, int pos_central_x, int pos_central_y){
        pos_central_x++;
        pos_central_y++;
        System.out.println("pos_central_x: "+pos_central_x);
        System.out.println("pos_central_y: "+pos_central_y);

        // al eliminar corriente deberia borrar su propia corriente, ya que la otra ya fue eliminada
        if (_Protoboard.protoboard[pos_central_x-1][pos_central_y-1]._posicion.corriente){
            if (pos_central_y-1 == 6){
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = false;
                }
            } else if (pos_central_y-1 > 1 && pos_central_y-1<7){
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = false;
                }
            }
            else if (pos_central_y-1 > 7 && pos_central_y-1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x-1][pos_central_y-1]._posicion.polaridad;
                }
            }

        } else if (_Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._posicion.corriente){

            if (pos_central_y-1 == 6){
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int i = 2 ; i < 7 ; i++){
                    // borrar la corrientes de ambas posiciones
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._posicion.polaridad;
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = false;

                }
            } else if (pos_central_y-1 > 1 && pos_central_y-1<7){
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._posicion.polaridad;
                }
            }
            else if (pos_central_y-1 > 7 && pos_central_y-1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y-1]._posicion.polaridad;
                }
            }
        } else if (_Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._posicion.corriente){
            if (pos_central_y+1 == 6){
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._posicion.polaridad;
                }
            } else if (pos_central_y+1 > 1 && pos_central_y+1<7) {
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2; i < 7; i++) {
                    _Protoboard.protoboard[pos_central_x + 1][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_central_x + 1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x + 1][pos_central_y + 1]._posicion.polaridad;
                }
            } else if (pos_central_y+1 > 7 && pos_central_y+1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_central_x+1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x+1][pos_central_y+1]._posicion.polaridad;
                }
            }
        } else if (_Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._posicion.corriente){
            if (pos_central_y+1 == 6){
                // se llenaria la columna de 2 mas adelante pero dependenderia de donde hay corriente, osea comprobar la posicion anterior o siguiente
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._posicion.polaridad;
                }
            } else if (pos_central_y+1 > 1 && pos_central_y+1<7){
                // se llenaria la columna de 2 mas adelante tambien con corriente pero con un for que empieza en 2
                for (int i = 2 ; i < 7 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._posicion.polaridad;
                }
            }
            else if (pos_central_y+1 > 7 && pos_central_y+1<13){
                // se llenaria la columna de 2 mas adelante con corriente pero con un for que empieza en 8
                for (int i = 8 ; i < 13 ; i++){
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.corriente = false;
                    _Protoboard.protoboard[pos_central_x-1][i]._posicion.polaridad = _Protoboard.protoboard[pos_central_x-1][pos_central_y+1]._posicion.polaridad;
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
                System.out.println("entra acaaaaaa");
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
