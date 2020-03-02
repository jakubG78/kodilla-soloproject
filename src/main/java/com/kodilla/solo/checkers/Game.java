package com.kodilla.solo.checkers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Game {

    private ChessBoard board;
    private GridPane gridPane;
    private GameType gameType;
    private FigureColor computerFiguresColor;
    private int oldX = -1;
    private int oldY = -1;
    private AI tempAI;

    public Game(ChessBoard board, GridPane gridPane, GameType gameType, FigureColor computerFiguresColor) {
        this.board = board;
        this.gridPane = gridPane;
        this.gameType = gameType;
        this.computerFiguresColor = computerFiguresColor;
        this.tempAI = new AI(board, computerFiguresColor);
    }

    public void displayBoard() {
        gridPane.getChildren().clear();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Figure figure = board.getFigure(x, y);
                if (figure.getColor() == FigureColor.BLACK && figure instanceof PawnFigure)
                    gridPane.add(new ImageView(new Image("file:src/main/resources/pieces/black_pawn.png")), x, y);
                else if (figure.getColor() == FigureColor.BLACK && figure instanceof QueenFigure)
                    gridPane.add(new ImageView(new Image("file:src/main/resources/pieces/black_queen.png")), x, y);
                else if (figure.getColor() == FigureColor.WHITE && figure instanceof PawnFigure)
                    gridPane.add(new ImageView(new Image("file:src/main/resources/pieces/white_pawn.png")), x, y);
                else if (figure.getColor() == FigureColor.WHITE && figure instanceof QueenFigure)
                    gridPane.add(new ImageView(new Image("file:src/main/resources/pieces/white_queen.png")), x, y);
            }
        }
    }

    public void click(int x, int y) {
        if (gameType.equals(GameType.HUMANvsHUMAN)) {
            if (oldX == -1) {
                oldX = x;
                oldY = y;
            } else {
                board.move(oldX, oldY, x, y);
                oldX = -1;
                oldY = -1;
                displayBoard();
                System.out.println("Current board value: " + board.calculateBoardValue());
            }
        }
        if (gameType.equals(GameType.HUMANvsCOMPUTER)) {
            if (oldX == -1) {
                oldX = x;
                oldY = y;
            } else {
                board.move(oldX, oldY, x, y);
                oldX = -1;
                oldY = -1;
                displayBoard();
                System.out.println("Current board value: " + board.calculateBoardValue());
                Move move = tempAI.selectBestMove();
                board.move(move.getX1(), move.getY1(), move.getX2(), move.getY2());
                displayBoard();
            }
        }
    }
}
