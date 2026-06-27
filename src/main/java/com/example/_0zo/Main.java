package com.example._0zo;


import com.example._0zo.view._0zoView;


import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {



    @Override
    public void start(Stage stage){



        try {



            _0zoView view =
                    new _0zoView();




            view.show(stage);





        } catch(Exception e){



            System.out.println(
                    "Error initializing 50zo game."
            );


            e.printStackTrace();



        }



    }








    public static void main(String[] args){



        launch(args);



    }





}