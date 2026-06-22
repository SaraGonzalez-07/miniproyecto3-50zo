package com.example._0zo.model;

public class _0zoModel {
    private int currentScore;
    private int currentBet;
    private String gameState;
    private boolean isPlayerTurn;

    public _0zoModel() {
        resetGame();
    }

    public void resetGame() {
        this.currentScore = 0;
        this.currentBet = 100; // Apuesta inicial predeterminada
        this.gameState = "Tu Turno";
        this.isPlayerTurn = true;
    }

    public void placeBet(int amount) {
        if (amount > 0) {
            this.currentBet += amount;
            this.gameState = "¡Apuesta aumentada! Riesgo alto.";
        }
    }

    public void foldHand() {
        this.isPlayerTurn = false;
        this.gameState = "Te has retirado de esta ronda.";
    }

    // Getters and Setters
    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getCurrentBet() {
        return currentBet;
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
}