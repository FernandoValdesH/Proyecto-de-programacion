package com.example.proyprotoboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controlador_Protoboard implements Initializable {


    private double punto_inicio_x_patita=0;
    private double punto_inicio_y_patita=0;
    private double punto_final_x_patita=0;
    private double punto_final_y_patita=0;
    private double startX=0;
    private double startY=0;
    private double punto_inicio_x_cable=0;
    private double punto_inicio_y_cable=0;
    private double x_switch;
    private double y_switch;
    private Boolean agrega_switch = false;
    private Boolean movible_cable = false;
    private Boolean agrega_led=false;
    private Boolean led_puesto=false;
    private Boolean patita_led_1=false;
    private Boolean patita_led_2=false;
    private int cantidad_patitas=0;
    double x_led=0; // inicializacion posicion x del led
    double y_led=0; // inicializacion posicion y del led
    private double distancia_aproximacion = 20; // distancia para que tome el punto mas cercano al mover un cable
    double punto_final_x_cable=0;
    double punto_final_y_cable=0;
    private boolean dibujar_patitas=false;

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
                x=x+20; // la distancia entre cada punto es 20
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
        gc.setFill(Color.BLACK);
        gc.fillOval(x_switch+4, y_switch+4, 40, 40);

    }
    public void dibujarCable(ActionEvent event){

        if (contador_cables < 2){
            JOptionPane.showMessageDialog(null,"Seleccione la posicion inicial");

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
        System.out.println(Color.DARKRED);
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
            punto_final_x_cable=event.getX();
            punto_final_y_cable=event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(punto_final_x_cable, punto_final_y_cable);
            if (puntoCercano != null) {
                punto_final_x_cable = puntoCercano[0];
                punto_final_y_cable = puntoCercano[1];
            }
            gc.strokeLine(punto_inicio_x_cable, punto_inicio_y_cable, punto_final_x_cable,punto_final_y_cable);
            contador_cables++;
            movible_cable = false;
        } else if (patita_led_1 && cantidad_patitas<2 && dibujar_patitas){
            punto_final_x_patita= event.getX();
            punto_final_y_patita=event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(punto_final_x_patita, punto_final_y_patita);
            if (puntoCercano != null) {
                punto_final_x_patita = puntoCercano[0];
                punto_final_y_patita = puntoCercano[1];
            }
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(3);
            gc.strokeLine(punto_inicio_x_patita, punto_inicio_y_patita, punto_final_x_patita, punto_final_y_patita);
            patita_led_1=false;
            cantidad_patitas++;

        }
        if (led_puesto && !patita_led_1){
            patita_led_1=true;

        }

    }

    private void click(MouseEvent event) {

        if (agrega_switch) { // agrega un led al hacer click en una posicion // verificaciones y demas
            x_switch = (int) event.getX();
            y_switch = (int) event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(x_switch, y_switch);
            if (puntoCercano != null) {
                x_switch = puntoCercano[0]-24;
                y_switch = puntoCercano[1]-24;
            }
            dibujarSwitch();
            agrega_switch = false;
        }
        if (agrega_led){ // agrega un led al hacer click en una posicion // verificaciones y demas
            x_led= event.getX();
            y_led= event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(x_led, y_led);
            if (puntoCercano != null) {
                x_led = puntoCercano[0]-15;
                y_led = puntoCercano[1]-15;
            }
            dibujarLed();
            agrega_led=false;
            led_puesto=true;
            patita_led_1=false;

        } else if (patita_led_1 && led_puesto &&  cantidad_patitas<2){ // aca arreglar verificacion para que no se pongan las patitas en cualquier lado
            Color color_click = getColor(event.getX(), event.getY());;
            if (color_click.equals(Color.DARKRED)){
                punto_inicio_x_patita = event.getX();
                punto_inicio_y_patita = event.getY();
                dibujar_patitas=true;

            } else{
                JOptionPane.showMessageDialog(null,"posicion invalida");
            }

            /*
            double[] puntoCercano = alcanzarPuntoCercano(punto_inicio_x_patita, punto_inicio_y_patita);
            if (puntoCercano != null) {
                punto_inicio_x_patita = puntoCercano[0];
                punto_inicio_y_patita = puntoCercano[1];
            } */ // aca la implementacion seria que inicie del LED y termine en un punto del protoboard
        }
        if (movible_cable){
            punto_inicio_x_cable = event.getX();
            punto_inicio_y_cable = event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(punto_inicio_x_cable, punto_inicio_y_cable);
            if (puntoCercano != null) {
                punto_inicio_x_cable = puntoCercano[0];
                punto_inicio_y_cable = puntoCercano[1];
            }

        }

    }

    private void arrastrarMouse(MouseEvent event) {
        GraphicsContext gc = tablero.getGraphicsContext2D();


    }

    private double[] alcanzarPuntoCercano(double x, double y) {
        double[][] puntosDisponibles = {
                // fila 0
                {15, 15}, {35, 15},{55, 15},{75, 15}, {95, 15},{115, 15},{135, 15}, {155, 15},{175, 15},{195, 15}, {215, 15},{235, 15},{255, 15}, {275, 15},{295, 15},
                {315, 15}, {335, 15},{355, 15},{375, 15}, {395, 15},{415, 15},{435, 15}, {455, 15},{475, 15},{495, 15}, {515, 15},{535, 15},{555, 15}, {575, 15},{595, 15},
                // fila 0.5
                {30, 30}, {35, 30},{55, 30},{75, 30}, {95, 30},{130, 30},{135, 30}, {305, 30},{175, 30},{195, 30}, {230, 30},{235, 30},{255, 30}, {275, 30},{295, 30},
                {330, 30}, {335, 30},{355, 30},{375, 30}, {395, 30},{430, 30},{435, 30}, {455, 30},{475, 30},{495, 30}, {530, 30},{535, 30},{555, 30}, {575, 30},{595, 30},
                // fila 1
                {15, 65}, {35, 65},{55, 65},{75, 65}, {95, 65},{115, 65},{135, 65}, {155, 65},{175, 65},{195, 65}, {215, 65},{235, 65},{255, 65}, {275, 65},{295, 65},
                {315, 65}, {335, 65},{355, 65},{375, 65}, {395, 65},{415, 65},{435, 65}, {455, 65},{475, 65},{495, 65}, {515, 65},{535, 65},{555, 65}, {575, 65},{595, 65},
                // fila 2
                {15, 85}, {35, 85},{55, 85},{75, 85}, {95, 85},{115, 85},{135, 85}, {155, 85},{175, 85},{195, 85}, {215, 85},{235, 85},{255, 85}, {275, 85},{295, 85},
                {315, 85}, {335, 85},{355, 85},{375, 85}, {395, 85},{415, 85},{435, 85}, {455, 85},{475, 85},{495, 85}, {515, 85},{535, 85},{555, 85}, {575, 85},{595, 85},
                // fila 3
                {15, 105}, {35, 105},{55, 105},{75, 105}, {95, 105},{115, 105},{135, 105}, {155, 105},{175, 105},{195, 105}, {215, 105},{235, 105},{255, 105}, {275, 105},{295, 105},
                {315, 105}, {335, 105},{355, 105},{375, 105}, {395, 105},{415, 105},{435, 105}, {455, 105},{475, 105},{495, 105}, {515, 105},{535, 105},{555, 105}, {575, 105},{595, 105},
                // fila 4
                {15, 120}, {35, 120},{55, 120},{75, 120}, {95, 120},{115, 120},{135, 120}, {155, 120},{175, 120},{195, 120}, {215, 120},{235, 120},{255, 120}, {275, 120},{295, 120},
                {315, 120}, {335, 120},{355, 120},{375, 120}, {395, 120},{415, 120},{435, 120}, {455, 120},{475, 120},{495, 120}, {515, 120},{535, 120},{555, 120}, {575, 120},{595, 120},
                // fila 5
                {15, 135}, {35, 135},{55, 135},{75, 135}, {95, 135},{115, 135},{135, 135}, {155, 135},{175, 135},{195, 135}, {215, 135},{235, 135},{255, 135}, {275, 135},{295, 135},
                {315, 135}, {335, 135},{355, 135},{375, 135}, {395, 135},{415, 135},{435, 135}, {455, 135},{475, 135},{495, 135}, {515, 135},{535, 135},{555, 135}, {575, 135},{595, 135},
                // fila 6
                {15, 165}, {35, 165},{55, 165},{75, 165}, {95, 165},{115, 165},{135, 165}, {155, 165},{175, 165},{195, 165}, {215, 165},{235, 165},{255, 165}, {275, 165},{295, 165},
                {315, 165}, {335, 165},{355, 165},{375, 165}, {395, 165},{415, 165},{435, 165}, {455, 165},{475, 165},{495, 165}, {515, 165},{535, 165},{555, 165}, {575, 165},{595, 165},
                // fila 7
                {15, 185}, {35, 185},{55, 185},{75, 185}, {95, 185},{115, 185},{135, 185}, {155, 185},{175, 185},{195, 185}, {215, 185},{235, 185},{255, 185}, {275, 185},{295, 185},
                {315, 185}, {335, 185},{355, 185},{375, 185}, {395, 185},{415, 185},{435, 185}, {455, 185},{475, 185},{495, 185}, {515, 185},{535, 185},{555, 185}, {575, 185},{595, 185},
                // fila 8
                {15, 205}, {35, 205},{55, 205},{75, 205}, {95, 205},{115, 205},{135, 205}, {155, 205},{175, 205},{195, 205}, {215, 205},{235, 205},{255, 205}, {275, 205},{295, 205},
                {315, 205}, {335, 205},{355, 205},{375, 205}, {395, 205},{415, 205},{435, 205}, {455, 205},{475, 205},{495, 205}, {515, 205},{535, 205},{555, 205}, {575, 205},{595, 205},
                // fila 9
                {15, 225}, {35, 225},{55, 225},{75, 225}, {95, 225},{115, 225},{135, 225}, {155, 225},{175, 225},{195, 225}, {215, 225},{235, 225},{255, 225}, {275, 225},{295, 225},
                {315, 225}, {335, 225},{355, 225},{375, 225}, {395, 225},{415, 225},{435, 225}, {455, 225},{475, 225},{495, 225}, {515, 225},{535, 225},{555, 225}, {575, 225},{595, 225},
                // fila 10
                {15, 245}, {35, 245},{55, 245},{75, 245}, {95, 245},{115, 245},{135, 245}, {155, 245},{175, 245},{195, 245}, {215, 245},{235, 245},{255, 245}, {275, 245},{295, 245},
                {315, 245}, {335, 245},{355, 245},{375, 245}, {395, 245},{415, 245},{435, 245}, {455, 245},{475, 245},{495, 245}, {515, 245},{535, 245},{555, 245}, {575, 245},{595, 245},
                // fila 11
                {15, 265}, {35, 265},{55, 265},{75, 265}, {95, 265},{115, 265},{135, 265}, {2655, 265},{175, 265},{195, 265}, {215, 265},{235, 265},{255, 265}, {275, 265},{295, 265},
                {315, 265}, {335, 265},{355, 265},{375, 265}, {395, 265},{415, 265},{435, 265}, {455, 265},{475, 265},{495, 265}, {515, 265},{535, 265},{555, 265}, {575, 265},{595, 265},
                // fila 12
                {15, 280}, {35, 280},{55, 280},{75, 280}, {95, 280},{115, 280},{135, 280}, {155, 280},{175, 280},{195, 280}, {215, 280},{235, 280},{255, 280}, {275, 280},{295, 280},
                {315, 280}, {335, 280},{355, 280},{375, 280}, {395, 280},{415, 280},{435, 280}, {455, 280},{475, 280},{495, 280}, {515, 280},{535, 280},{555, 280}, {575, 280},{595, 280},

        };

        double[] punto_mas_cercano = null;
        double distancia_minima = distancia_aproximacion;

        for (double[] point : puntosDisponibles) {
            double distance = Math.hypot(point[0] - x, point[1] - y); // usa la formula de la distancia raiz cuadrada de el x al cuadrado mas y al cuadrado

            if (distance < distancia_minima) {
                distancia_minima = distance;
                punto_mas_cercano = point;
            }
        }

        return punto_mas_cercano; // Devuelve null si no hay un punto cercano dentro de la aproximacion
    }

    private Color getColor(double x, double y) {
        // Obtener el PixelReader del Canvas
        PixelReader pixelReader = tablero.snapshot(null, null).getPixelReader();

        // Obtener el color en la posición especificada
        return pixelReader.getColor((int) x, (int) y);
    }

}
