package com.example._0zo.events;

import com.example._0zo.model._0zoModel;
import javafx.application.Platform;
import java.util.Random;

/**
 * Thread implementation responsible for managing an automated machine player's turn sequence.
 * Handles artificial delays for card placement and drawing actions to simulate realistic pacing.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
public class MachinePlayerThread extends Thread {

    private int machineIndex;
    private _0zoModel model;
    private Runnable onTurnFinished;
    private Random random;

    /**
     * Constructs a new machine execution thread thread with game context.
     *
     * @param machineIndex   The identifier index of the target bot.
     * @param model          The active game state model reference.
     * @param onTurnFinished Callback action to trigger upon sequence completion.
     */
    public MachinePlayerThread(int machineIndex, _0zoModel model, Runnable onTurnFinished) {
        this.machineIndex = machineIndex;
        this.model = model;
        this.onTurnFinished = onTurnFinished;
        this.random = new Random();
    }

    /**
     * Executes the timed procedural turn logic for the machine player.
     */
    @Override
    public void run() {
        try {
            // Wait 2-4 seconds before playing
            int playDelay = 2000 + random.nextInt(2001);
            Thread.sleep(playDelay);

            // Process automated card analysis and selection inside the model layer
            model.playMachineCard(machineIndex);

            // Wait 1-2 seconds before drawing
            int drawDelay = 1000 + random.nextInt(1001);
            Thread.sleep(drawDelay);

            // Replenish machine hand structure from the current active deck
            model.drawCardForMachine(machineIndex);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        // JavaFX UI thread synchronization wrapper
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                onTurnFinished.run();
            }
        });
    }
}