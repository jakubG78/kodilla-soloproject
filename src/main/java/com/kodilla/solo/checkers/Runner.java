package com.kodilla.solo.checkers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Runner extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        ChessBoard thisBorad = new ChessBoard();
        thisBorad.initBoard();
        Game game = new Game(thisBorad);
        Scene scene = new Scene(game.displayBoard(), 480, 480, Color.BLACK);
        primaryStage.setTitle("Chess board test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
