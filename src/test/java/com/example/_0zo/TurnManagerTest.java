package com.example._0zo;

import com.example._0zo.model._0zoModel;
import com.example._0zo.events.TurnManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testing class for TurnManager.
 * Validates turn state transitions, initial focus parameters, and turn loop indexing.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
class TurnManagerTest {

    /**
     * Case: Assert that according to game rules, the initial focus of the game
     * always falls onto the human user (turn 0).
     */
    @Test
    void initialTurnShouldBelongToHumanCorrectly() {
        _0zoModel model = new _0zoModel();
        TurnManager manager = new TurnManager(4, model, new Runnable() {
            @Override
            public void run() {}
        });

        assertTrue(manager.isHumanTurn());
    }

    /**
     * Case: Verify that triggering nextTurn shifts the state focus away from
     * the human player over to the machine bot sequence.
     */
    @Test
    void nextTurnShouldChangeTurnStateFromHumanToMachine() {
        _0zoModel model = new _0zoModel();
        TurnManager manager = new TurnManager(2, model, new Runnable() {
            @Override
            public void run() {}
        });

        manager.nextTurn();
        assertFalse(manager.isHumanTurn());
    }

    /**
     * Case: Mathematical boundary check for structural turn cycling back to 0
     * (the human) once all participating entities have completed their actions.
     */
    @Test
    void turnManagerShouldHandleMinimumPlayerBoundariesAndCycle() {
        _0zoModel model = new _0zoModel();
        TurnManager manager = new TurnManager(2, model, new Runnable() {
            @Override
            public void run() {}
        });

        assertTrue(manager.isHumanTurn()); // Turn 0: Human
        manager.nextTurn();
        assertFalse(manager.isHumanTurn()); // Turn 1: Machine 1

        manager.nextTurn();
        assertTrue(manager.isHumanTurn()); // Cycle back to Turn 0: Human
    }
}