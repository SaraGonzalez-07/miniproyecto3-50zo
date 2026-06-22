package com.example._0zo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class _0zoView extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Nota: Asegúrate de actualizar el archivo FXML anterior
            // con el atributo fx:controller="com.game.ozo.controller._0zoController"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/_0zo/_0zo-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 800, 600);

            primaryStage.setTitle("_0zo - La Academia de las Apuestas");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error al cargar la interfaz del juego _0zo.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
