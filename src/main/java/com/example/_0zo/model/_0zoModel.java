package com.example._0zo.model;


import java.util.ArrayList;
import java.util.List;



public class _0zoModel {


    // ==========================
    // DATOS DEL JUEGO
    // ==========================


    private int sumaMesa;


    private int puntajeJugador;


    private boolean juegoActivo;




    // ==========================
    // JUGADORES
    // ==========================


    private List<String> jugadores;



    // Cartas del jugador principal

    private List<Integer> manoJugador;




    // ==========================
    // CONSTRUCTOR
    // ==========================


    public _0zoModel(){


        sumaMesa = 0;


        puntajeJugador = 0;


        juegoActivo = true;



        jugadores = new ArrayList<>();


        manoJugador = new ArrayList<>();



    }






    // ==========================
    // SUMA DE LA MESA
    // ==========================



    public void agregarCartaMesa(int valor){


        sumaMesa += valor;



        // Regla del juego:
        // no debe pasar de 50


        if(sumaMesa > 50){


            juegoActivo = false;


        }


    }





    public int getSumaMesa(){


        return sumaMesa;

    }





    public void reiniciarMesa(){


        sumaMesa = 0;


        juegoActivo = true;


    }







    // ==========================
    // PUNTAJE
    // ==========================



    public void agregarPuntos(int puntos){


        puntajeJugador += puntos;


    }




    public int getPuntajeJugador(){


        return puntajeJugador;


    }





    public void reiniciarPuntaje(){


        puntajeJugador = 0;


    }






    // ==========================
    // CARTAS DEL JUGADOR
    // ==========================



    public void agregarCartaJugador(int carta){


        manoJugador.add(carta);


    }




    public void removerCartaJugador(int posicion){


        if(posicion >=0 && posicion < manoJugador.size()){


            manoJugador.remove(posicion);


        }


    }




    public List<Integer> getManoJugador(){


        return manoJugador;


    }






    // ==========================
    // JUGADORES
    // ==========================



    public void agregarJugador(String nombre){


        jugadores.add(nombre);


    }




    public List<String> getJugadores(){


        return jugadores;


    }





    // ==========================
    // ESTADO DEL JUEGO
    // ==========================



    public boolean isJuegoActivo(){


        return juegoActivo;


    }




    public void finalizarJuego(){


        juegoActivo = false;


    }





}