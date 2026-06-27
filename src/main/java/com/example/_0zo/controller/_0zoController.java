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
    private Label mainPlayerName;

    @FXML
    private Label leftPlayerName;

    @FXML
    private Label rightPlayerName;

    @FXML
    private Label topPlayerName;

    @FXML
    private Label leftPlayerCards;

    @FXML
    private Label rightPlayerCards;

    @FXML
    private Label topPlayerCards;


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

            gameModel.startGame(
                    machines
            );

            showHumanCards();

            showMachines(
                    machines
            );

            updateUI();

        });

        restartButton.setOnAction(e->{

            gameModel =
                    new _0zoModel();

            playerHand.getChildren()
                    .clear();

            lblTableSum.setText("0");

        });

        exitButton.setOnAction(e->{

            System.exit(0);

        });

    }

    private void showHumanCards(){

        playerHand
                .getChildren()
                .clear();

        int index = 0;

        for(String card:
                gameModel.getHumanHand()){

            Button cardButton =
                    new Button(card);

            cardButton
                    .getStyleClass()
                    .add("card");

            int cardIndex =
                    index;

            cardButton.setOnAction(e->{

                boolean played =
                        gameModel
                                .playHumanCard(cardIndex);

                if(played){

                    showHumanCards();

                    updateUI();

                }else{

                    Alert alert =
                            new Alert(
                                    Alert.AlertType.WARNING,
                                    "You cannot play this card. Sum would exceed 50"
                            );

                    alert.show();

                }

            });

            playerHand
                    .getChildren()
                    .add(cardButton);

            index++;

        }

    }

    private void showMachines(int amount) {

        if (amount >= 1) {

            leftPlayerName
                    .setText("Machine 1");

            leftPlayerCards
                    .setText("Cards: Hidden");

        }

        if (amount >= 2) {

            rightPlayerName
                    .setText("Machine 2");

            rightPlayerCards
                    .setText("Cards: Hidden");

        }

        if (amount >= 3) {

            topPlayerName
                    .setText("Machine 3");

            topPlayerCards
                    .setText("Cards: Hidden");

        }

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

    }

}