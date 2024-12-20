package com.example.proyprotoboard;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.util.Pair;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controlador_Protoboard implements Initializable {


    public Button btnResetearProtoboard;
    private boolean activar_eliminacion=false;
    private ArrayList<Double> arreglo_coordenadas_leds = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_switch = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_cables = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_patitas_leds = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_OctoSwitch = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_resistencias = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_resistencias_patitas = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_chip = new ArrayList<>();
    private ArrayList<Double> arreglo_coordenadas_display = new ArrayList<>();
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
    double punto_final_x_cable=0;
    double punto_final_y_cable=0;
    private boolean dibujar_patitas=false;
    private boolean switch_bateria=true;
    private boolean agrega_octo_switch=false;
    private Boolean agregar_resistencia = false;
    private boolean agregar_chip = false;

    private String color_led = " ";

    private boolean agregar_display = false;

    @FXML
    Button btnAgregarCable;
    @FXML
    Button btnAgregarLed;
    @FXML
    Button btnAgregarSwitch;
    @FXML
    Button btnEliminarObj;
    @FXML
    Button btnAgregarOctoSwitch;
    @FXML
    Button btnAgregarResistencia;
    @FXML
    Button btnAgregarChip;
    @FXML
    Button btnAgregarDisplay;


    @FXML
    private Canvas tablero;

    private Dibujador dibujador = new Dibujador();
    int contador_cables;

    logicalProtoboard[][] Protoboard_logica = new logicalProtoboard[30][17];
    protoboard _Protoboard_Funcional = protoboard.getInstance(Protoboard_logica);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablero.setOnMousePressed(this::click);
        tablero.setOnMouseReleased(this::soltarMouse);
        tablero.setOnMouseMoved(event -> {
            hoverPause.stop();
            tooltip.hide();
            mostrarVoltaje(event);
        });

        dibujador.dibujarProtoboard(tablero.getGraphicsContext2D(), 0, 0, _Protoboard_Funcional);
        dibujador.dibujarBateria(tablero.getGraphicsContext2D(), 660, 0, switch_bateria);
    }

    // metodos de simplificacion de codigo
    public int transformacionY_coordA_Matriz(double y){
        if (y < 68){
            y = (y - 15) /15;
        } else if (y >= 68 && y < 150){
            y =  (((y - 15) /15));
        } else if (y >= 150 && y <= 225){
            y =  (((y ) /15)-2);
        } else if (y > 225){
            y = ((y  /15 )-2);
        }
        return (int) y;
    }

    private Color getColor(double x, double y) {
        // Obtener el PixelReader del Canvas
        PixelReader pixelReader = tablero.snapshot(null, null).getPixelReader();

        // Obtener el color en la posición especificada
        return pixelReader.getColor((int) x, (int) y);
    }

    private boolean calcularDistanciaPuntos(double coord, double coord_2, double margen){
        return Math.abs(coord-coord_2) <= margen;
    }

    private void revisarLedsEncendidos(protoboard _protoboard, GraphicsContext gc) {
        boolean encontro_uno = false;
        for (int i = 0; i < 30 && !encontro_uno; i++) {
            for (int j = 0; j < 14 && !encontro_uno; j++) {
                if (_protoboard.protoboard[i][j]._led != null && _protoboard.protoboard[i][j]._led.posicion1.coordenadax != -1 && !_protoboard.protoboard[i][j]._led.revisado) {
                    if (_protoboard.protoboard[i][j]._led.encendido) {
                        int transformacion_inversa_x = (i * 20) + 15;
                        int transformacion_inversa_y = 0 ;
                        if ( j < 2) {
                            transformacion_inversa_y = ((j * 15 ) + 15) - 5;
                        } else if (j >= 3 && j <= 7) {
                            transformacion_inversa_y = (((j + 1) * 15) -5) + 10;
                        } else if (j > 7 && j <= 13) {
                            transformacion_inversa_y = ((j + 3) * 15) - 20;
                        } else if (j > 14) {
                            transformacion_inversa_y = ((j + 4) * 15) -25;
                        }
                        boolean encuentra_patitas = false;
                        int k = 0;
                        for (k = 0; k < arreglo_coordenadas_patitas_leds.size(); k++) {
                            if (calcularDistanciaPuntos(arreglo_coordenadas_patitas_leds.get(k), transformacion_inversa_x, 10) && calcularDistanciaPuntos(arreglo_coordenadas_patitas_leds.get(k + 1), transformacion_inversa_y, 10)) {
                                encuentra_patitas = true;
                                break;
                            }
                        }
                        k--;
                        if (encuentra_patitas) {
                            int indice = (k / 8);
                            Led led_encontrado = _protoboard.protoboard[i][j]._led;
                            x_led = arreglo_coordenadas_leds.get(indice * 2);
                            y_led = arreglo_coordenadas_leds.get((indice * 2) + 1);
                            _protoboard.protoboard[led_encontrado.posicion1.coordenadax][led_encontrado.posicion1.coordenaday]._led.revisado = true;
                            _protoboard.protoboard[led_encontrado.posicion2.coordenadax][led_encontrado.posicion2.coordenaday]._led.revisado = true;
                            dibujador.dibujarLed(gc, x_led, y_led, "rojo",_protoboard);
                            encontro_uno = true;
                            if (led_encontrado.quemado){
                                dibujador.dibujarLed(gc, x_led, y_led, "negro",_protoboard);
                            }
                        }
                    }
                }
            }
        }
    }

    // metodos que se activan al presionar boton
    public void AgregarResistencia() {
        JOptionPane.showMessageDialog(null,"Indicar posición resistencia");
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        btnAgregarOctoSwitch.setDisable(true);
        btnAgregarChip.setDisable(true);
        btnAgregarDisplay.setDisable(true);
        btnResetearProtoboard.setDisable(true);
        btnAgregarResistencia.setDisable(true);
        agregar_resistencia = true;
    }
    public void AgregarDisplay(){
        JOptionPane.showMessageDialog(null,"Indicar posición display");
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        btnAgregarOctoSwitch.setDisable(true);
        btnAgregarResistencia.setDisable(true);
        btnAgregarChip.setDisable(true);
        btnAgregarDisplay.setDisable(true);
        btnResetearProtoboard.setDisable(true);
        agregar_display = true;
    }
    public void agregarOctoSwitch(){
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        btnAgregarOctoSwitch.setDisable(true);
        btnAgregarResistencia.setDisable(true);
        btnAgregarChip.setDisable(true);
        btnAgregarDisplay.setDisable(true);
        btnResetearProtoboard.setDisable(true);
        JOptionPane.showMessageDialog(null, "Seleccione el punto central de donde desea ubicar");
        agrega_octo_switch = true;

    }

    public void AgregarSwitch() {
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        btnAgregarOctoSwitch.setDisable(true);
        btnAgregarResistencia.setDisable(true);
        btnAgregarChip.setDisable(true);
        btnAgregarDisplay.setDisable(true);
        btnResetearProtoboard.setDisable(true);
        JOptionPane.showMessageDialog(null, "Seleccione el punto central de donde desea ubicar");
        agrega_switch = true;
    }

    public void activaLed(){
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        btnAgregarOctoSwitch.setDisable(true);
        btnAgregarResistencia.setDisable(true);
        btnAgregarChip.setDisable(true);
        btnAgregarDisplay.setDisable(true);
        btnResetearProtoboard.setDisable(true);
        led_puesto=false;
        agrega_led=true;
        patita_led_1=false;
        cantidad_patitas=0;
        JOptionPane.showMessageDialog(null, "Seleccione punto inicial");
    }

    public void activarCable(){
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        btnAgregarOctoSwitch.setDisable(true);
        btnAgregarResistencia.setDisable(true);
        btnAgregarChip.setDisable(true);
        btnAgregarDisplay.setDisable(true);
        btnResetearProtoboard.setDisable(true);
        if (contador_cables < 2){
            JOptionPane.showMessageDialog(null,"Seleccione la posicion inicial");

        } // comprobar donde empieza la posicion x e y para ver si empieza con un cable " azul " o " rojo " ( esto es nuestra implementacion, no un requisito )

        movible_cable = true;
        contador_cables++;

    }

    public void activarEliminacion(){
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        btnAgregarOctoSwitch.setDisable(true);
        btnAgregarResistencia.setDisable(true);
        btnAgregarChip.setDisable(true);
        btnAgregarDisplay.setDisable(true);
        btnResetearProtoboard.setDisable(true);
        activar_eliminacion=true;
    }
    private String tipo_chip = "";
    public void agregarChip(){
        btnAgregarCable.setDisable(true);
        btnAgregarLed.setDisable(true);
        btnAgregarSwitch.setDisable(true);
        btnEliminarObj.setDisable(true);
        btnAgregarOctoSwitch.setDisable(true);
        btnAgregarResistencia.setDisable(true);
        btnAgregarChip.setDisable(true);
        btnAgregarDisplay.setDisable(true);
        btnResetearProtoboard.setDisable(true);

        List<String> opciones = new ArrayList<>();
        opciones.add("AND");
        opciones.add("OR");
        opciones.add("NOT");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("AND", opciones);
        dialog.setTitle("Seleccionar Tipo de chip");
        dialog.setHeaderText("Seleccione un tipo de chip de la lista:");
        dialog.setContentText("Tipo de chip:");

        Optional<String> seleccion = dialog.showAndWait();
        if (seleccion.isPresent()){
            tipo_chip = seleccion.get();
            JOptionPane.showMessageDialog(null, "Seleccione el punto central de donde desea ubicar");
            agregar_chip = true;
        } else{
            JOptionPane.showMessageDialog(null, "Seleccione bien el tipo de chip.");
            btnAgregarCable.setDisable(false);
            btnAgregarLed.setDisable(false);
            btnAgregarSwitch.setDisable(false);
            btnEliminarObj.setDisable(false);
            btnAgregarOctoSwitch.setDisable(false);
            btnAgregarResistencia.setDisable(false);
            btnAgregarChip.setDisable(false);
            btnAgregarDisplay.setDisable(false);
            btnResetearProtoboard.setDisable(false);
        }

    }

    // metodos distintos


    public void dibujarTodo(){
        GraphicsContext gc = tablero.getGraphicsContext2D();
        dibujador.dibujarProtoboard(gc, 0, 0, _Protoboard_Funcional);
        dibujador.dibujarBateria(gc, 660, 0,switch_bateria);

        for (int i = 0 ; i < arreglo_coordenadas_leds.size() ; i+=2){
            x_led=arreglo_coordenadas_leds.get(i);
            y_led=arreglo_coordenadas_leds.get(i+1);
            // recuperar coordenadas 2 y 3 del arreglo de patitas de leds, transformarlas a posicion de matriz y chequear y si esa posicion tiene corriente o no
            // si tiene corriente, dibujar el led en rojo, si no, dibujar el led en darkred


            int posicion1_x = (int) ((arreglo_coordenadas_patitas_leds.get(2+i*4) - 15) / 20);
            int posicion1_y = transformacionY_coordA_Matriz(arreglo_coordenadas_patitas_leds.get(3+i*4));
            if (_Protoboard_Funcional.protoboard[posicion1_x][posicion1_y]._led.posicion1.coordenadax!=-1) {
                color_led= _Protoboard_Funcional.protoboard[posicion1_x][posicion1_y]._led.color;
                if (_Protoboard_Funcional.protoboard[posicion1_x][posicion1_y]._led.encendido) {

                    if (color_led.equals("rojo")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "rojo",_Protoboard_Funcional);

                    }
                    if (color_led.equals("azul")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "azul",_Protoboard_Funcional);
                    }
                    if (color_led.equals("verde")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "verde",_Protoboard_Funcional);
                    }
                    if (color_led.equals("violeta")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "violeta",_Protoboard_Funcional);
                    } if (color_led.equals("amarillo")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "amarillo",_Protoboard_Funcional);
                    }
                    if (color_led.equals("negro")){
                        dibujador.dibujarLed(gc,x_led,y_led,"negro",_Protoboard_Funcional);
                    }

                } else {
                    if (color_led.equals("rojo")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "rojo_oscuro",_Protoboard_Funcional);
                    }
                    if (color_led.equals("azul")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "azul_oscuro",_Protoboard_Funcional);
                    }
                    if (color_led.equals("verde")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "verde_oscuro",_Protoboard_Funcional);
                    }
                    if (color_led.equals("violeta")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "violeta_oscuro",_Protoboard_Funcional);
                    }
                    if (color_led.equals("amarillo")) {
                        dibujador.dibujarLed(gc, x_led, y_led, "amarillo_oscuro",_Protoboard_Funcional);
                    }
                    if (color_led.equals("negro")){
                        dibujador.dibujarLed(gc,x_led,y_led,"negro",_Protoboard_Funcional);
                    }


                }

            }
        }

        for (int i = 0 ; i < arreglo_coordenadas_switch.size() ; i+=2){
            x_switch=arreglo_coordenadas_switch.get(i);
            y_switch=arreglo_coordenadas_switch.get(i+1);
            // transformar coordenadas a coordenadas de matriz
            int coord_original_x = (int) (x_switch+24);
            int coord_original_y = (int) (y_switch+24);
            int posicion1_x = (int) ((coord_original_x - 15) / 20);
            int posicion1_y = transformacionY_coordA_Matriz(coord_original_y);
            // ver la matriz a ver si el switch esta prendido o apagado

            if (_Protoboard_Funcional.protoboard[posicion1_x-1][posicion1_y-1]._switch!=null && _Protoboard_Funcional.protoboard[posicion1_x-1][posicion1_y-1]._switch.prendido){
                dibujador.dibujarSwitch(gc, x_switch, y_switch);
                gc.setFill(Color.LIMEGREEN);
                gc.fillOval(x_switch + 8 , y_switch +8 , 30, 30);
            } else {
                dibujador.dibujarSwitch(gc,x_switch, y_switch);
                gc.setFill(Color.BLACK);
                gc.fillOval(x_switch + 8 , y_switch +8 , 30, 30);
            }

        }

        for (int i = 0 ; i < arreglo_coordenadas_cables.size() ; i+=4){
            punto_inicio_x_cable = arreglo_coordenadas_cables.get(i);
            punto_inicio_y_cable = arreglo_coordenadas_cables.get(i+1);
            punto_final_x_cable = arreglo_coordenadas_cables.get(i+2);
            punto_final_y_cable = arreglo_coordenadas_cables.get(i+3);
            dibujador.dibujarCable(gc, (int) punto_inicio_x_cable, (int) punto_inicio_y_cable, (int) punto_final_x_cable, (int) punto_final_y_cable);
        }

        for (int i = 0 ; i < arreglo_coordenadas_patitas_leds.size() ; i+=4){
            punto_inicio_x_patita = arreglo_coordenadas_patitas_leds.get(i);
            punto_inicio_y_patita = arreglo_coordenadas_patitas_leds.get(i+1);
            punto_final_x_patita = arreglo_coordenadas_patitas_leds.get(i+2);
            punto_final_y_patita = arreglo_coordenadas_patitas_leds.get(i+3);

//            gc.setStroke(Color.GRAY);


            if ((i/4) %2==0){
                gc.setStroke(Color.RED);

            } else{
                gc.setStroke(Color.BLUE);
            }
            gc.setLineWidth(3);
            gc.strokeLine(punto_inicio_x_patita,punto_inicio_y_patita,punto_final_x_patita,punto_final_y_patita);



        }
        for (int i = 0 ; i < arreglo_coordenadas_OctoSwitch.size() ; i+=2){
            double x_Octo_switch=arreglo_coordenadas_OctoSwitch.get(i);
            double y_Octo_switch=arreglo_coordenadas_OctoSwitch.get(i+1);
            dibujador.dibujarOctoSwitch(x_Octo_switch, y_Octo_switch, gc, _Protoboard_Funcional);
        }
        if (arreglo_coordenadas_resistencias.size()>=1){
            for (int i = 0 ; i < arreglo_coordenadas_resistencias.size(); i+=2){

                // transformar las coordenadas a coordenadas de matriz para ver cual resistencia es y poder recuperar su banda1, banda2, multiplicador y tolerancia

//            int transformacion_x_2 = (int) (patita_x2) / 20;
//            int transformacion_y_2 = transformacionY_coordA_Matriz((int) patita_y2+15);
                int banda1=0, banda2=0, multiplicador=0, tolerancia=0;


                double patita_x1=0, patita_y1=0, patita_x2=0, patita_y2=0;
                double x_resistencia = 0, y_resistencia = 0;
                x_resistencia = arreglo_coordenadas_resistencias.get(i);
                y_resistencia = arreglo_coordenadas_resistencias.get(i + 1);

                patita_x1 = arreglo_coordenadas_resistencias_patitas.get((i*2));
                patita_y1 = arreglo_coordenadas_resistencias_patitas.get(((i*2)+1));
                patita_x2 = arreglo_coordenadas_resistencias_patitas.get(((i*2)+2));
                patita_y2 = arreglo_coordenadas_resistencias_patitas.get(((i*2)+3));
                int transformacion_x_1 = (int) (patita_x1) / 20;
                int transformacion_y_1 = transformacionY_coordA_Matriz((int) patita_y1 + 15);
                if (_Protoboard_Funcional.protoboard[transformacion_x_1][transformacion_y_1]._resistencia != null && _Protoboard_Funcional.protoboard[transformacion_x_1][transformacion_y_1]._resistencia.posicion1.coordenadax != -1) {
                    banda1 = _Protoboard_Funcional.protoboard[transformacion_x_1][transformacion_y_1]._resistencia.banda_1;
                    banda2 = _Protoboard_Funcional.protoboard[transformacion_x_1][transformacion_y_1]._resistencia.banda_2;
                    multiplicador = _Protoboard_Funcional.protoboard[transformacion_x_1][transformacion_y_1]._resistencia.multiplicador;
                    tolerancia = _Protoboard_Funcional.protoboard[transformacion_x_1][transformacion_y_1]._resistencia.tolerancia;

                }


                dibujador.dibujarResistencia(gc, x_resistencia, y_resistencia, patita_x1, patita_y1, patita_x2, patita_y2, banda1, banda2, multiplicador, tolerancia);




            }}
        for (int i = 0 ; i < arreglo_coordenadas_chip.size(); i+=2){
            double x_chip = arreglo_coordenadas_chip.get(i);
            double y_chip = arreglo_coordenadas_chip.get(i+1);
            // transformar y ver que tipo de chip es
            int transformacion_x = (int) (x_chip) / 20;
            int transformacion_y = transformacionY_coordA_Matriz((int) y_chip + 15);
            if (_Protoboard_Funcional.protoboard[transformacion_x][transformacion_y]._chip != null && _Protoboard_Funcional.protoboard[transformacion_x][transformacion_y]._chip.posicion1.coordenadax != -1){
                dibujador.dibujarChip(gc, (int) x_chip, (int) y_chip, _Protoboard_Funcional.protoboard[transformacion_x][transformacion_y]._chip.tipo_chip);
            }
        }
        for (int i = 0 ; i < arreglo_coordenadas_display.size(); i+=2){
            double x_display = arreglo_coordenadas_display.get(i);
            double y_display = arreglo_coordenadas_display.get(i+1);


            dibujador.dibujarDisplay(gc, (int) x_display, (int) y_display, _Protoboard_Funcional);

        }
    }

    public void eliminarElemento(double x, double y){
        GraphicsContext gc = tablero.getGraphicsContext2D();

        boolean cent_led=false;
        boolean cent_switch=false;
        boolean cent_cable=false;
        boolean cent_octo_switch=false;
        boolean cent_resistencia=false;
        boolean cent_chip = false;
        boolean cent_display = false;
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



            int indice_1 = 2 + i * 4;
            int indice_2 = 3 + i * 4;
            // transformar coordenadas a posiciones de la matriz para eliminar el cable y la corriente que llevaba este
            int posicion1_x = (int) ((arreglo_coordenadas_patitas_leds.get(indice_1) - 15) / 20);
            int posicion1_y = transformacionY_coordA_Matriz(arreglo_coordenadas_patitas_leds.get(indice_2));


            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);

            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);
            arreglo_coordenadas_patitas_leds.remove(4*i);


            _Protoboard_Funcional.eliminarElemento(_Protoboard_Funcional, posicion1_x, posicion1_y);


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
                // transformar coordenadas a posiciones de la matriz para eliminar el cable y la corriente que llevaba este
                int posicion1_x = (arreglo_coordenadas_switch.get(i).intValue() );
                posicion1_x+=24;
                posicion1_x = ((posicion1_x - 15) / 20);
                int posicion1_y = (arreglo_coordenadas_switch.get(i+1).intValue() );
                posicion1_y+=24;
                posicion1_y = transformacionY_coordA_Matriz(posicion1_y);

                posicion1_y--;
                posicion1_x--;

                arreglo_coordenadas_switch.remove(i);
                arreglo_coordenadas_switch.remove(i);

                _Protoboard_Funcional.eliminarElemento(_Protoboard_Funcional, posicion1_x, posicion1_y);




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
                    // transformar coordenadas a posiciones de la matriz para eliminar el cable y la corriente que llevaba este

                    int posicion1_x = (int) ((arreglo_coordenadas_cables.get(i) - 15) / 20);
                    int posicion1_y = (arreglo_coordenadas_cables.get(i+1).intValue());
                    posicion1_y = transformacionY_coordA_Matriz(posicion1_y);


                    arreglo_coordenadas_cables.remove(i);
                    arreglo_coordenadas_cables.remove(i);
                    arreglo_coordenadas_cables.remove(i);
                    arreglo_coordenadas_cables.remove(i);



                    _Protoboard_Funcional.eliminarElemento(_Protoboard_Funcional, posicion1_x, posicion1_y);

                    for (int fil = 0 ; fil < 30 ; fil++){
                        for (int com = 0 ; com < 17 ; com++){
                            if (_Protoboard_Funcional.protoboard[fil][com]._led!=null && _Protoboard_Funcional.protoboard[fil][com]._led.posicion1.coordenadax!=-1){
                                _Protoboard_Funcional.protoboard[fil][com]._led.revisado=false;
                            }
                        }
                    }
                    for (int fil = 0; fil < 30; fil++) {
                        for (int com = 0; com < 17; com++) {
                            if (_Protoboard_Funcional.protoboard[fil][com]._cable!=null){
                                _Protoboard_Funcional.protoboard[fil][com]._cable.procesado=false;
                            }

                        }
                    }


                    gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                    dibujarTodo();
                } else{
                    // borrar octoswitch
                    for ( i = 0 ; i < arreglo_coordenadas_OctoSwitch.size() && !cent_octo_switch; i+=2){
                        if ((calcularDistanciaPuntos(x, arreglo_coordenadas_OctoSwitch.get(i), 40 )) && (calcularDistanciaPuntos(y, arreglo_coordenadas_OctoSwitch.get(i+1), 40 ))){
                            cent_octo_switch = true;
                        }
                    } i = i-2;
                    if (cent_octo_switch){
                        // transformar coordenadas a posiciones de la matriz para eliminar el cable y la corriente que llevaba este
                        int posicion1_x = (int) (((arreglo_coordenadas_OctoSwitch.get(i) + 70)) - 15) / 20;
                        posicion1_x = posicion1_x - 3;
                        int posicion1_y = (arreglo_coordenadas_OctoSwitch.get(i+1).intValue() + 24);
                        posicion1_y = transformacionY_coordA_Matriz(posicion1_y);
                        posicion1_y= posicion1_y - 1;

                        arreglo_coordenadas_OctoSwitch.remove(i);
                        arreglo_coordenadas_OctoSwitch.remove(i);

                        _Protoboard_Funcional.eliminarElemento(_Protoboard_Funcional, posicion1_x, posicion1_y);

                        gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                        dibujarTodo();
                    } else{
                        // borrar resistencia
                        for (i = 0 ; i < arreglo_coordenadas_resistencias.size() && !cent_resistencia; i+=2){
                            if ((calcularDistanciaPuntos(x, arreglo_coordenadas_resistencias.get(i), 40 )) && (calcularDistanciaPuntos(y, arreglo_coordenadas_resistencias.get(i+1), 40 ))){
                                cent_resistencia = true;
                            }
                        } i = i-2;
                        if (cent_resistencia){
                            int posicion1_x = (int) (((arreglo_coordenadas_resistencias_patitas.get(i*2))) / 20);
                            int posicion1_y = (arreglo_coordenadas_resistencias_patitas.get((i*2)+1).intValue());
                            posicion1_y = transformacionY_coordA_Matriz(posicion1_y+15);

                            arreglo_coordenadas_resistencias.remove(i);
                            arreglo_coordenadas_resistencias.remove(i);

                            arreglo_coordenadas_resistencias_patitas.remove(i*2);
                            arreglo_coordenadas_resistencias_patitas.remove(i*2);
                            arreglo_coordenadas_resistencias_patitas.remove(i*2);
                            arreglo_coordenadas_resistencias_patitas.remove(i*2);


                            _Protoboard_Funcional.eliminarElemento(_Protoboard_Funcional, posicion1_x, posicion1_y);

                            gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                            dibujarTodo();
                        }
                        else{
                            // borrar chip
                            for (i = 0 ; i < arreglo_coordenadas_chip.size() && !cent_chip; i+=2){
                                if ((calcularDistanciaPuntos(x, arreglo_coordenadas_chip.get(i), 40 )) && (calcularDistanciaPuntos(y, arreglo_coordenadas_chip.get(i+1), 40 ))){
                                    cent_chip = true;
                                }
                            } i = i-2;
                            if (cent_chip){
                                int posicion1_x = (int) (((arreglo_coordenadas_chip.get(i))) / 20);
                                int posicion1_y = (arreglo_coordenadas_chip.get(i+1).intValue());
                                posicion1_y = transformacionY_coordA_Matriz(posicion1_y+15);

                                arreglo_coordenadas_chip.remove(i);
                                arreglo_coordenadas_chip.remove(i);

                                _Protoboard_Funcional.eliminarElemento(_Protoboard_Funcional, posicion1_x, posicion1_y);

                                gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                                dibujarTodo();
                            }
                            else {
                                // borrar display
                                for (i = 0; i < arreglo_coordenadas_display.size() && !cent_display; i += 2) {
                                    if ((calcularDistanciaPuntos(x, arreglo_coordenadas_display.get(i), 40)) && (calcularDistanciaPuntos(y, arreglo_coordenadas_display.get(i + 1), 40))) {
                                        cent_display = true;
                                    }
                                } i = i - 2;
                                if (cent_display) {
                                    int posicion1_x = (int) (((arreglo_coordenadas_display.get(i))) / 20);
                                    int posicion1_y = (arreglo_coordenadas_display.get(i + 1).intValue());
                                    posicion1_y = transformacionY_coordA_Matriz(posicion1_y + 15);

                                    arreglo_coordenadas_display.remove(i);
                                    arreglo_coordenadas_display.remove(i);

                                    _Protoboard_Funcional.eliminarElemento(_Protoboard_Funcional, posicion1_x, posicion1_y);

                                    gc.clearRect(0, 0, tablero.getWidth(), tablero.getHeight());
                                    dibujarTodo();
                                }
                            }
                        }


                    }

                }
            }
        }


    }


    int auxx = 0, auxy = 0;
    private void soltarMouse(MouseEvent event) {
        GraphicsContext gc = tablero.getGraphicsContext2D();

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
            int posicion1_y = transformacionY_coordA_Matriz(punto_inicio_y_cable);
            int posicion2_y = transformacionY_coordA_Matriz(punto_final_y_cable);

//            if (punto_final_x_cable - punto_inicio_x_cable > 150 || punto_inicio_y_cable - punto_final_y_cable > 100){ comentado para probar otras cosas
//                JOptionPane.showMessageDialog(null, "Haga el cable mas corto."); { else
            if((posicion1_x != posicion2_x) || (posicion1_y != posicion2_y)){
                if (punto_final_x_cable <= 0  || punto_inicio_y_cable >= 285 || punto_final_y_cable<=0 || punto_final_y_cable >=285){
                    JOptionPane.showMessageDialog(null, "Ingrese el cable dentro del protoboard");

                }else if(posicion2_x==32||posicion1_x==32|| posicion1_x == 31 || posicion2_x==31 || posicion1_x== 30 || posicion2_x==30 ||posicion1_x == 34 && punto_inicio_y_cable > 270 || posicion1_x == 34 && punto_inicio_y_cable<240 || posicion1_x == 33 && punto_inicio_y_cable > 270 || posicion1_x == 33 && punto_inicio_y_cable<240 || posicion2_x==34 && punto_final_y_cable >270 || posicion2_x == 34 && punto_final_y_cable<240 || posicion2_x==33 && punto_final_y_cable < 240 || posicion2_x == 33 && punto_final_y_cable > 270 || posicion2_x==35 && punto_final_y_cable < 240 || posicion2_x == 35 && punto_final_y_cable > 270){
                    JOptionPane.showMessageDialog(null,"Ingrese el cable dentro del protoboard o conectado a las puntas de la bateria");
                }
                else{
                    // agregar las coordenadas al arreglo



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
                    if(conectado_bateria){
                        if(posicion1_x == -3 || posicion1_x == -2 ){
                            if(!_Protoboard_Funcional.protoboard[posicion2_x][posicion2_y].conexion) {
                                arreglo_coordenadas_cables.add(punto_inicio_x_cable);
                                arreglo_coordenadas_cables.add(punto_inicio_y_cable);
                                arreglo_coordenadas_cables.add(punto_final_x_cable);
                                arreglo_coordenadas_cables.add(punto_final_y_cable);
                                // guarda el cable
                                _Protoboard_Funcional.cableSet(_Protoboard_Funcional, posicion1_x, posicion1_y, posicion2_x, posicion2_y, conectado_bateria);

                                // luego de poner un cable, chequear si paso corriente a un led, para esto buscamos en el protoboard a ver si hay un led encendido, y lo dibujamos rojo
                                // si no, lo dibujamos en darkred
                                revisarLedsEncendidos(_Protoboard_Funcional, gc);

                                gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                                dibujarTodo();


                                // dibujar el cable
                                dibujador.dibujarCable(gc, (int) punto_inicio_x_cable, (int) punto_inicio_y_cable, (int) punto_final_x_cable, (int) punto_final_y_cable);
                                contador_cables++;
                                movible_cable = false;


                                btnAgregarCable.setDisable(false);
                                btnAgregarLed.setDisable(false);
                                btnAgregarSwitch.setDisable(false);
                                btnEliminarObj.setDisable(false);
                                btnAgregarOctoSwitch.setDisable(false);
                                btnAgregarResistencia.setDisable(false);
                                btnAgregarChip.setDisable(false);
                                btnAgregarDisplay.setDisable(false);
                                btnResetearProtoboard.setDisable(false);

                            }
                        }
                        if(posicion2_x == -3 || posicion2_x == -2 ){
                            if(!_Protoboard_Funcional.protoboard[posicion1_x][posicion1_y].conexion) {
                                arreglo_coordenadas_cables.add(punto_inicio_x_cable);
                                arreglo_coordenadas_cables.add(punto_inicio_y_cable);
                                arreglo_coordenadas_cables.add(punto_final_x_cable);
                                arreglo_coordenadas_cables.add(punto_final_y_cable);
                                // guarda el cable
                                _Protoboard_Funcional.cableSet(_Protoboard_Funcional, posicion1_x, posicion1_y, posicion2_x, posicion2_y, conectado_bateria);

                                // luego de poner un cable, chequear si paso corriente a un led, para esto buscamos en el protoboard a ver si hay un led encendido, y lo dibujamos rojo
                                // si no, lo dibujamos en darkred
                                revisarLedsEncendidos(_Protoboard_Funcional, gc);

                                gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                                dibujarTodo();


                                // dibujar el cable
                                dibujador.dibujarCable(gc, (int) punto_inicio_x_cable, (int) punto_inicio_y_cable, (int) punto_final_x_cable, (int) punto_final_y_cable);
                                contador_cables++;
                                movible_cable = false;


                                btnAgregarCable.setDisable(false);
                                btnAgregarLed.setDisable(false);
                                btnAgregarSwitch.setDisable(false);
                                btnEliminarObj.setDisable(false);
                                btnAgregarOctoSwitch.setDisable(false);
                                btnAgregarResistencia.setDisable(false);
                                btnAgregarChip.setDisable(false);
                                btnAgregarDisplay.setDisable(false);
                                btnResetearProtoboard.setDisable(false);
                            }
                        }
                    } else
                    if(!_Protoboard_Funcional.protoboard[posicion1_x][posicion1_y].conexion && !_Protoboard_Funcional.protoboard[posicion2_x][posicion2_y].conexion) {
                        arreglo_coordenadas_cables.add(punto_inicio_x_cable);
                        arreglo_coordenadas_cables.add(punto_inicio_y_cable);
                        arreglo_coordenadas_cables.add(punto_final_x_cable);
                        arreglo_coordenadas_cables.add(punto_final_y_cable);
                        // guarda el cable
                        _Protoboard_Funcional.cableSet(_Protoboard_Funcional, posicion1_x, posicion1_y, posicion2_x, posicion2_y, conectado_bateria);

                        // luego de poner un cable, chequear si paso corriente a un led, para esto buscamos en el protoboard a ver si hay un led encendido, y lo dibujamos rojo
                        // si no, lo dibujamos en darkred
                        revisarLedsEncendidos(_Protoboard_Funcional, gc);

                        gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                        dibujarTodo();


                        // dibujar el cable
                        dibujador.dibujarCable(gc, (int) punto_inicio_x_cable, (int) punto_inicio_y_cable, (int) punto_final_x_cable, (int) punto_final_y_cable);
                        contador_cables++;
                        movible_cable = false;


                        btnAgregarCable.setDisable(false);
                        btnAgregarLed.setDisable(false);
                        btnAgregarSwitch.setDisable(false);
                        btnEliminarObj.setDisable(false);
                        btnAgregarOctoSwitch.setDisable(false);
                        btnAgregarResistencia.setDisable(false);
                        btnAgregarChip.setDisable(false);
                        btnAgregarDisplay.setDisable(false);
                        btnResetearProtoboard.setDisable(false);
                    }}}
            for (int fil = 0; fil < 30; fil++) {
                for (int com = 0; com < 17; com++) {
                    if (_Protoboard_Funcional.protoboard[fil][com]._cable!=null){
                        _Protoboard_Funcional.protoboard[fil][com]._cable.procesado=false;
                    }

                }
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
                int posicion1_y = transformacionY_coordA_Matriz(punto_final_y_patita);

                cantidad_patitas++;
                if(cantidad_patitas == 1){
                    auxx = posicion1_x;
                    auxy = posicion1_y;
                }

                // dibujar patitas
                if (cantidad_patitas==1){
                    gc.setStroke(Color.RED);
                } else{
                    gc.setStroke(Color.DARKBLUE);
                }
//                gc.setStroke(Color.GRAY);
                gc.setLineWidth(3);
                gc.strokeLine(punto_inicio_x_patita, punto_inicio_y_patita, punto_final_x_patita, punto_final_y_patita);
                patita_led_1=false;

                dibujar_patitas=false;


                Led led = _Protoboard_Funcional.ledInitiatorStart(_Protoboard_Funcional, posicion1_x, posicion1_y,auxx, auxy, cantidad_patitas, color_led);

                if (cantidad_patitas==2){
                    btnAgregarCable.setDisable(false);
                    btnAgregarLed.setDisable(false);
                    btnAgregarSwitch.setDisable(false);
                    btnEliminarObj.setDisable(false);
                    btnAgregarOctoSwitch.setDisable(false);
                    btnAgregarResistencia.setDisable(false);
                    btnAgregarChip.setDisable(false);
                    btnAgregarDisplay.setDisable(false);
                    btnResetearProtoboard.setDisable(false);
                    if (led.encendido){
                        if (color_led.equals("rojo")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "rojo",_Protoboard_Funcional);
                        }
                        if (color_led.equals("azul")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "azul",_Protoboard_Funcional);
                        }
                        if (color_led.equals("verde")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "verde",_Protoboard_Funcional);
                        }
                        if (color_led.equals("amarillo")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "amarillo",_Protoboard_Funcional);
                        }
                        if (color_led.equals("violeta")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "violeta",_Protoboard_Funcional);
                        }
                    } else {
                        if (color_led.equals("rojo")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "rojo_oscuro",_Protoboard_Funcional);
                        }
                        if (color_led.equals("azul")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "azul_oscuro",_Protoboard_Funcional);
                        }
                        if (color_led.equals("verde")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "verde_oscuro",_Protoboard_Funcional);
                        }
                        if (color_led.equals("amarillo")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "amarillo_oscuro",_Protoboard_Funcional);
                        }
                        if (color_led.equals("violeta")) {
                            dibujador.dibujarLed(gc, x_led, y_led, "violeta_oscuro",_Protoboard_Funcional);
                        }
                    }
                    if (led.quemado){
                        dibujador.dibujarLed(gc, x_led, y_led, "negro",_Protoboard_Funcional);
                    }

                }
            }


        }
        if (led_puesto && !patita_led_1){
            patita_led_1=true;
        }

        // restablecer lo revisado del led

    }
    boolean agregar_patita_1=false;
    boolean agregar_patita_2=false;
    boolean resistencia_puesta=false;
    int click_count=0;
    private void click(MouseEvent event) {
        GraphicsContext gc = tablero.getGraphicsContext2D();
        Color color_click_switch = getColor(event.getX(), event.getY());;

        // si el click es del color del boton
        if (color_click_switch.equals(Color.BLACK) && !activar_eliminacion || color_click_switch.equals(Color.LIMEGREEN) && !activar_eliminacion){
            // si presiono algo que podria ser el boton, se busca que sea el boton del switch

            // buscar cual switch es
            double busca_x_switch =  event.getX();
            double busca_y_switch =  event.getY();



            // buscar el switch que se apreto
            double[] puntoCercano = alcanzarPuntoCercano(event.getX(), event.getY());
            if (puntoCercano != null) {
                busca_x_switch = puntoCercano[0];
                busca_y_switch = puntoCercano[1];
            }
            busca_x_switch-=24;
            busca_y_switch-=24;

            // recorrer el arreglo de switch para ver cual se apreto
            for (int i = 0 ; i < arreglo_coordenadas_switch.size() ; i+=2){
                assert puntoCercano != null;
                if ((calcularDistanciaPuntos(puntoCercano[0], arreglo_coordenadas_switch.get(i), 10 )) && (calcularDistanciaPuntos(puntoCercano[1], arreglo_coordenadas_switch.get(i+1), 10))){
                    busca_x_switch = arreglo_coordenadas_switch.get(i);
                    busca_y_switch = arreglo_coordenadas_switch.get(i+1);
                    break;
                }
            } int coord_switch_x = (int) busca_x_switch;
            int coord_switch_y = (int) busca_y_switch;

            busca_x_switch+=24; busca_y_switch+=24;


            // transformar x e y para buscar ahora en la matriz de 30 x 14
            int transformacion_x_switch =  (int) ((busca_x_switch - 15 ) / 20);
            int transformacion_y_switch= transformacionY_coordA_Matriz(busca_y_switch);


            if (calcularDistanciaPuntos(event.getX()-24, coord_switch_x, 10) && calcularDistanciaPuntos(event.getY()-24, coord_switch_y, 10)){
                Switch _switch = _Protoboard_Funcional.protoboard[transformacion_x_switch-1][transformacion_y_switch-1]._switch;
                if (!_switch.prendido){

                    _switch.prendido=true;
                    System.out.println("Switch apretado");
                    // pasar la corriente del switch
                    _Protoboard_Funcional.toggleSwitch(_Protoboard_Funcional, _switch, _switch.prendido);
                    revisarLedsEncendidos(_Protoboard_Funcional, gc);
                    gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                    dibujarTodo();
                } else {
                    _switch.prendido=false;
                    System.out.println("Switch soltado");
                    // pasar la corriente del switch a la columna "original"
                    _Protoboard_Funcional.toggleSwitch(_Protoboard_Funcional, _switch, _switch.prendido);
                    revisarLedsEncendidos(_Protoboard_Funcional, gc);
                    gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                    dibujarTodo();
                }


            }



        }

        if (color_click_switch.equals(Color.GREEN) && !activar_eliminacion){
            switch_bateria=false;
            gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
            dibujarTodo();
            System.out.println("se apago la bateria");
            // buscamos el o los cables conectados a la bateria y eliminamos corriente
            for (int fil = 0; fil < 30; fil++) {
                for (int com = 0; com < 17; com++) {
                    if (_Protoboard_Funcional.protoboard[fil][com]._cable!=null){
                        _Protoboard_Funcional.protoboard[fil][com]._cable.procesado=false;
                    }

                }
            }
            for (int i = 0 ; i < 30 ; i++){
                for (int j = 0 ; j < 17 ; j++){
                    if (_Protoboard_Funcional.protoboard[i][j]._cable != null && _Protoboard_Funcional.protoboard[i][j]._cable.conexionBateria){
                        _Protoboard_Funcional.eliminarCorriente(_Protoboard_Funcional, i, j, true);
                    }
                }
            }
            _Protoboard_Funcional.QuemadoCheck(_Protoboard_Funcional);
            gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
            dibujarTodo();

        } else if (color_click_switch.equals(Color.INDIANRED) && !activar_eliminacion){
            System.out.println("se prendio la bateria");
            switch_bateria=true;
            gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
            dibujarTodo();
            // buscamos el o los cables conectados a la bateria y pasamos corriente
            for (int fil = 0; fil < 30; fil++) {
                for (int com = 0; com < 17; com++) {
                    if (_Protoboard_Funcional.protoboard[fil][com]._cable!=null){
                        _Protoboard_Funcional.protoboard[fil][com]._cable.procesado=false;
                    }

                }
            }
            for (int i = 0 ; i < 30 ; i++){
                for (int j = 0 ; j < 17 ; j++){
                    if (_Protoboard_Funcional.protoboard[i][j]._cable != null && _Protoboard_Funcional.protoboard[i][j]._cable.posicion1.coordenadax!=-1 && _Protoboard_Funcional.protoboard[i][j]._cable.conexionBateria){
                        cable _cable = _Protoboard_Funcional.protoboard[i][j]._cable;
                        _Protoboard_Funcional.pasarCorriente(_Protoboard_Funcional, _cable);
                    }
                }
            }
            for (int fil = 0; fil < 30; fil++) {
                for (int com = 0; com < 17; com++) {
                    if (_Protoboard_Funcional.protoboard[fil][com]._cable!=null){
                        _Protoboard_Funcional.protoboard[fil][com]._cable.procesado=false;
                    }

                }
            }
            _Protoboard_Funcional.QuemadoCheck(_Protoboard_Funcional);
            gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
            dibujarTodo();

        }

        if (color_click_switch.equals(Color.rgb(255,255,254)) && !activar_eliminacion){
            double x_del_switch_presionado = event.getX();
            double y_del_switch_presionado = event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(x_del_switch_presionado, y_del_switch_presionado);
            if (puntoCercano != null) {
                x_del_switch_presionado = puntoCercano[0];
                y_del_switch_presionado = puntoCercano[1];
            }
            int transformar_x_switch = (int) ((x_del_switch_presionado - 15 ) / 20);
            int transformar_y_switch= transformacionY_coordA_Matriz(y_del_switch_presionado);

            if (transformar_y_switch == _Protoboard_Funcional.protoboard[transformar_x_switch][transformar_y_switch]._octoSwitch.posicion1.coordenaday){
                transformar_y_switch++;
            }

            _Protoboard_Funcional.protoboard[transformar_x_switch][transformar_y_switch]._octoSwitch.pasarCorrientePosicion(_Protoboard_Funcional, transformar_x_switch, transformar_y_switch);
            tablero.getGraphicsContext2D().clearRect(0,0,tablero.getWidth(),tablero.getHeight());
            dibujarTodo();

        }

        else if (color_click_switch.equals(Color.DARKBLUE) && !activar_eliminacion){
            double x_del_switch_presionado = event.getX();
            double y_del_switch_presionado = event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(x_del_switch_presionado, y_del_switch_presionado);
            if (puntoCercano != null) {
                x_del_switch_presionado = puntoCercano[0];
                y_del_switch_presionado = puntoCercano[1];
            }
            int transformar_x_switch = (int) ((x_del_switch_presionado - 15 ) / 20);
            int transformar_y_switch= transformacionY_coordA_Matriz(y_del_switch_presionado);

            if (transformar_y_switch == _Protoboard_Funcional.protoboard[transformar_x_switch][transformar_y_switch]._octoSwitch.posicion1.coordenaday){
                transformar_y_switch++;
            }

            _Protoboard_Funcional.protoboard[transformar_x_switch][transformar_y_switch]._octoSwitch.eliminarCorrientePosicion(_Protoboard_Funcional, transformar_x_switch, transformar_y_switch);
            tablero.getGraphicsContext2D().clearRect(0,0,tablero.getWidth(),tablero.getHeight());
            dibujarTodo();
        }
        double x_resistencia = 0;
        double y_resistencia = 0;


        if(agregar_resistencia&&click_count==0){
            agregar_resistencia=false;


            x_resistencia= (int) event.getX();
            y_resistencia= (int) event.getY();


            double[] puntoCercano = alcanzarPuntoCercano(x_resistencia, y_resistencia);
            if (puntoCercano != null) {
                x_resistencia = (int) (puntoCercano[0] - 15);
                y_resistencia = (int) (puntoCercano[1] - 15);
            }


            arreglo_coordenadas_resistencias.add(x_resistencia);
            arreglo_coordenadas_resistencias.add(y_resistencia);
            resistencia_puesta = true;
            PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
            pause.setOnFinished(e -> click_count++);
            pause.play();
            JOptionPane.showMessageDialog(null, "Indicar posición 1");

            //dibujador.dibujarResistencia(gc, x_led, y_led);
            btnAgregarCable.setDisable(true);
            btnAgregarLed.setDisable(true);
            btnAgregarSwitch.setDisable(true);
            btnAgregarOctoSwitch.setDisable(true);
            btnEliminarObj.setDisable(true);
            btnAgregarResistencia.setDisable(true);
            btnAgregarChip.setDisable(true);
            btnAgregarDisplay.setDisable(true);
            btnResetearProtoboard.setDisable(true);

        }
        if(resistencia_puesta && !agregar_patita_1){
            agregar_patita_1=true;
        }
        double patitas_x_resistencia=0;
        double patitas_y_resistencia=0;
        double patitas_x_resistencia_2=0;
        double patitas_y_resistencia_2=0;

        if(agregar_patita_1&&click_count==1) {


            patitas_x_resistencia = (int) event.getX();
            patitas_y_resistencia = (int) event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(patitas_x_resistencia,patitas_y_resistencia);
            if (puntoCercano != null) {

                patitas_x_resistencia = (int) (puntoCercano[0] - 15);
                patitas_y_resistencia = (int) (puntoCercano[1] - 15);

            }
            int transformar_x_resistencia = (int) ((patitas_x_resistencia) / 20);
            int transformar_y_resistencia = transformacionY_coordA_Matriz(patitas_y_resistencia+15);
            if(!_Protoboard_Funcional.protoboard[(int)transformar_x_resistencia][(int)transformar_y_resistencia].conexion){

                arreglo_coordenadas_resistencias_patitas.add(patitas_x_resistencia);
                arreglo_coordenadas_resistencias_patitas.add(patitas_y_resistencia);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
                pause.setOnFinished(e -> click_count++);
                pause.play();
                JOptionPane.showMessageDialog(null, "Indicar posición 2");
                agregar_patita_1=false;
                agregar_patita_2=true;}
        }

        if (agregar_patita_2&&click_count==2) {




            patitas_x_resistencia_2 = (int) event.getX();
            patitas_y_resistencia_2 = (int) event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(patitas_x_resistencia_2,patitas_y_resistencia_2);
            if (puntoCercano != null) {
                patitas_x_resistencia_2 = (int) (puntoCercano[0] - 15);
                patitas_y_resistencia_2 = (int) (puntoCercano[1] - 15);
            }
            int transformar_x_patita = (int) ((patitas_x_resistencia_2) / 20);
            int transformar_y_patita = transformacionY_coordA_Matriz(patitas_y_resistencia_2+15);
            if(!_Protoboard_Funcional.protoboard[(int)transformar_x_patita][(int)transformar_y_patita].conexion){

                arreglo_coordenadas_resistencias_patitas.add(patitas_x_resistencia_2);
                arreglo_coordenadas_resistencias_patitas.add(patitas_y_resistencia_2);

                click_count=0;
                int tamaño = arreglo_coordenadas_resistencias.size();
                int tamaño_patitas=arreglo_coordenadas_resistencias_patitas.size();
                x_resistencia=arreglo_coordenadas_resistencias.get(tamaño-2);
                y_resistencia=arreglo_coordenadas_resistencias.get(tamaño-1);
                patitas_x_resistencia=arreglo_coordenadas_resistencias_patitas.get(tamaño_patitas-4);
                patitas_y_resistencia=arreglo_coordenadas_resistencias_patitas.get(tamaño_patitas-3);



                Dialog<List<Pair<String, String>>> dialog = new Dialog<>();
                dialog.setTitle("Datos resistencia");

                // Set the button types
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                // Create the combo boxes
                ComboBox<String> comboBox1 = new ComboBox<>();
                comboBox1.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
                comboBox1.setPromptText("Valor banda 1");

                ComboBox<String> comboBox2 = new ComboBox<>();
                comboBox2.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
                comboBox2.setPromptText("Valor banda 2");

                ComboBox<String> comboBox3 = new ComboBox<>();
                comboBox3.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
                comboBox3.setPromptText("Valor multiplicador");

                ComboBox<String> ComboBox4 = new ComboBox<>();
                ComboBox4.getItems().addAll("1", "2");
                ComboBox4.setPromptText("Valor tolerancia");

                // Create a grid pane and add the combo boxes
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.add(new Label("Banda 1:"), 0, 0);
                grid.add(comboBox1, 1, 0);
                grid.add(new Label("Banda 2:"), 0, 1);
                grid.add(comboBox2, 1, 1);
                grid.add(new Label("Multiplicador:"), 0, 2);
                grid.add(comboBox3, 1, 2);
                grid.add(new Label("Tolerancia:"), 0, 3);
                grid.add(ComboBox4, 1, 3);


                dialog.getDialogPane().setContent(grid);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == ButtonType.OK) {
                        if (comboBox1.getValue() == null || comboBox2.getValue() == null || comboBox3.getValue() == null || ComboBox4.getValue() == null) {
                            JOptionPane.showMessageDialog(null, "Ingrese los valores");
                            return null;
                        }
                        {
                            List<Pair<String, String>> result = new ArrayList<>();
                            result.add(new Pair<>("banda 1", comboBox1.getValue()));
                            result.add(new Pair<>("banda 2", comboBox2.getValue()));
                            result.add(new Pair<>("multiplicador", comboBox3.getValue()));
                            result.add(new Pair<>("tolerancia", ComboBox4.getValue()));
                            return result;
                        }
                    }
                    return null;
                });

                int banda1 = 0, banda2 = 0, multiplicador = 0, tolerancia = 0;
                dialog.showAndWait().ifPresent(result -> {
                });

                // intentarlo hasta que se ingresen bien los valores
                if (comboBox1.getValue() == null || comboBox2.getValue() == null || comboBox3.getValue() == null || ComboBox4.getValue() == null) {
                    arreglo_coordenadas_resistencias.removeLast();
                    arreglo_coordenadas_resistencias.removeLast();
                    arreglo_coordenadas_resistencias_patitas.removeLast();
                    arreglo_coordenadas_resistencias_patitas.removeLast();
                    arreglo_coordenadas_resistencias_patitas.removeLast();
                    arreglo_coordenadas_resistencias_patitas.removeLast();
                    btnAgregarCable.setDisable(false);
                    btnAgregarLed.setDisable(false);
                    btnAgregarSwitch.setDisable(false);
                    btnAgregarOctoSwitch.setDisable(false);
                    btnEliminarObj.setDisable(false);
                    btnAgregarResistencia.setDisable(false);
                    btnAgregarChip.setDisable(false);
                    btnAgregarDisplay.setDisable(false);
                    btnResetearProtoboard.setDisable(false);
                } else{
                    try {
                        banda1 = Integer.parseInt(comboBox1.getValue());
                        banda2 = Integer.parseInt(comboBox2.getValue());
                        multiplicador = Integer.parseInt(comboBox3.getValue());
                        tolerancia = Integer.parseInt(ComboBox4.getValue());

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ingrese bien los valores");

                    }

                    int transformacion_pos_1_x = (int) ((patitas_x_resistencia ) / 20);
                    int transformacion_pos_1_y = transformacionY_coordA_Matriz(patitas_y_resistencia+15);
                    int transformacion_pos_2_x = (int) ((patitas_x_resistencia_2) / 20);
                    int transformacion_pos_2_y = transformacionY_coordA_Matriz(patitas_y_resistencia_2+15);
                    _Protoboard_Funcional.resistenciaSet(_Protoboard_Funcional, transformacion_pos_1_x, transformacion_pos_1_y, transformacion_pos_2_x, transformacion_pos_2_y, banda1, banda2, multiplicador, tolerancia);
                    dibujador.dibujarResistencia(gc, x_resistencia,y_resistencia,patitas_x_resistencia,patitas_y_resistencia,patitas_x_resistencia_2,patitas_y_resistencia_2, banda1, banda2, multiplicador, tolerancia);
                    gc.clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                    dibujarTodo();
                    btnAgregarCable.setDisable(false);
                    btnAgregarLed.setDisable(false);
                    btnAgregarSwitch.setDisable(false);
                    btnAgregarOctoSwitch.setDisable(false);
                    btnEliminarObj.setDisable(false);
                    btnAgregarResistencia.setDisable(false);
                    btnAgregarChip.setDisable(false);
                    btnAgregarDisplay.setDisable(false);
                    btnResetearProtoboard.setDisable(false);}
            }
        }



        if (agrega_octo_switch){
            double inicio_x = event.getX();
            double inicio_y = event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(inicio_x, inicio_y);
            if (puntoCercano != null) {
                inicio_x = puntoCercano[0];
                inicio_y = puntoCercano[1];
            } arreglo_coordenadas_OctoSwitch.add(inicio_x-70); arreglo_coordenadas_OctoSwitch.add(inicio_y-24);
            dibujador.dibujarOctoSwitch(inicio_x-70, inicio_y-24,tablero.getGraphicsContext2D(), _Protoboard_Funcional );

            int transformacion_x_switch = (int) ((inicio_x - 15 ) / 20);
            int transformacion_y_switch= transformacionY_coordA_Matriz(inicio_y);

            _Protoboard_Funcional.octoSwitchSet(_Protoboard_Funcional, transformacion_x_switch, transformacion_y_switch);



            btnAgregarCable.setDisable(false);
            btnAgregarLed.setDisable(false);
            btnAgregarSwitch.setDisable(false);
            btnEliminarObj.setDisable(false);
            btnAgregarOctoSwitch.setDisable(false);
            btnAgregarResistencia.setDisable(false);
            btnAgregarChip.setDisable(false);
            btnAgregarDisplay.setDisable(false);
            btnResetearProtoboard.setDisable(false);
            agrega_octo_switch=false;
        }
        if (agregar_chip){
            double inicio_x = event.getX();
            double inicio_y = event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(inicio_x, inicio_y);
            if (puntoCercano != null) {
                inicio_x = puntoCercano[0];
                inicio_y = puntoCercano[1];
            } arreglo_coordenadas_chip.add(inicio_x-27); arreglo_coordenadas_chip.add(inicio_y-22);
            dibujador.dibujarChip(gc, (int) (inicio_x-27), (int) (inicio_y-22),tipo_chip);
            int transformacion_x_chip = (int) ((inicio_x - 15 ) / 20);
            int transformacion_y_chip= transformacionY_coordA_Matriz(inicio_y);
            // recuperar el punto de arriba a la izquierda
            transformacion_x_chip = transformacion_x_chip-1;
            transformacion_y_chip = transformacion_y_chip-1;


            _Protoboard_Funcional.chipSet(_Protoboard_Funcional, transformacion_x_chip, transformacion_y_chip, tipo_chip);

            btnAgregarCable.setDisable(false);
            btnAgregarLed.setDisable(false);
            btnAgregarSwitch.setDisable(false);
            btnEliminarObj.setDisable(false);
            btnAgregarOctoSwitch.setDisable(false);
            btnAgregarResistencia.setDisable(false);
            btnAgregarChip.setDisable(false);
            btnAgregarDisplay.setDisable(false);
            btnResetearProtoboard.setDisable(false);
            agregar_chip=false;
        }
        if (agregar_display){
            double inicio_x = event.getX();
            double inicio_y = event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(inicio_x, inicio_y);
            if (puntoCercano != null) {
                inicio_x = puntoCercano[0];
                inicio_y = puntoCercano[1];
            } arreglo_coordenadas_display.add(inicio_x-27); arreglo_coordenadas_display.add(inicio_y-50);
            dibujador.dibujarDisplay(gc, (int) (inicio_x-27), (int) (inicio_y-50), _Protoboard_Funcional);
            int transformacion_x_display = (int) ((inicio_x - 15 ) / 20);
            int transformacion_y_display= transformacionY_coordA_Matriz(inicio_y);
            // recuperar el punto de arriba a la izquierda
            transformacion_x_display = transformacion_x_display-1;
            transformacion_y_display = transformacion_y_display-3;

            _Protoboard_Funcional.displaySet(_Protoboard_Funcional, transformacion_x_display, transformacion_y_display);

            btnAgregarCable.setDisable(false);
            btnAgregarLed.setDisable(false);
            btnAgregarSwitch.setDisable(false);
            btnEliminarObj.setDisable(false);
            btnAgregarOctoSwitch.setDisable(false);
            btnAgregarResistencia.setDisable(false);
            btnAgregarChip.setDisable(false);
            btnAgregarDisplay.setDisable(false);
            btnResetearProtoboard.setDisable(false);

            agregar_display=false;
        }
        if (activar_eliminacion){
            double inicio_x_eliminar = event.getX();
            double inicio_y_eliminar = event.getY();
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
            btnAgregarOctoSwitch.setDisable(false);
            btnAgregarResistencia.setDisable(false);
            btnAgregarChip.setDisable(false);
            btnAgregarDisplay.setDisable(false);
            btnResetearProtoboard.setDisable(false);
            activar_eliminacion=false;
        }

        if (agrega_switch) { // agrega un led al hacer click en una posicion // verificaciones y demas
            x_switch = event.getX();
            y_switch =  event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(x_switch, y_switch);
            if (puntoCercano != null) {
                x_switch = puntoCercano[0];
                y_switch = puntoCercano[1];
            } arreglo_coordenadas_switch.add(x_switch-24); arreglo_coordenadas_switch.add(y_switch-24);

            int transformacion_x_switch =  (int) ((x_switch - 15 ) / 20);
            int transformacion_y_switch= transformacionY_coordA_Matriz(y_switch);



            if (transformacion_x_switch == 0 || transformacion_x_switch == 29){
                JOptionPane.showMessageDialog(null,"No se puede poner un switch en los extremos del protoboard.");
                arreglo_coordenadas_switch.removeLast();
                arreglo_coordenadas_switch.removeLast();
            } else if (transformacion_y_switch < 2 || transformacion_y_switch > 14){
                JOptionPane.showMessageDialog(null,"No se puede poner un switch en los buses del protoboard.");
                arreglo_coordenadas_switch.removeLast();
                arreglo_coordenadas_switch.removeLast();
            } else if (transformacion_y_switch == 4 || transformacion_y_switch == 13 || transformacion_y_switch == 7 || transformacion_y_switch == 9){
                JOptionPane.showMessageDialog(null,"No se puede poner un switch en los extremos del protoboard.");
                arreglo_coordenadas_switch.removeLast();
                arreglo_coordenadas_switch.removeLast();
            } else {
                _Protoboard_Funcional.switchSet(_Protoboard_Funcional, transformacion_x_switch,transformacion_y_switch, false);
                x_switch-=24;
                y_switch-=24;
                tablero.getGraphicsContext2D().clearRect(0,0,tablero.getWidth(),tablero.getHeight());
                dibujarTodo();
                agrega_switch = false;
                btnAgregarCable.setDisable(false);
                btnAgregarLed.setDisable(false);
                btnAgregarSwitch.setDisable(false);
                btnEliminarObj.setDisable(false);
                btnAgregarOctoSwitch.setDisable(false);
                btnAgregarResistencia.setDisable(false);
                btnAgregarChip.setDisable(false);
                btnAgregarDisplay.setDisable(false);
                btnResetearProtoboard.setDisable(false);
            }


        }
        if (agrega_led){ // agrega un led al hacer click en una posicion // verificaciones y demas
            x_led= event.getX();
            y_led= event.getY();
            double[] puntoCercano = alcanzarPuntoCercano(x_led, y_led);
            if (puntoCercano != null) {
                x_led = puntoCercano[0]-15;
                y_led = puntoCercano[1]-15;
            } arreglo_coordenadas_leds.add(x_led); arreglo_coordenadas_leds.add(y_led); // agregar al arreglo
            //cambio color led
            Dialog<List<Pair<String, String>>> dialog = new Dialog<>();
            dialog.setTitle("Color led");

            // Set the button types
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            // Create the combo boxes
            ComboBox<String> comboBox1 = new ComboBox<>();
            comboBox1.getItems().addAll("rojo","azul","verde","amarillo","violeta");
            comboBox1.setPromptText("color del led");

            // Create a grid pane and add the combo boxes
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(new Label("Color:"), 0, 0);
            grid.add(comboBox1, 1, 0);


            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    List<Pair<String, String>> result = new ArrayList<>();
                    result.add(new Pair<>("color", comboBox1.getValue()));
                    return result;
                }
                return null;
            });


            dialog.showAndWait().ifPresent(result -> {
            });

            // intentarlo hasta que se ingresen bien los valores

            if (comboBox1.getValue() == null) {
                arreglo_coordenadas_leds.removeLast();
                arreglo_coordenadas_leds.removeLast();
                btnAgregarCable.setDisable(false);
                btnAgregarLed.setDisable(false);
                btnAgregarSwitch.setDisable(false);
                btnEliminarObj.setDisable(false);
                btnAgregarOctoSwitch.setDisable(false);
                btnAgregarResistencia.setDisable(false);
                btnAgregarChip.setDisable(false);
                btnAgregarDisplay.setDisable(false);
                btnResetearProtoboard.setDisable(false);
                agrega_led=false;
                JOptionPane.showMessageDialog(null, "Ingrese bien el color");
            } else{
                try {
                    color_led =comboBox1.getValue();


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ingrese bien el color");

                }

                if (color_led.equals("rojo")) {
                    dibujador.dibujarLed(gc, x_led, y_led, "rojo_oscuro",_Protoboard_Funcional);
                }
                if (color_led.equals("azul")) {
                    dibujador.dibujarLed(gc, x_led, y_led, "azul_oscuro",_Protoboard_Funcional);
                }
                if (color_led.equals("verde")) {
                    dibujador.dibujarLed(gc, x_led, y_led, "verde_oscuro",_Protoboard_Funcional);
                }
                if (color_led.equals("amarillo")) {
                    dibujador.dibujarLed(gc, x_led, y_led, "amarillo_oscuro",_Protoboard_Funcional);
                }
                if (color_led.equals("violeta")) {
                    dibujador.dibujarLed(gc, x_led, y_led, "violeta_oscuro",_Protoboard_Funcional);
                }
                btnAgregarCable.setDisable(true);
                btnAgregarLed.setDisable(true);
                btnAgregarSwitch.setDisable(true);
                btnEliminarObj.setDisable(true);
                btnAgregarOctoSwitch.setDisable(true);
                btnAgregarResistencia.setDisable(true);
                btnAgregarChip.setDisable(true);
                btnAgregarDisplay.setDisable(true);
                btnResetearProtoboard.setDisable(true);

                agrega_led=false;
                led_puesto=true;
                patita_led_1=false;}

        } else if (patita_led_1 && led_puesto &&  cantidad_patitas<2){ // aca arreglar verificacion para que no se pongan las patitas en cualquier lado
            Color color_click = getColor(event.getX(), event.getY());

            if (color_click.equals(Color.DARKRED) || color_click.equals(Color.MIDNIGHTBLUE) || color_click.equals(Color.DARKGREEN) || color_click.equals(Color.rgb(255, 187, 0)) || color_click.equals(Color.DARKVIOLET)){
                punto_inicio_x_patita = event.getX();
                punto_inicio_y_patita = event.getY();
                dibujar_patitas=true;

            } else{

                JOptionPane.showMessageDialog(null,"Posicion invalida.");
                JOptionPane.showMessageDialog(null,"Ponga las patitas encima del led hacia un punto cercano.");

            }

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


    private double[] alcanzarPuntoCercano(double x, double y) {
        double[][] puntosDisponibles = {
                // BATERIA
                {675, 255}, {710, 255},
                // buses
                // fila 1
                {15, 15}, {35, 15},{55, 15},{75, 15}, {95, 15},{115, 15},{135, 15}, {155, 15},{175, 15},{195, 15}, {215, 15},{235, 15},{255, 15}, {275, 15},{295, 15},
                {315, 15}, {335, 15},{355, 15},{375, 15}, {395, 15},{415, 15},{435, 15}, {455, 15},{475, 15},{495, 15}, {515, 15},{535, 15},{555, 15}, {575, 15},{595, 15},
                // fila 2

                {15, 30}, {35, 30},{55, 30},{75, 30}, {95, 30},{130, 30},{135, 30}, {305, 30},{175, 30},{195, 30}, {230, 30},{235, 30},{255, 30}, {275, 30},{295, 30},
                {330, 30}, {335, 30},{355, 30},{375, 30}, {395, 30},{415, 30},{435, 30}, {455, 30},{475, 30},{495, 30}, {515, 30},{535, 30},{555, 30}, {575, 30},{595, 30},

                // fila 2.5 surco arriba
                {15,50}, {35,50},{55,50},{75,50}, {95,50},{115,50},{135,50}, {155,50},{175,50},{195,50}, {215,50},{235,50},{255,50}, {275,50},{295,50},
                {315,50}, {335,50},{355,50},{375,50}, {395,50},{415,50},{435,50}, {455,50},{475,50},{495,50}, {515,50},{535,50},{555,50}, {575,50},{595,50},

                // fila 3 canales
                {15, 70}, {35, 70},{55, 70},{75, 70}, {95, 70},{115, 70},{135, 70}, {155, 70},{175, 70},{195, 70}, {215, 70},{235, 70},{255, 70}, {275, 70},{295, 70},
                {315, 70}, {335, 70},{355, 70},{375, 70}, {395, 70},{415, 70},{435, 70}, {455, 70},{475, 70},{495, 70}, {515, 70},{535, 70},{555, 70}, {575, 70},{595, 70},

                // fila 4
                {15, 85}, {35, 85},{55, 85},{75, 85}, {95, 85},{115, 85},{135, 85}, {155, 85},{175, 85},{195, 85}, {215, 85},{235, 85},{255, 85}, {275, 85},{295, 85},
                {315, 85}, {335, 85},{355, 85},{375, 85}, {395, 85},{415, 85},{435, 85}, {455, 85},{475, 85},{495, 85}, {515, 85},{535, 85},{555, 85}, {575, 85},{595, 85},
                // fila 5
                {15, 100}, {35, 100},{55, 100},{75, 100}, {95, 100},{115, 100},{135, 100}, {155, 100},{175, 100},{195, 100}, {215, 100},{235, 100},{255, 100}, {275, 100},{295, 100},
                {315, 100}, {335, 100},{355, 100},{375, 100}, {395, 100},{415, 100},{435, 100}, {455, 100},{475, 100},{495, 100}, {515, 100},{535, 100},{555, 100}, {575, 100},{595, 100},
                // fila 6
                {15, 115}, {35, 115},{55, 115},{75, 115}, {95, 115},{115, 115},{135, 115}, {155, 115},{175, 115},{195, 115}, {215, 115},{235, 115},{255, 115}, {275, 115},{295, 115},
                {315, 115}, {335, 115},{355, 115},{375, 115}, {395, 115},{415, 115},{435, 115}, {455, 115},{475, 115},{495, 115}, {515, 115},{535, 115},{555, 115}, {575, 115},{595, 115},
                // fila 7
                {15, 130}, {35, 130},{55, 130},{75, 130}, {95, 130},{115, 130},{135, 130}, {155, 130},{175, 130},{195, 130}, {215, 130},{235, 130},{255, 130}, {275, 130},{295, 130},
                {315, 130}, {335, 130},{355, 130},{375, 130}, {395, 130},{415, 130},{435, 130}, {455, 130},{475, 130},{495, 130}, {515, 130},{535, 130},{555, 130}, {575, 130},{595, 130},
                //canal central
                {15, 150}, {35, 150},{55, 150},{75, 150}, {95, 150},{115, 150},{135, 150}, {155, 150},{175, 150},{195, 150}, {215, 150},{235, 150},{255, 150}, {275, 150},{295, 150},
                {315, 150}, {335, 150},{355, 150},{375, 150}, {395, 150},{415, 150},{435, 150}, {455, 150},{475, 150},{495, 150}, {515, 150},{535, 150},{555, 150}, {575, 150},{595, 150},
                // fila 8
                {15, 165}, {35, 165},{55, 165},{75, 165}, {95, 165},{115, 165},{135, 165}, {155, 165},{175, 165},{195, 165}, {215, 165},{235, 165},{255, 165}, {275, 165},{295, 165},
                {315, 165}, {335, 165},{355, 165},{375, 165}, {395, 165},{415, 165},{435, 165}, {455, 165},{475, 165},{495, 165}, {515, 165},{535, 165},{555, 165}, {575, 165},{595, 165},
                // fila 9
                {15, 180}, {35, 180},{55, 180},{75, 180}, {95, 180},{115, 180},{135, 180}, {155, 180},{175, 180},{195, 180}, {215, 180},{235, 180},{255, 180}, {275, 180},{295, 180},
                {315, 180}, {335, 180},{355, 180},{375, 180}, {395, 180},{415, 180},{435, 180}, {455, 180},{475, 180},{495, 180}, {515, 180},{535, 180},{555, 180}, {575, 180},{595, 180},
                // fila 10
                {15, 195}, {35, 195},{55, 195},{75, 195}, {95, 195},{115, 195},{135, 195}, {155, 195},{175, 195},{195, 195}, {215, 195},{235, 195},{255, 195}, {275, 195},{295, 195},
                {315, 195}, {335, 195},{355, 195},{375, 195}, {395, 195},{415, 195},{435, 195}, {455, 195},{475, 195},{495, 195}, {515, 195},{535, 195},{555, 195}, {575, 195},{595, 195},
                // fila 11
                {15, 210}, {35, 210},{55, 210},{75, 210}, {95, 210},{115, 210},{135, 210}, {155, 210},{175, 210},{195, 210}, {215, 210},{235, 210},{255, 210}, {275, 210},{295, 210},
                {315, 210}, {335, 210},{355, 210},{375, 210}, {395, 210},{415, 210},{435, 210}, {455, 210},{475, 210},{495, 210}, {515, 210},{535, 210},{555, 210}, {575, 210},{595, 210},
                // fila 12
                {15, 225}, {35, 225},{55, 225},{75, 225}, {95, 225},{115, 225},{135, 225}, {155, 225},{175, 225},{195, 225}, {215, 225},{235, 225},{255, 225}, {275, 225},{295, 225},
                {315, 225}, {335, 225},{355, 225},{375, 225}, {395, 225},{415, 225},{435, 225}, {455, 225},{475, 225},{495, 225}, {515, 225},{535, 225},{555, 225}, {575, 225},{595, 225},
                // fila 12.5 surco abajo
                {15, 245}, {35, 245},{55, 245},{75, 245}, {95, 245},{115, 245},{135, 245}, {155, 245},{175, 245},{195, 245}, {215, 245},{235, 245},{255, 245}, {275, 245},{295, 245},
                {315, 245}, {335, 245},{355, 245},{375, 245}, {395, 245},{415, 245},{435, 245}, {455, 245},{475, 245},{495, 245}, {515, 245},{535, 245},{555, 245}, {575, 245},{595, 245},
                // buses
                // fila 13
                {15, 265}, {35, 265},{55, 265},{75, 265}, {95, 265},{115, 265},{135, 265}, {155, 265},{175, 265},{195, 265}, {215, 265},{235, 265},{255, 265}, {275, 265},{295, 265},
                {315, 265}, {335, 265},{355, 265},{375, 265}, {395, 265},{415, 265},{435, 265}, {455, 265},{475, 265},{495, 265}, {515, 265},{535, 265},{555, 265}, {575, 265},{595, 265},
                // fila 14
                {15, 280}, {35, 280},{55, 280},{75, 280}, {95, 280},{115, 280},{135, 280}, {155, 280},{175, 280},{195, 280}, {215, 280},{235, 280},{255, 280}, {275, 280},{295, 280},
                {315, 280}, {335, 280},{355, 280},{375, 280}, {395, 280},{415, 280},{435, 280}, {455, 280},{475, 280},{495, 280}, {515, 280},{535, 280},{555, 280}, {575, 280},{595, 280},

        };

        double[] punto_mas_cercano = null;
        // distancia para que tome el punto mas cercano al mover un cable
        double distancia_minima = 20;

        for (double[] point : puntosDisponibles) {
            double distance = Math.hypot(point[0] - x, point[1] - y); // usa la formula de la distancia raiz cuadrada de el x al cuadrado mas y al cuadrado

            if (distance < distancia_minima) {
                distancia_minima = distance;
                punto_mas_cercano = point;
            }
        }

        return punto_mas_cercano; // Devuelve null si no hay un punto cercano dentro de la aproximacion
    }


    public void resetearProtoboard(ActionEvent actionEvent) {
        for (int i = 0 ; i < 30 ; i++){
            for (int j = 0 ; j < 17 ; j ++){
                _Protoboard_Funcional.protoboard[i][j].conexion = false;
                _Protoboard_Funcional.protoboard[i][j]._posicion.corriente = false;
                _Protoboard_Funcional.protoboard[i][j]._posicion.polaridad = false;
                _Protoboard_Funcional.protoboard[i][j]._cable = new cable();
                _Protoboard_Funcional.protoboard[i][j]._led = new Led();
                _Protoboard_Funcional.protoboard[i][j]._resistencia = new Resistencia();
                _Protoboard_Funcional.protoboard[i][j]._switch = new Switch();
                _Protoboard_Funcional.protoboard[i][j]._octoSwitch = new OctoSwitch();
                _Protoboard_Funcional.protoboard[i][j]._chip = new Chip();
                _Protoboard_Funcional.protoboard[i][j]._display = new Display();
            }
        }
        arreglo_coordenadas_cables.clear();
        arreglo_coordenadas_resistencias.clear();
        arreglo_coordenadas_resistencias_patitas.clear();
        arreglo_coordenadas_leds.clear();
        arreglo_coordenadas_patitas_leds.clear();
        arreglo_coordenadas_switch.clear();
        arreglo_coordenadas_OctoSwitch.clear();
        arreglo_coordenadas_chip.clear();
        arreglo_coordenadas_display.clear();

        tablero.getGraphicsContext2D().clearRect(0,0,tablero.getWidth(),tablero.getHeight());
        dibujarTodo();
    }
    private Tooltip tooltip = new Tooltip();
    private PauseTransition hoverPause = new PauseTransition(Duration.seconds(1));

    private void mostrarVoltaje(MouseEvent event) {
        hoverPause.setOnFinished(e -> {
            if (event.getX() < 595 && event.getY() < 280) {
                double x = event.getX();
                double y = event.getY();
                double[] puntoCercano = alcanzarPuntoCercano(x, y);
                if (puntoCercano != null) {
                    x = puntoCercano[0];
                    y = puntoCercano[1];
                }
                int transformacion_x = (int) ((x - 15) / 20);
                int transformacion_y = transformacionY_coordA_Matriz(y);
                double voltaje = (_Protoboard_Funcional.protoboard[transformacion_x][transformacion_y]._posicion.voltaje);

                tooltip.setText("Voltaje: " + voltaje);
                tooltip.show(tablero, event.getScreenX(), event.getScreenY());

                PauseTransition hidePause = new PauseTransition(Duration.seconds(2));
                hidePause.setOnFinished(ev -> tooltip.hide());
                hidePause.play();
            }
        });

        hoverPause.playFromStart();
    }
}
