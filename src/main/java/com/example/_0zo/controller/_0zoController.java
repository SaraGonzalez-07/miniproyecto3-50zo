package com.example._0zo.controller;


import com.example._0zo.model._0zoModel;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


import java.net.URL;
import java.util.ResourceBundle;



public class _0zoController implements Initializable {



    private _0zoModel gameModel;



    // TABLE


    @FXML
    private Label lblTableSum;


    @FXML
    private Label scoreValue;



    @FXML
    private StackPane drawPile;


    @FXML
    private StackPane discardPile;


    @FXML
    private Label currentCard;






    // PLAYER


    @FXML
    private Label mainPlayerName;


    @FXML
    private HBox playerHand;







    // OPPONENTS


    @FXML
    private Label leftPlayerName;


    @FXML
    private Label leftPlayerCards;


    @FXML
    private Label leftPlayerScore;




    @FXML
    private Label rightPlayerName;


    @FXML
    private Label rightPlayerCards;


    @FXML
    private Label rightPlayerScore;








    // BUTTONS


    @FXML
    private Button exitButton;



    @FXML
    private Button restartButton;








    @Override
    public void initialize(URL url, ResourceBundle rb){



        gameModel = new _0zoModel();


        setupButtons();


        updateUI();



    }








    private void updateUI(){



        lblTableSum.setText(
                String.valueOf(
                        gameModel.getTableSum()
                )
        );



        scoreValue.setText(
                String.valueOf(
                        gameModel.getPlayerScore()
                )
        );



        mainPlayerName.setText(
                "Player 1"
        );


    }








    private void setupButtons(){



        exitButton.setOnAction(e -> {


            System.exit(0);


        });





        restartButton.setOnAction(e -> {


            gameModel.resetGame();


            updateUI();


        });



    }







    public void playCard(int value){


        gameModel.addCardToTable(value);


        updateUI();


    }





}
