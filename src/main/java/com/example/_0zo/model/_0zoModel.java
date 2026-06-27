package com.example._0zo.model;


import java.util.ArrayList;
import java.util.List;



public class _0zoModel {


    private int tableSum;

    private int playerScore;

    private boolean gameActive;


    private List<Integer> playerHand;

    private List<String> players;



    public _0zoModel(){


        tableSum = 0;

        playerScore = 0;

        gameActive = true;


        playerHand = new ArrayList<>();

        players = new ArrayList<>();

    }



    // TABLE


    public void addCardToTable(int value){


        tableSum += value;


        if(tableSum > 50){

            gameActive = false;

        }


    }



    public int getTableSum(){

        return tableSum;

    }





    // SCORE


    public void addPlayerScore(int points){


        playerScore += points;

    }



    public int getPlayerScore(){

        return playerScore;

    }






    // HAND


    public void addCardToHand(int card){


        playerHand.add(card);

    }




    public List<Integer> getPlayerHand(){

        return playerHand;

    }






    // PLAYERS


    public void addPlayer(String name){


        players.add(name);

    }



    public List<String> getPlayers(){

        return players;

    }






    // GAME


    public boolean isGameActive(){

        return gameActive;

    }




    public void resetGame(){


        tableSum = 0;

        playerScore = 0;

        playerHand.clear();

        gameActive = true;


    }



}