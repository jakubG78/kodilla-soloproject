package com.kodilla.solo.checkers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class Game {

    private ChessBoard board;
    private GridPane grid = new GridPane();
    private Image imageback = new Image("file:resources/chessboard.png");
    private Image piece = new Image("file:resources/pieces/black_pawn.png");

    public Game(ChessBoard board) {
        this.board = board;
    }

    public GridPane displayBoard() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

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
        //tymczasowe wartości

        grid.add(new ImageView(piece), 0, 0);
        grid.add(new ImageView(piece), 0, 1);
        grid.add(new ImageView(piece), 1, 2);
        grid.add(new ImageView(piece), 7, 7);
        return grid;
    }
}
