package com.example._0zo.controller;


import com.example._0zo.model._0zoModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ResourceBundle;


public class _0zoController implements Initializable {

    private _0zoModel gameModel;

    @FXML
    private ComboBox<Integer> machineSelector;

    @FXML
    private Button startButton;

    @FXML
    private Button restartButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label lblTableSum;

    @FXML
    private Label currentCard;

    @FXML
    private Label scoreValue;

    @FXML
    private HBox playerHand;

    @FXML
    private StackPane drawPile;

    @FXML
    private Label mainPlayerName;

    @FXML
    private Label leftPlayerName;

    @FXML
    private Label rightPlayerName;

    @FXML
    private Label topPlayerName;

    @FXML
    private HBox leftMachineHand;

    @FXML
    private HBox rightMachineHand;

    @FXML
    private HBox topMachineHand;


    @Override
    public void initialize(URL url, ResourceBundle rb){

        gameModel =
                new _0zoModel();

        machineSelector
                .getItems()
                .addAll(1,2,3);

        setupButtons();

    }

    private void setupButtons(){

        startButton.setOnAction(e->{

            Integer machines =
                    machineSelector.getValue();

            if(machines == null){

                return;

            }

            gameModel.startGame(machines);

            showHumanCards();

            showMachineCards(machines);

            showDeck();

            updateUI();

        });

        restartButton.setOnAction(e->{

            gameModel =
                    new _0zoModel();

            playerHand
                    .getChildren()
                    .clear();

            leftMachineHand
                    .getChildren()
                    .clear();

            rightMachineHand
                    .getChildren()
                    .clear();

            topMachineHand
                    .getChildren()
                    .clear();

            updateUI();

        });

        exitButton.setOnAction(e->{

            System.exit(0);

        });

    }

    // CARTAS HUMANO BOCA ARRIBA

    private void showHumanCards(){

        playerHand
                .getChildren()
                .clear();

        int index = 0;

        for(String cardValue :
                gameModel.getHumanHand()){

            Button card =
                    new Button(cardValue);

            card.getStyleClass()
                    .add("card");

            int position =
                    index;

            card.setOnAction(e->{

                boolean played =
                        gameModel.playHumanCard(position);

                if(played){

                    showHumanCards();

                    updateUI();

                }
                else{

                    Alert alert =
                            new Alert(
                                    Alert.AlertType.WARNING,
                                    "You cannot play this card. The sum would exceed 50"
                            );

                    alert.show();

                }

            });

            playerHand
                    .getChildren()
                    .add(card);

            index++;

        }

    }

    // CARTAS MAQUINA BOCA ABAJO

    private void showMachineCards(int machines){

        leftMachineHand.getChildren()
                .clear();

        rightMachineHand.getChildren()
                .clear();

        topMachineHand.getChildren()
                .clear();

        if(machines >= 1){

            leftPlayerName
                    .setText("Machine 1");

            createHiddenCards(
                    leftMachineHand
            );

        }

        if(machines >= 2){

            rightPlayerName
                    .setText("Machine 2");

            createHiddenCards(
                    rightMachineHand
            );

        }

        if(machines >= 3){

            topPlayerName
                    .setText("Machine 3");

            createHiddenCards(
                    topMachineHand
            );

        }

    }

    private void createHiddenCards(HBox box){

        for(int i=0;i<4;i++){

            Button card =
                    new Button("?");

            card.getStyleClass()
                    .add("back-card");

            box.getChildren()
                    .add(card);

        }

    }

    // MOSTRAR MAZO

    private void showDeck(){

        drawPile
                .getChildren()
                .clear();

        Button deck =
                new Button("?");

        deck.getStyleClass()
                .add("back-card");

        drawPile
                .getChildren()
                .add(deck);

        deck.setOnAction(e->{

            String card =
                    gameModel.drawCard();

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION,
                            "You took card: " + card
                    );

            alert.show();

        });

    }

    private void updateUI(){

        lblTableSum
                .setText(
                        String.valueOf(
                                gameModel.getTableSum()
                        ));

        if(!gameModel.getTableCards()
                .isEmpty()){

            currentCard
                    .setText(
                            gameModel
                                    .getTableCards()
                                    .get(
                                            gameModel
                                                    .getTableCards()
                                                    .size()-1
                                    ));

        }

        scoreValue
                .setText("0");

    }

}