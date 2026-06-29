package com.example._0zo;

import com.example._0zo.view._0zoView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point for the Cincuentazo (50ZO) desktop application.
 * Initializes the primary stages and handles core JavaFX lifecycle execution.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Prepares and renders the primary application layout stage.
     *
     * @param stage The primary window frame provided by the JavaFX runtime environment.
     */
    @Override
    public void start(Stage stage) {
        _0zoView view = new _0zoView();

        stage.setScene(view.getScene());
        stage.setTitle("50ZO - Cincuentazo");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Standard runtime bootstrap method used to invoke execution sequences.
     *
     * @param args Execution parameters passed via command line structures.
     */
    public static void main(String[] args) {
        launch();
    }
}