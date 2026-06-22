package com.example._0zo;

import com.game.ozo.view._0zoView;
import javafx.application.Application;

/**
 * Main entry point for the _0zo application.
 * This class complies with JavaFX launch standards.
 */
public class _0zo {

    public static void main(String[] args) {
        /*
         * We delegate the application launch to the View class.
         * In JavaFX, launching directly from a class that extends 'Application'
         * (like our _0zoView) avoids issues when the program is compiled into a JAR.
         */
        Application.launch(_0zoView.class, args);
        "hola"
    }
}