package com.example._0zo.model;


import java.util.ArrayList;
import java.util.Collections;



public class _0zoModel {



    private ArrayList<String> deck;

    private ArrayList<String> tableCards;

    private ArrayList<String> humanHand;

    private ArrayList<ArrayList<String>> machineHands;

    private int tableSum;

    private int machinePlayers;

    private boolean humanAlive;


    public _0zoModel(){

        deck = new ArrayList<>();

        tableCards = new ArrayList<>();

        humanHand = new ArrayList<>();

        machineHands = new ArrayList<>();

    }

    private void createDeck(){

        deck.clear();

        String[] cards = {

                "A",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "J",
                "Q",
                "K"

        };

        for(String card : cards){

            for(int i = 0; i < 4; i++){

                deck.add(card);

            }

        }

        Collections.shuffle(deck);

    }

    public void startGame(int machines){

        machinePlayers = machines;

        humanAlive = true;

        createDeck();

        tableCards.clear();

        humanHand.clear();

        machineHands.clear();

        tableSum = 0;

        // repartir humano

        for(int i = 0; i < 4; i++){

            humanHand.add(
                    drawCard()
            );

        }

        // repartir maquinas

        for(int i = 0; i < machines; i++){

            ArrayList<String> hand =
                    new ArrayList<>();

            for(int j = 0; j < 4; j++){

                hand.add(
                        drawCard()
                );

            }

            machineHands.add(hand);

        }

        // carta inicial mesa

        String first =
                drawCard();

        tableCards.add(first);

        tableSum =
                calculateCardValue(
                        first,
                        tableSum
                );

    }

    public String drawCard(){

        if(deck.isEmpty()){

            recycleDeck();

        }

        return deck.remove(0);

    }

    public void addCardToHumanHand(String card){

        humanHand.add(card);

    }

    // CALCULAR CARTAS NORMALES

    public int calculateCardValue(
            String card,
            int currentSum
    ){

        switch(card) {

            case "9":

                return 0;

            case "J":

            case "Q":

            case "K":

                return -10;

            case "A":

                return 1;

            default:

                return Integer.parseInt(card);

        }

    }

    // JUGAR CARTA HUMANA CON AS ELEGIDO

    public boolean playHumanCard(
            int position,
            int aceValue
    ) {

        if (position < 0 ||
                position >= humanHand.size()) {

            return false;

        }

        String card =
                humanHand.get(position);

        int value;

        if (card.equals("A")) {

            value = aceValue;

        } else {

            value =
                    calculateCardValue(
                            card,
                            tableSum
                    );

        }

        int newSum =
                tableSum + value;

        if(newSum > 50){

            return false;

        }

        humanHand.remove(position);

        tableCards.add(card);

        tableSum = newSum;

        return true;

    }

    private void recycleDeck(){

        if(tableCards.size() <= 1){

            return;

        }

        String last =
                tableCards.get(
                        tableCards.size()-1);

        tableCards.remove(
                tableCards.size()-1);

        deck.addAll(tableCards);

        tableCards.clear();

        tableCards.add(last);

        Collections.shuffle(deck);

    }

    public ArrayList<String> getHumanHand(){

        return humanHand;

    }

    public ArrayList<String> getTableCards(){

        return tableCards;

    }

    public ArrayList<ArrayList<String>> getMachineHands(){

        return machineHands;

    }

    public int getTableSum(){

        return tableSum;

    }

    public int getMachinePlayers(){

        return machinePlayers;

    }

    public boolean isHumanAlive(){

        return humanAlive;

    }

}