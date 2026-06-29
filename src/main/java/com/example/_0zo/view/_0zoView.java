package com.example._0zo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

/**
 * Concrete view layer class responsible for loading FXML visual templates,
 * attaching external stylesheet assets, and building the primary application scene.
 *
 * @author Margareth Gamboa - 2518629
 * @author Sara González - 2519548
 * @author Lissette Patiño - 2520977
 * @version 1.0
 */
public class _0zoView {

    private Scene scene;

    /**
     * Constructs the visual layout environment by parsing structural FXML parameters.
     */
    public _0zoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/_0zo/_0zo-view.fxml"));
            Parent root = loader.load();

            scene = new Scene(root, 1100, 750);
            scene.getStylesheets().add(getClass().getResource("/com/example/_0zo/style.css").toExternalForm());

        } catch (IOException e) {
            System.out.println("Error loading view");
            e.printStackTrace();
        }
    }

    /**
     * Provides access to the constructed interface frame container.
     *
     * @return The active Scene instance representing the game window payload.
     */
    public Scene getScene() {
        return scene;
    }
}