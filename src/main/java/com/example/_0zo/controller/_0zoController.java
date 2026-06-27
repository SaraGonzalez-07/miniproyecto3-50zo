package com.example._0zo.controller;


import com.example._0zo.model._0zoModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;

public class _0zoController implements Initializable {

    private _0zoModel gameModel;

    // TOP BUTTONS

    @FXML
    private ComboBox<Integer> machineSelector;


    @FXML
    private Button startButton;


    @FXML
    private Button restartButton;


    @FXML
    private Button exitButton;





    // TABLE


    @FXML
    private Label lblTableSum;


    @FXML
    private Label currentCard;


    @FXML
    private Label scoreValue;


    @FXML
    private StackPane drawPile;


    @FXML
    private StackPane discardPile;





    // HUMAN PLAYER


    @FXML
    private Label mainPlayerName;


    @FXML
    private HBox playerHand;





    // MACHINES


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




    @FXML
    private Label leftPlayerScore;


    @FXML
    private Label rightPlayerScore;


    @FXML
    private Label topPlayerScore;









    @Override
    public void initialize(URL url, ResourceBundle rb){


        gameModel = new _0zoModel();



        machineSelector.getItems()
                .addAll(1,2,3);



        setupButtons();


        clearPlayers();



        updateUI();


    }









    private void setupButtons(){



        startButton.setOnAction(e -> {



            Integer machines =
                    machineSelector.getValue();



            if(machines == null){

                return;

            }




            gameModel.startGame(
                    machines
            );




            mainPlayerName
                    .setText(
                            "Human Player"
                    );



            showHumanHand();


            showMachines();



            updateUI();



        });








        restartButton.setOnAction(e -> {


            gameModel.resetGame();



            playerHand.getChildren()
                    .clear();



            clearPlayers();


            updateUI();



        });







        exitButton.setOnAction(e -> {


            System.exit(0);


        });



    }









    private void showHumanHand(){



        playerHand.getChildren()
                .clear();




        for(Integer cardValue :
                gameModel.getHumanHand()){



            StackPane card =
                    new StackPane();




            Label value =
                    new Label(
                            String.valueOf(cardValue)
                    );




            card.getChildren()
                    .add(value);



            card.getStyleClass()
                    .add("card");




            playerHand.getChildren()
                    .add(card);



        }



    }









    private void showMachines(){



        int machines =
                gameModel.getMachinePlayers();




        if(machines >= 1){



            leftPlayerName
                    .setText("Machine 1");


            leftPlayerCards
                    .setText("Cards: Hidden");


            leftPlayerScore
                    .setText("Score: 0");



        }





        if(machines >= 2){



            rightPlayerName
                    .setText("Machine 2");


            rightPlayerCards
                    .setText("Cards: Hidden");


            rightPlayerScore
                    .setText("Score: 0");



        }






        if(machines >= 3){



            topPlayerName
                    .setText("Machine 3");


            topPlayerCards
                    .setText("Cards: Hidden");


            topPlayerScore
                    .setText("Score: 0");



        }



    }









    private void clearPlayers(){



        leftPlayerName.setText("");


        rightPlayerName.setText("");


        topPlayerName.setText("");



        leftPlayerCards.setText("");


        rightPlayerCards.setText("");


        topPlayerCards.setText("");



        leftPlayerScore.setText("");


        rightPlayerScore.setText("");


        topPlayerScore.setText("");



    }









    private void updateUI(){



        lblTableSum
                .setText(
                        String.valueOf(
                                gameModel.getTableSum()
                        ));




        currentCard
                .setText(
                        String.valueOf(
                                gameModel.getCurrentCard()
                        ));




        scoreValue
                .setText(
                        String.valueOf(
                                gameModel.getPlayerScore()
                        ));



    }





}