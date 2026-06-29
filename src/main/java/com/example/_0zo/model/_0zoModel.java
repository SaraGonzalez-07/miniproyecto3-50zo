package com.example._0zo.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Model class for the Cincuentazo card game.
 * Manages the deck logic, player hands, game board metrics, and structural game state conditions.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
public class _0zoModel {

    private ArrayList<String> deck;
    private ArrayList<String> tableCards;
    private ArrayList<String> humanHand;
    private ArrayList<ArrayList<String>> machineHands;
    private ArrayList<Boolean> machineAlive;

    private int tableSum;
    private int machinePlayers;
    private boolean humanAlive;

    /**
     * Initializes base collection structures for the game.
     */
    public _0zoModel() {
        deck = new ArrayList<>();
        tableCards = new ArrayList<>();
        humanHand = new ArrayList<>();
        machineHands = new ArrayList<>();
        machineAlive = new ArrayList<>();
    }

    /**
     * Generates a standard set of cards and shuffles them randomly.
     */
    private void createDeck() {
        deck.clear();
        String[] values = {
                "A", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "J", "Q", "K"
        };
        for (String value : values) {
            for (int i = 0; i < 4; i++) {
                deck.add(value);
            }
        }
        Collections.shuffle(deck);
    }

    /**
     * Starts the global game state based on the selected amount of bots.
     *
     * @param machines Number of artificial players participating.
     */
    public void startGame(int machines) {
        machinePlayers = machines;
        humanAlive = true;

        createDeck();
        tableCards.clear();
        humanHand.clear();
        machineHands.clear();
        machineAlive.clear();
        tableSum = 0;

        // Deal 4 cards to the human player
        for (int i = 0; i < 4; i++) {
            humanHand.add(drawCard());
        }

        // Deal 4 cards to each machine player
        for (int i = 0; i < machines; i++) {
            ArrayList<String> hand = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                hand.add(drawCard());
            }
            machineHands.add(hand);
            machineAlive.add(true);
        }

        // Place the initial card on the table to define starting sum
        String firstCard = drawCard();
        tableCards.add(firstCard);
        tableSum = getCardValue(firstCard, tableSum);
    }

    /**
     * Removes and returns the top card from the deck. Recycles table cards if empty.
     *
     * @return String representing the card code.
     */
    public String drawCard() {
        if (deck.isEmpty()) {
            recycleDeck();
        }
        return deck.remove(0);
    }

    /**
     * Appends a drawn card into the human's hand array.
     *
     * @param card Value of the card.
     */
    public void addCardToHumanHand(String card) {
        humanHand.add(card);
    }

    /**
     * Appends a drawn card into a specific machine hand.
     *
     * @param machineIndex Target machine index.
     */
    public void drawCardForMachine(int machineIndex) {
        if (machineIndex < 0 || machineIndex >= machineHands.size() || !isMachineAlive(machineIndex)) {
            return;
        }
        String card = drawCard();
        machineHands.get(machineIndex).add(card);
    }

    /**
     * Computes numeric values for cards according to the game metrics criteria.
     *
     * @param card Target card code.
     * @param currentSum Contextual reference of the table state.
     * @return Integer modifier.
     */
    public int getCardValue(String card, int currentSum) {
        switch (card) {
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

    /**
     * Evaluates a card submission from the human hand.
     *
     * @param position Index position of the card within the user array.
     * @param aceValue Custom numeric override selected for the Ace card.
     * @throws CardException If selection is invalid or the mathematical result exceeds 50.
     */
    public void playHumanCard(int position, int aceValue) throws CardException {
        if (position < 0 || position >= humanHand.size()) {
            throw new CardException("La carta seleccionada no coincide con tu mano.");
        }

        String card = humanHand.get(position);

        int delta;
        if (card.equals("A")) {
            delta = aceValue;
        } else {
            delta = getCardValue(card, tableSum);
        }

        int newSum = tableSum + delta;

        if (newSum > 50) {
            throw new CardException("Jugada rechazada: La suma actual es " + tableSum +
                    ", el valor de la carta es " + delta + " y superaría los 50 puntos.");
        }

        humanHand.remove(position);
        tableCards.add(card);
        tableSum = newSum;
    }

    /**
     * Executes automated choices for artificial machine turns.
     *
     * @param machineIndex Index of the target machine bot.
     * @return true if card placement was successful; false if player gets eliminated.
     */
    public boolean playMachineCard(int machineIndex) {
        if (machineIndex < 0 || machineIndex >= machineHands.size() || !isMachineAlive(machineIndex)) {
            return false;
        }

        ArrayList<String> hand = machineHands.get(machineIndex);
        int playIndex = -1;
        int chosenDelta = 0;

        for (int i = 0; i < hand.size(); i++) {
            String card = hand.get(i);
            int delta;

            if (card.equals("A")) {

                if (tableSum + 10 <= 50) {
                    delta = 10;
                } else if (tableSum + 1 <= 50) {
                    delta = 1;
                } else {
                    continue;
                }
            } else {
                delta = getCardValue(card, tableSum);
            }

            if (tableSum + delta <= 50) {
                playIndex = i;
                chosenDelta = delta;
                break;
            }
        }

        if (playIndex == -1) {
            eliminateMachine(machineIndex);
            return false;
        } else {
            String played = hand.remove(playIndex);
            tableCards.add(played);
            tableSum = tableSum + chosenDelta;
            return true;
        }
    }

    /**
     * Shifts machine status to inactive and returns held items to the deck pool.
     *
     * @param machineIndex Target machine index.
     */
    public void eliminateMachine(int machineIndex) {
        if (machineIndex < 0 || machineIndex >= machineAlive.size()) {
            return;
        }
        machineAlive.set(machineIndex, false);
        deck.addAll(machineHands.get(machineIndex));
        machineHands.get(machineIndex).clear();
    }

    /**
     * Shifts human status to inactive and flushes remaining hand contents.
     */
    public void eliminateHuman() {
        humanAlive = false;
        deck.addAll(humanHand);
        humanHand.clear();
    }

    /**
     * Verifies if the human hand contains at least one valid card permutation.
     *
     * @return true if a valid play exists; false otherwise.
     */
    public boolean humanCanPlay() {
        for (String card : humanHand) {
            int delta;
            if (card.equals("A")) {
                delta = 1;
            } else {
                delta = getCardValue(card, tableSum);
            }

            if (tableSum + delta <= 50) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts dynamic state elements currently set as active.
     *
     * @return Number of active entities.
     */
    public int getActivePlayers() {
        int count = 0;
        if (humanAlive) {
            count = count + 1;
        }
        for (Boolean alive : machineAlive) {
            if (alive) {
                count = count + 1;
            }
        }
        return count;
    }

    /**
     * Returns string representation metadata of the winner.
     *
     * @return Winner string descriptor.
     */
    public String getWinnerName() {
        if (humanAlive) {
            return "Jugador humano";
        }
        for (int i = 0; i < machineAlive.size(); i++) {
            if (machineAlive.get(i)) {
                return "Máquina " + (i + 1);
            }
        }
        return "Nadie";
    }

    /**
     * Reconstitutes the deck collection from used table card elements.
     */
    private void recycleDeck() {
        if (tableCards.size() <= 1) {
            return;
        }
        String lastCard = tableCards.get(tableCards.size() - 1);
        tableCards.remove(tableCards.size() - 1);

        deck.addAll(tableCards);
        tableCards.clear();
        tableCards.add(lastCard);
        Collections.shuffle(deck);
    }

    // Getters and structural validation methods
    public ArrayList<String> getHumanHand() { return humanHand; }
    public ArrayList<String> getTableCards() { return tableCards; }
    public ArrayList<ArrayList<String>> getMachineHands() { return machineHands; }
    public int getTableSum() { return tableSum; }
    public int getMachinePlayers() { return machinePlayers; }
    public boolean isHumanAlive() { return humanAlive; }
    public int getDeckSize() { return deck.size(); }

    public boolean isMachineAlive(int machineIndex) {
        if (machineIndex < 0 || machineIndex >= machineAlive.size()) {
            return false;
        }
        return machineAlive.get(machineIndex);
    }
}