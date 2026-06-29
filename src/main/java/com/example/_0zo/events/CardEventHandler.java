package com.example._0zo.events;

/**
 * Interface that defines the event contract for when a card is played.
 * Used to decouple card interaction logic from the controller.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
public interface CardEventHandler {

    /**
     * Called when a player plays a card from their hand.
     *
     * @param position the index of the card in the hand
     * @param aceValue the chosen value for an Ace card (1 or 10); ignored for other cards
     */
    void onCardPlayed(int position, int aceValue);

}