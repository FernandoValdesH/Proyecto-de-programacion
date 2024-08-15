package com.example.proyprotoboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

public class Controlador_Protoboard implements Initializable {

    private double startX;
    private double startY;

    private Boolean movible = false;

    

    @FXML
    private Canvas tablero;

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
    for ( k=k ; k < 260 ; k++){
        gc.strokeLine(x+10, k,x+25, k);
        gc.strokeLine(x+45, k,x+60, k);
    }


    }

    public void dibujarCable(ActionEvent event){

    if (contador_cables < 2){
        JOptionPane.showMessageDialog(null,"Seleccione la posicion inicial ( Bateria )");

    } // comprobar donde empieza la posicion x e y para ver si empieza con un cable " azul " o " rojo " ( esto es nuestra implementacion, no un requisito )



    movible = true;
    GraphicsContext gc = tablero.getGraphicsContext2D();
    gc.setStroke(Color.RED);
    contador_cables++;


    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablero.setOnMousePressed(this::click);
        tablero.setOnMouseDragged(this::arrastrarMouse);
        tablero.setOnMouseReleased(this::soltarMouse);

        dibujarProtoboard(tablero.getGraphicsContext2D());
        dibujarBateria(tablero.getGraphicsContext2D());


    }

    private void soltarMouse(MouseEvent event) {
        if (movible){
            GraphicsContext gc = tablero.getGraphicsContext2D();
            gc.strokeLine(startX, startY, event.getX(), event.getY());
            contador_cables++;
            movible = false;

        }

    }
    private void click(MouseEvent event) {

        if (movible){
            startX = event.getX();
            startY = event.getY();
            /*
            startX = event.getSceneX() - cable.getTranslateX();
            startY = event.getSceneY() - cable.getTranslateY();

             */
        }

    }

    private void arrastrarMouse(MouseEvent event) {
        if (movible){
            /*
            GraphicsContext gc = tablero.getGraphicsContext2D();
            gc.strokeLine(startX, startY, event.getX(), event.getY()); */
        }

    }


}
