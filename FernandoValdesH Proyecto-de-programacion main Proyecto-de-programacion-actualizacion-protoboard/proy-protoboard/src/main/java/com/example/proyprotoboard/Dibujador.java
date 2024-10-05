package com.example.proyprotoboard;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Dibujador {

    public int transformacionY_coordA_Matriz(double y){
        if (y < 68){
            y = (y - 15) /15;
        } else if (y >= 68 && y < 150){
            y =  (((y - 15) /15) -1);
        } else if (y >= 150 && y <= 225){
            y =  (((y ) /15)-3);
        } else if (y > 225){
            y = ((y  /15 )-4);
        }
        return (int) y;
    }

    public void dibujarProtoboard(GraphicsContext gc, double x_inicio, double y_inicio, protoboard protoboard){

        gc.setLineWidth(1);
        gc.setStroke(Color.LIGHTGRAY);
        String letras ="jihgfedcba";

        // bucle para hacer el rectangulo solo con lineas

        for( int k = 0 ; k < 300 ; k++){        // k < a 350 es la altura del rectangulo, siendo 350 el tope de la altura
            gc.strokeLine(0, k,605+x_inicio, k);
        }
        gc.setFill(Color.GRAY);
        // bucle para rellenar de puntos el rectangulo

        for (int j = 0 ; j < 15 ; j++){
            for (int i = 0 ; i < 30 ; i++){
                int transformacion_inversa_x = ((i * 20) + 15)-5;
                if (j==7){
                    j++;
                }
                if (protoboard.protoboard[i][j]._posicion.corriente){
                    int transformacion_inversa_y = 0;
                    if (j >= 0 && j < 2) {
                        transformacion_inversa_y = ((j * 15 ) + 15) - 5;
                    } else if (j >= 2 && j <= 6) {
                        transformacion_inversa_y = (((j + 2) * 15) -5) + 10;
                    } else if (j > 6 && j <= 12) {
                        transformacion_inversa_y = ((j + 3) * 15) - 5;
                    } else if (j > 12) {
                        transformacion_inversa_y = ((j + 4) * 15) + 5;
                    }
                    if (protoboard.protoboard[i][j]._posicion.polaridad){
                        gc.setFill(Color.RED);
                        gc.fillOval(transformacion_inversa_x, transformacion_inversa_y, 8, 8);
                    } else{
                        gc.setFill(Color.BLUE);
                        gc.fillOval(transformacion_inversa_x, transformacion_inversa_y, 8, 8);
                    }

                } else{
                    int transformacion_inversa_y = 0;
                    if (j >= 0 && j < 2) {
                        transformacion_inversa_y = ((j * 15 ) + 15) - 5;
                    } else if (j >= 2 && j <= 6) {
                        transformacion_inversa_y = (((j + 2) * 15) -5) + 10;
                    } else if (j > 6 && j <= 12) {
                        transformacion_inversa_y = ((j + 3) * 15) - 5;
                    } else if (j > 12) {
                        transformacion_inversa_y = ((j + 4) * 15) + 5;
                    }
                    gc.setFill(Color.GRAY);
                    gc.fillOval(transformacion_inversa_x,transformacion_inversa_y,8,8); // luego del X e Y va el tamaño del punto
                }

            }

        }



        // bucle para rellenar el canal central
        x_inicio= 10;
        gc.setStroke(Color.DARKGRAY);
        for( int k = 138 ; k < 155 ; k++){        // k < a 350 es la altura del rectangulo, siendo 350 el tope de la altura
            gc.strokeLine(0, k,605+x_inicio, k);
        }

        gc.setFont(new Font("Arial", 12));
        // hacer simbolos + y -
        // simbolos -
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        x_inicio=2; y_inicio=10;
        //gc.setFont(new Font("Arial", 40));
        gc.fillText("-", x_inicio,y_inicio); gc.strokeLine(x_inicio+10,y_inicio -4,600,y_inicio -4) ;
        y_inicio= 260;
        gc.fillText("-", x_inicio,y_inicio); gc.strokeLine(x_inicio+10,y_inicio -4,600,y_inicio -4) ;


        // simbolos +
        y_inicio=42;
        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.fillText("+", x_inicio,y_inicio); gc.strokeLine(x_inicio+10,y_inicio-4,600,y_inicio-4);
        y_inicio=290;
        gc.fillText("+", x_inicio,y_inicio); gc.strokeLine(x_inicio+10,y_inicio-4,600,y_inicio-4);



        // bucle para hacer los numeros
        x_inicio=8;
        y_inicio=55;

        gc.setFill(Color.BLACK);
        //gc.setFont(new Font("Arial", 60));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.CENTER);
        for (int i = 1 ; i <=30 ; i++) {
            gc.fillText(String.valueOf(i), x_inicio, y_inicio);
            x_inicio += 20;
        }
        // bucle para hacer las letras al costado
        x_inicio= 605;
        y_inicio= 68;
        int cuenta_letras=0;
        for (char letra : letras.toCharArray()) {
            cuenta_letras++;
            if (cuenta_letras==6){
                y_inicio=y_inicio+20;
            }
            gc.fillText(String.valueOf(letra), x_inicio, y_inicio);
            y_inicio += 15;
        }
    }

    public void dibujarBateria(GraphicsContext gc, double x_bateria, boolean switch_bateria) {


        // bucle para hacer el rectangulo solo con lineas

        gc.setStroke(Color.BLACK);
        int k=0;
        for( k = 130 ; k < 220 ; k++){        // k < a 350 es la altura del rectangulo, siendo 350 el tope de la altura
            gc.strokeLine(x_bateria, k,x_bateria+70, k);
        } gc.setStroke(Color.BROWN);
        for ( k=k ; k < 250 ; k++){
            gc.strokeLine(x_bateria, k,x_bateria+70, k);
        }

        gc.setStroke(Color.GRAY);
        for ( k=k ; k < 260 ; k++){ // hacer las puntitas de la bateria
            gc.strokeLine(x_bateria+10, k,x_bateria+25, k);
            gc.strokeLine(x_bateria+45, k,x_bateria+60, k);
        }

        // hacer simbolos + y -
        // simbolos -
        gc.setFill(Color.BLUE);
        gc.setFont(new Font("Arial", 30));
        gc.fillText("-", x_bateria+48,252);
        gc.setFill(Color.RED);
        gc.setFont(new Font("Arial", 19));
        gc.fillText("+", x_bateria+13,253);

        // dibujar switch para pasar o no corriente
        gc.setStroke(Color.DARKGRAY);
        k = 150;
        for ( k=k ; k < 180 ; k++){
            gc.strokeLine(x_bateria+10, k,x_bateria+60, k);
        } k = 155;

        if (switch_bateria){
            gc.setStroke(Color.GREEN);
            gc.setLineWidth(3);
            for ( k=k ; k < 175 ; k++){
                gc.strokeLine(x_bateria+30, k,x_bateria+59, k);
            }
        } else{
            gc.setStroke(Color.DARKGRAY);
            for ( k=k ; k < 175 ; k++){
                gc.strokeLine(x_bateria+30, k,x_bateria+59, k);
            } k = 155;
            gc.setLineWidth(3);
            gc.setStroke(Color.INDIANRED);
            for ( k=k ; k < 175 ; k++){
                gc.strokeLine(x_bateria+11, k,x_bateria+34, k);
            }
        }



    }
    public void dibujarLed(GraphicsContext gc, double x_led, double y_led, Color color) {
        gc.setFill(color);
        gc.fillOval(x_led, y_led, 30, 30);
    }

    public void dibujarCable(GraphicsContext gc, int pos_inicio_x, int pos_inicio_y, int pos_final_x, int pos_final_y){
        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        gc.strokeLine(pos_inicio_x, pos_inicio_y, pos_final_x, pos_final_y);
    }

    public void dibujarSwitch(GraphicsContext gc, double x_switch, double y_switch) {
        gc.setStroke(Color.GREY);
        gc.setLineWidth(2);
        for (int k = 0; k < 43; k++) {        // k < a 350 es la altura del rectangulo, siendo 350 el tope de la altura
            gc.strokeLine(x_switch, y_switch + k , x_switch+46, y_switch + k); //ancho del switch = 48
        }
        // esquinas del switch
        gc.setFill(Color.DARKSLATEGRAY);
        gc.fillOval(x_switch, y_switch+3,8,8);
        gc.fillOval(x_switch, y_switch+33,8,8);
        gc.fillOval(x_switch+39, y_switch+3,8,8);
        gc.fillOval(x_switch+39, y_switch+33,8,8);

        // boton del switch
        gc.setFill(Color.BLACK);
        gc.fillOval(x_switch+8, y_switch+8, 30, 30);
    }

    public void dibujarOctoSwitch(double x, double y, GraphicsContext gc, protoboard _protoboard){
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        for (int k = 0; k < 45; k++) {
            gc.strokeLine(x, y + k , x+155, y + k);
        }
        // switches del switch xd
        gc.setStroke(Color.WHITE);
        gc.setFill(Color.WHITE);
        String numeros = "12345678";
        gc.setFont(new Font("Arial", 9));
        gc.fillText("ON", x+5, y+5);
        gc.setFont(new Font("Arial", 10));
        int x_provisoria = (int) x;
        for (int k = 0; k < 8; k++){
            gc.fillText(String.valueOf(numeros.charAt(k)), x_provisoria+7, y+40);
            x_provisoria+=19;
        }

        int transformacion_del_x = (int) (((x + 70) - 15) / 20);
        int transformacion_del_y = transformacionY_coordA_Matriz(y + 24);

        for (int i = 0 ; i < 8 ; i++){
            switch (i){
                case 0:
                    if (_protoboard.protoboard[transformacion_del_x][transformacion_del_y]._octoSwitch.mini_switch_1.encendido){
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    }else{
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    } x+=19;
                    break;
                case 1:
                    if (_protoboard.protoboard[transformacion_del_x][transformacion_del_y]._octoSwitch.mini_switch_2.encendido){
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    }else{
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    } x+=19;
                    break;
                case 2:
                    if (_protoboard.protoboard[transformacion_del_x][transformacion_del_y]._octoSwitch.mini_switch_3.encendido){
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    }else{
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    } x+=19;
                    break;
                case 3:
                    if (_protoboard.protoboard[transformacion_del_x][transformacion_del_y]._octoSwitch.mini_switch_4.encendido){
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    }else{
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    } x+=19;
                    break;
                case 4:
                    if (_protoboard.protoboard[transformacion_del_x][transformacion_del_y]._octoSwitch.mini_switch_5.encendido){
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    }else{
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    } x+=19;
                    break;
                case 5:
                    if (_protoboard.protoboard[transformacion_del_x][transformacion_del_y]._octoSwitch.mini_switch_6.encendido){
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    }else{
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    } x+=19;
                    break;
                case 6:
                    if (_protoboard.protoboard[transformacion_del_x][transformacion_del_y]._octoSwitch.mini_switch_7.encendido){
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    }else{
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    } x+=19;
                    break;
                case 7:
                    if (_protoboard.protoboard[transformacion_del_x][transformacion_del_y]._octoSwitch.mini_switch_8.encendido){
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    }else{
                        for (int k = (int) (y+10); k < y+35; k++){
                            gc.setStroke(Color.DARKBLUE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                        for (int k = (int) (y+20); k < y+35; k++){
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(x+5, k, x+15, k);
                        }
                    } x+=19;
                    break;
            }

        }

    }

    public void dibujarResistencia(GraphicsContext gc, double x_resistencia, double y_resistencia) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(x_resistencia, y_resistencia, x_resistencia + 10, y_resistencia);
        gc.strokeLine(x_resistencia + 40, y_resistencia, x_resistencia + 50, y_resistencia);
        gc.strokeLine(x_resistencia + 10, y_resistencia, x_resistencia + 15, y_resistencia - 10);
        gc.strokeLine(x_resistencia + 15, y_resistencia - 10, x_resistencia + 35, y_resistencia - 10);
        gc.strokeLine(x_resistencia + 35, y_resistencia - 10, x_resistencia + 40, y_resistencia);
        gc.strokeLine(x_resistencia + 15, y_resistencia + 10, x_resistencia + 35, y_resistencia + 10);
    }

    public void dibujarChip(){}

    public void dibujarDisplay(){}
}
