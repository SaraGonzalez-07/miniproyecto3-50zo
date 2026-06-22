package com.example._0zo.controller;

import com.game.ozo.model._0zoModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class _0zoController {

    // Vincular componentes del FXML usando las clases del CSS
    @FXML
    private Label scoreValue; // El Label que muestra "0000"

    @FXML
    private Label statusValue; // El Label que muestra el estado ("Tu Turno")

    @FXML
    private Label boardPlaceholder; // El área del juego central

    private _0zoModel gameModel;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        // Initialize the game model
        gameModel = new _0zoModel();

        // Load initial data into the UI
        updateUI();
    }

    /**
     * Handles the action when the user clicks the "¡APRESTAR!" / "Bet" button.
     */
    @FXML
    private void handleBetAction() {
        // Business logic simulation
        gameModel.placeBet(500);
        gameModel.setCurrentScore(gameModel.getCurrentScore() + 150);

        // Update user interface
        updateUI();
        boardPlaceholder.setText("[ ¡LA APUESTA SE PONE INTERESANTE! ]");
    }

    /**
     * Handles the action when the user clicks the "Retirarse" / "Fold" button.
     */
    @FXML
    private void handleFoldAction() {
        gameModel.foldHand();
        updateUI();
        boardPlaceholder.setText("[ HAS PERDIDO ESTA MANO ]");
    }

    /**
     * Syncs the model data with the view labels in Spanish.
     */
    private void updateUI() {
        scoreValue.setText(String.format("%04d", gameModel.getCurrentScore()));
        statusValue.setText(gameModel.getGameState());
    }
}