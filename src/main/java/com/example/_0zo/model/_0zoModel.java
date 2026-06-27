package com.example._0zo.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class _0zoModel {


    private ArrayList<Integer> deck;


    private ArrayList<Integer> humanHand;


    private ArrayList<ArrayList<Integer>> machineHands;


    private int tableSum;


    private int currentCard;


    private int playerScore;


    private int machinePlayers;


    private boolean gameStarted;


    public _0zoModel(){


        deck = new ArrayList<>();


        humanHand = new ArrayList<>();


        machineHands = new ArrayList<>();


        createDeck();


    }


    private void createDeck(){


        deck.clear();


        for(int number = 1; number <= 10; number++){


            for(int copy = 0; copy < 4; copy++){


                deck.add(number);


            }

        }


        Collections.shuffle(deck);

    }


    public void startGame(int machines){


        gameStarted = true;


        machinePlayers = machines;


        tableSum = 0;


        playerScore = 0;


        humanHand.clear();


        machineHands.clear();


        createDeck();


        // Human initial hand
        for(int i = 0; i < 5; i++){


            humanHand.add(
                    drawCard()
            );

        }

        // Machines initial hand

        for(int i = 0; i < machines; i++){

            ArrayList<Integer> hand =
                    new ArrayList<>();

            for(int j = 0; j < 5; j++){

                hand.add(
                        drawCard()
                );

            }

            machineHands.add(hand);

        }

        // First card on table

        currentCard =
                drawCard();

        tableSum =
                currentCard;

    }

    public int drawCard(){

        if(deck.isEmpty()){

            createDeck();

        }

        return deck.remove(0);

    }

    public void addCardToHumanHand(){

        int card =
                drawCard();

        humanHand.add(card);

        tableSum += card;

    }

    public void playCard(int index){

        if(index >=0 &&
                index < humanHand.size()){

            int card =
                    humanHand.remove(index);

            currentCard =
                    card;

            tableSum += card;

        }

    }

    public void resetGame(){

        deck.clear();

        humanHand.clear();

        machineHands.clear();

        tableSum = 0;

        currentCard = 0;

        playerScore = 0;

        machinePlayers = 0;

        gameStarted = false;

        createDeck();

    }


    public ArrayList<Integer> getHumanHand(){

        return humanHand;

    }


    public ArrayList<ArrayList<Integer>> getMachineHands(){

        return machineHands;

    }

    public int getTableSum(){

        return tableSum;

    }


    public int getCurrentCard(){

        return currentCard;

    }


    public int getPlayerScore(){

        return playerScore;

    }

    public int getMachinePlayers(){

        return machinePlayers;

    }


    public boolean isGameStarted(){

        return gameStarted;

    }


    public int getDeckSize(){

        return deck.size();


    }

}