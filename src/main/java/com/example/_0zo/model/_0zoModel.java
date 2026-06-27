package com.example._0zo.model;

/**
 * Modelo de la aplicación _0zo.
 * Se encarga exclusivamente de gestionar los datos, las reglas del juego
 * y la lógica de negocio, manteniéndose independiente de la interfaz gráfica.
 */
public class _0zoModel {
    private int currentScore;
    private int currentBet;
    private String gameState;
    private boolean isPlayerTurn;

    /**
     * Constructor del modelo. Inicializa el estado base de una nueva partida.
     */
    public _0zoModel() {
        resetGame();
    }

    /**
     * Restablece los valores del juego a su estado inicial predeterminado.
     */
    public void resetGame() {
        this.currentScore = 0;
        this.currentBet = 100; // Apuesta inicial base
        this.gameState = "Tu Turno";
        this.isPlayerTurn = true;
    }

    /**
     * Incrementa la apuesta actual del jugador si el monto es válido.
     * * @param amount Cantidad de puntos/fichas a añadir a la apuesta.
     */
    public void placeBet(int amount) {
        if (amount > 0) {
            this.currentBet += amount;
            this.gameState = "¡Apuesta aumentada! Riesgo alto.";
        } else {
            this.gameState = "Monto de apuesta inválido.";
        }
    }

    /**
     * Cambia el estado del juego para reflejar que el jugador se ha retirado.
     */
    public void foldHand() {
        this.isPlayerTurn = false;
        this.gameState = "Te has retirado de esta ronda.";
    }

    // ==========================================
    //            GETTERS AND SETTERS
    // ==========================================

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.isPlayerTurn = playerTurn;
    }
}
