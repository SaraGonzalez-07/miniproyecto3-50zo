package com.example._0zo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Gestiona la configuración y renderizado del escenario principal de _0zo.
 */
public class _0zoView {

    public _0zoView(Stage primaryStage) {
        try {
            // Carga el archivo de la interfaz gráfica
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/_0zo/_0zo-view.fxml"));
            Parent root = loader.load();

            // Configuración de la escena
            Scene scene = new Scene(root, 800, 600);

            // Propiedades de la ventana principal
            primaryStage.setTitle("_0zo - La Academia de las Apuestas");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error al inicializar el escenario de _0zo.");
            e.printStackTrace();
        }
    }
}
