package com.example.proyprotoboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controlador_Protoboard implements Initializable {



    private double startX;
    private double startY;
    private double x_switch;
    private double y_switch;
    private Boolean agrega_switch = false;


    private Boolean movible_cable = false;
    private Boolean agrega_led=false;
    private Boolean led_puesto=false;
    private Boolean patita_led_1=false;
    private Boolean patita_led_2=false;
    private int cantidad_patitas=0;
    int x_led=0; // inicializacion posicion x del led
    int y_led=0; // inicializacion posicion y del led

    @FXML
    private Canvas tablero;


    int contador_cables;

    private void dibujarProtoboard(GraphicsContext gc){
        int x= 10;
        int y= 10;

        gc.setStroke(Color.LIGHTGRAY);
        String letras ="jihgfedcba";

        // bucle para hacer el rectangulo solo con lineas

        for( int k = 0 ; k < 300 ; k++){        // k < a 350 es la altura del rectangulo, siendo 350 el tope de la altura
            gc.strokeLine(0, k,605+x, k);
        }
        gc.setFill(Color.GRAY);
        // bucle para rellenar de puntos el rectangulo
        for (int j =0; j < 14 ; j++){
            for (int i = 0 ; i < 30 ; i++){
                gc.fillOval(x,y,8,8); // luego del X e Y va el tamaño del punto
                x=x+20;
            } x= 10;
            if (j==1) {
                y=y+25;
            } if (j==6){
                y=y+20;
            }
            if (j==11){
                y=y+25;
            }
            y = y+15;
        }

        // bucle para rellenar el canal central
        x= 10;
        gc.setStroke(Color.DARKGRAY);
        for( int k = 138 ; k < 155 ; k++){        // k < a 350 es la altura del rectangulo, siendo 350 el tope de la altura
            gc.strokeLine(0, k,605+x, k);
        }

        // hacer simbolos + y -
        // simbolos -
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        x=2; y=10;
        //gc.setFont(new Font("Arial", 40));
        gc.fillText("-", x,y); gc.strokeLine(x+10,y -4,600,y -4) ;
        y= 260;
        gc.fillText("-", x,y); gc.strokeLine(x+10,y -4,600,y -4) ;


        // simbolos +
        y=42;
        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.fillText("+", x,y); gc.strokeLine(x+10,y-4,600,y-4);
        y=290;
        gc.fillText("+", x,y); gc.strokeLine(x+10,y-4,600,y-4);



        // bucle para hacer los numeros
        x=8;
        y=55;
        gc.setFill(Color.BLACK);
        //gc.setFont(new Font("Arial", 60));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.CENTER);
        for (int i = 1 ; i <=30 ; i++) {
            gc.fillText(String.valueOf(i), x, y);
            x += 20;
        }
        // bucle para hacer las letras al costado
        x= 605;
        y= 68;
        int cuenta_letras=0;
        for (char letra : letras.toCharArray()) {
            cuenta_letras++;
            if (cuenta_letras==6){
                y=y+20;
            }
            gc.fillText(String.valueOf(letra), x, y);
            y += 15;
        }
    }
    private void dibujarBateria(GraphicsContext gc){
        int x = 660;


        // bucle para hacer el rectangulo solo con lineas

        gc.setStroke(Color.BLACK);
        int k=0;
        for( k = 130 ; k < 220 ; k++){        // k < a 350 es la altura del rectangulo, siendo 350 el tope de la altura
            gc.strokeLine(x, k,x+70, k);
        } gc.setStroke(Color.BROWN);
        for ( k=k ; k < 250 ; k++){
            gc.strokeLine(x, k,x+70, k);
        }
        // posiblemente agregar + y - de la bateria

        gc.setStroke(Color.GRAY);
        for ( k=k ; k < 260 ; k++){ // hjacer las puntitas de la bateria
            gc.strokeLine(x+10, k,x+25, k);
            gc.strokeLine(x+45, k,x+60, k);
        }


    }
    public void AgregarSwitch() {
        JOptionPane.showMessageDialog(null, "Seleccione el punto central de donde desea ubicar");
        agrega_switch = true;
    }

    public void dibujarSwitch() {
        GraphicsContext gc = tablero.getGraphicsContext2D();
        gc.setStroke(Color.GREY);
        for (int k = 0; k < 48; k++) {        // k < a 350 es la altura del rectangulo, siendo 350 el tope de la altura
            gc.strokeLine(x_switch, y_switch + k, x_switch+48, y_switch + k); //ancho del switch = 48
        }
        gc.fillOval(x_switch+4, y_switch+4, 40, 40);

    }
    public void dibujarCable(ActionEvent event){

        if (contador_cables < 2){
            JOptionPane.showMessageDialog(null,"Seleccione la posicion inicial ( Bateria )");

        } // comprobar donde empieza la posicion x e y para ver si empieza con un cable " azul " o " rojo " ( esto es nuestra implementacion, no un requisito )



        movible_cable = true;
        GraphicsContext gc = tablero.getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        contador_cables++;


    }
    public void dibujarLed(){
        GraphicsContext gc = tablero.getGraphicsContext2D();

        gc.setFill(Color.DARKRED);
        gc.fillOval(x_led,y_led,30,30);

        /* pines del led (comentados porque se dibujan al agregar el led)
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(2);
        gc.strokeLine(x_led+8, y_led+28, x_led+8, y_led+68);
        gc.strokeLine(x_led+23, y_led+28, x_led+23, y_led+68);*/
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablero.setOnMousePressed(this::click);
        tablero.setOnMouseDragged(this::arrastrarMouse);
        tablero.setOnMouseReleased(this::soltarMouse);


        dibujarProtoboard(tablero.getGraphicsContext2D());
        dibujarBateria(tablero.getGraphicsContext2D());


    }
    public void activaLed(){
        led_puesto=false;
        agrega_led=true;
        patita_led_1=false;
        cantidad_patitas=0;
    }

    private void soltarMouse(MouseEvent event) {
        GraphicsContext gc = tablero.getGraphicsContext2D();
        if (movible_cable){
            gc.strokeLine(startX, startY, event.getX(), event.getY());
            contador_cables++;
            movible_cable = false;
        } else if (patita_led_1 && cantidad_patitas<2){
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(3);
            gc.strokeLine(startX, startY, event.getX(), event.getY());
            patita_led_1=false;
            cantidad_patitas++;
            //patita_led_2=true;
        } else if (patita_led_2){
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(3);
            gc.strokeLine(startX,startY,event.getX(), event.getY());
            patita_led_2=false;
        }
        if (led_puesto && !patita_led_1){
            patita_led_1=true;

        }

    }

    private void click(MouseEvent event) {
        if (agrega_switch) { // agrega un led al hacer click en una posicion // verificaciones y demas
            x_switch = (int) event.getX()-24;
            y_switch = (int) event.getY()-24;
            dibujarSwitch();
            agrega_switch = false;
        }
        if (agrega_led){ // agrega un led al hacer click en una posicion // verificaciones y demas
            x_led= (int) event.getX()-15;
            y_led= (int) event.getY()-15;
            dibujarLed();
            agrega_led=false;
            led_puesto=true;
            patita_led_1=false;

        } else if (patita_led_1 && led_puesto &&  cantidad_patitas<2){
            startX = event.getX();
            startY = event.getY();
        }
        if (patita_led_2){
            startX = event.getX();
            startY = event.getY();
        }
        if (movible_cable){
            startX = event.getX();
            startY = event.getY();

        }

    }

    private void arrastrarMouse(MouseEvent event) {
        GraphicsContext gc = tablero.getGraphicsContext2D();


    }

}
