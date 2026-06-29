package com.example._0zo.events;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

/**
 * Adapter class that connects JavaFX mouse click events on a card
 * to the CardEventHandler interface.
 *
 * This class bridges the gap between JavaFX's event system and our
 * own game event logic, following the adapter pattern.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
public class CardMouseAdapter implements EventHandler<MouseEvent> {

    /** The card value shown on this button (e.g. "A", "K", "7") */
    private String cardValue;

    /** The position of this card in the player's hand */
    private int position;

    /** The handler that will process the card play action */
    private CardEventHandler handler;

    /**
     * Creates a new adapter for a specific card.
     *
     * @param cardValue the face value of the card
     * @param position  the index in the hand
     * @param handler   the event handler to notify on click
     */
    public CardMouseAdapter(String cardValue, int position, CardEventHandler handler) {
        this.cardValue = cardValue;
        this.position = position;
        this.handler = handler;
    }

    /**
     * Handles the mouse click. If the card is an Ace, shows a dialog
     * to let the player choose between 1 and 10. Then notifies the handler.
     *
     * @param event the mouse event triggered by the click
     */
    @Override
    public void handle(MouseEvent event) {
        int aceValue = 1;

        // If Ace, ask the player which value to use
        if (cardValue.equals("A")) {
            aceValue = askAceValue();
        }

        handler.onCardPlayed(position, aceValue);
    }

    /**
     * Shows a dialog so the player can choose the Ace value.
     *
     * @return 1 or 10 depending on the player's choice
     */
    private int askAceValue() {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);

        dialog.setTitle("Valor del As");
        dialog.setHeaderText("Selecciona el valor que deseas asignar al As:");

        ButtonType one = new ButtonType("As = 1");
        ButtonType ten = new ButtonType("As = 10");

        dialog.getButtonTypes().setAll(one, ten);

        ButtonType result = dialog.showAndWait().orElse(one);

        if (result == ten) {
            return 10;
        } else {
            return 1;
        }
    }
}