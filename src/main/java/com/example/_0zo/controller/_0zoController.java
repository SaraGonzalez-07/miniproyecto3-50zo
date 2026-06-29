package com.example._0zo.controller;

import com.example._0zo.events.CardEventHandler;
import com.example._0zo.events.CardMouseAdapter;
import com.example._0zo.events.TurnManager;
import com.example._0zo.model._0zoModel;
import com.example._0zo.model.CardException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main controller class connecting the FXML visual interface layout elements
 * with the underlying game physics model layer and sequential tracking systems.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
public class _0zoController implements Initializable {

    @FXML private ComboBox<Integer> machineSelector;
    @FXML private Button startButton;
    @FXML private Button restartButton;
    @FXML private Button exitButton;

    @FXML private Label lblTableSum;
    @FXML private Label currentCard;
    @FXML private Label scoreValue;

    @FXML private HBox playerHand;
    @FXML private StackPane drawPile;

    @FXML private HBox leftMachineHand;
    @FXML private HBox rightMachineHand;
    @FXML private HBox topMachineHand;

    @FXML private Label leftPlayerName;
    @FXML private Label rightPlayerName;
    @FXML private Label topPlayerName;

    private _0zoModel gameModel;
    private TurnManager turnManager;
    private int lastKnownActivePlayers;

    /**
     * Executes upon layout root initialization to configure core structural values.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameModel = new _0zoModel();
        machineSelector.getItems().addAll(1, 2, 3);
        setupButtons();
    }

    /**
     * Registers actions using explicit Inner Anonymous Class implementations.
     */
    private void setupButtons() {
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer machines = machineSelector.getValue();
                if (machines == null) {
                    return;
                }

                gameModel.startGame(machines);

                turnManager = new TurnManager(machines + 1, gameModel, new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                showMachineCards(gameModel.getMachinePlayers());
                                updateUI();


                                int currentActive = gameModel.getActivePlayers();
                                if (currentActive < lastKnownActivePlayers) {
                                    int eliminated = lastKnownActivePlayers - currentActive;
                                    for (int e = 0; e < eliminated; e++) {
                                        turnManager.notifyPlayerEliminated();
                                    }
                                    lastKnownActivePlayers = currentActive;
                                }

                                checkGameOver();

                                if (gameModel.getActivePlayers() > 1 && !turnManager.isHumanTurn()) {
                                    turnManager.orchestrateNextTurn();
                                } else if (turnManager.isHumanTurn()) {

                                    if (!gameModel.humanCanPlay()) {
                                        gameModel.eliminateHuman();
                                        turnManager.notifyPlayerEliminated();
                                        showInfo("¡Has sido eliminado! Las cartas de tu mano superan los 50 puntos con la suma actual.");
                                        checkGameOver();
                                    } else {
                                        enableHumanControls(true);
                                    }
                                }
                            }
                        });
                    }
                });

                lastKnownActivePlayers = machines + 1;
                showDeck();
                showHumanCards();
                showMachineCards(machines);
                updateUI();
                enableHumanControls(true);
            }
        });

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameModel = new _0zoModel();
                turnManager = null;

                playerHand.getChildren().clear();
                leftMachineHand.getChildren().clear();
                rightMachineHand.getChildren().clear();
                topMachineHand.getChildren().clear();

                leftMachineHand.setOpacity(1.0);
                rightMachineHand.setOpacity(1.0);
                topMachineHand.setOpacity(1.0);

                leftPlayerName.setText("Machine 1");
                rightPlayerName.setText("Machine 2");
                topPlayerName.setText("Machine 3");

                updateUI();
                enableHumanControls(false);
                startButton.setDisable(false);
                machineSelector.setDisable(false);
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }

    /**
     * Concrete inner implementation handler to manage real-time card submissions.
     */
    private class HumanCardEventHandler implements CardEventHandler {
        @Override
        public void onCardPlayed(int position, int aceValue) {
            if (turnManager == null || !turnManager.isHumanTurn()) {
                return;
            }

            if (turnManager.hasPlayedCard()) {
                showWarning("Ya jugaste una carta en este turno. ¡Debes robar del mazo!");
                return;
            }

            try {
                gameModel.playHumanCard(position, aceValue);
                turnManager.registerCardPlayed();
                showHumanCards();
                updateUI();
                highlightDeck(true);

            } catch (CardException ex) {
                showWarning(ex.getMessage());
            }
        }
    }

    /**
     * Refreshes the local visual layout components bound to the human player hand array.
     */
    private void showHumanCards() {
        playerHand.getChildren().clear();
        HumanCardEventHandler cardHandler = new HumanCardEventHandler();
        int index = 0;

        for (String cardValue : gameModel.getHumanHand()) {
            Button cardButton = new Button(cardValue);
            cardButton.getStyleClass().add("card");

            CardMouseAdapter adapter = new CardMouseAdapter(cardValue, index, cardHandler);
            cardButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, adapter);

            playerHand.getChildren().add(cardButton);
            index = index + 1;
        }
    }

    /**
     * Configures structural behaviors for interaction points on the deck object.
     */
    private void showDeck() {
        drawPile.getChildren().clear();
        Button deckButton = new Button("?");
        deckButton.getStyleClass().add("back-card");

        deckButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onHumanDrawCard();
            }
        });

        drawPile.getChildren().add(deckButton);
    }

    /**
     * Renders explicit visual border indicators surrounding the drawing pile structure.
     */
    private void highlightDeck(boolean active) {
        if (drawPile.getChildren().isEmpty()) {
            return;
        }
        Button deckButton = (Button) drawPile.getChildren().get(0);
        if (active) {
            deckButton.setStyle("-fx-border-color: #ffd700; -fx-border-width: 3;");
        } else {
            deckButton.setStyle("");
        }
    }

    /**
     * Triggers human resource request sequences against the core deck structures.
     */
    private void onHumanDrawCard() {
        if (turnManager == null || !turnManager.isHumanTurn()) {
            return;
        }

        if (!turnManager.hasPlayedCard()) {
            showWarning("Primero debes lanzar una carta antes de poder robar del mazo.");
            return;
        }

        String drawn = gameModel.drawCard();
        gameModel.addCardToHumanHand(drawn);

        showHumanCards();
        highlightDeck(false);
        updateUI();

        turnManager.nextTurn();

        checkGameOver();

        if (gameModel.getActivePlayers() > 1 && !turnManager.isHumanTurn()) {
            enableHumanControls(false);
            turnManager.orchestrateNextTurn();
        }
    }

    /**
     * Orchestrates rendering updates across all active machine layout blocks.
     */
    private void showMachineCards(int machines) {
        leftMachineHand.getChildren().clear();
        rightMachineHand.getChildren().clear();
        topMachineHand.getChildren().clear();

        if (machines >= 1) {
            rebuildMachineHand(leftMachineHand, leftPlayerName, 0, "Machine 1");
        }
        if (machines >= 2) {
            rebuildMachineHand(rightMachineHand, rightPlayerName, 1, "Machine 2");
        }
        if (machines >= 3) {
            rebuildMachineHand(topMachineHand, topPlayerName, 2, "Machine 3");
        }
    }

    /**
     * Alters properties of existing layout items without disrupting FXML assignments.
     */
    private void rebuildMachineHand(HBox hand, Label nameLabel, int machineIndex, String defaultName) {
        hand.getChildren().clear();

        if (!gameModel.isMachineAlive(machineIndex)) {
            nameLabel.setText(defaultName + " (ELIMINADO)");
            hand.setOpacity(0.4);
            return;
        }

        nameLabel.setText(defaultName);
        hand.setOpacity(1.0);
        int cardCount = gameModel.getMachineHands().get(machineIndex).size();

        for (int i = 0; i < cardCount; i = i + 1) {
            Button card = new Button("?");
            card.getStyleClass().add("back-card");
            hand.getChildren().add(card);
        }
    }

    /**
     * Sets access constraints across individual interactive interactive nodes.
     */
    private void enableHumanControls(boolean enabled) {
        playerHand.setDisable(!enabled);
        drawPile.setDisable(!enabled);
    }


    /**
     * Evaluates core game session metrics to conclude loops if constraints are met.
     * Declares the winner and freezes the interface when only one player remains.
     */
    private void checkGameOver() {
        if (gameModel.getActivePlayers() <= 1) {
            String winner = gameModel.getWinnerName();

            // Deshabilitar todos los controles
            enableHumanControls(false);
            startButton.setDisable(true);
            machineSelector.setDisable(true);

            // Mostrar estado final en el label
            lblTableSum.setText(gameModel.getTableSum() + " (FIN)");

            // Mostrar alerta de ganador
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin del juego");
            alert.setHeaderText("¡Juego terminado!");
            alert.setContentText("🏆 Ganador: " + winner + "\n\nPresiona Reiniciar para jugar de nuevo.");
            alert.showAndWait();
        }
    }

    /**
     * Pulls scalar values straight from the model to rewrite active text containers.
     */
    private void updateUI() {
        lblTableSum.setText(String.valueOf(gameModel.getTableSum()));
        if (!gameModel.getTableCards().isEmpty()) {
            String last = gameModel.getTableCards().get(gameModel.getTableCards().size() - 1);
            currentCard.setText(last);
        } else {
            currentCard.setText("-");
        }
        scoreValue.setText("Mazo: " + gameModel.getDeckSize());
    }

    /**
     * Presents basic alert windows categorized for validation warning contexts.
     */
    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.setHeaderText(null);
        alert.show();
    }

    /**
     * Presents basic alert windows categorized for general informational updates.
     */
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setHeaderText(null);
        alert.show();
    }
}