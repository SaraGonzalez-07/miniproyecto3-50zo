package com.example._0zo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;


public class _0zoView {

    private Scene scene;

    public _0zoView(){

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/com/example/_0zo/_0zo-view.fxml"
                                    )
                    );

            Parent root =
                    loader.load();

            scene =
                    new Scene(
                            root,
                            1100,
                            650
                    );

            scene.getStylesheets()
                    .add(
                            getClass()
                                    .getResource(
                                            "/com/example/_0zo/style.css"
                                    )
                                    .toExternalForm()
                    );

        }
        catch(IOException e){

            System.out.println(
                    "Error loading view"
            );

            e.printStackTrace();

        }

    }

    public Scene getScene(){

        return scene;

    }

}