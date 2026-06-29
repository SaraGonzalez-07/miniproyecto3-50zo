package com.example._0zo.model;

/**
 * Custom exception thrown when a card play action violates active game rules.
 * Extends the standard Exception class to enforce explicit try-catch error handling.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
public class CardException extends Exception {

    /**
     * Constructs a new CardException containing the specific error details.
     *
     * @param message Descriptive error notification string.
     */
    public CardException(String message) {
        super(message);
    }
}