package com.example._0zo;

import com.example._0zo.view._0zoView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point for the _0zo application.
 * This class inherits from Application and coordinates the initial lifecycle.
 */
public class Main extends Application {

    /**
     * Starts the _0zo application by delegating window initialization to the View layer.
     * This lifecycle method instantiates the {@link _0zoStage} using the primary stage,
     * transferring the responsibility of loading the FXML resource, configuring layout
     * dimensions, and rendering the scene graph.
     *
     * @param primaryStage the primary {@link Stage} container window for this application,
     * constructed automatically by the JavaFX runtime engine
     * @throws Exception if an error occurs during the view initialization or FXML loading process
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Delegamos la construcción de la interfaz a nuestra clase Stage personalizada
        new _0zoView (primaryStage);
    }

    /**
     * Main method that serves as the standard fallback entry point for the JVM.
     * It launches the standalone JavaFX application framework execution environment.
     *
     * @param args the command-line arguments passed to the application upon execution
     */
    public static void main(String[] args) {
        launch(args);
    }
}