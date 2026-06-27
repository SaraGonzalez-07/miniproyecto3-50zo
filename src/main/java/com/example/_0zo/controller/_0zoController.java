package com.example._0zo.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;



public class _0zoController implements Initializable {


    // ==========================
    // CONTADOR CENTRAL
    // ==========================

    @FXML
    private Label lblSumaMesa;


    // Puntaje del jugador
    @FXML
    private Label scoreValue;



    // ==========================
    // JUGADOR PRINCIPAL
    // ==========================


    @FXML
    private HBox manoJugador;


    @FXML
    private Label nombreJugadorPrincipal;




    // ==========================
    // JUGADORES RIVALES
    // ==========================


    @FXML
    private Label nombreJugadorIzquierda;


    @FXML
    private Label cartasIzquierda;


    @FXML
    private Label puntosIzquierda;



    @FXML
    private Label nombreJugadorDerecha;


    @FXML
    private Label cartasDerecha;


    @FXML
    private Label puntosDerecha;




    // ==========================
    // CARTAS / MESA
    // ==========================


    @FXML
    private StackPane mazoRobo;


    @FXML
    private StackPane pilaDescarte;


    @FXML
    private Label cartaActualMesa;



    // Cartas del jugador

    @FXML
    private StackPane carta1;


    @FXML
    private StackPane carta2;


    @FXML
    private StackPane carta3;



    // ==========================
    // BOTONES
    // ==========================


    @FXML
    private Button btnSalir;


    @FXML
    private Button btnReiniciar;




    // ==========================
    // VARIABLES DEL JUEGO
    // ==========================


    private int sumaMesa = 0;

    private int puntosJugador = 0;




    @Override
    public void initialize(URL url, ResourceBundle rb) {


        configurarEventos();


        updateUI();

    }





    // ==========================
    // ACTUALIZAR INTERFAZ
    // ==========================


    private void updateUI(){


        if(lblSumaMesa != null){

            lblSumaMesa.setText(
                    String.valueOf(sumaMesa)
            );

        }



        if(scoreValue != null){

            scoreValue.setText(
                    String.valueOf(puntosJugador)
            );

        }


    }





    // ==========================
    // EVENTOS
    // ==========================


    private void configurarEventos(){



        if(btnSalir != null){

            btnSalir.setOnAction(e -> {

                System.exit(0);

            });

        }




        if(btnReiniciar != null){


            btnReiniciar.setOnAction(e -> {


                sumaMesa = 0;

                puntosJugador = 0;


                updateUI();


            });


        }



    }






    // ==========================
    // MÉTODOS DEL JUEGO
    // ==========================


    public void agregarCarta(int valor){


        sumaMesa += valor;


        if(sumaMesa > 50){

            sumaMesa = 0;

        }


        updateUI();


    }



    public void ganarPuntos(int cantidad){


        puntosJugador += cantidad;


        updateUI();

    }




}
