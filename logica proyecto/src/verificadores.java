public void verificadorProtoboard(protoboard _protoboard){
    int i = 0;int j=0;
    while(i<30){
        while(j<14){
            //Basado en logica convencional de protoboard,se requerira revisar cada puerto para saber si posee una conexion, saber a donde esta conectada esta conexion, y finalmente que cualidades deben cambiar en los puertos adyacentes
            if(_protoboard.protoboard[i][j].conexion == true){
                    if(_protoboard.protoboard[i][j]._cable != null & (_protoboard.protoboard[i][j]._cable.posicion1.coordenadax == i & _protoboard.protoboard[i][j]._cable.posicion1.coordenaday == j) & _protoboard.protoboard[i][j]._cable.posicion1.corriente & _protoboard.protoboard[i][j]._cable.posicion2.corriente){
                        if(_protoboard.protoboard[i][j]._cable.posicion1.coordenaday == 0 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday == 0){
                            int tempi = 0;
                            while(tempi<30){
                                _protoboard.protoboard[tempi][j]._posicion.corriente = true;
                                _protoboard.protoboard[tempi][j]._posicion.polaridad = true;
                                
                            }
                        }
                        if(_protoboard.protoboard[i][j]._cable.posicion1.coordenaday == 1 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday == 1){
                            int tempi = 0;
                            while(tempi<30){
                                _protoboard.protoboard[tempi][j]._posicion.corriente = true;
                                _protoboard.protoboard[tempi][j]._posicion.polaridad = false;
                            }
                        }
                        if(_protoboard.protoboard[i][j]._cable.posicion1.coordenaday == 12 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday == 12){
                            int tempi = 0;
                            while(tempi<30){
                                _protoboard.protoboard[tempi][j]._posicion.corriente = true;
                                _protoboard.protoboard[tempi][j]._posicion.polaridad = true;
                            }
                        }
                        if(_protoboard.protoboard[i][j]._cable.posicion1.coordenaday == 13 || _protoboard.protoboard[i][j]._cable.posicion2.coordenaday == 13){
                            int tempi = 0;
                            while(tempi<30){
                                _protoboard.protoboard[tempi][j]._posicion.corriente = true;
                                _protoboard.protoboard[tempi][j]._posicion.polaridad = false;
                            }
                        }
                        if((1<_protoboard.protoboard[i][j]._cable.posicion1.coordenaday & _protoboard.protoboard[i][j]._cable.posicion1.coordenaday <7)){
                            int tempj = 2;
                            while(tempj<7){
                                _protoboard.protoboard[i][j]._posicion.corriente = true;
                                _protoboard.protoboard[i][j]._posicion.corriente = _protoboard.protoboard[i][j]._cable.posicion1.polaridad;
                            }
                        }
                    }
            }
        j++;
        }i++;
        j=0;
    }
}