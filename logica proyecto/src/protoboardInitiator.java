public logicalProtoboard[][] initiator(logicalProtoboard[][] protoboard){
    int j = 0;int i = 0;
    while(i<logicalProtoboard.length){
        while(j<logicalProtoboard.length){
            logicalProtoboard[i][j].conexion = false;
            j++;
        }
        i++
        j = 0;
    }
    return protoboard;
}