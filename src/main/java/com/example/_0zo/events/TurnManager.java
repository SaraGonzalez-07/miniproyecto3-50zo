package com.example._0zo.events;

import com.example._0zo.model._0zoModel;

/**
 * Manages the sequential turn distribution and encapsulates background thread orchestration
 * for automated machine players, maintaining operational decoupling from the primary controller.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
public class TurnManager {

    private int currentTurn;
    private int totalPlayers;
    private boolean hasPlayedCard;

    private _0zoModel model;
    private Runnable onTurnVisualUpdate;

    /**
     * Constructs a new turn manager instance utilizing injected model resources and callback pipelines.
     *
     * @param totalPlayers       The total count of participants in the game session.
     * @param model              The active game state model reference.
     * @param onTurnVisualUpdate The UI callback runnable to execute after state modifications.
     */
    public TurnManager(int totalPlayers, _0zoModel model, Runnable onTurnVisualUpdate) {
        this.totalPlayers = totalPlayers;
        this.currentTurn = 0;
        this.hasPlayedCard = false;
        this.model = model;
        this.onTurnVisualUpdate = onTurnVisualUpdate;
    }

    public boolean isHumanTurn() {
        return currentTurn == 0;
    }

    public void registerCardPlayed() {
        this.hasPlayedCard = true;
    }

    public boolean hasPlayedCard() {
        return hasPlayedCard;
    }

    /**
     * Shifts the active turn position index to the next structural entity.
     */
    public void nextTurn() {
        currentTurn = currentTurn + 1;
        if (currentTurn >= totalPlayers) {
            currentTurn = 0;
        }
        hasPlayedCard = false;
    }

    /**
     * Coordinates background task sequences for active automated machine participants.
     */
    public void orchestrateNextTurn() {
        if (isHumanTurn()) {
            return;
        }

        int machineIndex = currentTurn - 1;

        // Automatically bypass bots that have been eliminated from the session state
        if (!model.isMachineAlive(machineIndex)) {
            nextTurn();
            onTurnVisualUpdate.run();
            return;
        }

        // Initialize and execute background simulation thread structures
        MachinePlayerThread machineThread = new MachinePlayerThread(machineIndex, model, new Runnable() {
            @Override
            public void run() {
                nextTurn();
                onTurnVisualUpdate.run();
            }
        });

        machineThread.setDaemon(true);
        machineThread.start();
    }
}