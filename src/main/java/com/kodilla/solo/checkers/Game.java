package com.kodilla.solo.checkers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Game {

    private ChessBoard board;
    private GridPane gridPane;
    private int oldX = -1;
    private int oldY = -1;
    private FigureColor wohoseMoveIsIt;

    public Game(ChessBoard board, GridPane gridPane) {
        this.board = board;
        this.gridPane = gridPane;
    }

    public void displayBoard() {
        gridPane.getChildren().clear();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Figure figure = board.getFigure(x,y);
                if (figure.getColor() == FigureColor.BLACK && figure instanceof PawnFigure)
                    gridPane.add(new ImageView(new Image("file:src/main/resources/pieces/black_pawn.png")),x,y);
                else if (figure.getColor() == FigureColor.BLACK && figure instanceof QueenFigure)
                    gridPane.add(new ImageView(new Image("file:src/main/resources/pieces/black_queen.png")),x,y);
                else if (figure.getColor() == FigureColor.WHITE && figure instanceof PawnFigure)
                    gridPane.add(new ImageView(new Image("file:src/main/resources/pieces/white_pawn.png")),x,y);
                else if (figure.getColor() == FigureColor.WHITE && figure instanceof QueenFigure)
                    gridPane.add(new ImageView(new Image("file:src/main/resources/pieces/white_queen.png")),x,y);
            }
        }
    }

    public void click(int x, int y) {
        if (oldX == -1) {
            oldX=x;
            oldY=y;
        } else {
            board.move(oldX,oldY, x, y);
            oldX = -1;
            oldY = -1;
            displayBoard();
        }
    }
}
