public Indicador verificadorProtoboard(logicalProtoboard[][] protoboard){
    int i = 0;int j=0;
    while(i<protoboard.length){
        while(j<protoboard.length){
            if(protoboard[i][j].conexion == true){
                //Basado en logica convencional de protoboard,se requerira revisar cada puerto para saber si posee una conexion, saber a donde esta conectada esta conexion, y finalmente que cualidades deben cambiar en los puertos adyacentes
                
                if(protoboard[i][j]._cable.posicion1.coordenadax != null){
                    if(protoboard[i][j]._cable.corriente == true){

                    }
                }
                if(protoboard[i][j]._cable.posicion2.coordenaday != null){
                    if(protoboard[i][j]._cable.corriente == true){

                    }
                }
                if(protoboard[i][j]._led.posicion1.coordenadax != null){
                    if(protoboard[i][j]._led.corriente == true){

                    }
                }
                if(protoboard[i][j]._led.posicion2.coordenaday != null){
                    if(protoboard[i][j]._led.corriente == true){
                        if(protoboard[i][j]._led.polaridad == true){
                            if(j<5){
                                int tempj = 0;
                                while(tempj <5){
                                    protoboard[i][tempj]._polaridad = true;
                                }
                            }
                        }
                        if(protoboard[i][j]._led.polaridad == false){
                            if(j>5){

                            }
                        }
                    }
                }
            }
            j++;
        }
        i++;
    }
}