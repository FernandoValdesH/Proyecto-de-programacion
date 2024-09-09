package com.example.proyprotoboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controlador_Protoboard implements Initializable {

    private boolean activar_eliminacion=false;
    private ArrayList<Double> arreglo_coordenadas_leds = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_switch = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_cables = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_patitas_leds = new ArrayList<>();
    private double punto_inicio_x_patita=0;
    private double punto_inicio_y_patita=0;
    private double punto_final_x_patita=0;
    private double punto_final_y_patita=0;
    private double punto_inicio_x_cable=0;
    private double punto_inicio_y_cable=0;
    private double x_switch;
    private double y_switch;
    private Boolean agrega_switch = false;
    private Boolean movible_cable = false;
    private Boolean agrega_led=false;
    private Boolean led_puesto=false;
    private Boolean patita_led_1=false;
    private int cantidad_patitas=0;
    double x_led=0; // inicializacion posicion x del led
    double y_led=0; // inicializacion posicion y del led
    private double distancia_aproximacion = 20; // distancia para que tome el punto mas cercano al mover un cable
    double punto_final_x_cable=0;
    double punto_final_y_cable=0;
    private boolean dibujar_patitas=false;
    private double inicio_x_eliminar=0;
    private double inicio_y_eliminar=0;
    int x=10,y=10;

    @FXML
    Button btnAgregarCable;
    @FXML
    Button btnAgregarLed;
    @FXML
    Button btnAgregarSwitch;
    @FXML
    Button btnEliminarObj;


    @FXML
    private Canvas tablero;


    int contador_cables;

    logicalProtoboard[][] Protoboard_logica = new logicalProtoboard[30][14];
    protoboard _Protoboard_Funcional = protoboard.getInstance(Protoboard_logica);

    private void dibujarProtoboard(GraphicsContext gc){
        x= 10;
        y= 10;
        gc.setLineWidth(1);
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
        x = 660;


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
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        JOptionPane.showMessageDialog(null, "Seleccione el punto central de donde desea ubicar");
        agrega_switch = true;
    }

    public void dibujarSwitch() {
        GraphicsContext gc = tablero.getGraphicsContext2D();
        gc.setStroke(Color.GREY);
        gc.setLineWidth(2);
        for (int k = 0; k < 43; k++) {        // k < a 350 es la altura del rectangulo, siendo 350 el tope de la altura
            gc.strokeLine(x_switch, y_switch + k , x_switch+46, y_switch + k); //ancho del switch = 48
        }

        gc.setFill(Color.BLACK);
        gc.fillOval(x_switch, y_switch+3,8,8);
        gc.fillOval(x_switch, y_switch+33,8,8);
        gc.fillOval(x_switch+39, y_switch+3,8,8);
        gc.fillOval(x_switch+39, y_switch+33,8,8);

        gc.setFill(Color.BLACK);
        gc.fillOval(x_switch+8, y_switch+8, 30, 30);

    }
    public void dibujarCable(ActionEvent event){
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        if (contador_cables < 2){
            JOptionPane.showMessageDialog(null,"Seleccione la posicion inicial");

        } // comprobar donde empieza la posicion x e y para ver si empieza con un cable " azul " o " rojo " ( esto es nuestra implementacion, no un requisito )

        movible_cable = true;
        GraphicsContext gc = tablero.getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        contador_cables++;


    }
    public void dibujarLed(Color color){
        GraphicsContext gc = tablero.getGraphicsContext2D();

        gc.setFill(color);
        gc.fillOval(x_led,y_led,30,30);

        /* pines del led (comentados porque se dibujan al agregar el led)
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(2);
        gc.strokeLine(x_led+8, y_led+28, x_led+8, y_led+68);
        gc.strokeLine(x_led+23, y_led+28, x_led+23, y_led+68);*/
    }

    public void activarEliminacion(){
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        activar_eliminacion=true;
    }

    public void dibujarTodo(){
        GraphicsContext gc = tablero.getGraphicsContext2D();
        dibujarProtoboard(gc);
        dibujarBateria(gc);

        for (int i = 0 ; i < arreglo_coordenadas_leds.size() ; i+=2){
            x_led=arreglo_coordenadas_leds.get(i);
            y_led=arreglo_coordenadas_leds.get(i+1);
            dibujarLed(Color.DARKRED);
        }

        for (int i = 0 ; i < arreglo_coordenadas_switch.size() ; i+=2){
            x_switch=arreglo_coordenadas_switch.get(i);
            y_switch=arreglo_coordenadas_switch.get(i+1);
            dibujarSwitch();
        }

        for (int i = 0 ; i < arreglo_coordenadas_cables.size() ; i+=4){
            punto_inicio_x_cable = arreglo_coordenadas_cables.get(i);
            punto_inicio_y_cable = arreglo_coordenadas_cables.get(i+1);
            punto_final_x_cable = arreglo_coordenadas_cables.get(i+2);
            punto_final_y_cable = arreglo_coordenadas_cables.get(i+3);
            gc.setStroke(Color.RED);
            gc.setLineWidth(5);
            gc.strokeLine(punto_inicio_x_cable,punto_inicio_y_cable,punto_final_x_cable,punto_final_y_cable);
        }

        for (int i = 0 ; i < arreglo_coordenadas_patitas_leds.size() ; i+=4){
            punto_inicio_x_patita = arreglo_coordenadas_patitas_leds.get(i);
            punto_inicio_y_patita = arreglo_coordenadas_patitas_leds.get(i+1);
            punto_final_x_patita = arreglo_coordenadas_patitas_leds.get(i+2);
            punto_final_y_patita = arreglo_coordenadas_patitas_leds.get(i+3);
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(3);
            gc.strokeLine(punto_inicio_x_patita,punto_inicio_y_patita,punto_final_x_patita,punto_final_y_patita);


        }
    }

    private boolean calcularDistanciaPuntos(double coord, double coord_2, double margen){
        return Math.abs(coord-coord_2) <= margen;
    }

    public void eliminarElemento(double x, double y){
        GraphicsContext gc = tablero.getGraphicsContext2D();

        boolean cent_led=false;
        boolean cent_switch=false;
        boolean cent_cable=false;
        int i=0;
        for ( i = 0 ; i < arreglo_coordenadas_leds.size() && !cent_led; i+=2){
            if ((calcularDistanciaPuntos(x, arreglo_coordenadas_leds.get(i), 20 )) && (calcularDistanciaPuntos(y, arreglo_coordenadas_leds.get(i+1), 20 ))){
                cent_led = true;
            }
        }    i=i-2;
        if (cent_led){


            arreglo_coordenadas_leds.remove(i);
            arreglo_coordenadas_leds.remove(i);

            // calcular que patitas son : 2*i + los 3 siguientes a ese

            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);

            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);


            gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
            dibujarTodo();
        } else {
            i=0;
            for ( i = 0 ; i < arreglo_coordenadas_switch.size() && !cent_switch ; i+=2){
                if ((calcularDistanciaPuntos(x, arreglo_coordenadas_switch.get(i), 40 )) && (calcularDistanciaPuntos(y, arreglo_coordenadas_switch.get(i+1), 40 ))){
                    cent_switch = true;
                }
            } i = i-2;
            if (cent_switch){
                arreglo_coordenadas_switch.remove(i);
                arreglo_coordenadas_switch.remove(i);
                gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                dibujarTodo();
            } else{
                i=0;
                for ( i = 0 ; i < arreglo_coordenadas_cables.size() && !cent_cable; i+=4){
                    if (((calcularDistanciaPuntos(x, arreglo_coordenadas_cables.get(i), 20 )) && (calcularDistanciaPuntos(y, arreglo_coordenadas_cables.get(i+1), 20 ))) || (calcularDistanciaPuntos(x, arreglo_coordenadas_cables.get(i+2), 20 )) && calcularDistanciaPuntos(y, arreglo_coordenadas_cables.get(i+3), 20 )){
                        cent_cable = true;
                    }
                } i=i-4;
                if (cent_cable){
                    arreglo_coordenadas_cables.remove(i);
                    arreglo_coordenadas_cables.remove(i);
                    arreglo_coordenadas_cables.remove(i);
                    arreglo_coordenadas_cables.remove(i);


                    gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                    dibujarTodo();
                }
            }
        }


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
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        led_puesto=false;
        agrega_led=true;
        patita_led_1=false;
        cantidad_patitas=0;
        JOptionPane.showMessageDialog(null, "Seleccione punto inicial");
    }
    int auxx = 0, auxy = 0;
    private void soltarMouse(MouseEvent event) {
        GraphicsContext gc = tablero.getGraphicsContext2D();
        int posicion1_y = 0;
        int posicion2_y = 0;
        boolean conectado_bateria=false;
        if (movible_cable){
            punto_final_x_cable=event.getX();
            punto_final_y_cable=event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(punto_final_x_cable, punto_final_y_cable);
            if (puntoCercano != null) {
                punto_final_x_cable = puntoCercano[0];
                punto_final_y_cable = puntoCercano[1];
            }

            // retornar coordenada transformada a posicion de una matriz de 30 elementos : es coordenada - 15 / 20
            int posicion1_x = (int) ((punto_inicio_x_cable - 15) / 20);
            int posicion2_x = (int) ((punto_final_x_cable - 15) / 20);

            if (punto_final_y_cable < 68){
                posicion2_y = (int)(punto_final_y_cable - 15) /15;
            } else if (punto_final_y_cable >= 68 && punto_final_y_cable < 180){
                posicion2_y = (int) (((punto_final_y_cable - 15) /15) -1);
            } else if (punto_final_y_cable >= 180 && punto_final_y_cable <= 225){
                posicion2_y = (int) (((punto_final_y_cable - 30) /15)-2);
            } else if (punto_final_y_cable > 225){
                posicion2_y = (int) ((punto_final_y_cable - 15 )/20);
            }
            if (punto_inicio_y_cable < 68){
                posicion1_y = (int) ((punto_inicio_y_cable - 15) /15);
            } else if (punto_inicio_y_cable >= 68 && punto_inicio_y_cable < 180){
                posicion1_y = (int) (((punto_inicio_y_cable - 15 )/15) -1);
            } else if (punto_inicio_y_cable >= 180 && punto_inicio_y_cable <= 225){
                posicion1_y = (int) (((punto_inicio_y_cable - 30) /15)-2);
            } else if (punto_inicio_y_cable > 225){
                posicion1_y = (int) ((punto_inicio_y_cable - 15 )/20);
            }

            if (punto_final_x_cable - punto_inicio_x_cable > 150 || punto_inicio_y_cable - punto_final_y_cable > 100){
                JOptionPane.showMessageDialog(null, "Haga el cable mas corto.");
            } else if (punto_final_x_cable <= 0  || punto_inicio_y_cable >= 285 || punto_final_y_cable<=0 || punto_final_y_cable >=285){
                JOptionPane.showMessageDialog(null, "Ingrese el cable dentro del protoboard");

            }else if(posicion2_x==32||posicion1_x==32|| posicion1_x == 31 || posicion2_x==31 || posicion1_x== 30 || posicion2_x==30 ||posicion1_x == 34 && punto_inicio_y_cable > 270 || posicion1_x == 34 && punto_inicio_y_cable<240 || posicion1_x == 33 && punto_inicio_y_cable > 270 || posicion1_x == 33 && punto_inicio_y_cable<240 || posicion2_x==34 && punto_final_y_cable >270 || posicion2_x == 34 && punto_final_y_cable<240 || posicion2_x==33 && punto_final_y_cable < 240 || posicion2_x == 33 && punto_final_y_cable > 270 || posicion2_x==35 && punto_final_y_cable < 240 || posicion2_x == 35 && punto_final_y_cable > 270){
                JOptionPane.showMessageDialog(null,"Ingrese el cable dentro del protoboard o conectado a las puntas de la bateria");
            }
            else{
                // agregar las coordenadas al arreglo
                arreglo_coordenadas_cables.add(punto_inicio_x_cable); arreglo_coordenadas_cables.add(punto_inicio_y_cable);arreglo_coordenadas_cables.add(punto_final_x_cable);  arreglo_coordenadas_cables.add(punto_final_y_cable);

                if ((punto_final_x_cable == 675 && punto_final_y_cable== 255)){
                    posicion2_x = -2; posicion2_y = -2;
                    conectado_bateria=true;
                } else if (punto_inicio_x_cable == 675 && punto_inicio_y_cable== 255 ){
                    posicion1_x = -2; posicion1_y = -2;
                    conectado_bateria=true;
                } else if ((punto_final_x_cable == 710 && punto_final_y_cable== 255)){
                    posicion2_x = -3; posicion2_y = -3;
                    conectado_bateria=true;
                }else if((punto_inicio_x_cable == 710 && punto_inicio_y_cable== 255)){
                    posicion1_x = -3; posicion1_y = -3;
                    conectado_bateria=true;
                }

                // guarda el cable
                _Protoboard_Funcional.cableSet(_Protoboard_Funcional, posicion1_x, posicion1_y, posicion2_x, posicion2_y,conectado_bateria);




                gc.strokeLine(punto_inicio_x_cable, punto_inicio_y_cable, punto_final_x_cable,punto_final_y_cable); // dibuja el cable
                contador_cables++;
                movible_cable = false;

                btnAgregarCable.setDisable(false);
                btnAgregarLed.setDisable(false);
                btnAgregarSwitch.setDisable(false);
                btnEliminarObj.setDisable(false);
            }


        } else if (patita_led_1 && cantidad_patitas<2 && dibujar_patitas){

            punto_final_x_patita= event.getX();
            punto_final_y_patita=event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(punto_final_x_patita, punto_final_y_patita);
            if (puntoCercano != null) {
                punto_final_x_patita = puntoCercano[0];
                punto_final_y_patita = puntoCercano[1];
            }
            if (punto_final_x_patita - punto_inicio_x_patita > 50){
                JOptionPane.showMessageDialog(null,"Demasiada distancia. Haga la patita mas corta.");
            }
            else{

                arreglo_coordenadas_patitas_leds.add(punto_inicio_x_patita);arreglo_coordenadas_patitas_leds.add(punto_inicio_y_patita);arreglo_coordenadas_patitas_leds.add(punto_final_x_patita); arreglo_coordenadas_patitas_leds.add(punto_final_y_patita);
                // retornar coordenada transformada a posicion de una matriz de 30 elementos : es coordenada - 15 / 20
                // retornar coordenada transformada a posicion de una matriz de 30 elementos : es coordenada - 15 / 20
                int posicion1_x = (int) ((punto_final_x_patita - 15) / 20);

                if (punto_final_y_patita < 68){
                    posicion1_y = (int) ((punto_final_y_patita - 15) /15);
                } else if (punto_final_y_patita >= 68 && punto_final_y_patita < 180){
                    posicion1_y = (int) (((punto_final_y_patita - 15 )/15) -1);
                } else if (punto_final_y_patita >= 180 && punto_final_y_patita <= 225){
                    posicion1_y = (int) (((punto_final_y_patita - 30) /15)-2);
                } else if (punto_final_y_patita > 225){
                    posicion1_y = (int) ((punto_final_y_patita - 15 )/20);
                }
                cantidad_patitas++;
                if(cantidad_patitas == 1){
                    auxx = posicion1_x;
                    auxy = posicion1_y;
                }
                // ahora retornar todo lo anterior a la lista de coordenadas de patitas (leds)

                // dibujar patitas
                gc.setStroke(Color.GRAY);
                gc.setLineWidth(3);
                gc.strokeLine(punto_inicio_x_patita, punto_inicio_y_patita, punto_final_x_patita, punto_final_y_patita);
                patita_led_1=false;

                dibujar_patitas=false;
                _Protoboard_Funcional.ledInitiatorStart(_Protoboard_Funcional, posicion1_x, posicion1_y,auxx, auxy, cantidad_patitas);
                if(_Protoboard_Funcional.protoboard[posicion1_x][posicion1_y]._led.encendido){

                dibujarLed(Color.RED);
                }
                if (cantidad_patitas==2){
                    btnAgregarCable.setDisable(false);
                    btnAgregarLed.setDisable(false);
                    btnAgregarSwitch.setDisable(false);
                    btnEliminarObj.setDisable(false);

                }
            }


        }
        if (led_puesto && !patita_led_1){
            patita_led_1=true;

        }

    }

    private void click(MouseEvent event) {
        if (activar_eliminacion){
            inicio_x_eliminar=event.getX();
            inicio_y_eliminar=event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(inicio_x_eliminar, inicio_y_eliminar);
            if (puntoCercano != null) {
                inicio_x_eliminar = puntoCercano[0];
                inicio_y_eliminar = puntoCercano[1];
            }


            eliminarElemento(inicio_x_eliminar, inicio_y_eliminar);
            btnAgregarCable.setDisable(false);
            btnAgregarLed.setDisable(false);
            btnAgregarSwitch.setDisable(false);
            btnEliminarObj.setDisable(false);
            activar_eliminacion=false;
        }

        if (agrega_switch) { // agrega un led al hacer click en una posicion // verificaciones y demas
            x_switch = event.getX();
            y_switch =  event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(x_switch, y_switch);
            if (puntoCercano != null) {
                x_switch = puntoCercano[0]-24;
                y_switch = puntoCercano[1]-24;
            } arreglo_coordenadas_switch.add(x_switch); arreglo_coordenadas_switch.add(y_switch);
            dibujarSwitch();
            agrega_switch = false;
            btnAgregarCable.setDisable(false);
            btnAgregarLed.setDisable(false);
            btnAgregarSwitch.setDisable(false);
            btnEliminarObj.setDisable(false);
        }
        if (agrega_led){ // agrega un led al hacer click en una posicion // verificaciones y demas
            x_led= event.getX();
            y_led= event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(x_led, y_led);
            if (puntoCercano != null) {
                x_led = puntoCercano[0]-15;
                y_led = puntoCercano[1]-15;
            } arreglo_coordenadas_leds.add(x_led); arreglo_coordenadas_leds.add(y_led); // agregar al arreglo
            dibujarLed(Color.DARKRED);
            btnAgregarCable.setDisable(true);
            btnAgregarLed.setDisable(true);
            btnAgregarSwitch.setDisable(true);
            btnEliminarObj.setDisable(true);

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

                JOptionPane.showMessageDialog(null,"Posicion invalida.");
                JOptionPane.showMessageDialog(null,"Ponga las patitas encima del led hacia un punto cercano.");

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
                // BATERIA
                {675, 255}, {710, 255},
                // fila 0
                {15, 15}, {35, 15},{55, 15},{75, 15}, {95, 15},{115, 15},{135, 15}, {155, 15},{175, 15},{195, 15}, {215, 15},{235, 15},{255, 15}, {275, 15},{295, 15},
                {315, 15}, {335, 15},{355, 15},{375, 15}, {395, 15},{415, 15},{435, 15}, {455, 15},{475, 15},{495, 15}, {515, 15},{535, 15},{555, 15}, {575, 15},{595, 15},
                // fila 0.5

                {15, 30}, {35, 30},{55, 30},{75, 30}, {95, 30},{130, 30},{135, 30}, {305, 30},{175, 30},{195, 30}, {230, 30},{235, 30},{255, 30}, {275, 30},{295, 30},
                {330, 30}, {335, 30},{355, 30},{375, 30}, {395, 30},{430, 30},{435, 30}, {455, 30},{475, 30},{495, 30}, {530, 30},{535, 30},{555, 30}, {575, 30},{595, 30},
                // fila 1
                {15, 68}, {35, 68},{55, 68},{75, 68}, {95, 68},{115, 68},{135, 68}, {155, 68},{175, 68},{195, 68}, {215, 68},{235, 68},{255, 68}, {275, 68},{295, 68},
                {315, 68}, {335, 68},{355, 68},{375, 68}, {395, 68},{415, 68},{435, 68}, {455, 68},{475, 68},{495, 68}, {515, 68},{535, 68},{555, 68}, {575, 68},{595, 68},

                // fila 2
                {15, 85}, {35, 85},{55, 85},{75, 85}, {95, 85},{115, 85},{135, 85}, {155, 85},{175, 85},{195, 85}, {215, 85},{235, 85},{255, 85}, {275, 85},{295, 85},
                {315, 85}, {335, 85},{355, 85},{375, 85}, {395, 85},{415, 85},{435, 85}, {455, 85},{475, 85},{495, 85}, {515, 85},{535, 85},{555, 85}, {575, 85},{595, 85},
                // fila 3

                {15, 100}, {35, 100},{55, 100},{75, 100}, {95, 100},{115, 100},{135, 100}, {155, 100},{175, 100},{195, 100}, {215, 100},{235, 100},{255, 100}, {275, 100},{295, 100},
                {315, 100}, {335, 100},{355, 100},{375, 100}, {395, 100},{415, 100},{435, 100}, {455, 100},{475, 100},{495, 100}, {515, 100},{535, 100},{555, 100}, {575, 100},{595, 100},
                // fila 4
                {15, 115}, {35, 115},{55, 115},{75, 115}, {95, 115},{115, 115},{135, 115}, {155, 115},{175, 115},{195, 115}, {215, 115},{235, 115},{255, 115}, {275, 115},{295, 115},
                {315, 115}, {335, 115},{355, 115},{375, 115}, {395, 115},{415, 115},{435, 115}, {455, 115},{475, 115},{495, 115}, {515, 115},{535, 115},{555, 115}, {575, 115},{595, 115},
                // fila 5
                {15, 130}, {35, 130},{55, 130},{75, 130}, {95, 130},{115, 130},{135, 130}, {155, 130},{175, 130},{195, 130}, {215, 130},{235, 130},{255, 130}, {275, 130},{295, 130},
                {315, 130}, {335, 130},{355, 130},{375, 130}, {395, 130},{415, 130},{435, 130}, {455, 130},{475, 130},{495, 130}, {515, 130},{535, 130},{555, 130}, {575, 130},{595, 130},

                // fila 6
                {15, 165}, {35, 165},{55, 165},{75, 165}, {95, 165},{115, 165},{135, 165}, {155, 165},{175, 165},{195, 165}, {215, 165},{235, 165},{255, 165}, {275, 165},{295, 165},
                {315, 165}, {335, 165},{355, 165},{375, 165}, {395, 165},{415, 165},{435, 165}, {455, 165},{475, 165},{495, 165}, {515, 165},{535, 165},{555, 165}, {575, 165},{595, 165},
                // fila 7

                {15, 180}, {35, 180},{55, 180},{75, 180}, {95, 180},{115, 180},{135, 180}, {155, 180},{175, 180},{195, 180}, {215, 180},{235, 180},{255, 180}, {275, 180},{295, 180},
                {315, 180}, {335, 180},{355, 180},{375, 180}, {395, 180},{415, 180},{435, 180}, {455, 180},{475, 180},{495, 180}, {515, 180},{535, 180},{555, 180}, {575, 180},{595, 180},
                // fila 8
                {15, 195}, {35, 195},{55, 195},{75, 195}, {95, 195},{115, 195},{135, 195}, {155, 195},{175, 195},{195, 195}, {215, 195},{235, 195},{255, 195}, {275, 195},{295, 195},
                {315, 195}, {335, 195},{355, 195},{375, 195}, {395, 195},{415, 195},{435, 195}, {455, 195},{475, 195},{495, 195}, {515, 195},{535, 195},{555, 195}, {575, 195},{595, 195},
                // fila 9
                {15, 210}, {35, 210},{55, 210},{75, 210}, {95, 210},{115, 210},{135, 210}, {155, 210},{175, 210},{195, 210}, {215, 210},{235, 210},{255, 210}, {275, 210},{295, 210},
                {315, 20}, {335, 210},{355, 210},{375, 210}, {395, 210},{415, 210},{435, 210}, {455, 210},{475, 210},{495, 210}, {515, 210},{535, 210},{555, 210}, {575, 210},{595, 210},
                // fila 10
                {15, 225}, {35, 225},{55, 225},{75, 225}, {95, 225},{115, 225},{135, 225}, {155, 225},{175, 225},{195, 225}, {215, 225},{235, 225},{255, 225}, {275, 225},{295, 225},
                {315, 225}, {335, 225},{355, 225},{375, 225}, {395, 225},{415, 225},{435, 225}, {455, 225},{475, 225},{495, 225}, {515, 225},{535, 225},{555, 225}, {575, 225},{595, 225},

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
