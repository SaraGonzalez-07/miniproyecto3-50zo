package com.example._0zo;

import com.example._0zo.model._0zoModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testing class for _0zoModel.
 * Verifies the correctness of card value parsing, initial hand distribution,
 * and game state transition bounds.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
class _0zoModelTest {

    /**
     * Case: Verify that special and normal cards return their exact mathematical modifiers.
     */
    @Test
    void cardValuesShouldFollowSpecificGameRules() {
        _0zoModel model = new _0zoModel();

        assertEquals(0, model.getCardValue("9", 0));
        assertEquals(-10, model.getCardValue("J", 0));
        assertEquals(-10, model.getCardValue("Q", 15));
        assertEquals(-10, model.getCardValue("K", 40));

        assertEquals(1, model.getCardValue("A", 0));
        assertEquals(5, model.getCardValue("5", 0));
    }

    /**
     * Case: Ensure game initialization sets exactly 4 cards for all active players.
     */
    @Test
    void gameInitializationShouldDistributeCorrectHandSizes() {
        _0zoModel model = new _0zoModel();
        int requestedMachines = 3;
        model.startGame(requestedMachines);

        assertEquals(4, model.getHumanHand().size());

        assertEquals(requestedMachines, model.getMachineHands().size());
        for (int i = 0; i < requestedMachines; i++) {
            assertEquals(4, model.getMachineHands().get(i).size());
        }
    }

    /**
     * Case: Validate dynamic tracking of active player structures upon successive eliminations.
     */
    @Test
    void activePlayerCountShouldDecreaseWhenPlayersAreEliminated() {
        _0zoModel model = new _0zoModel();
        model.startGame(2);
        assertEquals(3, model.getActivePlayers());

        model.eliminateMachine(0);
        assertEquals(2, model.getActivePlayers());
        assertFalse(model.isMachineAlive(0));

        model.eliminateHuman();
        assertEquals(1, model.getActivePlayers());
        assertFalse(model.isHumanAlive());
    }
}