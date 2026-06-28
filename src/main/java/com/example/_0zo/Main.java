package com.example._0zo;


import com.example._0zo.view._0zoView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage){

        _0zoView view =
                new _0zoView();

        stage.setScene(
                view.getScene()
        );

        stage.setTitle(
                "50ZO - Cincuentazo"
        );

        stage.setResizable(false);

        stage.show();

    }

    public static void main(String[] args){

        launch();

    }

}