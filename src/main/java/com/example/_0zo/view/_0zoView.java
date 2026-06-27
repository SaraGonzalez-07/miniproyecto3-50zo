package com.example._0zo.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;

public class _0zoView {

    private Scene scene;

    private BorderPane root;



    public _0zoView(){

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/com/example/_0zo/_0zo-view.fxml"
                                    )
                    );


            root = loader.load();

            scene = new Scene(
                    root,
                    1000,
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

        } catch(IOException e){

            System.out.println(
                    "Error loading 50zo view"
            );

            e.printStackTrace();

        }

    }

    public Scene getScene(){

        return scene;

    }

    public void show(Stage stage) {

        stage.setTitle(
                "50zo - Card Game"
        );

        stage.setScene(scene);

        stage.setWidth(1100);

        stage.setHeight(650);

        stage.setMinWidth(950);

        stage.setMinHeight(550);

        stage.setResizable(true);

        stage.show();

    }

}
