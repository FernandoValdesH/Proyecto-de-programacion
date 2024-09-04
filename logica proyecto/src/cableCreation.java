public void cableSet(protoboard _Protoboard){
    int i,j =0;
    cable _Cable = new cable();
    //Ingresar coordenadas y color desde protoboard grafico
    _Cable.posicion1.coordenadax = 1;
    _Cable.posicion1.coordenaday = 1;
    _Cable.posicion2.coordenadax = 1;
    _Cable.posicion2.coordenaday = 1;
    _Cable.color = "rojo";
    
    _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._cable = _Cable;
    _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._cable = _Cable;
    _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday].conexion = true;
    _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday].conexion = true;
    
    if(_Cable.conexionBateria){
        _Cable.posicion1.corriente = true;
        _Cable.posicion2.corriente = true;
        _Cable.posicion1.polaridad = true;
        _Cable.posicion2.polaridad = true;
        _Cable.posicion1.corriente = true;
        _Cable.posicion2.corriente = true;
        _Cable.posicion1.polaridad = false;
        _Cable.posicion2.polaridad = false;
    }
    if(_Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.corriente || _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.corriente){
        _Cable.posicion1.corriente = true;
        _Cable.posicion2.corriente = true;
        _Cable.posicion1.polaridad = _Protoboard.protoboard[_Cable.posicion1.coordenadax][_Cable.posicion1.coordenaday]._posicion.polaridad;
        _Cable.posicion2.polaridad = _Protoboard.protoboard[_Cable.posicion2.coordenadax][_Cable.posicion2.coordenaday]._posicion.polaridad;
        //while("recorrer corriente basado en posicion, si es 0-1-12-13, significa que es un bus y se requiere recorrer horizontalmente, si es entre 2-6 se considera vertical superior y si es 7-11, vertical inferior, se debe realizar esta verificacion para cada uno de los cables")
    }}//else"considerar el caso en donde ninguna de las conexiones del cable posean conexion"
    //if("conexion a bateria cable == true" -> cambiar corriente cable = true, establecer polaridad) Check \(~u~)/
    //if("luego de conectar cable, revisar donde se conecta y cambiar lugares adyacentes")
    //Considerar que en cada momento en el que se conecte un cable, se debera realizar el cambio a todo el protoboard, pero no es necesario realizar el cambio en todo el protoboard al mismo tiempo, solo en la zona existente
    //Considerar posible fallo al conectar en 2 espacios con polaridades contrarias
    
