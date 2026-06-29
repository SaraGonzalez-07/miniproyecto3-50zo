package com.example._0zo;

import com.example._0zo.model._0zoModel;
import com.example._0zo.model.CardException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testing class for CardException validation.
 * Ensures custom checked exceptions trigger successfully under illegal game states.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
class CardExceptionTest {

    /**
     * Case: Verify that choosing an array index out of bounds (e.g., -1)
     * raises an immediate CardException as enforced by the model layout.
     */
    @Test
    void playHumanCardShouldThrowExceptionWhenIndexIsOutOfBounds() {
        _0zoModel model = new _0zoModel();
        model.startGame(2);

        assertThrows(CardException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                model.playHumanCard(-1, 1);
            }
        });
    }

    /**
     * Case: Validate that custom error message strings are explicitly
     * preserved inside the exception instance.
     */
    @Test
    void exceptionShouldPreserveCustomErrorMessageCorrectly() {
        String expectedMessage = "La carta seleccionada no coincide con tu mano.";
        CardException exception = new CardException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Case: Edge case exploring empty input strings passed to the exception wrapper.
     */
    @Test
    void exceptionShouldHandleEmptyMessageGracefully() {
        CardException emptyException = new CardException("");
        assertEquals("", emptyException.getMessage());
    }
}