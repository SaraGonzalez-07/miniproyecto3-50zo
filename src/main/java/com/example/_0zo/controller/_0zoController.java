package com.example._0zo.controller;

import com.example._0zo.model._0zoModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador de la interfaz gráfica. Se encarga de capturar los eventos del usuario,
 * actualizar el modelo del juego y reflejar los cambios en las etiquetas (Labels).
 */
public class _0zoController {

    // Se vinculan directamente con el archivo FXML usando el atributo fx:id
    @FXML
    private Label scoreValue; // Muestra el puntaje (ej. "0000")

    @FXML
    private Label statusValue; // Muestra el estado actual (ej. "Tu Turno")

    @FXML
    private Label boardPlaceholder; // Área central de texto del juego

    private _0zoModel gameModel;

    /**
     * Inicializa el controlador. JavaFX llama automáticamente a este método
     * inmediatamente después de cargar el archivo FXML.
     */
    @FXML
    public void initialize() {
        // Inicializamos el modelo del juego para manejar los datos
        gameModel = new _0zoModel();

        // Cargamos el estado inicial en la interfaz de usuario
        updateUI();
    }

    /**
     * Se ejecuta cuando el usuario hace clic en el botón de Apostar ("¡APRESTAR!").
     */
    @FXML
    private void handleBetAction() {
        // Modificamos los datos dentro del modelo (Lógica de negocio)
        gameModel.placeBet(500);
        gameModel.setCurrentScore(gameModel.getCurrentScore() + 150);

        // Sincronizamos la UI con los nuevos cambios del modelo
        updateUI();
        boardPlaceholder.setText("[ ¡LA APUESTA SE PONE INTERESANTE! ]");
    }

    /**
     * Se ejecuta cuando el usuario hace clic en el botón de Retirarse ("Fold").
     */
    @FXML
    private void handleFoldAction() {
        // Modificamos el estado en el modelo
        gameModel.foldHand();

        // Sincronizamos la UI
        updateUI();
        boardPlaceholder.setText("[ HAS PERDIDO ESTA MANO ]");
    }

    /**
     * Método interno para sincronizar los textos de la interfaz gráfica
     * con los datos actuales almacenados en el modelo.
     */
    private void updateUI() {
        // Formatea el puntaje para mostrarlo con 4 dígitos (ej. 0150)
        scoreValue.setText(String.format("%04d", gameModel.getCurrentScore()));
        statusValue.setText(gameModel.getGameState());
    }
}