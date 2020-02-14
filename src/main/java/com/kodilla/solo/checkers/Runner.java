package com.kodilla.solo.checkers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Runner extends Application {
    private Image imageback = new Image("file:src/main/resources/chessboard.png");

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(0, 5, 5, 5));
        grid.setBackground(background);
        for (int i = 0; i < 8; i++) {
            ColumnConstraints column = new ColumnConstraints(60);
            grid.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 8; i++) {
            RowConstraints row = new RowConstraints(60);
            grid.getRowConstraints().add(row);
        }

        Scene scene = new Scene(grid, 480, 480, Color.BLACK);
        ChessBoard board = new ChessBoard();
        board.initBoard();
        Game game = new Game(board, grid);
        game.displayBoard();
        grid.setOnMouseClicked(event -> {
            System.out.println(event.getX()+","+event.getY());
            int x = (int)(event.getX()/60);
            int y = (int)(event.getY()/60);
            System.out.println(x+","+y);
            game.click(x,y);
        });
        primaryStage.setTitle("Chess board test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}